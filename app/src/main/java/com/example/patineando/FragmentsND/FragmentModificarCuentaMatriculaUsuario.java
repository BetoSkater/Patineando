package com.example.patineando.FragmentsND;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.patineando.R;
import com.example.patineando.Tusuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentModificarCuentaMatriculaUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentModificarCuentaMatriculaUsuario extends Fragment {

    EditText txtCorreoActual;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final Tusuario ARG_PARAM1 = new Tusuario();
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Tusuario mParam1;
    private String mParam2;

    public FragmentModificarCuentaMatriculaUsuario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param3 Parameter 1.

     * @return A new instance of fragment FragmentModificarCuentaMatriculaUsuario.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentModificarCuentaMatriculaUsuario newInstance(Tusuario param3) {

        FragmentModificarCuentaMatriculaUsuario fragment = new FragmentModificarCuentaMatriculaUsuario();
        Bundle args = new Bundle();
        args.putSerializable("DatosUsuario2", param3);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Tusuario) getArguments().getSerializable("DatosUsuario2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_modificar_cuenta_matricula_usuario, container, false);

     //   txtCorreoActual = (EditText) vista.findViewById(R.id.txtCorreoAntiguoModificarCorreoMatricula);

      //  String correoACambiar = "prueba";
//                correoACambiar =mParam1.getCorreoUsuario().toString();
     //   txtCorreoActual.setText(correoACambiar);

        return vista;
    }
}