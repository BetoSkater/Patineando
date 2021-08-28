package com.example.patineando;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.List;

public class AdaptadorListadoVideos extends RecyclerView.Adapter<AdaptadorListadoVideos.ViewHolder> {

    //Variables:
    private List<TVideos> listadoVideosPorCurso;
    private ItemClickListenerVerVideo clickListenerVerVideo;

    //Constructor:


    public AdaptadorListadoVideos(List<TVideos> listadoVideosPorCurso, ItemClickListenerVerVideo clickListenerVerVideo) {
        this.listadoVideosPorCurso = listadoVideosPorCurso;
        this.clickListenerVerVideo = clickListenerVerVideo;
    }


    //Sobreescritura del onCreate:
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_recursos_curso, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }



    //Sobreescritura del método onBind
    public void onBindViewHolder(ViewHolder holder, int position){
        String nombreVideo = listadoVideosPorCurso.get(position).getNombreVideo().toString();
        String nivelVideo = listadoVideosPorCurso.get(position).getNivelVideo().toString();
        Uri urlVideo = Uri.parse(listadoVideosPorCurso.get(position).getEnlaceVideo().toString()) ;

        holder.txtNombre.setText(nombreVideo);
        holder.txtNivel.setText(nivelVideo);
        holder.vistaVideo.setVideoURI(urlVideo);


        //TODO, entiendo que con esto los videos no re reproducen.
        holder.vistaVideo.pause();

        //TODO en teoria esto muestra la imagen del tiempo puesto mientras no se reproduce el video, es decir, la imagen detalle de a
       // https://stackoverflow.com/questions/36797972/working-with-videoview-inside-recyclerview
        //videoView.seekTo(requiredTime);
        if(position%2 ==0) {
            holder.tarjetaVideo.setBackgroundDrawable(ponerBordeMorado());
        }else{
            holder.tarjetaVideo.setBackgroundDrawable(ponerBordeAmarillo());
        }

    }


    //Sobreescritura del metodo que indica el tamaño del ArrayList
    @Override
    public int getItemCount(){
        return listadoVideosPorCurso.size();
    }



    //Creacion de la clase anidada ViewHolder:

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView tarjetaVideo;
        private TextView txtNombre, txtNivel;
       // private VideoView vistaVideo;
        private SimpleExoPlayer vistaVideo;

        public ViewHolder(View vista) {
            super(vista);
            //Asignaciond e las variables a los campos:

            tarjetaVideo = (CardView) vista.findViewById(R.id.crdTarjetaVideoLista);
            txtNombre = (TextView) vista.findViewById(R.id.txtNombreVideoLista);
            txtNivel = (TextView) vista.findViewById(R.id.txtDificultadLista);
            vistaVideo = (SimpleExoPlayer) vista.findViewById(R.id.exoVideoLista);
           }
    }//Fin clase anidada ViewHolder


    //Interfaz para el ItemClickListener
    public interface ItemClickListenerVerVideo{
        public void onItemClick(TVideos modeloDatos);
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



