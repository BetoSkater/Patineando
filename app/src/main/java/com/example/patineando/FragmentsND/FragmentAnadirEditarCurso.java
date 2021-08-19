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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

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

        txtMostrarHora = (TextView) vista.findViewById(R.id.txtHoraSeleccionada);
        btnSeleccionarHora = (Button) vista.findViewById(R.id.btnSeleccionarHora);

        spinnerCursos = (Spinner) vista.findViewById(R.id.sprSeleccionarTipoCurso);
        spinnerLocalizacion = (Spinner) vista.findViewById(R.id.sprLocalizacionClases);
        spinnerProfesores = (Spinner) vista.findViewById(R.id.sprSeleccionarProfesor);

        //TODO si da tiempo, el Spinner de lso cursos debería tener una imagen, ver https://androidexample.com/Custom_Spinner_With_Image_And_Text_-_Android_Example/index.php?view=article_discription&aid=84&aaid=107

        //Adaptador tipo cursos:
        String [] cursos = {"Agresivo", "Artistico", "Baile", "Tecnica", "Hockey", "Slalom", "Velocidad", "Otros"};
        ArrayAdapter<String> adaptadorCursos = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, cursos);
        spinnerCursos.setAdapter(adaptadorCursos);


        //Se añade un OnClickListener al botón en el que se llama a un método que muestra el dialog con el seleccionador de hora:
        btnSeleccionarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*
                showDialog(TIME_DIALOG_ID);
                //guia: https://www.bogotobogo.com/Android/android8DatePickerTimePickerClocks.php#TimePicker
                //TODO a aprtir de aqui lo ponen fuera:
                //Ahora se asigna a las variables horaSeleccionada y minutoSeleccionado los valores recogidos mediante la creacion de un objeto Calendar.

                final Calendar calendario = Calendar.getInstance();
                horaSeleccionador = calendario.get(Calendar.HOUR_OF_DAY);
                minutoSeleccionador = calendario.get(Calendar.MINUTE);

                //Mostrar fecha

                actualizarHora();


                */
            }
        });

/*
        protected Dialog onCreateDialog(int id){
            switch (id){
                case TIME_DIALOG_ID:
                    return new TimePickerDialog(getContext(), horaSetListener, horaSeleccionador, minutoSeleccionador , false);
            }
            return null;
        }//Fin override Dialog onCreateDialog

        //Metodo que actualiza la hora en la caja de texto:
        private void actualizarHora(){
            txtMostrarHora.setText(new StringBuilder().append(pad(horaSeleccionador)).append(":").append(pad(minutoSeleccionador)));
        }//Fin metodo actualizarHora().

        //Respuesta obtenida cuando el usuario ha seleccionado una hora:
         TimePickerDialog.OnTimeSetListener horaSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                horaSeleccionador = hourOfDay;
                minutoSeleccionador = minute;
                actualizarHora();
            }
        };

        //Metodo para  formatear la hora:
        private static String pad(int c){
            if (c >= 10){
                return String.valueOf(c);
            }else{
                return "0" + String.valueOf(c);
            }
        }


 */



        return vista;

    }//Fin onCreateView
}