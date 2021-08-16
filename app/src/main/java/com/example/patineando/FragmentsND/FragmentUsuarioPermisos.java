package com.example.patineando.FragmentsND;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.patineando.AuxAdaptadorGestionPermisos;
import com.example.patineando.R;
import com.example.patineando.Tusuario;
//TODO Fragment al que se accede desde el fragment FragmentGestionarPermisos
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUsuarioPermisos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUsuarioPermisos extends Fragment {

    private Spinner spinnerPermisos;
    private TextView txtNombreUsuarioPermisos, txtCorreoUsuarioPermisos, txtPermisosUsuarioPermisos;
    private ImageView imgUsuarioPermisos;


    //

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final AuxAdaptadorGestionPermisos ARG_PARAM1 = new AuxAdaptadorGestionPermisos();
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private AuxAdaptadorGestionPermisos mParam1;
    //private String mParam2;

    public FragmentUsuarioPermisos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.onCreate

     * @return A new instance of fragment FragmentUsuarioPermisos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUsuarioPermisos newInstance(Tusuario param1) {
        FragmentUsuarioPermisos fragment = new FragmentUsuarioPermisos();
        Bundle args = new Bundle();
        args.putSerializable("DatosUsuario", param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = (AuxAdaptadorGestionPermisos) getArguments().getSerializable("DatosUsuario");
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_usuario_permisos, container, false);
        //Asignación de las variables con los controladores:
        spinnerPermisos = (Spinner) vista.findViewById(R.id.sprDesplegablePermisos);
        imgUsuarioPermisos = (ImageView) vista.findViewById(R.id.imgImagenFraUsuarioPermisos);
        txtNombreUsuarioPermisos = (TextView) vista.findViewById(R.id.txtNombreFraUsuarioPermisos);
        txtCorreoUsuarioPermisos = (TextView) vista.findViewById(R.id.txtCorreoFraUsuarioPermisos);
        txtPermisosUsuarioPermisos = (TextView) vista.findViewById(R.id.txtTipoUsuarioFraUsuarioPermisos);


        //Extracción de los valores recibidos:

         String idUsuarioObtenido = mParam1.getIdUsuario();
         String correoUsuarioObtenido = mParam1.getCorreoUsuario();
         int imagenUsuarioObtenido = mParam1.getImagenUsuario();
         String tipoUsuarioObtenido = mParam1.getTipoUsuario();
         String nombreUsuarioObtenido = mParam1.getNombreUsuario();
         String apellidosUsuarioObtenido = mParam1.getApellidosUsuario();

         imgUsuarioPermisos.setImageResource(imagenUsuarioObtenido);
         txtNombreUsuarioPermisos.setText(nombreUsuarioObtenido+" "+apellidosUsuarioObtenido);
         txtCorreoUsuarioPermisos.setText(correoUsuarioObtenido);
         txtPermisosUsuarioPermisos.setText(tipoUsuarioObtenido);




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