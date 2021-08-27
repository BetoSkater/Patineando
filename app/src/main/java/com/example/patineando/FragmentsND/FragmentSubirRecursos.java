package com.example.patineando.FragmentsND;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.example.patineando.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSubirRecursos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSubirRecursos extends Fragment {


    private  static final int videoSeleccionado = 1;
    private static final int resultOK = -1; //Esto no se de donde lo saca, pero al ponerlo en el video, pone que el valor es -1.
    VideoView cuadroVideo;
    Button btnSeleccionarVideo, btnSubirVidero;
    ProgressBar barraProgreso;
    EditText txtNombreVideo;
    private Uri videoUri;
    MediaController mediaController;



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
        btnSubirVidero = (Button) vista.findViewById(R.id.btnSubirRecurso);
        txtNombreVideo = (EditText) vista.findViewById(R.id.txtNombreVideo);
        barraProgreso = (ProgressBar) vista.findViewById(R.id.prgBarraProgresoSubirVideo);

        //Asociacion del mediaController al VideoView
        mediaController = new MediaController(vista.getContext());
        cuadroVideo.setMediaController(mediaController);
        cuadroVideo.start();


        //bOTONES
        btnSeleccionarVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarVideo();
                //TODO, poner que cuando se seleccione por segunda vez el boton de seleccionar video, quite el video anterior del VideooView, ya que de lo contrario el segundo video seleccionado sale en negro.
            }
        });
        return vista;


        btnSubirVidero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarVideo()
            }
        });
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


    //Metodo para mostrar el video
    public void mostrarVideo(){

    }//Fin metodo mostrar video
}