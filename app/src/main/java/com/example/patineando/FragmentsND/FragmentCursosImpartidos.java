package com.example.patineando.FragmentsND;

import android.app.FragmentTransaction;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.patineando.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCursosImpartidos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCursosImpartidos extends Fragment {


    ImageButton botonAgresivo, botonArtistico, botonBaile, botonTecnica, botonOtras, botonHockey, botonSlalom, botonVelocidad;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentCursosImpartidos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCursosImpartidos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCursosImpartidos newInstance(String param1, String param2) {
        FragmentCursosImpartidos fragment = new FragmentCursosImpartidos();
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
        View vista =  inflater.inflate(R.layout.fragment_cursos_impartidos, container, false);

        botonAgresivo = (ImageButton) vista.findViewById(R.id.imbAgresivo);
        botonArtistico = (ImageButton) vista.findViewById(R.id.imbArtistico);
        botonBaile = (ImageButton) vista.findViewById(R.id.imbBaile);
        botonTecnica = (ImageButton) vista.findViewById(R.id.imbTecnica);
        botonOtras = (ImageButton) vista.findViewById(R.id.imbOtras);
        botonHockey = (ImageButton) vista.findViewById(R.id.imbHockey);
        botonSlalom = (ImageButton) vista.findViewById(R.id.imbSlalom);
        botonVelocidad = (ImageButton) vista.findViewById(R.id.imbVelocidad);

        botonAgresivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmento = new FragmentOpciones();
                FragmentTransaction transaccion = getFragmentManager().beginTransaction();
                transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
                transaccion.addToBackStack(null);
                transaccion.commit();
            }
        });

        botonArtistico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        botonBaile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        botonTecnica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        botonOtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        botonHockey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        botonSlalom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        botonVelocidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return vista;
    }
}