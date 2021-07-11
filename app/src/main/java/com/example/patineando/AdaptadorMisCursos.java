package com.example.patineando;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorMisCursos extends RecyclerView.Adapter<AdaptadorMisCursos.ViewHolder> {
    //Variables:
    private List<CursoMisCursos> listadoMisCursos;

    //Constructor:


    public AdaptadorMisCursos(List<CursoMisCursos> listadoMisCursos) {
        this.listadoMisCursos = listadoMisCursos;
    }


    //2º) sobreescritura del onCreate
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_mis_cursos_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    //3º) Sobreescritura del onBind

    public void onBindViewHolder(ViewHolder holder, int position){
        int imagenCurso = listadoMisCursos.get(position).getImagenMiCurso();
        String nombreCurso = listadoMisCursos.get(position).getNombreMiCurso();
        String nivelCurso = listadoMisCursos.get(position).getNivelMiCurso();
        //LocalDate diaCurso = listadoMisCursos.get(position).getDiaMiCurso();
        String  diaCurso = listadoMisCursos.get(position).getDiaMiCurso().toString();
        // LocalTime horaCurso = listadoMisCursos.get(position).getHoraMiCurso();
        String  horaCurso = listadoMisCursos.get(position).getHoraMiCurso().toString();
        String localizacionCurso = listadoMisCursos.get(position).getLocalizacionMiCurso();
        String profesorCurso = listadoMisCursos.get(position).getProfesorMiCurso();
        String apodoProfesorCurso = listadoMisCursos.get(position).getApodoProfesorMiCurso();
        String nombreApodoCurso;
        //TODO AQUI TENGO QUE PONER LA FUSION DEL NOMBRE CON EL APODO EN CASO DE QUE EXISTA

        if(apodoProfesorCurso !=null){
            nombreApodoCurso = profesorCurso + " ("+ apodoProfesorCurso+")";
        }else{
            nombreApodoCurso = profesorCurso;
        }
        //TODO Transformacion de la fecha y hora a formato texto mediente el metodo parse de LocalDate y LocalTime junto con el estilo de formato:
        //Todo esto es por si quiero hacer suo de los valores numericos, en este caso no se necesitan hacer operaciones solo se neceitan sus valores de string, por lo que igual es mejor cambiar directamente el tipo de dato en la clase CursoMisCursos
        //Si pongo el minSDK (build gradle module) en 26, solo podrían usar la app entorno al 60,25% de los dispositivos android, no sé si sería muy rentable
        //Así que de momento lo pongo como strings
        //Dia:
        //https://www.journaldev.com/17899/java-simpledateformat-java-date-format
        // DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("EEEE"); //Necesita como mínimo api26

        //Hora:


        holder.imgTipoCurso.setImageResource(imagenCurso);
        holder.txtCursoMisCursos.setText(nombreCurso);
        holder.txtNivelMisCursos.setText(nivelCurso);
        holder.txtDiaMisCursos.setText(diaCurso); //TODO METER FORMATTER
        holder.txtHoraMisCursos.setText(horaCurso); //TODO METER FORMATTER
        holder.txtlocalizacionMisCursos.setText((localizacionCurso));
        holder.txtProfesorMisCursos.setText(nombreApodoCurso); //TODO comprobar que funciona tanto con apodo como sin apodo

        //TODO aqui tengo que poner lo del recuadro de las tarjetas cmo lo hice en el ejemplo de futbol.


    }

    //4º) Sobreescritura del método que  indica el tamaño del ArrayList
    @Override
    public int getItemCount(){
        return listadoMisCursos.size();
    }



    //1º Creacion de la clase anidada ViewHolder:

    public static  class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgTipoCurso;
        private TextView txtCursoMisCursos, txtNivelMisCursos, txtDiaMisCursos, txtHoraMisCursos,
                txtlocalizacionMisCursos, txtProfesorMisCursos; //TODO nota: el apodo se pone entre parentesis en la misma caja de texto que el nombre del profesor)

        public ViewHolder(View vista){
            super(vista);
            //Asignacion de las variables a los campos de texto del layout auxiliar que tienen el diseño del layout que mostrar en el recyclerView
            imgTipoCurso = (ImageView) vista.findViewById(R.id.imgImagenMisCursos);
            txtCursoMisCursos = (TextView) vista.findViewById(R.id.txtTipoEscuelaMisCursos);
            txtNivelMisCursos = (TextView) vista.findViewById(R.id.txtNivelMisCursos);
            txtDiaMisCursos = (TextView) vista.findViewById(R.id.txtDiasMisCursos);
            txtHoraMisCursos = (TextView) vista.findViewById(R.id.txtHoraMisCursos);
            txtlocalizacionMisCursos = (TextView) vista.findViewById(R.id.txtLocalizacionMisCursos);
            txtProfesorMisCursos = (TextView) vista.findViewById(R.id.txtProfesorMisCursos);


        }

    }
}
