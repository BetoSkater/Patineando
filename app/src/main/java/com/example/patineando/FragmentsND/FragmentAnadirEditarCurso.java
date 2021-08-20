package com.example.patineando.FragmentsND;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.patineando.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAnadirEditarCurso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAnadirEditarCurso extends Fragment {

    private TextView txtMostrarHora;
    private Button btnSeleccionarHora, btnConfirmar;

    private int horaSeleccionador, minutoSeleccionador;

    static final int TIME_DIALOG_ID = 0;

    private Spinner spinnerCursos, spinnerLocalizacion, spinnerProfesores;
    private RadioGroup grupoDificultad, grupoHora, grupoMinutos;
    private RadioButton radioBasico, radioMedio, radioAvanzado, radioExperto;


    private Button confirmar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentAnadirEditarCurso() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAnadirEditarCurso.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAnadirEditarCurso newInstance(String param1, String param2) {
        FragmentAnadirEditarCurso fragment = new FragmentAnadirEditarCurso();
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
        View vista =  inflater.inflate(R.layout.fragment_anadir_editar_curso, container, false);

        spinnerCursos = (Spinner) vista.findViewById(R.id.sprSeleccionarTipoCurso);
        spinnerLocalizacion = (Spinner) vista.findViewById(R.id.sprLocalizacionClases);
        spinnerProfesores = (Spinner) vista.findViewById(R.id.sprSeleccionarProfesor);

        grupoDificultad = (RadioGroup) vista.findViewById(R.id.rdgRadioDificultad);
        radioBasico = (RadioButton) vista.findViewById(R.id.rdbBasico);
        radioMedio = (RadioButton) vista.findViewById(R.id.rdbMedio);
        radioAvanzado = (RadioButton) vista.findViewById(R.id.rdbAvanzado);
        radioExperto = (RadioButton) vista.findViewById(R.id.rdbExperto);


        grupoHora = (RadioGroup) vista.findViewById(R.id.rdgGrupoHoras);
        grupoMinutos = (RadioGroup) vista.findViewById(R.id.rdgGrupoMinutos);


        btnConfirmar = (Button) vista.findViewById(R.id.btnConfirmar);

        //TODO si da tiempo, el Spinner de lso cursos debería tener una imagen, ver https://androidexample.com/Custom_Spinner_With_Image_And_Text_-_Android_Example/index.php?view=article_discription&aid=84&aaid=107

        //Adaptador tipo cursos:
        String [] cursos = {"Agresivo", "Artistico", "Baile", "Tecnica", "Hockey", "Slalom", "Velocidad", "Otros"};
        ArrayAdapter<String> adaptadorCursos = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, cursos);
        spinnerCursos.setAdapter(adaptadorCursos);


        //RedioGroup seleccionador dificultad:


        //Funcionalidad para añadir un nuevo curso:
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultadoDificultado = obtenerDificultad();
                Toast.makeText(vista.getContext(),resultadoDificultado,Toast.LENGTH_SHORT).show();
            }
        });
        return vista;

    }//Fin onCreateView


    //Metodo que obtiene el radio releccionado del campo dificultad, se debe meter en el boton de confirmar:
    public String obtenerDificultad (){
        int radioSeleccionado = grupoDificultad.getCheckedRadioButtonId();
        String resultadoDificultad = "";
        switch (radioSeleccionado){
            case R.id.rdbBasico:
                resultadoDificultad = radioBasico.getText().toString().trim();
                break;
            case R.id.rdbMedio:
                resultadoDificultad = radioMedio.getText().toString().trim();
                break;
            case R.id.rdbAvanzado:
                resultadoDificultad = radioAvanzado.getText().toString().trim();
                break;
            case R.id.rdbExperto:
                resultadoDificultad = radioExperto.getText().toString().trim();
                break;
            default:
                break;
        }

        return resultadoDificultad;
    }
}