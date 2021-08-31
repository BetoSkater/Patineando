package com.example.patineando.FragmentsND;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patineando.AdaptadorGestionPermisos;
import com.example.patineando.AuxAdaptadorGestionPermisos;
import com.example.patineando.R;
import com.example.patineando.Tusuario;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentGestionarPermisos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGestionarPermisos extends Fragment implements AdaptadorGestionPermisos.ItemClickListener {

    //Declaracion de las variables a nivel de clase:
    private RecyclerView listadoPermisos;
    private EditText criterioBusqueda;
    private Button botonBusquedaApellido;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
   // private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    //private String mParam2;

    public FragmentGestionarPermisos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * He quitado de aqui la parte generica de los params
     * @return A new instance of fragment FragmentGestionarPermisos.
     */
    // TODO: Rename and change types and number of parameters
   // public static FragmentGestionarPermisos newInstance(String param1, String param2) {
        public static FragmentGestionarPermisos newInstance() {
        FragmentGestionarPermisos fragment = new FragmentGestionarPermisos();
       // Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_gestionar_permisos, container, false);
        criterioBusqueda = vista.findViewById(R.id.txtFragmentoGestionarPermisosBusqueda);
        botonBusquedaApellido = vista.findViewById(R.id.btnFragmentoPermisosBuscar);



        listadoPermisos = vista.findViewById(R.id.rcvListadoPermisos);
        listadoPermisos.setHasFixedSize(true);
        listadoPermisos.setLayoutManager(new LinearLayoutManager(vista.getContext()));
        botonBusquedaApellido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearListaUsuariosApellido();
               // listadoPermisos.setAdapter(new AdaptadorGestionPermisos(crearListaUsuariosApellido(),this));
            }
        });

        //listadoPermisos.setAdapter(new AdaptadorGestionPermisos(crearListaUsuariosApellido(),this));


        return vista;
    }

    //Listado para comprobar que el Adaptador funciona bien:

    public List<AuxAdaptadorGestionPermisos> ListadoGestionPermisos(){
        List<AuxAdaptadorGestionPermisos> Listado = new ArrayList<>();

        Listado.add(new AuxAdaptadorGestionPermisos("adsda", "correo", R.drawable.ic_menu_gallery, "Alumno", "Benito", "Floro"));
        Listado.add(new AuxAdaptadorGestionPermisos("asda2", "correo2", R.drawable.ic_menu_gallery, "Alumno", "Claudia", "Martinez Lopez"));
        Listado.add(new AuxAdaptadorGestionPermisos("adsda3", "correo3", R.drawable.ic_menu_gallery, "Alumno", "Pepe", "Moreno"));
        Listado.add(new AuxAdaptadorGestionPermisos("adsda4", "correo4", R.drawable.ic_menu_gallery, "Alumno", "Raquel", "Perez"));
        Listado.add(new AuxAdaptadorGestionPermisos("adsda5", "correo5", R.drawable.ic_menu_gallery, "Alumno", "Ben", "Flo"));



        return Listado;
    }

    //Sobreescritura del interfaz onItemClick para poder mostrar la vista detalle un elemento del fragment de los permisos:
    @Override
    public void onItemClick(Tusuario auxiliarModeloDatos){

        Fragment fragmento = FragmentUsuarioPermisos.newInstance(auxiliarModeloDatos);
        //Fragment fragmento = FragmentUsuarioPermisos.newInstance(auxiliarModeloDatos.getIdUsuario());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //transaction.hide(getActivity().getFragmentManager().findFragmentByTag("fragment_gestionar_permisos")); //TODO igual el tag está mal, ya que po lo que parece no es el id, entiendo que es el nobre del xml
       // transaction.add(R.id.fragmento_gestionar_permisos_usuario_permisos,fragmento);
        transaction.replace(R.id.contenedor_fragments_ND,fragmento);
        transaction.addToBackStack(null); //Esto no entiendo que hace
        transaction.commit();
    //TODO creo que el error Attempt to invoke virtual method 'void android.app.Fragment.setNextAnim(int)' on a null object reference salta por algo de esta interfaz

    }//Fin onItemClick

    //Extracción de los datos en función del apellido:

    private List<Tusuario> crearListaUsuariosApellido() {
        List<Tusuario> Listado = new ArrayList<>();

            DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Obtención de la palabra del criterio de busqueda (el apellido);
        String palabraBusqueda = criterioBusqueda.getText().toString().trim();

        //Busqueda de los usuarios: https://stackoverflow.com/questions/37642352/how-to-perform-search-in-a-firebasedatabase

        mDatabase.child("Usuarios").orderByChild("apellidosUsuario").equalTo(palabraBusqueda).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for( DataSnapshot data: snapshot.getChildren()){
                    Tusuario usuarioBusqueda = new Tusuario();

                    usuarioBusqueda.setApellidosUsuario(data.getValue(Tusuario.class).getApellidosUsuario().toString());
                    usuarioBusqueda.setApodoUsuario(data.getValue(Tusuario.class).getApodoUsuario().toString());
                    usuarioBusqueda.setCorreoUsuario(data.getValue(Tusuario.class).getCorreoUsuario().toString());
                    usuarioBusqueda.setDescripcionUsuario(data.getValue(Tusuario.class).getDescripcionUsuario().toString());
                    usuarioBusqueda.setFechaCreacionUsuario(data.getValue(Tusuario.class).getFechaCreacionUsuario());
                    usuarioBusqueda.setIdUsuario(data.getValue(Tusuario.class).getIdUsuario().toString());
                    usuarioBusqueda.setImagenUsuario(data.getValue(Tusuario.class).getImagenUsuario().toString());
                    usuarioBusqueda.setMatriculaActivaUsuario(data.getValue(Tusuario.class).getMatriculaActivaUsuario());
                    usuarioBusqueda.setNombreUsuario(data.getValue(Tusuario.class).getNombreUsuario().toString());
                    usuarioBusqueda.setPatinesUsuario(data.getValue(Tusuario.class).getPatinesUsuario().toString());
                    usuarioBusqueda.setTipoUsuario(data.getValue(Tusuario.class).getTipoUsuario().toString());

                    Listado.add(usuarioBusqueda);
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        }); //Fin busqueda de usuarios

        listadoPermisos.setAdapter(new AdaptadorGestionPermisos(Listado,this,getContext()));

        return Listado;
    }//Fin método crearListaUsuariosApellido



    //Método para refrescar el ReciclerView
    public void refrescarRecyclerView(){
            //https://stackoverflow.com/questions/31367599/how-to-update-recyclerview-adapter-data
        

    }
}