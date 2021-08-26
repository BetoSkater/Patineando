package com.example.patineando.FragmentsND;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patineando.AdaptadorMisCursos;
import com.example.patineando.CursoMisCursos;
import com.example.patineando.R;
import com.example.patineando.TCursoPublicado;
import com.example.patineando.TCursoUsuario;
import com.google.firebase.auth.FirebaseAuth;
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
 * Use the {@link FragmentMisCursos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMisCursos extends Fragment implements AdaptadorMisCursos.ItemClickListenerAcceso{

    private RecyclerView listador;
    private RecyclerView.Adapter adaptador;
    private List<TCursoPublicado> listadoMisCursos = new ArrayList<>();
    private FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMisCursos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMisCursos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMisCursos newInstance(String param1, String param2) {
        FragmentMisCursos fragment = new FragmentMisCursos();
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
        View vista = inflater.inflate(R.layout.fragment_mis_cursos, container, false);

        //obtencion del id del usuario autenticado
        mAuth =  FirebaseAuth.getInstance();
        String idUsuario = mAuth.getUid();


        //Busqueda del controlador en el que se van a cargar los datos:
        listador = (RecyclerView) vista.findViewById(R.id.rcvListadoMisCursosND);

        //Indicacion del que el tamaño del recyclerView no se va a cambiar. Que tiene hijos (items) de altura y anchura predeterminadas.  Mejora la opctimizacion del RecyclerView

        listador.setHasFixedSize(true);
        listador.setLayoutManager(new LinearLayoutManager(vista.getContext()));

        //Especificacion del adaptador con la lissta a visualizar:

       // adaptador = new AdaptadorMisCursos(listadoMisCursos,this); //Al poner el listado no funciona, si o si en el adaptador hay que poner la funcion que devuelve el arraylist a meter, y dentro de esta funcion, el notifyonDAtaChange.
        adaptador = new AdaptadorMisCursos(obtenerMisCursos(idUsuario),this);
        listador.setAdapter(adaptador);

        obtenerMisCursos(idUsuario);

        return vista;
    }//Fin onCreateView

    //Sobreescritua del metodo onItemClick del interfaz del adaptadorMisCursos.class
    @Override
    public void onItemClick(TCursoPublicado modeloDatosAcceso){

        //Funcion que obtiene el id del curso en el que se ha pinchado, para mostrar un fragment que contiene los recursos del curso de la tabla CursoRecurso o como la quiera llamar.
        String cursoSeleccionado = modeloDatosAcceso.getIdCurso();
        irARecursosCurso(cursoSeleccionado);




    }


    //Listado para probar que el RecyclerView de mis cursos funciona, este deberá contener en un futuro la informacion de la base de datos:

    public List<CursoMisCursos> ListadoMisCursos(){
        List<CursoMisCursos> Listado = new ArrayList<>();

        Listado.add(new CursoMisCursos(R.drawable.ic_menu_happy,"Técnica de patinaje", "Avanzado", "Jueves", "19:15","Retiro","Victor",null));
        Listado.add(new CursoMisCursos(R.drawable.ic_menu_umbrella, "RollerDance", "Medio","Domingo", "17:00", "Alcobendas", "Javier", null));
        Listado.add((new CursoMisCursos(R.drawable.ic_menu_sun, "Velocidad", "Básico", "Martes", "20:00", "Wanda Metropolitano", "Sonsoles", "Sonso")));
        return Listado;
    } //Fin metodo obtencion de valores de prueba.



    //ESte metodo extrae los valores de la base de datos de la siguiente manera, primero se mira si en la tabla Curso
    public List<TCursoPublicado> obtenerMisCursos(String idUsuarioBuscar){
        List<TCursoPublicado> listadoMisCursos = new ArrayList<>();
        List<TCursoUsuario> listadoIDCursosAlumno = new ArrayList<>();

        //Obtencion de la referencia de la base de datos.
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("RelacionCursoAlumno").orderByChild("idUsuario").equalTo(idUsuarioBuscar).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot datos: snapshot.getChildren()){
                    TCursoUsuario auxTCursoUsuario = new TCursoUsuario();

                    auxTCursoUsuario.setIdCurso(datos.getValue(TCursoUsuario.class).getIdCurso());
                    auxTCursoUsuario.setFechaMatricula(datos.getValue(TCursoUsuario.class).getFechaMatricula());
                    auxTCursoUsuario.setIdUsuario(datos.getValue(TCursoUsuario.class).getIdUsuario());

                    listadoIDCursosAlumno.add(auxTCursoUsuario);

                }//Fin for extracion idCursos:


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        mDatabase.child("CursosOfertados").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                int contador = 0;
                for(DataSnapshot data : snapshot.getChildren()){
                    String idSnapShot = data.getKey().toString();

                    for(int i = 0; i<listadoIDCursosAlumno.size();i++){
                        String idCursoAuxListadoAlumnos =listadoIDCursosAlumno.get(i).getIdCurso().toString();

                        if(idCursoAuxListadoAlumnos.equals(idSnapShot)){
                            TCursoPublicado auxCurso = new TCursoPublicado();

                            auxCurso.setImagenCurso(data.getValue(TCursoPublicado.class).getImagenCurso());
                            auxCurso.setIdCurso(data.getKey()); //TODO id del curso
                            auxCurso.setTipoCurso(data.getValue(TCursoPublicado.class).getTipoCurso());
                            auxCurso.setNivelDificultad(data.getValue(TCursoPublicado.class).getNivelDificultad());

                            auxCurso.setDiaClase(data.getValue(TCursoPublicado.class).getDiaClase());
                            auxCurso.setHoraClase(data.getValue(TCursoPublicado.class).getHoraClase());
                            auxCurso.setLocalizacion(data.getValue(TCursoPublicado.class).getLocalizacion());

                            auxCurso.setProfesor(data.getValue(TCursoPublicado.class).getProfesor());


                            listadoMisCursos.add(auxCurso);

                            contador++;

                            //TODO aqui tengo que extraer el objeto y calzarselo al listado listadoMisCursos que es el que se debe retornar:
                        }

                    }//Fin for anidado
                    if(contador == listadoIDCursosAlumno.size()){
                        break;
                    }
                }//Fin for snapshot
                //TODO el fallo está qui, el listadoMisCursos obtiene los dos valores de datos de la base de datos de forma satisfactoria
                listador.getAdapter().notifyDataSetChanged();
            }//Fin onDataChange

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });//Fin Query



        return listadoMisCursos;
    }//Fin metodo obtener mis cursos


    public void irARecursosCurso(String idCurso){
        Fragment fragmento = new FragmentRecursosCurso();
        //FragmentListadoCursosTipo fragmentDetalle = new FragmentListadoCursosTipo();
        Bundle parametroEnviar = new Bundle();
        parametroEnviar.putString("CursoSeleccionado",idCurso);
        fragmento.setArguments(parametroEnviar);


        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }

}