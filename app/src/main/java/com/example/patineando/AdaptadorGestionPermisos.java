package com.example.patineando;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patineando.FragmentsND.FragmentGestionarPermisos;
import com.example.patineando.FragmentsND.FragmentOpciones;
import com.example.patineando.FragmentsND.FragmentUsuarioPermisos;

import java.util.List;

public class AdaptadorGestionPermisos extends RecyclerView.Adapter<AdaptadorGestionPermisos.ViewHolder> {
    //Variables:
    private List<AuxAdaptadorGestionPermisos> listadoUsuariosPermisos;
    private ItemClickListener clickListener;

    //Constructor:
    public AdaptadorGestionPermisos(List<AuxAdaptadorGestionPermisos> listadoUsuariosPermisos, ItemClickListener clickListener){
        this.listadoUsuariosPermisos = listadoUsuariosPermisos;
        this.clickListener = clickListener;
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
        int imagenUsuarioPermisos = listadoUsuariosPermisos.get(position).getImagenUsuario();
        String gradoPermisos = listadoUsuariosPermisos.get(position).getTipoUsuario();
        String nombreCompleto = listadoUsuariosPermisos.get(position).getApellidosUsuario() + ", " + listadoUsuariosPermisos.get(position).getNombreUsuario();

        String idUsuario = listadoUsuariosPermisos.get(position).getIdUsuario(); //Información a enviar al siguiente fragment.

        holder.imgUsuarioPermisos.setImageResource(imagenUsuarioPermisos);
        holder.txtTipoUsuarioPermisos.setText(gradoPermisos);
        holder.txtNombreApellidoPermisos.setText(nombreCompleto);

        holder.tarjetaRecycler.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
                /*
               FragmentUsuarioPermisos fragmentoND = new FragmentUsuarioPermisos();
               FragmentManager fragmentManager = getFragmentManager(); //TODO me quedo aqui
               //Se crea una nueva transacción:
               FragmentTransaction transaccion = fragmentManager.beginTransaction();

               transaccion.replace(R.id.contenedor_fragments_ND,fragmentoND);
               transaccion.commit();

                 */
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
        public void onItemClick(AuxAdaptadorGestionPermisos modeloDatos);
    }
}
