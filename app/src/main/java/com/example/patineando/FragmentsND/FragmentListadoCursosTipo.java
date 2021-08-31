package com.example.patineando.FragmentsND;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.app.Fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.patineando.AdaptadorEliminarCurso;
import com.example.patineando.AdaptadorListadoCursosTipo;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListadoCursosTipo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListadoCursosTipo extends Fragment implements AdaptadorListadoCursosTipo.ItemClickListenerSolicitarPlaza {

    private  List<TCursoPublicado> Listado = new ArrayList<>();
    private RecyclerView listadorCursos;
    private RecyclerView.Adapter adaptador;
    private Button botonPruebas; //TODO Borrar

    private FirebaseAuth mAuth;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentListadoCursosTipo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentListadoCursosTipo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListadoCursosTipo newInstance(String param1, String param2) {
        FragmentListadoCursosTipo fragment = new FragmentListadoCursosTipo();
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
        View vista =  inflater.inflate(R.layout.fragment_listado_cursos_tipo, container, false);

        listadorCursos = (RecyclerView) vista.findViewById(R.id.rcvListaPorDisciplina);
      //   botonPruebas = (Button) vista.findViewById(R.id.botonPruebaBorrar);


        listadorCursos.setHasFixedSize(true);
        listadorCursos.setLayoutManager(new LinearLayoutManager(vista.getContext()));
        listadorCursos.setAdapter(new AdaptadorListadoCursosTipo(Listado,this));



        String criterioBusquedaObtenido = getArguments().get("Disciplina").toString();
        generarListadoCursos(criterioBusquedaObtenido);


        /*
        botonPruebas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vista.getContext(),criterioBusquedaObtenido ,Toast.LENGTH_SHORT).show();
            }
        });

         */
        //Poner la llamada al método que recupere los valores, y dentro
        //TODO dentro de esta llamada, poner la asignacion al adaptador.



        return vista;
    }//Fin onCreateView

    //Metodo para buscar en funcion del parámetro obtenido:

    public void generarListadoCursos(String palabraClaveBuscar){

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("CursosOfertados").orderByChild("tipoCurso").equalTo(palabraClaveBuscar).addListenerForSingleValueEvent(new ValueEventListener() {
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
                listadorCursos.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        }); //Fin busqueda por curso

     //  adaptador.notifyDataSetChanged();



    }// Fin metodo generarListadoCursos(STring ....)

    //Sobreescritura del interfaz onItemClick para poder mostrar la vista detalle un elemento del fragment de los permisos:
    @Override
    public void onItemClick(TCursoPublicado auxiliarModeloDatos) { //TODO no funciona, es decir, no borra el dato
        //Toast.makeText(getContext(), auxiliarModeloDatos.getIdCurso(), Toast.LENGTH_LONG).show();


        //Estas dos lineas de imagen forman parte del AlertDialog
        ImageView imagen = new ImageView(getContext());
        imagen.setImageResource(R.drawable.icono_rueda);

        new AlertDialog.Builder(getContext())
                .setView(imagen)
                .setTitle(R.string.alertdialog_titulo_matricularse)
                .setMessage(R.string.alertdialog_cuerpo_matricularse)
                .setPositiveButton(R.string.alertdialog_boton_aceptar_matricularse, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        realizarMatricula(auxiliarModeloDatos.getIdCurso());

                    }

                })
                .setNegativeButton(R.string.alertdialog_boton_cancelar_matricularse, null)
                .show();

    }//Fin onItemClick

    //Método del boton positivo del alertDialog:
    public void realizarMatricula(String idCurso){
        String idUsuario;
        long fechaSolicitudMatricula = Instant.now().toEpochMilli(); //https://stackoverflow.com/questions/21198882/convert-current-datetime-to-long-in-java

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAuth =  FirebaseAuth.getInstance();

        idUsuario = mAuth.getUid().toString();

        TCursoUsuario relacionCursoAlumno = new TCursoUsuario(idCurso,idUsuario,fechaSolicitudMatricula);

        //Insercion de la relacion curso-alumno en la tabla RelacionCursoAlumno:
        mDatabase.child("RelacionCursoAlumno").push().setValue(relacionCursoAlumno);


        //Cuando un usuario tiene en la temporada de cursos una matricula activa, es alumno, por lo que se tiene que activar el campo de matricula activa de dicho usuario en la tabla de usuarios
        mDatabase.child("Usuarios").child(idUsuario).child("matriculaActivaUsuario").setValue(true);


        Toast.makeText(getContext(),R.string.toast_matricula_realizada_con_exito,Toast.LENGTH_SHORT).show();




    }//fin metodo realizarMatricula


}