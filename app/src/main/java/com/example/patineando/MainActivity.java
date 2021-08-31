package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    //ActionBar actionbar = getSupportActionBar(); //TODO ver si hay alguna forma mejor de hacer esto. Casca
    //Recurso: https://stackoverflow.com/questions/26492522/how-do-i-remove-the-title-bar-from-my-app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //actionbar.hide(); //TODO casca

        irAAcceso();
    }//Fin onCreate()------------



    //Metodo para ir a acceso tras unos segundos
    //https://stackoverflow.com/questions/9959666/making-a-screen-switch-after-a-few-seconds-thread-intent-android
    public void irAAcceso(){
        int tiempoEspera = 3000; //En ms

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Acceso.class);
                startActivity(intent);
            }
        },tiempoEspera);
    }

/*
    //Método para poder acceder al acceso:
    public void pruebaAcceso(View view){
        Intent intent = new Intent(this,Acceso.class);
        startActivity(intent);
    }
*/

}