package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Acceso extends AppCompatActivity {

    EditText cajaCorreo, cajaContrasena;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        cajaCorreo = (EditText) findViewById(R.id.txtCorreoAcceso);
        cajaContrasena = (EditText) findViewById(R.id.txtContrasenaAcceso);


        mAuth = FirebaseAuth.getInstance(); //Inicialización de la instancia de FirebaseAuth

    }//Fin onCrete

    //Comprobación de que el usuario ha accedido en el onStart()
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser usuarioActual = mAuth.getCurrentUser();
        updateUI(usuarioActual);
    }
    //Método para ejecutar el acceso con correo y contraseña. Asignado al botón btnAccederAcceso

    public void accederConCorreo(View view){

    }//Fin método accederConCorreo()


    //Método para activar el recuperar contraseña. Asignado al botón btnOlvidoAcceso

    public void restablecerContrasena(View view){

    }//Fin método restablecerContrasena



    //Método para registrarse. Asignado al botón btnRegistroAcceso

    public void registrarUsuario(View view){

    }//Fin método registrarUsuario()


    //TODO poner aqui el botón de acceso con Google
}