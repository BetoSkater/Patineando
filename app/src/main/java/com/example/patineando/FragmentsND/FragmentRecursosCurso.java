package com.example.patineando.FragmentsND;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.patineando.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRecursosCurso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRecursosCurso extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentRecursosCurso() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRecursosCurso.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRecursosCurso newInstance(String param1, String param2) {
        FragmentRecursosCurso fragment = new FragmentRecursosCurso();
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
        View vista =  inflater.inflate(R.layout.fragment_recursos_curso, container, false);

        String textoToast = getArguments().get("CursoSeleccionado").toString();

        Toast.makeText(vista.getContext(),textoToast,Toast.LENGTH_SHORT).show();


        //TODO funciona y se obtiene el idCurso de las tablas CursosOfertados/ RelacionCursoAlumno
        return vista;
    }
}