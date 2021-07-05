package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //ActionBar actionbar = getSupportActionBar(); //TODO ver si hay alguna forma mejor de hacer esto. Casca
    //Recurso: https://stackoverflow.com/questions/26492522/how-do-i-remove-the-title-bar-from-my-app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //actionbar.hide(); //TODO casca
    }//Fin onCreate()------------


    //Método para poder ir a ver la información climática:

    public  void pruebaClima(View view){
        Intent intent = new Intent(this, InformacionClimatica.class);

        startActivity(intent);
    }
}