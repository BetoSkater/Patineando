package com.example.patineando;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class Acceso extends AppCompatActivity {

    private static final String TAG = "AutenticacionGoogle"; //TODO lo mismo, revisar, esto sale de la guia de acceso con Gooogle
    private static  int RC_SIGN_IN = 123456789; //TODO mucho ojo, revisar, pone que es un numero que tengo que asignar yo , imagino que por seguridad

    EditText cajaCorreo, cajaContrasena;
    SignInButton botonGoogle;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private String idTokenWebApiGoogleCredenciales = "678360797170-uarh5m0pnnronjumerj6nr9qfqdm1h8k.apps.googleusercontent.com";
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
                        GoogleSignInOptions gso = new GoogleSignInOptions
                                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(getString(R.string.default_web_client_id))
                                .requestEmail()
                                .build();

                        mGoogleSignInClient = GoogleSignIn.getClient(Acceso.this,gso); //Todo en la guia pone solo this
                        accesoGoogle(); //TODO creo que va aqui
                        firebaseAuthConGoogle(idTokenWebApiGoogleCredenciales);//TODO aqui va lo del token, arreglar
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



        //Comprobación de que el usuario ya ha accedido previamente con su cuenta de Google: //Todo esto igual lo ntengo que quitar ya que esta realizado con
        //TODO esto, que no tiene lo de firebase: https://developers.google.com/identity/sign-in/android/sign-in?utm_source=studio
       // GoogleSignInAccount cuenta = GoogleSignIn.getLastSignedInAccount(this);
        //updateUIGoogle(cuenta);
    }//Fin método onStart()

    
    //Método para ejecutar el acceso con correo y contraseña. Asignado al botón btnAccederAcceso

    public void accederConCorreo(View view){
        //Nota: con el try catch y los if con los return de las cajas de texto, se evita que salte el error de las cajas de texto vacias, ya que se está forzando al usuario a que introduzca un correo y una contraseña para continuar, de no hacerlo, vuelve (deja de ejecutar el metodo del boton).
            try {
                 String correoAcceso = cajaCorreo.getText().toString().trim();
                 String contrasenaAcceso = cajaContrasena.getText().toString().trim();
                //https://stackoverflow.com/questions/6290531/how-do-i-check-if-my-edittext-fields-are-empty
                if (cajaCorreo.getText().toString().trim().isEmpty()) {
                    cajaCorreo.setError(getResources().getString(R.string.AccesoFaltaCorreo));
                    //cajaCorreo.setError("String", getResources().getDrawable(R.drawable.ic_menu_umbrella)); //TODo con icono no funciona, debe ser que no admite el formato o algo. Si tengo tiempo personalizar la exclamación del error.
                    return;
                }
                if (cajaContrasena.getText().toString().trim().isEmpty()) {
                    cajaContrasena.setError(getResources().getString(R.string.AccesoFaltaContrasena));
                    return;
                }
                mAuth.signInWithEmailAndPassword(correoAcceso, contrasenaAcceso).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, getResources().getString(R.string.AccesoConCorreoExitoso));
                            FirebaseUser usuario = mAuth.getCurrentUser();
                            System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO " + usuario.getEmail()); //TODO Borrar, es una simple comprobación
                            updateUIFirebase(usuario);
                        } else {
                            Log.w(TAG, getResources().getString(R.string.AccesoConCorreoFallido), task.getException());
                            Toast.makeText(Acceso.this, getResources().getString(R.string.autenticacionFallidaAcceso), Toast.LENGTH_SHORT).show();
                            updateUIFirebase(null);
                        }
                    }
                });

            }catch(IllegalStateException e){
                System.out.println(e);
            }//fin try..catch

    }//Fin método accederConCorreo()



    //Método para activar el recuperar contraseña. Asignado al botón btnOlvidoAcceso

    public void restablecerContrasena(View view){
    }//Fin método restablecerContrasena



    //Método para registrarse. Asignado al botón btnRegistroAcceso
    public void registrarUsuario(View view){
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }//Fin método registrarUsuario()







    //Método updateUI para el acceso con Correo y Contraseña
    public void updateUIFirebase(FirebaseUser usuario){
        if(usuario!=null){
            //TODO hacer esto, no me queda claro que poner ni en este ni en el de Google, lo dejo asi para que no salten errores,
            //En el siguiente enlace hay información que puede que sea interesante:

            //TODO: Lo que se quiere hacer aqui es que, en caso de que un usuario acceda por primera vez, se cree una instancia en la tbala usuarios con la información de e-mail, fecha de registro, tipo de usuario (por defecto, alumno)y el identificador de usuario (no se si es conveniente crear un UID nuevo automatico o poner el de la tabalde Authentication.
            //hacer una llamada al método:
            agregarUsuarioATablaUsuarios(); //Todo comprobar que en el segundo acceso no tira los datos que se hayan puesto una vez actualizado el perfil. Es decir, este código solo se deberia ejecutar una vez, en el momento de creación del registro en la base de datos

            //https://stackoverflow.com/questions/44491418/can-not-resolve-updateui-firebase
            Intent intent = new Intent(this,MenuPrincipal.class);
            startActivity(intent); //En teoría, se tiene que saltar el login e ir directamente al menú principal.
        }
    }//Fin Método updateUI(...) con Firebase


    //Método updateUI  para el acceso con Google //TODO en teoría esto no se está usando (el método updateUIGoogle):
        //Recurso: https://developers.google.com/identity/sign-in/android/sign-in?utm_source=studio
    public void updateUIGoogle(GoogleSignInAccount usuario){

        if(usuario!=null){

            //TODO entiendo que obtene la informacion de la ultima sesión, y por tanto aqui tengo que poner un intent al menú principal?
            Intent intent = new Intent(this,MenuPrincipal.class);
            startActivity(intent); //En teoría, se tiene que saltar el login e ir directamente al menú principal.
        }

    }//Fin método updateUI(...) para google


    //Método para el acceso mediante una cuenta de Google (Google Sign-In)
    public void accesoGoogle(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        //https://stackoverflow.com/questions/34852846/what-does-the-rc-sign-in-means-in-googleplus-login

    }//Fin método accesoGoogle

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        //Resultado obtenido de lazar el Intent desde GoogleSignInClient.getSignInIntent(...);
        if(requestCode == RC_SIGN_IN){
            //COmo la tarea de esta llamada simepre se completa, no hace falta añadir un listener
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                //Acceso con Google exitoso, autenticar con Firebase
                GoogleSignInAccount cuenta = task.getResult(ApiException.class);
                Log.d(TAG,getResources().getString(R.string.AutenticacionFirebaseConGoogle)+cuenta.getId());
                firebaseAuthConGoogle(cuenta.getIdToken());
            }catch(ApiException e){
                //Acceso fallido
                Log.w(TAG, getResources().getString(R.string.AccesoConGoogleFallido),e);
                //Poner el usuario como null con updateUIGoogle()??? //TODO
            }
            //handleSignInResult(task);
        }

    }//Fin método onActivityResult()

    private void firebaseAuthConGoogle(String idToken){
        AuthCredential credencial = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credencial).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Exito al entrar: se actualiza el updateUI con la información del usuario que ha accedido:
                    Log.d(TAG, getResources().getString(R.string.logExitoAccesoGoogle));
                    FirebaseUser usuario = mAuth.getCurrentUser();
                    System.out.println("oooooooooooooooooo "+ usuario.getEmail());
                    updateUIFirebase(usuario);
                }else{
                    //Si el acceso falla, se informa al usuario:

                    Log.w(TAG, getResources().getString(R.string.logErrorAccesoGoogle),task.getException());
                    Snackbar.make(botonGoogle.getRootView(), getResources().getString(R.string.autenticacionFallidaAcceso),Snackbar.LENGTH_SHORT).show();
                    //TODO no sé por que no funciona correctamente, siempre salta este toast al hacer el acceso

                    updateUIFirebase(null);

                }
            }
        });
    }//Fin método firebaseAuthConGoogle

    //---------Métodos para la creación de usuarios en la tabla de usuarios.

    private void agregarUsuarioATablaUsuarios(){
        //Para leer o escribir, lo primero es obtener una referencia a la base de datos:

             DatabaseReference mDatabase; //TODO debería ser private, pero no lo permite ya que la clase original es pública. Debo cambiar esto?
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Creacion de un objeto usuario con el constructor vacio, se le pondrán los valores de la tabal Auth, el resto los deberá poner más adelante el usuario:

        FirebaseUser usuario = mAuth.getCurrentUser();
        //El siguiente fragmento de código busca si en la tabla usuarios existe el child con el id del usuario que tiene sesión iniciada. En caso de no existir, se crea el registro
            //con los datos que el usuario alumno predeterminados (algunos sacados de la tabla Auth, otros impuestos, es decir, nada mas crear una cuenta, se
            //fuerza a que sea una cuenta alumno, ademas se pone que no está cursando ningún curso (ya que se acaba de registrar). Algunos campos como nombre, imagen, etc
            //los podrá cambiar el alumno desde su perfil.
        mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                boolean usuarioRegistrado = false;
                List<String> listadoIDUsuarios = new ArrayList<>();

                for(DataSnapshot child: snapshot.getChildren()){
                    String identificadorUsuario = child.getKey();
                    listadoIDUsuarios.add(identificadorUsuario);
                    //https://stackoverflow.com/questions/46651782/retrieve-data-of-all-user-id-from-firebase este enlace es el que me ha encendido no mucho
                    if(listadoIDUsuarios.contains(usuario.getUid())){ //El if dentro del bucle, asi en cuento se encuentre el IDusuario se dejan de cagar los datos de la base de datos, ya que solo nos interesa saber si dicho registro existe o no, no se necesitan ni sus valores ni nada.
                        usuarioRegistrado = true;
                        break;
                    }
                }//Fin for
                if(!usuarioRegistrado){
                    //Si el usuario nunca ha accedido, su id no estará en la tabla de usuarios, por lo que la primera vez que se accede no lo encuentra, al no encontrarse, se crea el registro:
                    Tusuario tUsuario = new Tusuario();

                    tUsuario.setIdUsuario(usuario.getUid()); //Nota: Se pone el UID de la tabla Auth ya que independientemente de que el acceso sea con correo/contraseña o con Google, se crea el UID, por lo que se entiende que siempre van a ser unicos independientemente de la forma de acceso.
                    tUsuario.setCorreoUsuario(usuario.getEmail());
                    tUsuario.setFechaCreacionUsuario(usuario.getMetadata().getCreationTimestamp());
                    tUsuario.setTipoUsuario("Alumno");
                    tUsuario.setMatriculaActivaUsuario(false);

                    //Parametros vacios, el usuario los tendrá que modificar desde su perfil mas adelante:
                    tUsuario.setImagenUsuario(""); //Poner el url al recurso alojado en CloudFireStore
                    tUsuario.setNombreUsuario("");
                    tUsuario.setApellidosUsuario("");
                    tUsuario.setApodoUsuario("");
                    tUsuario.setPatinesUsuario("");
                    tUsuario.setDescripcionUsuario("");

                    //insercción del objeto en la base de datos:
                    mDatabase.child("Usuarios").child(usuario.getUid()).setValue(tUsuario); //Nota: setValues borra todo y pome el nuevo, por lo que lo mejor es poner el objeto entero, con los campos que no sean de autenticacion para posteriormente modificar los campos marcados
                    //Una de las razones para meter el objeto entero aqui, es que así en la base de datos apareceran los nombres de los notdos, por lo que será más sencillo editar dichos campos.
                }else{
                    //Si el usuario ya tiene registro en la tabla, no se hace nada;
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
            }
        });//Fin valueEventListener. Idea sacada de aqui: https://stackoverflow.com/questions/39260729/how-to-check-if-data-already-exists-in-firebase-realtime-database-with-android

    }//Fin método agregarUsuarioATablaUsuarios

    //TODO: crear una clase con todos los métodos de modificación y formateo de fechas. Solo se necesita modificar el timestamp de long a legible por humanos en la parte de FRONT!!!!
    //Método para obtener un LocalDateTime desde un long TimeStamp (es tipo long)
    //Pasar de long timeStamp a LocalDateTime: https://stackoverflow.com/questions/44883432/long-timestamp-to-localdatetime
    public LocalDateTime longTimeStampALocalDateTime(long timestampACambiar){
        LocalDateTime fecha = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestampACambiar),
                TimeZone.getDefault().toZoneId());
        return fecha;
    }//Fin método longTimeStampALocalDateTime
}