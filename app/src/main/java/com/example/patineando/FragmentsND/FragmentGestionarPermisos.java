package com.example.patineando.FragmentsND;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import android.app.Fragment;

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
        botonBusquedaApellido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearListaUsuariosApellido();
            }
        });
        listadoPermisos = vista.findViewById(R.id.rcvListadoPermisos);
        listadoPermisos.setHasFixedSize(true);
        listadoPermisos.setLayoutManager(new LinearLayoutManager(vista.getContext()));
        listadoPermisos.setAdapter(new AdaptadorGestionPermisos(ListadoGestionPermisos(),this));


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
    public void onItemClick(AuxAdaptadorGestionPermisos auxiliarModeloDatos){

        Fragment fragmento = FragmentUsuarioPermisos.newInstance(auxiliarModeloDatos);
        //Fragment fragmento = FragmentUsuarioPermisos.newInstance(auxiliarModeloDatos.getIdUsuario());
        FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
        transaction.hide(getActivity().getFragmentManager().findFragmentByTag("fragment_gestionar_permisos")); //TODO igual el tag está mal, ya que po lo que parece no es el id, entiendo que es el nobre del xml
        transaction.add(R.id.fragmento_gestionar_permisos_usuario_permisos,fragmento);
        transaction.addToBackStack(null); //Esto no entiendo que hace
        transaction.commit();
    //TODO creo que el error Attempt to invoke virtual method 'void android.app.Fragment.setNextAnim(int)' on a null object reference salta por algo de esta interfaz

    }//Fin onItemClick

    //Extracción de los datos en función del apellido:

    private void crearListaUsuariosApellido() {
        List<Tusuario> Listado = new ArrayList<>();

            DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Obtención de la palabra del criterio de busqueda (el apellido);
        String palabraBusqueda = criterioBusqueda.getText().toString().trim();

        //Busqueda de los usuarios:

        mDatabase.child("Usuarios").orderByChild("apellidosUsuario").equalTo(palabraBusqueda).addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey){
                System.out.println(dataSnapshot.getKey());
                for(DataSnapshot child: dataSnapshot.getChildren()){
                   // AuxAdaptadorGestionPermisos usuariosApellidos = new AuxAdaptadorGestionPermisos();
                    //usuariosApellidos = (AuxAdaptadorGestionPermisos) dataSnapshot.getValue();

                    Tusuario usuariosApellidos = (Tusuario) dataSnapshot.getValue();
                    Listado.add(usuariosApellidos);
                }

            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });//Fin consulta:

        listadoPermisos.setAdapter(new AdaptadorGestionPermisos(Listado,this));


    }//Fin método crearListaUsuariosApellido
}