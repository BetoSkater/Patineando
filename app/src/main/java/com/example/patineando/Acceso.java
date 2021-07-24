package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Acceso extends AppCompatActivity {

    EditText cajaCorreo, cajaContrasena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        cajaCorreo = (EditText) findViewById(R.id.txtCorreoAcceso);
        cajaContrasena = (EditText) findViewById(R.id.txtContrasenaAcceso);

    }
}