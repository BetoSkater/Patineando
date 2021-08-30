package com.example.patineando.FragmentsND;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.patineando.R;
import com.example.patineando.Tusuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentModificarMiPerfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentModificarMiPerfil extends Fragment {

    TextView txtNombreMod, txtApellidoMod, txtApodoMod, txtPatinesMod, txtDescripcionMod;
    Button btnGuardarCambios;
    ImageView imgImagenMod;
    private FirebaseAuth mAuth;

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int RESULT_OK = -1; //Esto lo pongo yo porque si no no tiene sentido
    private Uri uriImagen;


     StorageReference mStorageReference;
     DatabaseReference mDatabaseReference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentModificarMiPerfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentModificarMiPerfil.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentModificarMiPerfil newInstance(String param1, String param2) {
        FragmentModificarMiPerfil fragment = new FragmentModificarMiPerfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View vista = inflater.inflate(R.layout.fragment_modificar_mi_perfil, container, false);
        mAuth = FirebaseAuth.getInstance(); //Inicialización de la instancia de FirebaseAuth


        imgImagenMod = (ImageView) vista.findViewById(R.id.imgImagenModificarMiPerfilND);
        txtNombreMod = (TextView) vista.findViewById(R.id.txtNombreModificarMiPerfilND);
        txtApellidoMod = (TextView) vista.findViewById(R.id.txtApellidosModificarMiPerfilND);
        txtApodoMod = (TextView) vista.findViewById(R.id.txtApodoModificarMiPerfilND);
        txtPatinesMod = (TextView) vista.findViewById(R.id.txtPatinesModificarMiPerfilND);
        txtDescripcionMod = (TextView) vista.findViewById(R.id.txtDescripcionModificarMiPerfilND);

        cargarDatosUsuario();


        btnGuardarCambios = (Button) vista.findViewById(R.id.btnGuardarCambiosMiPerfilND);
        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //guardarCambios();
                aceptarCambios();
            }
        });


        //Para seleccionar una imagen, se tiene que hacer click en el imageView:
        imgImagenMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirSelectorImagen();
            }
        });



       return vista;
    }//Fin onCreateView

    //Metodo para obtener los valores actuales de la base de datos para que sean visibles por el usuario.
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
                    String imagenRecuperada = String.valueOf(task.getResult().child("imagenUsuario").getValue());
                    Picasso.get().load(imagenRecuperada).into(imgImagenMod);

                    String nombreRecuperado = String.valueOf(task.getResult().child("nombreUsuario").getValue());
                    txtNombreMod.setText(nombreRecuperado);

                    String apellidosRecuperados = String.valueOf(task.getResult().child("apellidosUsuario").getValue());
                    txtApellidoMod.setText(apellidosRecuperados);

                    String apodoRecuperado = String.valueOf(task.getResult().child("apodoUsuario").getValue());
                    txtApodoMod.setText(apodoRecuperado);

                    String patinesRecuperado = String.valueOf(task.getResult().child("patinesUsuario").getValue());
                    txtPatinesMod.setText(patinesRecuperado);

                    String descripcionRecuperado = String.valueOf(task.getResult().child("descripcionUsuario").getValue());
                    txtDescripcionMod.setText(descripcionRecuperado);

                }else{
                    Log.e(getResources().getString(R.string.logFirebase),getResources().getString(R.string.logErrorObtencionDatos),task.getException());
                }
            }
        });
    }
/*
    //Metodo para guardar los cambios en la tabla usuarios
    public void guardarCambios(){
        DatabaseReference mDatabase; //TODO debería ser private, pero no lo permite ya que la clase original es pública. Debo cambiar esto?
        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser usuario = mAuth.getCurrentUser();


        Tusuario tUsuario = new Tusuario();
       // String urlImagen = "@drawable/ic_menu_sun.png"; //TODO HARDCODED, poner bien cuando se haga la funcionalidad de seleccionar imagenes.
        //pd, no es un url, es una referencia a una foto tomada o sacada de la galeria del dispositivo del alumno
        String nombre = txtNombreMod.getText().toString();
        String apellidos = txtApellidoMod.getText().toString();
        String apodo = txtApodoMod.getText().toString();
        String patines = txtPatinesMod.getText().toString();
        String descripcion = txtDescripcionMod.getText().toString(); //Todo Esto tiene que admitir mas de 255 caracteres ¿No entiendo como hacerlo con BufferedReader/Writter

        tUsuario.setNombreUsuario(nombre);
        tUsuario.setApellidosUsuario(apellidos);
        tUsuario.setApodoUsuario(apodo);
        tUsuario.setPatinesUsuario(patines);
        tUsuario.setDescripcionUsuario(descripcion);
        //TODO falta por poner lo de la imagen



       // mDatabase.child("Usuarios").child(usuario.getUid()).child("imagenUsuario").setValue(urlImagen);
        mDatabase.child("Usuarios").child(usuario.getUid()).child("nombreUsuario").setValue(nombre);
        mDatabase.child("Usuarios").child(usuario.getUid()).child("apellidosUsuario").setValue(apellidos);
        mDatabase.child("Usuarios").child(usuario.getUid()).child("apodoUsuario").setValue(apodo);
        mDatabase.child("Usuarios").child(usuario.getUid()).child("patinesUsuario").setValue(patines);
        mDatabase.child("Usuarios").child(usuario.getUid()).child("descripcionUsuario").setValue(descripcion);
    }//Fin metodo guardarCambios()


 */

    //Metodo para elegir imagen:
    private void abrirSelectorImagen(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
    //Se tiene que sobreescribir el metodo onActivityResult ( ctrl + o) opara abrir el panel de metos a sobreescribir:

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST || requestCode == RESULT_OK || data !=null || data.getData() != null){

            uriImagen = data.getData();

            Picasso.get().load(uriImagen).into(imgImagenMod);

        }

    } //Fin sobreescritura metodo onActivityResult


    //Metodo para obtener la extension:
    private String obtenerExtension(Uri uri){
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }//Fin obtenerExtencion


    //Metodo para subir la imagen a la carpeta de imagenes de usuario del Storage, ademas, se incluye una referencia al storage en la tabla usuarios
    private void aceptarCambios(){
        if(uriImagen != null){

             //TODO debería ser private, pero no lo permite ya que la clase original es pública. Debo cambiar esto?
            mStorageReference = FirebaseStorage.getInstance().getReference("Imagenes"); //TODO igual tengo que cambiarlo a Images
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(); //Nota dentro de este getReference no va localizacion ta que se especifica abajo que se  modificarán los cambos en la tabla de usuarios, para subir ottras cosas si se pondr´`ia aqui

            //Obtencion del id del usuario actual para nombrar como tal a ala imagen
            mAuth = FirebaseAuth.getInstance(); //Creo que aqui no haria falta;
            FirebaseUser usuario = mAuth.getCurrentUser();
            String idFicheroStorage =usuario.getUid();

            StorageReference fileReference = mStorageReference.child(idFicheroStorage+"."+obtenerExtension(uriImagen));

            fileReference.putFile(uriImagen).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    String urlImagen = taskSnapshot.getUploadSessionUri().toString();


                     //TODO HARDCODED, poner bien cuando se haga la funcionalidad de seleccionar imagenes.
                    //pd, no es un url, es una referencia a una foto tomada o sacada de la galeria del dispositivo del alumno
                    String nombre = txtNombreMod.getText().toString();
                    String apellidos = txtApellidoMod.getText().toString();
                    String apodo = txtApodoMod.getText().toString();
                    String patines = txtPatinesMod.getText().toString();
                    String descripcion = txtDescripcionMod.getText().toString(); //Todo Esto tiene que admitir mas de 255 caracteres ¿No entiendo como hacerlo con BufferedReader/Writter
/*                  //No se puede volver a introducir el objeto ya que //TODO machaca todos los datos, se debe actualizar campo a campo para respetar el resto de valores.
                    //Tusuario tUsuario = new Tusuario();
                    tUsuario.setNombreUsuario(nombre);
                    tUsuario.setApellidosUsuario(apellidos);
                    tUsuario.setApodoUsuario(apodo);
                    tUsuario.setPatinesUsuario(patines);
                    tUsuario.setDescripcionUsuario(descripcion);
                    //TODO falta por poner lo de la imagen

 */



                    mDatabaseReference.child("Usuarios").child(usuario.getUid()).child("imagenUsuario").setValue(urlImagen);
                    mDatabaseReference.child("Usuarios").child(usuario.getUid()).child("nombreUsuario").setValue(nombre);
                    mDatabaseReference.child("Usuarios").child(usuario.getUid()).child("apellidosUsuario").setValue(apellidos);
                    mDatabaseReference.child("Usuarios").child(usuario.getUid()).child("apodoUsuario").setValue(apodo);
                    mDatabaseReference.child("Usuarios").child(usuario.getUid()).child("patinesUsuario").setValue(patines);
                    mDatabaseReference.child("Usuarios").child(usuario.getUid()).child("descripcionUsuario").setValue(descripcion);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(getContext(),(R.string.toast_fallo_al_subir_imagen + ": " + e.getMessage()) , Toast.LENGTH_SHORT).show();

                }
            });


        }else{
            Toast.makeText(getContext(),R.string.toast_no_has_seleccionado_imagen, Toast.LENGTH_SHORT).show();
        }
    }




}