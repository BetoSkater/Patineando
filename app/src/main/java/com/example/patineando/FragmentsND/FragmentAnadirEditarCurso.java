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
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

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
    private RadioGroup grupoDificultad, grupoHoraUno, grupoHoraDos, grupoMinutos;
    private RadioButton radioBasico, radioMedio, radioAvanzado, radioExperto;
    private RadioButton radioCero, radioUno, radioDos, radioTres, radioCuatro, radioCinco;
    private RadioButton radioSeis, radioSiete, radioOcho, radioNueve, radioDiez, radioOnce;
    private ChipGroup chipsDias;
    private Chip chipLunes, chipMartes, chipMiercoles, chipJueves, chipViernes, chipSabado, chipDomingo;//TODO no lo he usado y tira bien, lo dejo por si acaso

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

        chipsDias = (ChipGroup) vista.findViewById(R.id.chpGrupoDiasSemana);
        chipLunes = (Chip) vista.findViewById(R.id.chpLunes);
        chipMartes = (Chip) vista.findViewById(R.id.chpMartes);
        chipMiercoles = (Chip) vista.findViewById(R.id.chpMiercoles);
        chipJueves = (Chip) vista.findViewById(R.id.chpJueves);
        chipViernes = (Chip) vista.findViewById(R.id.chpViernes);
        chipSabado = (Chip) vista.findViewById(R.id.chpSabado);
        chipDomingo = (Chip) vista.findViewById(R.id.chpDomingo);



        grupoHoraUno = (RadioGroup) vista.findViewById(R.id.rdgGrupoHoras1);
        radioCero = (RadioButton) vista.findViewById(R.id.rdbCero);
        radioUno = (RadioButton) vista.findViewById(R.id.rdbUno);
        radioDos = (RadioButton) vista.findViewById(R.id.rdbDos);
        radioTres = (RadioButton) vista.findViewById(R.id.rdbTres);
        radioCuatro = (RadioButton) vista.findViewById(R.id.rdbCuarto);
        radioCinco = (RadioButton) vista.findViewById(R.id.rdbCinco);

        grupoHoraDos = (RadioGroup) vista.findViewById(R.id.rdgGrupoHoras2);
        radioSeis = (RadioButton) vista.findViewById(R.id.rdbSeis);
        radioSiete = (RadioButton) vista.findViewById(R.id.rdbSiete);
        radioOcho = (RadioButton) vista.findViewById(R.id.rdbOcho);
        radioNueve = (RadioButton) vista.findViewById(R.id.rdbNueve);
        radioDiez = (RadioButton) vista.findViewById(R.id.rdbDiez);
        radioOnce = (RadioButton) vista.findViewById(R.id.rdbOnce);

        //Se deseleccionan los posibles radioButtons para que no existan conflictos entre ambos radiogrupos:

        grupoHoraUno.clearCheck();
        grupoHoraDos.clearCheck();

        //Se asocian a ambos radioGrupos listenrs que desactivan los botones del otro en cuanto se activa un radioButton en el.

        grupoHoraUno.setOnCheckedChangeListener(escuchadorUno);
        grupoHoraDos.setOnCheckedChangeListener(escuchadorDos);


        grupoMinutos = (RadioGroup) vista.findViewById(R.id.rdgGrupoMinutos);


        btnConfirmar = (Button) vista.findViewById(R.id.btnConfirmar);

        //TODO si da tiempo, el Spinner de lso cursos debería tener una imagen, ver https://androidexample.com/Custom_Spinner_With_Image_And_Text_-_Android_Example/index.php?view=article_discription&aid=84&aaid=107

        //Adaptador tipo cursos:
        String [] cursos = {"Agresivo", "Artistico", "Baile", "Tecnica", "Hockey", "Slalom", "Velocidad", "Otros"};
        ArrayAdapter<String> adaptadorCursos = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, cursos);
        spinnerCursos.setAdapter(adaptadorCursos);


        //RedioGroup seleccionador dificultad: (ver metodo obtener dificultad)

        //Funcionalidad del grupo Chips (ver metodo obtenerDia)

        //Funcionalidad Extracción y formateo de la hora:

        //TODO lo primero es hacer lo de vincular los dos radioGrupos de las horas.







        //Funcionalidad para boton para añadir un nuevo curso:
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultadoCurso = obtenerTipoCurso();
                String resultadoDificultad = obtenerDificultad();
                int resultadoImagen = obtenerImagenRecursoID(resultadoDificultad);
                int chipSeleccionado = chipsDias.getCheckedChipId();
                String resultadoDia = obtenerDia(chipSeleccionado);


                String mensaje = resultadoCurso + ", " +resultadoDificultad + ", " + resultadoDia+ ", " ;
                Toast.makeText(vista.getContext(),mensaje,Toast.LENGTH_SHORT).show();
            }
        });
        return vista;

    }//Fin onCreateView


    //Método para obtener el tipo de curso seleccionado:

    public String obtenerTipoCurso(){
        String resultadoCurso = "";

        resultadoCurso =spinnerCursos.getSelectedItem().toString();

        return resultadoCurso;
    }
    public int obtenerImagenRecursoID(String cursoSeleccionado){
        int rutaRecurso = 0;
        //String [] cursos = {"Agresivo", "Artistico", "Baile", "Tecnica", "Hockey", "Slalom", "Velocidad", "Otros"};
        switch (cursoSeleccionado){
            case "Agresivo":
                rutaRecurso = R.drawable.agresivo_sin_texto;
                break;
            case "Artistico":
                rutaRecurso = R.drawable.artistico_sin_texto;
                break;
            case "Baile":
                rutaRecurso = R.drawable.baile_sin_texto;
                break;
            case "Tecnica":
                rutaRecurso = R.drawable.tecnica_sin_texto;
                break;
            case "Hockey":
                rutaRecurso = R.drawable.hockey_sin_texto;
                break;
            case "Slalom":
                rutaRecurso = R.drawable.slalom_sin_texto;
                break;
            case "Velocidad":
                rutaRecurso = R.drawable.velocidad_sin_texto;
                break;
            case "Otros":
                rutaRecurso = R.drawable.otras_sin_texto;
                break;
            default:
                rutaRecurso = R.drawable.otras_sin_texto;
                break;

        }
        return rutaRecurso;
    }

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


    //Método para obtener el dia de la semana seleccionado:
    public String obtenerDia(int chipChecked){
        String resultadoDia = "";

        Chip chip = chipsDias.findViewById(chipChecked);
        resultadoDia = chip.getChipText().toString().trim();


        return resultadoDia;
    }

    //Metodos onChecked que eliminan los checs del radiogrupo no seleccionado en el apartado de las horas:

    private RadioGroup.OnCheckedChangeListener escuchadorUno = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId != -1){
                grupoHoraDos.setOnCheckedChangeListener(null);
                grupoHoraDos.clearCheck();
                grupoHoraDos.setOnCheckedChangeListener(escuchadorDos);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener escuchadorDos = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId != -1){
                grupoHoraUno.setOnCheckedChangeListener(null);
                grupoHoraUno.clearCheck();
                grupoHoraUno.setOnCheckedChangeListener(escuchadorUno);
            }
        }
    };

}