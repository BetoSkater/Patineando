package com.example.patineando.FragmentsND;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patineando.AdaptadorGestionPermisos;
import com.example.patineando.AuxAdaptadorGestionPermisos;
import com.example.patineando.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentGestionarPermisos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGestionarPermisos extends Fragment {

    //Declaracion de las variables a nivel de clase:
    private RecyclerView listadoPermisos;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentGestionarPermisos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentGestionarPermisos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGestionarPermisos newInstance(String param1, String param2) {
        FragmentGestionarPermisos fragment = new FragmentGestionarPermisos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_gestionar_permisos, container, false);
        listadoPermisos = vista.findViewById(R.id.rcvListadoPermisos);
        listadoPermisos.setHasFixedSize(true);
        listadoPermisos.setLayoutManager(new LinearLayoutManager(vista.getContext()));
        listadoPermisos.setAdapter(new AdaptadorGestionPermisos(ListadoGestionPermisos()));

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
}