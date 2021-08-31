package com.example.patineando.FragmentsND;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patineando.AdaptadorListadoCursosTipo;
import com.example.patineando.AdaptadorListadoRutas;
import com.example.patineando.R;
import com.example.patineando.TCursoPublicado;
import com.example.patineando.TRutas;
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
 * Use the {@link FragmentListadoRutas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListadoRutas extends Fragment implements AdaptadorListadoRutas.ItemClickListenerVerRuta {


    RecyclerView recyclerRutas;

    List<TRutas> listadoRutas = new ArrayList<TRutas>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentListadoRutas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentListadoRutas.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListadoRutas newInstance(String param1, String param2) {
        FragmentListadoRutas fragment = new FragmentListadoRutas();
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
        View vista =  inflater.inflate(R.layout.fragment_listado_rutas, container, false);

        recyclerRutas = (RecyclerView) vista.findViewById(R.id.rcvListadoRutasPublicadas);



        recyclerRutas.setHasFixedSize(true);
        recyclerRutas.setLayoutManager(new LinearLayoutManager(vista.getContext()));
       recyclerRutas.setAdapter(new AdaptadorListadoRutas(listadoRutas,this,getContext()));

           obtenerListadoRutas();



       // recyclerRutas.getAdapter().notifyDataSetChanged();
        return vista;
    }


    //Sobreescritura del interfaz onItemClick para poder mostrar la vista detalle un elemento del fragment de los permisos:
    @Override
    public void onItemClick(TRutas auxiliarModeloDatos) { //TODO no funciona, es decir, no borra el dato
        //Toast.makeText(getContext(), auxiliarModeloDatos.getIdCurso(), Toast.LENGTH_LONG).show();

            Fragment fragmento =  new FragmentMapaRuta();
            //FragmentListadoCursosTipo fragmentDetalle = new FragmentListadoCursosTipo();
           Bundle parametroEnviar = new Bundle();
          // parametroEnviar.putSerializable("RutaSeleccionada",auxiliarModeloDatos);
        String urlMapa = auxiliarModeloDatos.getUriMapa().toString();
        parametroEnviar.putString("urlMapa",urlMapa);
            fragmento.setArguments(parametroEnviar);


            FragmentTransaction transaccion = getFragmentManager().beginTransaction();
            transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
            transaccion.addToBackStack(null);
            transaccion.commit();





    }//Fin onItemClick


    //Obtencion de las rutas publicadas

    public  List<TRutas> obtenerListadoRutas(){
      List<TRutas> resultado = new ArrayList<>();
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Rutas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot data : snapshot.getChildren()) {
                    TRutas rutaObtenida = new TRutas();
                    rutaObtenida.setUriMapa(data.getValue(TRutas.class).getUriMapa());
                    rutaObtenida.setNombreRuta(data.getValue(TRutas.class).getNombreRuta());
                    rutaObtenida.setLocalizacionRuta(data.getValue(TRutas.class).getLocalizacionRuta());
                    rutaObtenida.setDificultadRuta(data.getValue(TRutas.class).getDificultadRuta());
                    listadoRutas.add(rutaObtenida);
                }
                //recyclerRutas.setAdapter(new AdaptadorListadoRutas(listadoRutas,FragmentListadoRutas.this::onItemClick));
               recyclerRutas.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });


       return resultado;

    }//Fin metodo obtener rutas publicadas


}