package com.example.patineando;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdaptadorListadoRutas extends RecyclerView.Adapter<AdaptadorListadoRutas.ViewHolder> {

    private List<TRutas> listadoRutas;
    private ItemClickListenerVerRuta  clickListenerVerRuta;
    private Context contexto;

    public AdaptadorListadoRutas(List<TRutas> listadoRutas, ItemClickListenerVerRuta clickListenerVerRuta, Context contexto) {
        this.listadoRutas = listadoRutas;
        this.clickListenerVerRuta = clickListenerVerRuta;
        this.contexto = contexto;
    }

    //Sobreescritura del onCreate:
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_rutas, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    //Sobreescritura onBind:
    public void onBindViewHolder(ViewHolder holder, int position){
        String uriImagen = listadoRutas.get(position).getUriMapa();
        Uri uriImagenResultado = Uri.parse(uriImagen);
        String nombre = listadoRutas.get(position).getNombreRuta();
        String localizacion = listadoRutas.get(position).getLocalizacionRuta();
        String dificultad = listadoRutas.get(position).getDificultadRuta();


        Glide.with(contexto).load(uriImagenResultado).into(holder.imgRuta);
        holder.txtNombre.setText(nombre);
        holder.txtLocalizacion.setText(localizacion);
        holder.txtDificultad.setText(dificultad);



        if(position%2 ==0) {
            holder.tarjetaRuta.setBackgroundDrawable(ponerBordeMorado());
        }else{
            holder.tarjetaRuta.setBackgroundDrawable(ponerBordeAmarillo());
        }

        holder.tarjetaRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListenerVerRuta.onItemClick(listadoRutas.get(position));
            }
        });
    }




    //Sobreescritura del metodo que indica el tamaño del ArrayList
    @Override
    public int getItemCount(){
        return listadoRutas.size();
    }

    //Creacion de la clase anidada ViewHolder:

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgRuta;
        private TextView txtNombre, txtLocalizacion, txtDificultad;
        private CardView tarjetaRuta;

        public ViewHolder(View vista) {
            super(vista);
            //Asignaciond e las variables a los campos:
            imgRuta = (ImageView) vista.findViewById(R.id.imvListaRutas);
            tarjetaRuta = (CardView) vista.findViewById(R.id.crdTarjetaRuta);

            txtNombre = (TextView) vista.findViewById(R.id.lblNombreListadoRuta);
            txtLocalizacion = (TextView) vista.findViewById(R.id.lblLocalizacionListadoRuta);
            txtDificultad = (TextView) vista.findViewById(R.id.lblDificultadListadoRuta);


        }
    }//Fin clase anidada ViewHolder


    //Interfaz para el ItemClickListener
    public interface ItemClickListenerVerRuta{
        public void onItemClick(TRutas modeloDatos);
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
