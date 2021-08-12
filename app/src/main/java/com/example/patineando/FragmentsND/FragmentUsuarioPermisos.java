package com.example.patineando.FragmentsND;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.patineando.R;
//TODO Fragment al que se accede desde el fragment FragmentGestionarPermisos
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUsuarioPermisos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUsuarioPermisos extends Fragment {

    private Spinner spinnerPermisos;
    private TextView txtNombreUsuarioPermisos, txtCorreoUsuarioPermisos, txtPermisosUsuarioPermisos;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentUsuarioPermisos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentUsuarioPermisos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUsuarioPermisos newInstance(String param1, String param2) {
        FragmentUsuarioPermisos fragment = new FragmentUsuarioPermisos();
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
        View vista = inflater.inflate(R.layout.fragment_usuario_permisos, container, false);
        //Asignación de las variables con los controladores:
        spinnerPermisos = (Spinner) vista.findViewById(R.id.sprDesplegablePermisos);
        txtNombreUsuarioPermisos = (TextView) vista.findViewById(R.id.txtNombreFraUsuarioPermisos);
        txtCorreoUsuarioPermisos = (TextView) vista.findViewById(R.id.txtCorreoFraUsuarioPermisos);
        txtPermisosUsuarioPermisos = (TextView) vista.findViewById(R.id.txtTipoUsuarioFraUsuarioPermisos);

        //Array con los valores de las opciones  que se van a mostrar en el Spinner:
        String [] opcionesPermisos = {"Alumnado","Profesorado","Administración"};

        //Creación del objeto arrayAdapter en el que se pasa como argumento el tipo de lista y el array con los valores:
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, opcionesPermisos);
                //Solucion al error del contexto: error: no suitable constructor found for ArrayAdapter(FragmentUsuarioPermisos,int,String[])
                //        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opcionesPermisos);
                        // * https://stackoverflow.com/questions/38633460/error-no-suitable-constructor-found-for-arrayadapter

        //Asociacion del ArrayAdapter al Spinner
        spinnerPermisos.setAdapter(adaptador);
        return vista;


    }
}