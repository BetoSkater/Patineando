package com.example.patineando.FragmentsND;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.example.patineando.R;
import com.example.patineando.TVideos;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSubirRecursos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSubirRecursos extends Fragment {


    private  static final int videoSeleccionado = 1;
    private static final int resultOK = -1; //Esto no se de donde lo saca, pero al ponerlo en el video, pone que el valor es -1.
    VideoView cuadroVideo;
    Button btnSeleccionarVideo, btnSubirVideo;
    ProgressBar barraProgreso;
    EditText txtNombreVideo;
    private Uri videoUri;
    MediaController mediaController;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    TVideos auxVideo;
    UploadTask uploadTask;
    RadioGroup grupoTipoDocumento;
    RadioButton radioImagen, radioVideo, radioPDF;
    Spinner spinnerDisciplina, spinnerNivel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentSubirRecursos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSubirRecursos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSubirRecursos newInstance(String param1, String param2) {
        FragmentSubirRecursos fragment = new FragmentSubirRecursos();
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
        View vista =  inflater.inflate(R.layout.fragment_subir_recursos, container, false);

        cuadroVideo = (VideoView) vista.findViewById(R.id.vdvVistaVideo);
        btnSeleccionarVideo = (Button) vista.findViewById(R.id.btnSeleccionarVideo);
        btnSubirVideo = (Button) vista.findViewById(R.id.btnSubirRecurso);
        txtNombreVideo = (EditText) vista.findViewById(R.id.txtNombreVideo);
        barraProgreso = (ProgressBar) vista.findViewById(R.id.prgBarraProgresoSubirVideo);

        grupoTipoDocumento = (RadioGroup) vista.findViewById(R.id.rdgTipoDocumento);
        radioImagen = (RadioButton) vista.findViewById(R.id.rdbImagen);
        radioPDF = (RadioButton) vista.findViewById(R.id.rdbPDF);
        radioVideo = (RadioButton) vista.findViewById(R.id.rdbVideo);

        spinnerDisciplina = (Spinner) vista.findViewById(R.id.sprDisciplinaRecursos);
        spinnerNivel = (Spinner) vista.findViewById(R.id.sprNivelRecursos);


        //Asociacion del mediaController al VideoView
        mediaController = new MediaController(vista.getContext());
        cuadroVideo.setMediaController(mediaController);
        cuadroVideo.start();

        //Creacion del objeto que se insertará en la tabla de Firebase RealtimeDAtabase normal (no el storage)
        auxVideo = new TVideos();

        //Obtencion de la referencia del Storage de Firebase

        storageReference = FirebaseStorage.getInstance().getReference("Video");
        databaseReference = FirebaseDatabase.getInstance().getReference("Video");//Llamo a ambas tablas igual, ya que estan en bases de datos distintas?


        //Spinners:
        //Adaptador tipo cursos:
        String [] disciplinas = {"Agresivo", "Artistico", "Baile", "Tecnica", "Hockey", "Slalom", "Velocidad", "Otros"};
        ArrayAdapter<String> adaptadorDisciplinas = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, disciplinas);
        spinnerDisciplina.setAdapter(adaptadorDisciplinas);

        //Adaptador niveles:
        String [] niveles ={"Basico","Medio","Avanzado","Experto"};
        ArrayAdapter<String> adaptadorNiveles = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, niveles);
        spinnerNivel.setAdapter(adaptadorNiveles);

        //Se añade un onClickListener ene l radioGrupo, si lo pulsan, se ha marcado una opvion

        //bOTONES
        btnSeleccionarVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarVideo();
                //TODO, poner que cuando se seleccione por segunda vez el boton de seleccionar video, quite el video anterior del VideooView, ya que de lo contrario el segundo video seleccionado sale en negro.
            }
        });


        btnSubirVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                String disciplina = obtenerDisciplina();
                String nivel = obtenerNivel();
                String tipoDoc = obtenerTipoDocumento();
                String nombre = txtNombreVideo.getText().toString().trim();
                String criterioBusqueda = disciplina+nivel;
                String mensaje =  (getText(R.string.alert_mensaje_nombre) + " " + nombre + "\n" +
                                     getText(R.string.alert_mensaje_disciplina) + " " + disciplina +"\n"+
                                     getText(R.string.alert_mensaje_nivel) + " " + nivel + "\n"+
                                     getText(R.string.alert_mensaje_tipo) + " " + tipoDoc +"\n" +
                                    getString(R.string.alert_mensaje_criteriobusqueda) + " " + criterioBusqueda) ;
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.alert_confirmar_datos_subida)
                        .setMessage(mensaje)
                        .setPositiveButton(R.string.alert_confirmar, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                subirVideo();
                            }

                        })
                        .setNegativeButton(R.string.alert_cancelar, null)
                        .show();




            }
        });





        return vista;
    }//Fin onCreateView



    //Sobreescritura del metodo onActivityResult, asi cuando el navigatorDrawer obtiene la opcion del video seleccionado, lo reproduce en el VideoView


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == videoSeleccionado || resultCode == resultOK || data != null || data.getData() != null){
            videoUri = data.getData();

            cuadroVideo.setVideoURI(videoUri);
        }
    }

    //Metodo para seleccionar un video de la galeria:
    public void seleccionarVideo(){

        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, videoSeleccionado);


    }//Fin seleccionarVideo

    //Metodo para obtener la extension del fichero (Ext): explicar mas
           // Esto se usa en el metodo subir video, se dice que el nombre del fichero en el Storage sea igual al tiempo de la subida en milisegundos (1970 o cuando fuese) punto y la extension del video
    private String getExt(Uri uri){
        ContentResolver contentResolver = getActivity().getContentResolver(); //https://stackoverflow.com/questions/20874427/how-to-access-contentresolver-of-a-fragment-in-a-different-activity
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }




    //Metodo para mostrar el video
    public void mostrarVideo(){

    }//Fin metodo mostrar video


    //Metodo para subir el video:
    private void subirVideo(){

        String nombreVideo = txtNombreVideo.getText().toString().trim();
        String tipoDisciplina = obtenerDisciplina();
        String nivelDificultad = obtenerNivel();
        String tipoDocumento = obtenerTipoDocumento();
        String criterioBusqueda = tipoDisciplina+nivelDificultad;
        if(videoUri != null || !TextUtils.isEmpty(nombreVideo) ){ //TODO Poner que todos los parametros tienen que ser =/= de null

            barraProgreso.setVisibility(View.VISIBLE);
            final StorageReference reference = storageReference.child(System.currentTimeMillis() +"."+ getExt(videoUri));
            uploadTask = reference.putFile(videoUri);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull @NotNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                   // return null;
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return reference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<Uri> task) {

                    if(task.isSuccessful()) {

                        Uri downloadUrl = task.getResult();
                        barraProgreso.setVisibility(View.INVISIBLE);
                        Toast.makeText(getContext(),R.string.toast_video_subido_exito, Toast.LENGTH_SHORT).show();

                        auxVideo.setNombreVideo(nombreVideo);
                        auxVideo.setDisciplinaVideo(tipoDisciplina);
                        auxVideo.setNivelVideo(nivelDificultad);
                        auxVideo.setTipoRecurso(tipoDocumento);
                        auxVideo.setEnlaceVideo(downloadUrl.toString());
                        auxVideo.setCriterioBusqueda(criterioBusqueda);
                        //El que se va a introducir en la tabla video esta puesto en la primera referencia del onCreateView. Aqui se indica que se crea un nuevo hijo en
                        //dicha tabla y se obtiene el id autogenerado, seguidamente bajo ese id se introduce el objeto Video.

                        String idTablaVideo = databaseReference.push().getKey();
                        databaseReference.child(idTablaVideo).setValue(auxVideo);
                    }else{
                        Toast.makeText(getContext(), R.string.toast_video_subido_fallo, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(getContext(), R.string.toast_video_subido_faltan_campos, Toast.LENGTH_SHORT).show();
            //grupoTipoDocumento
            //TODO hacer algo con los spinners y radios, pero en teoria por defecto tienen un campo marcado

            txtNombreVideo.setError(getResources().getString(R.string.txt_error_video_sin_nombre));
        }
    }//Fin metodo subirVideo


    //Metodo para obtener el tipo de documento:

    public String obtenerTipoDocumento(){
        String resultado ="";

        int opcionRadioSeleccionada = grupoTipoDocumento.getCheckedRadioButtonId();
        switch (opcionRadioSeleccionada){
            case R.id.rdbImagen:
                resultado = "Imagen";
                break;
            case R.id.rdbPDF:
                resultado ="PDF";
                break;
            case R.id.rdbVideo:
                resultado ="Video";
                break;

        }

        if(resultado.equals("")){
            grupoTipoDocumento.setBackgroundColor(getResources().getColor(R.color.rojoError));
        }else{
            grupoTipoDocumento.setBackgroundColor(getResources().getColor(R.color.white));
        }

        return resultado;
    }//Fin metodo obtenerTipoDocumento
    //Metodo para obtener la disciplina

    public String obtenerDisciplina(){
        String resultado = spinnerDisciplina.getSelectedItem().toString().trim();
        return resultado;
    }//Fin metodo ObtenerDisciplina:
    //Metodo para obtener el nivel de dificultad asociado al video
    public String obtenerNivel(){
        return spinnerNivel.getSelectedItem().toString().trim();
    }
    // }
}