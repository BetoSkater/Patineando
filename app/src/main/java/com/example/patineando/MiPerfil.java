package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

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


    public void cerrarSesion(View view){

        //Cerrar la sesión de Firebase
        FirebaseAuth.getInstance().signOut();




        //TODO no tiene mucho sentido, no s esupone que una vez se accede con Google, deberia estar el propio FirebaseAuth???

        Intent intent = new Intent(this,Acceso.class);
        startActivity(intent);
    }

}