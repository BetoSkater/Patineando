package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MisCursos extends AppCompatActivity {

    //Variables necesarias para el funcionamiento del RecyclerView

    private RecyclerView listador;
    private RecyclerView.Adapter adaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_cursos);

        //Busqueda del controlador en el que se van a cargar los datos:
        listador = (RecyclerView) findViewById(R.id.rcvListadoMisCursos);

        //Indicacion del que el tamaño del recyclerView no se va a cambiar. Que tiene hijos (items) de altura y anchura predeterminadas.  Mejora la opctimizacion del RecyclerView

        listador.setHasFixedSize(true);
        listador.setLayoutManager(new LinearLayoutManager(this));

        //Especificacion del adaptador con la lissta a visualizar:

      //  adaptador = new AdaptadorMisCursos(ListadoMisCursos());
        listador.setAdapter(adaptador);

    }//Fin onCreate

    //Listado para probar que el RecyclerView de mis cursos funciona, este deberá contener en un futuro la informacion de la base de datos:

    public List<CursoMisCursos> ListadoMisCursos(){
        List<CursoMisCursos> Listado = new ArrayList<>();

        Listado.add(new CursoMisCursos(R.drawable.ic_menu_happy,"Técnica de patinaje", "Avanzado", "Jueves", "19:15","Retiro","Victor",null));
        Listado.add(new CursoMisCursos(R.drawable.ic_menu_umbrella, "RollerDance", "Medio","Domingo", "17:00", "Alcobendas", "Javier", null));
        Listado.add((new CursoMisCursos(R.drawable.ic_menu_sun, "Velocidad", "Básico", "Martes", "20:00", "Wanda Metropolitano", "Sonsoles", "Sonso")));
        return Listado;
    }
}