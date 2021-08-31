package com.example.patineando.FragmentsND;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patineando.AdaptadorEliminarCurso;
import com.example.patineando.AdaptadorGestionPermisos;
import com.example.patineando.R;
import com.example.patineando.TCursoPublicado;
import com.example.patineando.TinformacionCursos;
import com.example.patineando.Tusuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEliminarCurso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEliminarCurso extends Fragment implements AdaptadorEliminarCurso.ItemClickListenerBorrar {


    private RadioGroup grupoCriterioBusqueda;
    private RadioButton radioCurso, radioNivel, radioProfesor, radioLugar, radioDia;
    private EditText txtBusqueda;
    private RecyclerView listadorCursos;
    private Button botonBuscar;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentEliminarCurso() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEliminarCurso.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEliminarCurso newInstance(String param1, String param2) {
        FragmentEliminarCurso fragment = new FragmentEliminarCurso();
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
        View vista =  inflater.inflate(R.layout.fragment_eliminar_curso, container, false);

        txtBusqueda = (EditText) vista.findViewById(R.id.txtCriterioBusquedaCursosEliminarCurso);

        botonBuscar = (Button) vista.findViewById(R.id.btnBuscarCursosEliminarCurso);

        grupoCriterioBusqueda = (RadioGroup) vista.findViewById(R.id.rdgEliminarCurso);
        radioCurso = (RadioButton) vista.findViewById(R.id.rdbCursoEC);
        radioNivel = (RadioButton) vista.findViewById(R.id.rdbNivelEC);
        radioProfesor = (RadioButton) vista.findViewById(R.id.rdbProfeEC);
        radioLugar = (RadioButton) vista.findViewById(R.id.rdbLugarEC);
        radioDia = (RadioButton) vista.findViewById(R.id.rdbDiaEC);

        listadorCursos = (RecyclerView) vista.findViewById(R.id.rcvBuscadorCursosEliminarCurso);

        listadorCursos.setHasFixedSize(true);
        listadorCursos.setLayoutManager(new LinearLayoutManager(vista.getContext()));

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String criterioBuscar = obtenerCriterioBusqueda();


                switch (criterioBuscar){
                    case "Curso":
                        crearListaBusquedaPorCurso();
                        break;
                    case "Nivel":
                        crearListaBusquedaPorNivel();
                        break;
                    case "Docente":
                        crearListaBusquedaPorDocente();
                        break;
                    case "Dia":
                        crearListaBusquedaPorDia();
                        break;
                    case "Lugar":
                        crearListaBusquedaPorLugar();
                        break;

                    default:
                        break;
                }//Fin switch:


            }
        });

        return vista;
    }//Fin onCreateVista


    //Sobreescritura del interfaz onItemClick para poder mostrar la vista detalle un elemento del fragment de los permisos:
    @Override
    public void onItemClick(TCursoPublicado modeloDatos) { //TODO no funciona, es decir, no borra el dato

        new AlertDialog.Builder(getContext())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.alert_eliminar_titulo)
                .setMessage(R.string.alert_cuerpo_titulo)
                .setPositiveButton(R.string.alert_confirmar, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     DatabaseReference mDatabase;
                     mDatabase = FirebaseDatabase.getInstance().getReference();

                     String criterioBorrar = modeloDatos.getIdCurso();

                     mDatabase.child("CursosOfertados").child(criterioBorrar).removeValue();
                     Toast.makeText(getContext(),getResources().getString(R.string.toast_exito_borrado),Toast.LENGTH_SHORT).show();
                    }

                })
                .setNegativeButton(R.string.alert_cancelar, null)
                .show();

        //----------





    }


    //Funcion para obtener el criterio de busqueda seleccioando:
    public String obtenerCriterioBusqueda (){
        int radioSeleccionado = grupoCriterioBusqueda.getCheckedRadioButtonId();
        String resultado = "";
        switch (radioSeleccionado){
            case R.id.rdbCursoEC:
                resultado = radioCurso.getText().toString().trim();
                break;
            case R.id.rdbNivelEC:
                resultado = radioNivel.getText().toString().trim();
                break;
            case R.id.rdbProfeEC:
                resultado = radioProfesor.getText().toString().trim();
                break;
            case R.id.rdbLugarEC:
                resultado = radioLugar.getText().toString().trim();
                break;
            case R.id.rdbDiaEC:
                resultado = radioDia.getText().toString().trim();
                break;
            default:
                break;
        }

        return resultado;
    }//Fin metodo obtenerCriterioBusqueda

    //Métodos para las consultas personalizadas:

    //Extracción de los datos en función del curso:

    private List<TCursoPublicado> crearListaBusquedaPorCurso() {
        List<TCursoPublicado> Listado = new ArrayList<>();

        String criterioBusqueda = txtBusqueda.getText().toString().trim();

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Obtención de la palabra del criterio de busqueda (el apellido);
        //String palabraBusqueda = criterioBusqueda.getText().toString().trim();

        //Busqueda de los usuarios: https://stackoverflow.com/questions/37642352/how-to-perform-search-in-a-firebasedatabase

        mDatabase.child("CursosOfertados").orderByChild("tipoCurso").equalTo(criterioBusqueda).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for( DataSnapshot data: snapshot.getChildren()){
                    TCursoPublicado cursoBusqueda = new TCursoPublicado();


                    cursoBusqueda.setIdCurso(data.getKey().toString());
                    cursoBusqueda.setImagenCurso(data.getValue(TCursoPublicado.class).getImagenCurso());
                    cursoBusqueda.setTipoCurso(data.getValue(TCursoPublicado.class).getTipoCurso().toString());
                    cursoBusqueda.setNivelDificultad(data.getValue(TCursoPublicado.class).getNivelDificultad().toString());
                    cursoBusqueda.setDiaClase(data.getValue(TCursoPublicado.class).getDiaClase().toString());
                    cursoBusqueda.setHoraClase(data.getValue(TCursoPublicado.class).getHoraClase().toString());
                    cursoBusqueda.setLocalizacion(data.getValue(TCursoPublicado.class).getLocalizacion().toString());
                    cursoBusqueda.setProfesor(data.getValue(TCursoPublicado.class).getProfesor().toString());


                    Listado.add(cursoBusqueda);
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        }); //Fin busqueda por curso

        listadorCursos.setAdapter(new AdaptadorEliminarCurso(Listado,this));

        return Listado;
    }//Fin método crearListaBusquedaPorCurso



    //Extracción de los datos en función del Nivel:

    private List<TCursoPublicado> crearListaBusquedaPorNivel() {
        List<TCursoPublicado> Listado = new ArrayList<>();

        String criterioBusqueda = txtBusqueda.getText().toString().trim();

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Obtención de la palabra del criterio de busqueda (el apellido);
        //String palabraBusqueda = criterioBusqueda.getText().toString().trim();

        //Busqueda de los usuarios: https://stackoverflow.com/questions/37642352/how-to-perform-search-in-a-firebasedatabase

        mDatabase.child("CursosOfertados").orderByChild("nivelDificultad").equalTo(criterioBusqueda).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for( DataSnapshot data: snapshot.getChildren()){
                    TCursoPublicado cursoBusqueda = new TCursoPublicado();


                    cursoBusqueda.setIdCurso(data.getKey().toString());
                    cursoBusqueda.setImagenCurso(data.getValue(TCursoPublicado.class).getImagenCurso());
                    cursoBusqueda.setTipoCurso(data.getValue(TCursoPublicado.class).getTipoCurso().toString());
                    cursoBusqueda.setNivelDificultad(data.getValue(TCursoPublicado.class).getNivelDificultad().toString());
                    cursoBusqueda.setDiaClase(data.getValue(TCursoPublicado.class).getDiaClase().toString());
                    cursoBusqueda.setHoraClase(data.getValue(TCursoPublicado.class).getHoraClase().toString());
                    cursoBusqueda.setLocalizacion(data.getValue(TCursoPublicado.class).getLocalizacion().toString());
                    cursoBusqueda.setProfesor(data.getValue(TCursoPublicado.class).getProfesor().toString());


                    Listado.add(cursoBusqueda);
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        }); //Fin busqueda por curso

        listadorCursos.setAdapter(new AdaptadorEliminarCurso(Listado,this));

        return Listado;
    }//Fin método crearListaBusquedaPorNivel


    //Extracción de los datos en función del Docente:

    private List<TCursoPublicado> crearListaBusquedaPorDocente() {
        List<TCursoPublicado> Listado = new ArrayList<>();

        String criterioBusqueda = txtBusqueda.getText().toString().trim();

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Obtención de la palabra del criterio de busqueda (el apellido);
        //String palabraBusqueda = criterioBusqueda.getText().toString().trim();

        //Busqueda de los usuarios: https://stackoverflow.com/questions/37642352/how-to-perform-search-in-a-firebasedatabase

        mDatabase.child("CursosOfertados").orderByChild("profesor").equalTo(criterioBusqueda).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for( DataSnapshot data: snapshot.getChildren()){
                    TCursoPublicado cursoBusqueda = new TCursoPublicado();


                    cursoBusqueda.setIdCurso(data.getKey().toString());
                    cursoBusqueda.setImagenCurso(data.getValue(TCursoPublicado.class).getImagenCurso());
                    cursoBusqueda.setTipoCurso(data.getValue(TCursoPublicado.class).getTipoCurso().toString());
                    cursoBusqueda.setNivelDificultad(data.getValue(TCursoPublicado.class).getNivelDificultad().toString());
                    cursoBusqueda.setDiaClase(data.getValue(TCursoPublicado.class).getDiaClase().toString());
                    cursoBusqueda.setHoraClase(data.getValue(TCursoPublicado.class).getHoraClase().toString());
                    cursoBusqueda.setLocalizacion(data.getValue(TCursoPublicado.class).getLocalizacion().toString());
                    cursoBusqueda.setProfesor(data.getValue(TCursoPublicado.class).getProfesor().toString());


                    Listado.add(cursoBusqueda);
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        }); //Fin busqueda por curso

        listadorCursos.setAdapter(new AdaptadorEliminarCurso(Listado,this));

        return Listado;
    }//Fin método crearListaBusquedaPorDocente



    //Extracción de los datos en función del Dia:

    private List<TCursoPublicado> crearListaBusquedaPorDia() {
        List<TCursoPublicado> Listado = new ArrayList<>();

        String criterioBusqueda = txtBusqueda.getText().toString().trim();

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Obtención de la palabra del criterio de busqueda (el apellido);
        //String palabraBusqueda = criterioBusqueda.getText().toString().trim();

        //Busqueda de los usuarios: https://stackoverflow.com/questions/37642352/how-to-perform-search-in-a-firebasedatabase

        mDatabase.child("CursosOfertados").orderByChild("diaClase").equalTo(criterioBusqueda).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for( DataSnapshot data: snapshot.getChildren()){
                    TCursoPublicado cursoBusqueda = new TCursoPublicado();


                    cursoBusqueda.setIdCurso(data.getKey().toString());
                    cursoBusqueda.setImagenCurso(data.getValue(TCursoPublicado.class).getImagenCurso());
                    cursoBusqueda.setTipoCurso(data.getValue(TCursoPublicado.class).getTipoCurso().toString());
                    cursoBusqueda.setNivelDificultad(data.getValue(TCursoPublicado.class).getNivelDificultad().toString());
                    cursoBusqueda.setDiaClase(data.getValue(TCursoPublicado.class).getDiaClase().toString());
                    cursoBusqueda.setHoraClase(data.getValue(TCursoPublicado.class).getHoraClase().toString());
                    cursoBusqueda.setLocalizacion(data.getValue(TCursoPublicado.class).getLocalizacion().toString());
                    cursoBusqueda.setProfesor(data.getValue(TCursoPublicado.class).getProfesor().toString());


                    Listado.add(cursoBusqueda);
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        }); //Fin busqueda por curso

        listadorCursos.setAdapter(new AdaptadorEliminarCurso(Listado,this));

        return Listado;
    }//Fin método crearListaBusquedaPorDia


    //Extracción de los datos en función del Dia:

    private List<TCursoPublicado> crearListaBusquedaPorLugar() {
        List<TCursoPublicado> Listado = new ArrayList<>();

        String criterioBusqueda = txtBusqueda.getText().toString().trim();

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Obtención de la palabra del criterio de busqueda (el apellido);
        //String palabraBusqueda = criterioBusqueda.getText().toString().trim();

        //Busqueda de los usuarios: https://stackoverflow.com/questions/37642352/how-to-perform-search-in-a-firebasedatabase

        mDatabase.child("CursosOfertados").orderByChild("localizacion").equalTo(criterioBusqueda).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for( DataSnapshot data: snapshot.getChildren()){
                    TCursoPublicado cursoBusqueda = new TCursoPublicado();


                    cursoBusqueda.setIdCurso(data.getKey().toString());
                    cursoBusqueda.setImagenCurso(data.getValue(TCursoPublicado.class).getImagenCurso());
                    cursoBusqueda.setTipoCurso(data.getValue(TCursoPublicado.class).getTipoCurso().toString());
                    cursoBusqueda.setNivelDificultad(data.getValue(TCursoPublicado.class).getNivelDificultad().toString());
                    cursoBusqueda.setDiaClase(data.getValue(TCursoPublicado.class).getDiaClase().toString());
                    cursoBusqueda.setHoraClase(data.getValue(TCursoPublicado.class).getHoraClase().toString());
                    cursoBusqueda.setLocalizacion(data.getValue(TCursoPublicado.class).getLocalizacion().toString());
                    cursoBusqueda.setProfesor(data.getValue(TCursoPublicado.class).getProfesor().toString());


                    Listado.add(cursoBusqueda);
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        }); //Fin busqueda por curso

        listadorCursos.setAdapter(new AdaptadorEliminarCurso(Listado,this));

        return Listado;
    }//Fin método crearListaBusquedaPorLocalizacion


}