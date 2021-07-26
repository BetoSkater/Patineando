package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Acceso extends AppCompatActivity {

    private static final String TAG = ""; //TODO lo mismo, revisar, esto sale de la guia de acceso con Gooogle
    private static  int RC_SIGN_IN = 123456789; //TODO mucho ojo, revisar, pone que es un numero que tengo que asignar yo , imagino que por seguridad
    EditText cajaCorreo, cajaContrasena;
    SignInButton botonGoogle;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        cajaCorreo = (EditText) findViewById(R.id.txtCorreoAcceso);
        cajaContrasena = (EditText) findViewById(R.id.txtContrasenaAcceso);
        botonGoogle = (SignInButton) findViewById(R.id.sign_in_button);

        mAuth = FirebaseAuth.getInstance(); //Inicialización de la instancia de FirebaseAuth

        botonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.sign_in_button:
                        accesoGoogle();
                }//Fin switch
            }
        });

    }//Fin onCrete

    //Comprobación de que el usuario ha accedido en el onStart()
    @Override
    public void onStart(){
        super.onStart();
       FirebaseUser usuarioActual = mAuth.getCurrentUser();
        updateUIFirebase(usuarioActual); //TODO ver que hacer con esto



        //Comprobación de que el usuario ya ha accedido previamente con su cuenta de Google:
        GoogleSignInAccount cuenta = GoogleSignIn.getLastSignedInAccount(this);
        updateUIGoogle(cuenta);
    }//Fin método onStart()
    
    //Sobreescritura del método onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        
        //Resultado obtenido de lazar el Intent desde GoogleSignInClient.getSignInIntent(...);
        if(requestCode == RC_SIGN_IN){
            //COmo la tarea de esta llamada simepre se completa, no hace falta añadir un listener
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        
    }//Fin método onActivityResult()
    
    
    
    
    //Método para ejecutar el acceso con correo y contraseña. Asignado al botón btnAccederAcceso

    public void accederConCorreo(View view){

    }//Fin método accederConCorreo()


    //Método para activar el recuperar contraseña. Asignado al botón btnOlvidoAcceso

    public void restablecerContrasena(View view){

    }//Fin método restablecerContrasena



    //Método para registrarse. Asignado al botón btnRegistroAcceso

    public void registrarUsuario(View view){
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }//Fin método registrarUsuario()




    //Método para el acceso mediante una cuenta de Google (Google Sign-In)
    public void accesoGoogle(){


        //Paso 1: Enlazar
        //Paso 2: añadir el Gradle (app) la dependencia de firebase de acceso con google
        //Paso 3:
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        //Paso 4: Construcción de un objeto GoogleSignInClient con los parametros especificados en el paso 3
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        //Nota: creo que la primera parte del flujo de código mo me hace falta, ya que lo que yo quiero es poner el acceso con el View, pero igual tengo que pasar por el aro:
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        //https://stackoverflow.com/questions/34852846/what-does-the-rc-sign-in-means-in-googleplus-login
    }//Fin método accesoGoogle




    //Método updateUI para el acceso con Correo y Contraseña
    public void updateUIFirebase(FirebaseUser usuario){
        if(usuario!=null){
            //TODO hacer esto, no me queda claro que poner ni en este ni en el de Google, lo dejo asi para que no salten errores,
            //en el siguiente enlace hay información que puede que sea interesante:
            //https://stackoverflow.com/questions/44491418/can-not-resolve-updateui-firebase
        }
    }//Fin Método updateUI(...) con Firebase


    //Método updateUI  para el acceso con Google
        //Recurso: https://developers.google.com/identity/sign-in/android/sign-in?utm_source=studio
    public void updateUIGoogle(GoogleSignInAccount usuario){

        if(usuario!=null){

            //TODO entiendo que obtene la informacion de la ultima sesión, y por tanto aqui tengo que poner un intent al menú principal?
            Intent intent = new Intent(this,MenuPrincipal.class);
            startActivity(intent); //En teoría, se tiene que saltar el login e ir directamente al menú principal.
        }

    }//Fin método updateUI(...) para google

    //Método handleSignInResult() Necesario para la sobreescritura del método onActivityResult
    
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try{
            GoogleSignInAccount cuenta = completedTask.getResult(ApiException.class);
            //Entrada exitosa, mostrar el usuario autenticado:
            updateUIGoogle(cuenta);
        }catch(ApiException e){
            Log.w(TAG,getResources().getString(R.string.logErrorAccesoGoogle) + e.getStatusMessage());
            updateUIGoogle(null);
        }
    }//Fin método handleSignInResult
}