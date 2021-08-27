package com.example.patineando.FragmentsND;

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
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.patineando.ConversionesTemporales;
import com.example.patineando.R;
import com.example.patineando.TCursoPublicado;
import com.example.patineando.TinformacionCursos;
import com.example.patineando.Tusuario;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAnadirEditarCurso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAnadirEditarCurso extends Fragment {

    private TextView txtMostrarHora;
    private Button btnSeleccionarHora, btnConfirmar;

    public List<Tusuario> listadoProfesores = new ArrayList<Tusuario>();
    boolean listadoCargado = false;
    private int horaSeleccionador, minutoSeleccionador;

    static final int TIME_DIALOG_ID = 0;

    private Spinner spinnerCursos, spinnerLocalizacion, spinnerProfesores;
    private RadioGroup grupoDificultad, grupoHoraUno, grupoHoraDos, grupoMinutos;
    private RadioButton radioBasico, radioMedio, radioAvanzado, radioExperto;
    private RadioButton radioCero, radioUno, radioDos, radioTres, radioCuatro, radioCinco;
    private RadioButton radioSeis, radioSiete, radioOcho, radioNueve, radioDiez, radioOnce;
    private RadioButton radioEnPunto, radioYCuarto, radioYMedia, radioMenosCuarto;
    private ChipGroup chipsDias;
    private Chip chipLunes, chipMartes, chipMiercoles, chipJueves, chipViernes, chipSabado, chipDomingo;//TODO no lo he usado y tira bien, lo dejo por si acaso
    private ToggleButton toggleAMPM;
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
        radioEnPunto = (RadioButton) vista.findViewById(R.id.rdbEnPunto);
        radioYCuarto = (RadioButton ) vista.findViewById(R.id.rdbCuarto);
        radioYMedia = (RadioButton) vista.findViewById(R.id.rdbMedia);
        radioMenosCuarto = (RadioButton) vista.findViewById(R.id.rdbMenosCuarto);

        toggleAMPM = (ToggleButton) vista.findViewById(R.id.tgbAMPM);

        btnConfirmar = (Button) vista.findViewById(R.id.btnConfirmar);

        //TODO si da tiempo, el Spinner de lso cursos debería tener una imagen, ver https://androidexample.com/Custom_Spinner_With_Image_And_Text_-_Android_Example/index.php?view=article_discription&aid=84&aaid=107

        //Adaptador tipo cursos:
        String [] cursos = {"Agresivo", "Artistico", "Baile", "Tecnica", "Hockey", "Slalom", "Velocidad", "Otros"};
        ArrayAdapter<String> adaptadorCursos = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, cursos);
        spinnerCursos.setAdapter(adaptadorCursos);

        //Adaptador localizaciones:
        String [] localizaciones ={"Las Rozas","Arganzuela","Retiro", "Matadero", "Las Aguilas", "Colón", "Juan Carlos I"};
        ArrayAdapter<String> adaptadorLocalizaciones = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, localizaciones);
        spinnerLocalizacion.setAdapter(adaptadorLocalizaciones);


        //Adaptador profesores: requiere extraer valores de la base de datos:

        //Generacion de la lista de objetos profesor:

      //  listadoProfesores = generadorListaProfesores();

            //Ahora se crea un Array que contiene los nombres de los profesores:


       //ArrayList<String> profesores = listadoNombresProfesores(); //TODO, EXTRAER LOS PROFESORES DE LA BASE DE DATOS
        String [] profesores = {"Profesor1 Uno (El primero)","Emilio Sanz", "Elena Alarcon (Helen)", "Luis Casado (Luismi)", "Isabel Valenzuela (Isa)" };
        ArrayAdapter<String> adaptadorProfesores = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_item, profesores);
        spinnerProfesores.setAdapter(adaptadorProfesores);


        //RedioGroup seleccionador dificultad: (ver metodo obtener dificultad)

        //Funcionalidad del grupo Chips (ver metodo obtenerDia)

        //Funcionalidad Extracción y formateo de la hora:

        //TODO lo primero es hacer lo de vincular los dos radioGrupos de las horas.







        //Funcionalidad para boton para añadir un nuevo curso:
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultadoCurso = obtenerTipoCurso();
                int resultadoImagen = obtenerImagenRecursoID(resultadoCurso);
                String resultadoDificultad = obtenerDificultad();

                int chipSeleccionado = chipsDias.getCheckedChipId();
                String resultadoDia = obtenerDia(chipSeleccionado);
                String resultadoHora = obtenerHora();
                String resultadoLocalizacion = obtenerLocalizacion();
                String resultadoProfesor = obtenerProfesor();
                String mensaje = resultadoCurso + ", " +resultadoDificultad + ", " + resultadoDia+ ", " + resultadoHora +", " + resultadoLocalizacion + ", "+ resultadoProfesor ;
                Toast.makeText(vista.getContext(),mensaje,Toast.LENGTH_SHORT).show();

                TCursoPublicado curso = new TCursoPublicado();
                curso.setImagenCurso(resultadoImagen);
                curso.setTipoCurso(resultadoCurso);
                curso.setNivelDificultad(resultadoDificultad);
                curso.setDiaClase(resultadoDia);
                curso.setHoraClase(resultadoHora);
                curso.setLocalizacion(resultadoLocalizacion);
                curso.setProfesor(resultadoProfesor);

                DatabaseReference mDatabase; //TODO debería ser private, pero no lo permite ya que la clase original es pública. Debo cambiar esto?
                mDatabase = FirebaseDatabase.getInstance().getReference();

                mDatabase.child("CursosOfertados").push().setValue(curso);

            }
        });//Fin boton agregar curso:


        return vista;

    }//Fin onCreateView

    //Generar Array con los valores de los profesores: //TODO la obtencion de los nombres de los profesores de la abse de datos no funciona
    /*
    public List<Tusuario> generadorListaProfesores(){
        List<Tusuario> listado = new ArrayList<Tusuario>();

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String palabraBusquedaProfe = "Profesorado";

        mDatabase.child("Usuarios").orderByChild("tipoUsuario").equalTo(palabraBusquedaProfe).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for( DataSnapshot data: snapshot.getChildren()){
                    Tusuario usuarioBusqueda = new Tusuario();

                    usuarioBusqueda.setApellidosUsuario(data.getValue(Tusuario.class).getApellidosUsuario().toString());
                    usuarioBusqueda.setApodoUsuario(data.getValue(Tusuario.class).getApodoUsuario().toString());
                    usuarioBusqueda.setCorreoUsuario(data.getValue(Tusuario.class).getCorreoUsuario().toString());
                    usuarioBusqueda.setDescripcionUsuario(data.getValue(Tusuario.class).getDescripcionUsuario().toString());
                    usuarioBusqueda.setFechaCreacionUsuario(data.getValue(Tusuario.class).getFechaCreacionUsuario());
                    usuarioBusqueda.setIdUsuario(data.getValue(Tusuario.class).getIdUsuario().toString());
                    usuarioBusqueda.setImagenUsuario(data.getValue(Tusuario.class).getImagenUsuario().toString());
                    usuarioBusqueda.setMatriculaActivaUsuario(data.getValue(Tusuario.class).getMatriculaActivaUsuario());
                    usuarioBusqueda.setNombreUsuario(data.getValue(Tusuario.class).getNombreUsuario().toString());
                    usuarioBusqueda.setPatinesUsuario(data.getValue(Tusuario.class).getPatinesUsuario().toString());
                    usuarioBusqueda.setTipoUsuario(data.getValue(Tusuario.class).getTipoUsuario().toString());

                    listado.add(usuarioBusqueda);
                }
                listadoCargado = true;
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        }); //Fin busqueda de usuarios


        /*
        mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot child: snapshot.getChildren()){
                    Tusuario auxUsuario = new Tusuario();
                    if((child.getValue(Tusuario.class).getTipoUsuario().toString()).equals("Profesorado")){
                        auxUsuario.setTipoUsuario(child.getValue(Tusuario.class).getApellidosUsuario().toString());
                        auxUsuario.setApodoUsuario(child.getValue(Tusuario.class).getApodoUsuario().toString());
                        auxUsuario.setCorreoUsuario(child.getValue(Tusuario.class).getCorreoUsuario().toString());
                        auxUsuario.setDescripcionUsuario(child.getValue(Tusuario.class).getDescripcionUsuario().toString());
                        auxUsuario.setFechaCreacionUsuario(child.getValue(Tusuario.class).getFechaCreacionUsuario());
                        auxUsuario.setIdUsuario(child.getValue(Tusuario.class).getIdUsuario().toString());
                        auxUsuario.setImagenUsuario(child.getValue(Tusuario.class).getImagenUsuario().toString());
                        auxUsuario.setMatriculaActivaUsuario(child.getValue(Tusuario.class).getMatriculaActivaUsuario());
                        auxUsuario.setNombreUsuario(child.getValue(Tusuario.class).getNombreUsuario().toString());
                        auxUsuario.setPatinesUsuario(child.getValue(Tusuario.class).getPatinesUsuario().toString());
                        auxUsuario.setTipoUsuario(child.getValue(Tusuario.class).getTipoUsuario().toString());

                        listado.add(auxUsuario);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        return listado;
    }//Fin metodo generarListaProfesores

    public ArrayList<String> listadoNombresProfesores(){
        ArrayList<String> listado = new ArrayList<>();

        for( int i = 0; i <= listadoProfesores.size(); i++){
            Tusuario auxUsuario = listadoProfesores.get(i);
            String nombre = auxUsuario.getNombreUsuario() + " "+ auxUsuario.getApellidosUsuario();
            String apodo = auxUsuario.getApodoUsuario();

            if(!apodo.equals("")){
                nombre = nombre + " ("+apodo+")";
            }
            listado.add(nombre);

        }

        return listado;
    }
   */


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
       // https://stackoverflow.com/questions/10425569/radiogroup-with-two-columns-which-have-ten-radiobuttons
        Chip chip = chipsDias.findViewById(chipChecked);
        resultadoDia = chip.getChipText().toString().trim();


        return resultadoDia;
    }

    //Metodos onChecked que eliminan los checs del radiogrupo no seleccionado en el apartado de las horas:
    //https://stackoverflow.com/questions/11168538/radiogroup-doesnt-work-properly
    //https://stackoverflow.com/questions/10425569/radiogroup-with-two-columns-which-have-ten-radiobuttons
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



    //Metodo para la obtencion de la hora:
    private String obtenerHora(){
        String resultadoHora = "" ;
        String horaString = "";

        boolean isGrupoHoraUno = false;
        boolean isGrupoHoraDos = false;
        boolean isPM = false;

        int comprobacionGrupoUno = grupoHoraUno.getCheckedRadioButtonId();
        int comprobacionGrupoDos = grupoHoraDos.getCheckedRadioButtonId();
        if(comprobacionGrupoUno != -1){
            isGrupoHoraUno = true;
        }
        if(comprobacionGrupoDos != -1){
            isGrupoHoraDos = true;
        }

        if(isGrupoHoraUno){

            switch (grupoHoraUno.getCheckedRadioButtonId()){
                case R.id.rdbCero:
                    horaString = "00";
                    break;
                case R.id.rdbUno:
                    horaString = "01";
                    break;
                case R.id.rdbDos:
                    horaString = "02";
                    break;
                case R.id.rdbTres:
                    horaString = "03";
                    break;
                case R.id.rdbCuatro:
                    horaString = "04";
                    break;
                case R.id.rdbCinco:
                    horaString = "05";
                    break;
            }

            isGrupoHoraUno = false;



        }else if(isGrupoHoraDos){
            switch (grupoHoraDos.getCheckedRadioButtonId()){
                case R.id.rdbSeis:
                    horaString = "06";
                    break;
                case R.id.rdbSiete:
                    horaString = "07";
                    break;
                case R.id.rdbOcho:
                    horaString = "08";
                    break;
                case R.id.rdbNueve:
                    horaString = "09";
                    break;
                case R.id.rdbDiez:
                    horaString = "10";
                    break;
                case R.id.rdbOnce:
                    horaString = "11";
                    break;
            }
            isGrupoHoraDos = false;
        }//En if hora:

        switch (grupoMinutos.getCheckedRadioButtonId()){
            case R.id.rdbEnPunto:
                horaString = horaString + ":" + "00";
                break;
            case R.id.rdbCuarto:
                horaString = horaString + ":" + "15";
                break;
            case R.id.rdbMedia:
                horaString = horaString + ":" + "30";
                break;
            case R.id.rdbMenosCuarto:
                horaString = horaString + ":" + "45";
                break;
        }

       if(toggleAMPM.isChecked()){
           //horaString = horaString + " " +toggleAMPM.getTextOn();
           isPM = true;
       }else{
           //horaString = horaString + " " + toggleAMPM.getTextOff();
       }


        ConversionesTemporales conversor = new ConversionesTemporales();

       resultadoHora = conversor.pasarDeAMPM(horaString,isPM);

        return resultadoHora;
    }//Fin metodo obtener hora


    public String obtenerLocalizacion(){
        String resultadoLocalizacion = "";

        resultadoLocalizacion =spinnerLocalizacion.getSelectedItem().toString();

        return resultadoLocalizacion;
    }//Fin metodo obtenerLocalizacion

    public String obtenerProfesor(){
        String resultadoProfesor = "";

        resultadoProfesor =spinnerProfesores.getSelectedItem().toString();

        return resultadoProfesor;
    }
}