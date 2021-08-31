package com.example.patineando.FragmentsND;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.patineando.R;
import com.example.patineando.TRutas;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentGestionarRutas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGestionarRutas extends Fragment {



    EditText txtNombreRuta, txtLocalizacionRuta;
    Spinner sprDificultad;
    ImageView imgMapaRuta;
    Button btnPublicarMapa;


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

    public FragmentGestionarRutas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentGestionarRutas.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGestionarRutas newInstance(String param1, String param2) {
        FragmentGestionarRutas fragment = new FragmentGestionarRutas();
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
        View vista = inflater.inflate(R.layout.fragment_gestionar_rutas, container, false);

        txtNombreRuta = (EditText) vista.findViewById(R.id.txtNombreRuta);
        txtLocalizacionRuta = (EditText) vista.findViewById(R.id.txtLocalizacionRuta);
        sprDificultad = (Spinner) vista.findViewById(R.id.sprDificultadRuta);
        imgMapaRuta = (ImageView) vista.findViewById(R.id.imvMapaRuta);
        btnPublicarMapa = (Button) vista.findViewById(R.id.btnPublicarRuta);

        //Spinner:
        //Adaptador tipo cursos:
        String [] dificultadRuta = {"Basico", "Medio", "Avanzado", "Experto"};
        ArrayAdapter<String> adaptadorCursos = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, dificultadRuta);
        sprDificultad.setAdapter(adaptadorCursos);

        //Se agrega el selector de imagenes al imageView con un onCLick
        imgMapaRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSelectorMapa();
            }
        });

        btnPublicarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publicarRuta();
            }
        });

        return vista;
    }//Fin onCreateView


    //Metodo para obtener el nivel de dificultad:
    public String obtenerDificultadRuta(){
        String resultado = "";

        resultado =sprDificultad.getSelectedItem().toString();

        return resultado;
    }


    //Metodo para elegir imagen:
    private void abrirSelectorMapa(){
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

            Picasso.get().load(uriImagen).into(imgMapaRuta);

        }

    } //Fin sobreescritura metodo onActivityResult


    //Metodo para obtener la extension:
    private String obtenerExtension(Uri uri){
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }//Fin obtenerExtencion


    //Metodo para subir la imagen a la carpeta de imagenes de usuario del Storage, ademas, se incluye una referencia al storage en la tabla usuarios
    private void publicarRuta(){
        if(uriImagen != null){

            String nombreRuta = txtNombreRuta.getText().toString().trim();
            String localizacionRuta = txtLocalizacionRuta.getText().toString().trim();
            String didicultadRuta = obtenerDificultadRuta();



            //TODO debería ser private, pero no lo permite ya que la clase original es pública. Debo cambiar esto?
            mStorageReference = FirebaseStorage.getInstance().getReference("Rutas"); //TODO igual tengo que cambiarlo a Images
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(); //Nota dentro de este getReference no va localizacion ta que se especifica abajo que se  modificarán los cambos en la tabla de usuarios, para subir ottras cosas si se pondr´`ia aqui



            StorageReference fileReference = mStorageReference.child(localizacionRuta+"_"+nombreRuta+"."+obtenerExtension(uriImagen));

            fileReference.putFile(uriImagen).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    String urlImagen = taskSnapshot.getUploadSessionUri().toString();

                    TRutas auxRuta = new TRutas(nombreRuta,localizacionRuta,didicultadRuta, urlImagen);

                        mDatabaseReference.child("Rutas").push().setValue(auxRuta);

                    Toast.makeText(getContext(),(R.string.toast_ruta_subida_exito), Toast.LENGTH_SHORT).show();

                    txtNombreRuta.setText("");
                    txtLocalizacionRuta.setText("");
                  
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(getContext(),(R.string.toast_fallo_al_subir_el_mapa + ": " + e.getMessage()) , Toast.LENGTH_SHORT).show();

                }
            });


        }else{
            Toast.makeText(getContext(),R.string.toast_no_has_seleccionado_mapa, Toast.LENGTH_SHORT).show();
        }
    }



}