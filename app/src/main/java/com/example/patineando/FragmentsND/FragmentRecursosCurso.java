package com.example.patineando.FragmentsND;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patineando.AdaptadorListadoVideos;
import com.example.patineando.AdaptadorMisCursos;
import com.example.patineando.R;
import com.example.patineando.TCursoPublicado;
import com.example.patineando.TVideos;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRecursosCurso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRecursosCurso extends Fragment implements AdaptadorListadoVideos.ItemClickListenerVerVideo {

    /*FirebaseDatabase mDatabase;
    DatabaseReference databaseReference;

     */
    TextView txtTituloCurso, txtProfesor;
    RecyclerView recyclerVideos;
    private RecyclerView.Adapter adaptador;
    String disciplina ="";
    String nivel ="";
    String criterioBuscador = "";
    String profesor = "";
    int contadorDificultad = 0;

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

        //Obtención del ID del curso con alumnos del cual se quieren mostrar los recursos:
        String textoToast = getArguments().get("CursoSeleccionado").toString();

        //Toast.makeText(vista.getContext(),textoToast,Toast.LENGTH_SHORT).show();
        //TODO funciona y se obtiene el idCurso de las tablas CursosOfertados/ RelacionCursoAlumno


        txtTituloCurso = (TextView) vista.findViewById(R.id.lblCursoNivelRecursosCurso);
        txtProfesor = (TextView) vista.findViewById(R.id.lblProfeRecursosCurso);


        //RecyclerVIew:
        recyclerVideos = vista.findViewById(R.id.rcvListaRecursosCurso);
        recyclerVideos.setHasFixedSize(true);
        recyclerVideos.setLayoutManager(new LinearLayoutManager(vista.getContext()));

        //TODO AQUI FALTA POR PONER LO DEL ADAPTER
        // adaptador = new AdaptadorMisCursos(listadoMisCursos,this); //Al poner el listado no funciona, si o si en el adaptador hay que poner la funcion que devuelve el arraylist a meter, y dentro de esta funcion, el notifyonDAtaChange.
       /*
        adaptador = new AdaptadorListadoVideos(obtenerListadoVideos(),this);
        recyclerVideos.setAdapter(adaptador);


        */



        return vista;
    }//Fin onCreateView


    @Override
    public void onStart(){ //En teoria tiene que ser protected, pero da errores por el acceso de los otros fragments.
        super.onStart();


        //Obtencion del id del curso:
        String idCursoBusqueda = getArguments().get("CursoSeleccionado").toString();

        //Primero se extraen  las propiedades disciplina y nivel de la tabla de los cursos con el id del curso:

        DatabaseReference referenciaUno = FirebaseDatabase.getInstance().getReference();
        referenciaUno.child("CursosOfertados").child(idCursoBusqueda).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                disciplina = snapshot.getValue(TCursoPublicado.class).getTipoCurso().toString();
                nivel = snapshot.getValue(TCursoPublicado.class).getNivelDificultad().toString();
                profesor = snapshot.getValue(TCursoPublicado.class).getProfesor().toString();
                criterioBuscador = disciplina+nivel;

                //Encabezado:
                String titulo = disciplina + " " + nivel;
                txtTituloCurso.setText(titulo);
                txtProfesor.setText(profesor);

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                Query consulta = mDatabase.child("Video").orderByChild("criterioBusqueda").equalTo(criterioBuscador);
                FirebaseRecyclerOptions<TVideos> options = new FirebaseRecyclerOptions.Builder<TVideos>().setQuery(consulta, TVideos.class).build();


                FirebaseRecyclerAdapter<TVideos, AdaptadorListadoVideos.ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TVideos, AdaptadorListadoVideos.ViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull @NotNull AdaptadorListadoVideos.ViewHolder holder, int i, @NonNull @NotNull TVideos tVideos) {
                        //https://stackoverflow.com/questions/39066381/cannot-resolve-method-getapplicationcontext-in-fragment-class
                        //TODO aqui puedo calzar un if
                        //TODO funciona pero no como cabria de esperar, la base de datos tiene tres videos, pues bien, al seleccionar la disciplina de basico, salen dos cuadrados negros
                        //Creoq ue tengo que hacer el if, y dentro del if, toda la parte de la consulta y tal persinalizando la query

                        holder.setExoplayer(getActivity().getApplication(), tVideos.getNombreVideo(), tVideos.getNivelVideo(), tVideos.getEnlaceVideo());



                    }

                    @NonNull
                    @NotNull
                    @Override
                    public AdaptadorListadoVideos.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_recursos_curso, parent, false);

                        return new AdaptadorListadoVideos.ViewHolder(view);
                    }
                };
                firebaseRecyclerAdapter.startListening();
                recyclerVideos.setAdapter(firebaseRecyclerAdapter);


            }//Fin onDataChange

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



       // DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Video");

        //Poniendo esto en el setQuery tampoco Funka .child("Video").orderByChild("nivelVideo").equalTo(nivel)



       // https://stackoverflow.com/questions/55429197/cannot-resolve-symbol-firebaserecycleroptions
       // FirebaseRecyclerOptions<TVideos> options = new FirebaseRecyclerOptions.Builder<TVideos>().setQuery(mDatabase, TVideos.class).build();
/*
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        Query consulta = mDatabase.child("Video").orderByChild("criterioBusqueda").equalTo(criterioBuscador);
        FirebaseRecyclerOptions<TVideos> options = new FirebaseRecyclerOptions.Builder<TVideos>().setQuery(consulta, TVideos.class).build();


        FirebaseRecyclerAdapter<TVideos, AdaptadorListadoVideos.ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TVideos, AdaptadorListadoVideos.ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull AdaptadorListadoVideos.ViewHolder holder, int i, @NonNull @NotNull TVideos tVideos) {
                    //https://stackoverflow.com/questions/39066381/cannot-resolve-method-getapplicationcontext-in-fragment-class
                //TODO aqui puedo calzar un if
                //TODO funciona pero no como cabria de esperar, la base de datos tiene tres videos, pues bien, al seleccionar la disciplina de basico, salen dos cuadrados negros
                    //Creoq ue tengo que hacer el if, y dentro del if, toda la parte de la consulta y tal persinalizando la query

                holder.setExoplayer(getActivity().getApplication(), tVideos.getNombreVideo(), tVideos.getNivelVideo(), tVideos.getEnlaceVideo());



            }

            @NonNull
            @NotNull
            @Override
            public AdaptadorListadoVideos.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
              View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_recursos_curso, parent, false);

              return new AdaptadorListadoVideos.ViewHolder(view);
            }
        };
        firebaseRecyclerAdapter.startListening();
        recyclerVideos.setAdapter(firebaseRecyclerAdapter);

 */
    }//fin onStart



    //Sobreescritura del método onClick del adaptador:
    @Override
    public void onItemClick(TVideos modeloDatos){
        String nombreVideo = modeloDatos.getNombreVideo() + " " + modeloDatos.getEnlaceVideo();
        Toast.makeText(getContext(),nombreVideo,Toast.LENGTH_SHORT).show();
    }


    //Metodo para obtener el listado de los videos de la base de datos: Este es para probar con la tabla videos.

    public List<TVideos> obtenerListadoVideos(){

        List<TVideos> listaResultado = new ArrayList<>();

        //Obtencion de la referencia de la tabal videos de la tabla videos de realtime database //Cambiar a la tabla que contenga los recursos del curso:

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference(); //Esto lo ponen despues del recycler, pero deberia ir aqui //TODO POSIBLE FALLO AQUI

         mDatabase.child("Video").addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                 for(DataSnapshot data : snapshot.getChildren()){
                     TVideos auxVid = new TVideos();
                     auxVid.setDisciplinaVideo(data.getValue(TVideos.class).getDisciplinaVideo().toString());
                     auxVid.setNivelVideo(data.getValue(TVideos.class).getNivelVideo().toString());
                     auxVid.setNombreVideo(data.getValue(TVideos.class).getNombreVideo().toString());
                     auxVid.setTipoRecurso(data.getValue(TVideos.class).getTipoRecurso().toString());
                     auxVid.setEnlaceVideo(data.getValue(TVideos.class).getEnlaceVideo().toString());

                     listaResultado.add(auxVid);

                 }

                 recyclerVideos.getAdapter().notifyDataSetChanged();
             }

             @Override
             public void onCancelled(@NonNull @NotNull DatabaseError error) {

             }
         });

        return listaResultado;
    }

}