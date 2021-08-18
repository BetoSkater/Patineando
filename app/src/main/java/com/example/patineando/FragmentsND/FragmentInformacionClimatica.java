package com.example.patineando.FragmentsND;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.patineando.InformacionClimatica;
import com.example.patineando.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInformacionClimatica#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInformacionClimatica extends Fragment {

    private WebView contenedorNavegador;
    private String urlAEMET = "http://www.aemet.es/en/eltiempo/prediccion/municipios/madrid-id28079";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentInformacionClimatica() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentInformacionClimatica.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentInformacionClimatica newInstance(String param1, String param2) {
        FragmentInformacionClimatica fragment = new FragmentInformacionClimatica();
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
        View vista = inflater.inflate(R.layout.fragment_informacion_climatica, container, false);
        this.contenedorNavegador = (WebView) vista.findViewById(R.id.wbvClimaMadridND);

        //Llamada al método que muestra la información climática de Madrid de la Web AEMET en el WebView
        irAlEnlace();

        return vista;
    }//Fin onCreateView

    //Función que carga el enlace en el WebView contenedorNavegador:

    public void irAlEnlace(){
        this.contenedorNavegador.loadUrl(urlAEMET); //Carga del url, es decir, que enlace se debe cargar.
        this.contenedorNavegador.setWebViewClient(new MostrarEnLaAplicacion()); //Lugar donde se debe mostrar la web cargada, en este caso, el WebView de la actividad.
            //TODO en la actividad la linea de arriba estaba como :
    }
    //Clase anidada que evita que se abra por defecto el navegador web del dispositivo del usuario. Esta clase se debe llamar desde el método
// irAlEnlace para que muestra la página web cargada en el WebView y no en el navegador del usuario.

    private class MostrarEnLaAplicacion extends WebViewClient { //Es private ya que solo deberá ser accesible por la clase InformacionClimatica.java.
        public boolean shouldOverrideUrlLoading(WebView webView, String url){
            webView.loadUrl(url);
            return true;
        }
    }




}