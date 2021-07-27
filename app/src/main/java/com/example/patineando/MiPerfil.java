package com.example.patineando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
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

        //Cerrar la sesión de Firebase. //TODO no se si esto no funciona, o si es que el código del onStart de la actividad Acceso es lo que fastidia todo:
        //  FirebaseAuth.getInstance().signOut();
        //Confirmado que lo anterior no funciona, el siguiente fragmento se ha sacado de la siguiente guia y funciona fenomenal:
            //https://stackoverflow.com/questions/38707133/google-firebase-sign-out-and-forget-user-in-android-app
        GoogleSignInClient mGoogleSignInClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(getString(R.string.default_web_client_id))
                                .requestEmail()
                                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getBaseContext(),gso);
        mGoogleSignInClient.signOut().addOnCompleteListener(MiPerfil.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getBaseContext(),Acceso.class);
                Toast.makeText(getBaseContext(),getResources().getString(R.string.sesionCerradaMiPerfil),Toast.LENGTH_SHORT).show();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //Todo investigar que hace esto
                startActivity(intent);
                finish();
            }
        });




        //TODO no tiene mucho sentido, no s esupone que una vez se accede con Google, deberia estar el propio FirebaseAuth???


    }

}