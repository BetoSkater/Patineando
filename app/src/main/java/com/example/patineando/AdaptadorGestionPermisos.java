package com.example.patineando;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.patineando.FragmentsND.FragmentGestionarPermisos;
import com.example.patineando.FragmentsND.FragmentOpciones;
import com.example.patineando.FragmentsND.FragmentUsuarioPermisos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorGestionPermisos extends RecyclerView.Adapter<AdaptadorGestionPermisos.ViewHolder> {
    //Variables:
    private List<Tusuario> listadoUsuariosPermisos;
    private ItemClickListener clickListener;
    private Context context;
    //Constructor:


    public AdaptadorGestionPermisos(List<Tusuario> listadoUsuariosPermisos, ItemClickListener clickListener, Context context) {
        this.listadoUsuariosPermisos = listadoUsuariosPermisos;
        this.clickListener = clickListener;
        this.context = context;
    }

    //2º) Sobrescritura onCreate():
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_fragment_permisos_rv,parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    //3º) Sobreescritura onBind:
    public void onBindViewHolder(ViewHolder holder, int position){
        String urlUsuarioPermisos = listadoUsuariosPermisos.get(position).getImagenUsuario();
        Uri uriImagen = Uri.parse(urlUsuarioPermisos);
        String gradoPermisos = listadoUsuariosPermisos.get(position).getTipoUsuario();
        String nombreCompleto = listadoUsuariosPermisos.get(position).getApellidosUsuario() + ", " + listadoUsuariosPermisos.get(position).getNombreUsuario();

        String idUsuario = listadoUsuariosPermisos.get(position).getIdUsuario(); //Información a enviar al siguiente fragment.

        Glide.with(context).load(urlUsuarioPermisos).into(holder.imgUsuarioPermisos);
        //holder.imgUsuarioPermisos.setImageResource(imagenUsuarioPermisos);
        holder.txtTipoUsuarioPermisos.setText(gradoPermisos);
        holder.txtNombreApellidoPermisos.setText(nombreCompleto);
        if(position % 2 ==0){
            holder.tarjetaRecycler.setBackgroundDrawable(ponerBordeMorado());
        }else{
            holder.tarjetaRecycler.setBackgroundDrawable(ponerBordeAmarillo());
        }

        holder.tarjetaRecycler.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){

               clickListener.onItemClick(listadoUsuariosPermisos.get(position));
           }
        });
    }

    //4º)Sobreescritura del método que indica el tamaño del ArrayList
    @Override
    public int getItemCount(){
        return listadoUsuariosPermisos.size();
    }

    //1º) Creacion de la clase anidada ViewHolder:

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgUsuarioPermisos;
        private TextView txtTipoUsuarioPermisos, txtNombreApellidoPermisos;
        private CardView tarjetaRecycler;
        //private Context contextito;
        public ViewHolder(View vista){
            super(vista);
            //Asignacion de las variables con los campos:
            imgUsuarioPermisos = (ImageView) vista.findViewById(R.id.imgListadoPermisos);
            txtTipoUsuarioPermisos = (TextView) vista.findViewById(R.id.lblListadoGPPermiso);
            txtNombreApellidoPermisos = (TextView) vista.findViewById(R.id.lblListadoGPNombreApe);
            tarjetaRecycler = (CardView) vista.findViewById(R.id.crdTarjetaRVFraGestionarPermisos);
            //contextito = tarjetaRecycler.getContext();
        }
    }//Fin ViewHolder

    //Se crea la interfaz para el ItemClickListener
    public interface ItemClickListener{
        public void onItemClick(Tusuario modeloDatos);
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
