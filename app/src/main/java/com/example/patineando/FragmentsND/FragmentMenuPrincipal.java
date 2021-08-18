package com.example.patineando.FragmentsND;

import android.app.FragmentTransaction;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.patineando.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMenuPrincipal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMenuPrincipal extends Fragment {


    ImageButton btnOpciones,btnClima, btnRutas, btnCursos;
    Button btnMisCursos, btnMiPerfil;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMenuPrincipal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMenuPrincipal.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMenuPrincipal newInstance(String param1, String param2) {
        FragmentMenuPrincipal fragment = new FragmentMenuPrincipal();
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
        View vista = inflater.inflate(R.layout.fragment_menu_principal, container, false);

        btnOpciones = (ImageButton) vista.findViewById(R.id.imbOpcionesMPND);
        btnOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verOpciones();
            }
        });



        btnClima = (ImageButton) vista.findViewById(R.id.imbTiempoMPND);
        btnClima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verTiempo();
            }
        });

        btnRutas = (ImageButton) vista.findViewById(R.id.imbRutasMPND);
        btnRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verRutas();
            }
        });

        btnCursos = (ImageButton) vista.findViewById(R.id.imbCursosMPND);
        btnCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verCursos();
            }
        });

        btnMisCursos = (Button) vista.findViewById(R.id.btnMisCursosMPND);
        btnMisCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verMisCursos();
            }
        });

        btnMiPerfil = (Button) vista.findViewById(R.id.btnMiPerfilMPND);
        btnMiPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verMiPerfil();
            }
        });

        return vista;
    }//Fin View onCreateView


    //Funcionalidad de los botones:

    public void verOpciones(){
        Fragment fragmento = new FragmentOpciones();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }

    public void verTiempo(){
        Fragment fragmento = new FragmentInformacionClimatica();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }

    public void verRutas(){
        Fragment fragmento = new FragmentVerRutas();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }

    public void verCursos(){
        Fragment fragmento = new FragmentCursosImpartidos();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }

    public void verMisCursos(){
        Fragment fragmento = new FragmentMisCursos();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }

    public void verMiPerfil(){
        Fragment fragmento = new FragmentMiPerfil();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }
}