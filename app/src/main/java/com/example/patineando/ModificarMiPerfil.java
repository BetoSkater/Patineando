package com.example.patineando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModificarMiPerfil extends AppCompatActivity {

    EditText txtNombre, txtApellidos, txtApodo, txtPatines, txtDescripcion;
    //TODO poner la variable de la imagen aqui:
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_mi_perfil);

        //Asignacion de las variables con los controladores:
        txtNombre = (EditText) findViewById(R.id.txtNombreModificarMiPerfil);
        txtApellidos = (EditText) findViewById(R.id.txtApellidosModificarMiPerfil);
        txtApodo = (EditText) findViewById(R.id.txtApodoModificarMiPerfil);
        txtPatines = (EditText) findViewById(R.id.txtPatinesModificarMiPerfil);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcionModificarMiPerfil);

        mAuth = FirebaseAuth.getInstance(); //Inicialización de la instancia de FirebaseAuth

        //Llamada al método que carga los datos de la base de datos en los EditText para que el usuario tenga una mayor facilidad para cambair dichos datos.
        //TODO falta añadir en la funcion la carga de la imagen:
        cargarDatosUsuario();

    }//Fin método onCreate

    //Método para guardar los cambios en el perfil de Usuario por parte del Alumno:
    public void guardarCambiosEnPerfil(View view){

        DatabaseReference mDatabase; //TODO debería ser private, pero no lo permite ya que la clase original es pública. Debo cambiar esto?
        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser usuario = mAuth.getCurrentUser();


        Tusuario tUsuario = new Tusuario();
        String urlImagen = "@drawable/ic_menu_sun.png"; //TODO HARDCODED, poner bien cuando se haga la funcionalidad de seleccionar imagenes.
            //pd, no es un url, es una referencia a una foto tomada o sacada de la galeria del dispositivo del alumno
        String nombre = txtNombre.getText().toString();
        String apellidos = txtApellidos.getText().toString();
        String apodo = txtApodo.getText().toString();
        String patines = txtPatines.getText().toString();
        String descripcion = txtDescripcion.getText().toString(); //Todo Esto tiene que admitir mas de 255 caracteres ¿No entiendo como hacerlo con BufferedReader/Writter

        tUsuario.setNombreUsuario(nombre);
        tUsuario.setApellidosUsuario(apellidos);
        tUsuario.setApodoUsuario(apodo);
        tUsuario.setPatinesUsuario(patines);
        tUsuario.setDescripcionUsuario(descripcion);
        //TODO falta por poner lo de la imagen



        mDatabase.child("Usuarios").child(usuario.getUid()).child("imagenUsuario").setValue(urlImagen);
        mDatabase.child("Usuarios").child(usuario.getUid()).child("nombreUsuario").setValue(nombre);
        mDatabase.child("Usuarios").child(usuario.getUid()).child("apellidosUsuario").setValue(apellidos);
        mDatabase.child("Usuarios").child(usuario.getUid()).child("apodoUsuario").setValue(apodo);
        mDatabase.child("Usuarios").child(usuario.getUid()).child("patinesUsuario").setValue(patines);
        mDatabase.child("Usuarios").child(usuario.getUid()).child("descripcionUsuario").setValue(descripcion);
    }//Fin metodo guardarCambios()


    //Método que carga los datos del alumno alojados en la base de datos, en la primera carga, los datos estarán vacios.
    public void cargarDatosUsuario(){
        DatabaseReference mDatabase; //TODO debería ser private, pero no lo permite ya que la clase original es pública. Debo cambiar esto?
        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser usuario = mAuth.getCurrentUser(); //TODO igual lo mejor es hacer esta declaracion a nivel de clase



         mDatabase.child("Usuarios").child(usuario.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
             @Override
             public void onComplete(@NonNull Task<DataSnapshot> task) {
                 if(task.isSuccessful()){

                     //TODO PONER IMAGEN

                     String nombreRecuperado = String.valueOf(task.getResult().child("nombreUsuario").getValue());
                     txtNombre.setText(nombreRecuperado);

                     String apellidosRecuperados = String.valueOf(task.getResult().child("apellidosUsuario").getValue());
                     txtApellidos.setText(apellidosRecuperados);

                     String apodoRecuperado = String.valueOf(task.getResult().child("apodoUsuario").getValue());
                     txtApodo.setText(apodoRecuperado);

                     String patinesRecuperado = String.valueOf(task.getResult().child("patinesUsuario").getValue());
                     txtPatines.setText(patinesRecuperado);

                     String descripcionRecuperado = String.valueOf(task.getResult().child("descripcionUsuario").getValue());
                     txtDescripcion.setText(descripcionRecuperado);

                 }else{
                     Log.e(getResources().getString(R.string.logFirebase),getResources().getString(R.string.logErrorObtencionDatos),task.getException());
                 }
             }
         });
    }
}