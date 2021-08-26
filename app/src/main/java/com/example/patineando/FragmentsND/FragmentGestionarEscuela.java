package com.example.patineando.FragmentsND;

import android.app.FragmentTransaction;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.patineando.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentGestionarEscuela#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGestionarEscuela extends Fragment {

    Button btnAnadir, btnEditar, btnEliminar;
    Button btnAsignarRecurso, btnSubirRecurso, btnEliminarRecurso;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentGestionarEscuela() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentGestionarEscuela.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGestionarEscuela newInstance(String param1, String param2) {
        FragmentGestionarEscuela fragment = new FragmentGestionarEscuela();
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
        View vista =inflater.inflate(R.layout.fragment_gestionar_escuela, container, false);
        btnAnadir = (Button) vista.findViewById(R.id.btnAnadirGE);
        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirCurso();
            }
        });

        btnEditar = (Button) vista.findViewById(R.id.btnModificarGE);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarCurso();
            }
        });

        btnEliminar = (Button) vista.findViewById(R.id.btnEliminarGE);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarCurso();
            }
        });

        btnAsignarRecurso = (Button) vista.findViewById(R.id.btnAsignarRecursosGE);
        btnAsignarRecurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irFragmentAsignarRecursos();
            }
        });

        btnSubirRecurso = (Button) vista.findViewById(R.id.btnSubirRecursosGE);
        btnSubirRecurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irFragmentSubirRecursos();
            }
        });

        btnEliminarRecurso = (Button) vista.findViewById(R.id.btnEliminarRecursosGE);
        btnEliminarRecurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            irFragmentEliminarRecursos();
            }
        });

        return vista;
    }//Fin on CreateView


    //Metodo para ir a añadir un curso:
    public void anadirCurso(){
        Fragment fragmento = new FragmentAnadirEditarCurso();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }//Fin metodo anadirCurso()

    //Metodo para ir a modificar un curso:
    public void editarCurso(){
        Fragment fragmento = new FragmentAnadirEditarCurso();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }//Fin metodo editarCurso()

    //Metodo para ir a eliminar un curso:
    public void eliminarCurso(){
        Fragment fragmento = new FragmentEliminarCurso();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }

    //Metodo para ir al fragment que permite asignar recursos:
    public void irFragmentAsignarRecursos(){
        Fragment fragmento = new FragmentAsignarRescursos();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }//Fin metodo irFragmentAsignarRecursos

    //Metodo para ir al fragment que permite subir recursos:
    public void irFragmentSubirRecursos(){
        Fragment fragmento = new FragmentSubirRecursos();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }//Fin metodo irFragmentSubirRecursos

    //Metodo que permite ir al fragment en el que se pueden eliminar recursos subidos:
    public void irFragmentEliminarRecursos(){
        Fragment fragmento = new FragmentEliminarRecursos();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }//Fin metodo irFragmentEliminarRecursos


}