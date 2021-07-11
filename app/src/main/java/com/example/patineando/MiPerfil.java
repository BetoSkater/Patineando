package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MiPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
    }//Fin onCreate

    //----Funcionalidad del botón volver:

    public void volverAMenuPrincipal(View view){ //TODO comprobar que funciona
        finish();
    }
}