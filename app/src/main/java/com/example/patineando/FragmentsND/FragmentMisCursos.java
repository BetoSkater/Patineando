package com.example.patineando.FragmentsND;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patineando.AdaptadorMisCursos;
import com.example.patineando.CursoMisCursos;
import com.example.patineando.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMisCursos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMisCursos extends Fragment {

    private RecyclerView listador;
    private RecyclerView.Adapter adaptador;

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

        //Busqueda del controlador en el que se van a cargar los datos:
        listador = (RecyclerView) vista.findViewById(R.id.rcvListadoMisCursosND);

        //Indicacion del que el tamaño del recyclerView no se va a cambiar. Que tiene hijos (items) de altura y anchura predeterminadas.  Mejora la opctimizacion del RecyclerView

        listador.setHasFixedSize(true);
        listador.setLayoutManager(new LinearLayoutManager(vista.getContext()));

        //Especificacion del adaptador con la lissta a visualizar:

        adaptador = new AdaptadorMisCursos(ListadoMisCursos());
        listador.setAdapter(adaptador);



        return vista;
    }



    //Listado para probar que el RecyclerView de mis cursos funciona, este deberá contener en un futuro la informacion de la base de datos:

    public List<CursoMisCursos> ListadoMisCursos(){
        List<CursoMisCursos> Listado = new ArrayList<>();

        Listado.add(new CursoMisCursos(R.drawable.ic_menu_happy,"Técnica de patinaje", "Avanzado", "Jueves", "19:15","Retiro","Victor",null));
        Listado.add(new CursoMisCursos(R.drawable.ic_menu_umbrella, "RollerDance", "Medio","Domingo", "17:00", "Alcobendas", "Javier", null));
        Listado.add((new CursoMisCursos(R.drawable.ic_menu_sun, "Velocidad", "Básico", "Martes", "20:00", "Wanda Metropolitano", "Sonsoles", "Sonso")));
        return Listado;
    }

}