package com.example.patineando;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorGestionPermisos extends RecyclerView.Adapter<AdaptadorGestionPermisos.ViewHolder> {
    //Variables:
    private List<AuxAdaptadorGestionPermisos> listadoUsuariosPermisos;

    //Constructor:
    public AdaptadorGestionPermisos(List<AuxAdaptadorGestionPermisos> listadoUsuariosPermisos){
        this.listadoUsuariosPermisos = listadoUsuariosPermisos;
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

        holder.imgUsuarioPermisos.setImageResource(imagenUsuarioPermisos);
        holder.txtTipoUsuarioPermisos.setText(gradoPermisos);
        holder.txtNombreApellidoPermisos.setText(nombreCompleto);
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

        public ViewHolder(View vista){
            super(vista);
            //Asignacion de las variables con los campos:
            imgUsuarioPermisos = (ImageView) vista.findViewById(R.id.imgListadoPermisos);
            txtTipoUsuarioPermisos = (TextView) vista.findViewById(R.id.lblListadoGPPermiso);
            txtNombreApellidoPermisos = (TextView) vista.findViewById(R.id.lblListadoGPNombreApe);
        }
    }
}
