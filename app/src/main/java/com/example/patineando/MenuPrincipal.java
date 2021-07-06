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
}