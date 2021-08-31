package com.example.patineando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Registro extends AppCompatActivity {

    private EditText txtCorreo, txtCorreoConfirmacion, txtContrasena, txtConfirmarContrasena;

    private Button botonRegistrar; //Como el método para registrar es private, no se le puede poner un VIew y asignarlo desde el xml //TODO Confirmar que esto es asi
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Asignación de las variables con los controladores:

        txtCorreo = (EditText) findViewById(R.id.txtCorreoRegistro);
        txtCorreoConfirmacion = (EditText) findViewById(R.id.txtConfirmarCorreo);
        txtContrasena = (EditText) findViewById(R.id.txtContrasenaRegistro);
        txtConfirmarContrasena = (EditText) findViewById(R.id.txtContrasenaConfirmarRegistro);
        botonRegistrar = (Button) findViewById(R.id.btnRegistrarRegistro);
        //Inicialización del objeto firebaseAuth:
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        //Asignacion del método registrarCorreo al botón botonRegistrar

        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarCorreo();
            }
        });
    }//Fin onCreate


    //Método para registrar a un usuario con Correo y Contraseña (El normal, no es acceso con Google)

    private void registrarCorreo(){

        //Ontención del correo y la contraseña, junto con sus confirmaciones:
        String correo = null;
        String contrasena = null;


            if (txtCorreo.getText().toString().trim().equals(txtCorreoConfirmacion.getText().toString().trim())) {
                correo = txtCorreoConfirmacion.getText().toString().trim();
                txtCorreoConfirmacion.setTextColor(getResources().getColor(R.color.black));
                //nota: trim lo que hace es buscar el caracter espacio tanto al inicio como al final de la cadena y lo elimina. No elimina los espacios intermedios:
                //https://www.geeksforgeeks.org/java-string-trim-method-example/#:~:text=trim()is%20a%20built,and%20returns%20the%20omitted%20string.
            } else {
                Toast.makeText(this, getResources().getString(R.string.toastCorreosNoCoinciden), Toast.LENGTH_SHORT).show();
                txtCorreoConfirmacion.setTextColor(getResources().getColor(R.color.rojoError));
            }



            if (txtContrasena.getText().toString().trim().equals(txtConfirmarContrasena.getText().toString().trim())) {
                contrasena = txtConfirmarContrasena.getText().toString().trim();
                txtConfirmarContrasena.setTextColor(getResources().getColor(R.color.black));
            }else{
                Toast.makeText(this, getResources().getString(R.string.toastContrasenasNoCoinciden), Toast.LENGTH_SHORT).show();
                txtCorreoConfirmacion.setTextColor(getResources().getColor(R.color.rojoError));

            }


        if(TextUtils.isEmpty(correo)){
            Toast.makeText(this,getResources().getString(R.string.toastFaltaCorreo),Toast.LENGTH_SHORT).show();
            return; //TODO no entiendo este return aqui, creo que es para que continúa la ejecución
        }

        if(TextUtils.isEmpty(contrasena)){
            Toast.makeText(this,getResources().getString(R.string.toastFaltaContra), Toast.LENGTH_SHORT).show();
            return;
        }
        //Mensaje que aparece como barra de prograseo mientras se realiza el proceso de registro:
        progressDialog.setMessage(getResources().getString(R.string.toastRegistrandoUsuario));
        progressDialog.show();

        //Creación del usuario:
        if(correo!= null && contrasena != null) {
            firebaseAuth.createUserWithEmailAndPassword(correo, contrasena).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //Comprobación de que la creación del usuario en la Tabla de Authentication ha sido exitosa:
                    if(task.isSuccessful()){
                        Toast.makeText(Registro.this, getResources().getString(R.string.toastRegistroExitoso),Toast.LENGTH_SHORT).show();
                            //Nota, el contexto tiene que ser Nombre.this, si se pone solo this, salta un error de que ese contexto no puede ser ejecutado o algo así.
                            Intent intent = new Intent(Registro.this, Acceso.class);
                            startActivity(intent);
                            //-----------------------
                        //Esto que se me ha ocurrido, hace que el usuario tenga que poner sus credenciales tras el registro
                            firebaseAuth.signOut();
                            firebaseAuth.removeAuthStateListener(new FirebaseAuth.AuthStateListener() {
                                @Override
                                public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {
                                    firebaseAuth.signOut();
                                }
                            });
                            //_--------
                    }else{
                        Toast.makeText(Registro.this,getResources().getString(R.string.toastRegistroFallido),Toast.LENGTH_SHORT).show();

                    }
                    progressDialog.dismiss(); //Quita el progressDialog de la vista, ta que se entiende que el progreso ha terminado.
                }
            });
        }





    }//Fin método registrarCorreo

}//fin clase Registro