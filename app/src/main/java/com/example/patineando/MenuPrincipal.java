package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }


    //-----Función para acceder a InformacionClimatica.java

    public void verTiempo(View view){
        Intent intent = new Intent(this, InformacionClimatica.class);
        startActivity(intent);
    }
    public void irAImagenCursosImpartidos(View view){
        Intent intent = new Intent(this, ImagenCursosImpartidos.class);
        startActivity(intent);
    }
    //-----Función para acceder a MisCursos.java

    public void irAMisCursos(View view){
        Intent intent = new Intent(this, MisCursos.class);
        startActivity(intent);
    }
    //-----Función para acceder a MiPerfil.java

    public void irAMiPerfil(View view){
        Intent intent = new Intent(this, MiPerfil.class);
        startActivity(intent);
    }


}