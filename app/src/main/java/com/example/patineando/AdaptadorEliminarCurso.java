package com.example.patineando;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class AdaptadorEliminarCurso extends RecyclerView.Adapter<AdaptadorEliminarCurso.ViewHolder> {

    //Variables:
    private List<TCursoPublicado> listadoCursosEliminar;
    private ItemClickListenerBorrar clickListenerBorrar;

    //Construcotr:
    public AdaptadorEliminarCurso(List<TCursoPublicado> listadoCursosEliminar, ItemClickListenerBorrar clickListenerBorrar){
        this.listadoCursosEliminar = listadoCursosEliminar;
        this.clickListenerBorrar = clickListenerBorrar;
    }

    //Sobreescritura del onCreate:
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_mis_cursos_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    //Sobreescritura onBind:
    public void onBindViewHolder(ViewHolder holder, int position){
        int imagenUsuarioPermisos = listadoCursosEliminar.get(position).getImagenCurso();
        String tecnica = listadoCursosEliminar.get(position).getTipoCurso();
        String nivel = listadoCursosEliminar.get(position).getNivelDificultad();
        String dia = listadoCursosEliminar.get(position).getDiaClase();
        String hora = listadoCursosEliminar.get(position).getHoraClase();
        String localizacion = listadoCursosEliminar.get(position).getLocalizacion();
        String profe = listadoCursosEliminar.get(position).getProfesor();
        String idCurso = listadoCursosEliminar.get(position).getIdCurso();

        holder.imgCursoBorrar.setImageResource(imagenUsuarioPermisos);
        holder.txtTecnica.setText(tecnica);
        holder.txtNivel.setText(nivel);
        holder.txtDia.setText(dia);
        holder.txtHora.setText(hora);
        holder.txtLocalizacion.setText(localizacion);
        holder.txtProfe.setText(profe);

        holder.tarjetaCurso.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                clickListenerBorrar.onItemClick(listadoCursosEliminar.get(position));
            }
        });
        if(position%2 ==0) {
            holder.tarjetaCurso.setBackgroundDrawable(ponerBordeMorado());
        }else{
            holder.tarjetaCurso.setBackgroundDrawable(ponerBordeAmarillo());
        }
    }



    //Sobreescritura del metodo que indica el tamaño del ArrayList
    @Override
    public int getItemCount(){
        return listadoCursosEliminar.size();
    }


    //Creacion de la clase anidada ViewHolder:

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgCursoBorrar;
        private TextView txtTecnica, txtNivel, txtDia, txtHora, txtLocalizacion, txtProfe;
        private CardView tarjetaCurso;

        public ViewHolder(View vista){
            super(vista);
            //Asignaciond e las variables a los campos:
            imgCursoBorrar = (ImageView) vista.findViewById(R.id.imgImagenMisCursos);
            tarjetaCurso = (CardView) vista.findViewById(R.id.crdCursos);

            txtTecnica = (TextView)vista.findViewById(R.id.txtTipoEscuelaMisCursos);
            txtNivel = (TextView)vista.findViewById(R.id.txtNivelMisCursos);
            txtDia = (TextView)vista.findViewById(R.id.txtDiasMisCursos);
            txtHora = (TextView)vista.findViewById(R.id.txtHoraMisCursos);
            txtLocalizacion = (TextView)vista.findViewById(R.id.txtLocalizacionMisCursos);
            txtProfe = (TextView)vista.findViewById(R.id.txtProfesorMisCursos);


        }

    }



    //Interfaz para el ItemClickListener
    public interface ItemClickListenerBorrar{
        public void onItemClick(TCursoPublicado modeloDatos);
    }

    //Metodo para generar el marco de las tarjetas en tiempo de ejecucion en funcion de si es posivion par o impar:

     public GradientDrawable ponerBordeAmarillo(){
         GradientDrawable borde = new GradientDrawable();
         borde.setColor(Color.argb(0,255,255,255));
         borde.setStroke(15,Color.argb(255,254,220,151));
         return borde;
     }

     public GradientDrawable ponerBordeMorado(){
         GradientDrawable borde = new GradientDrawable();
         borde.setColor(Color.argb(0,255,255,255));
         borde.setStroke(15,Color.argb(255,41,19,42));
         return borde;
     }

}
