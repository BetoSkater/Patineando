package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InformacionClimatica extends AppCompatActivity {

    private WebView contenedorNavegador;
    private String urlAEMET = "http://www.aemet.es/en/eltiempo/prediccion/municipios/madrid-id28079"; //TODO debería alamcenarlo en el fichero de Strings?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_climatica);

        this.contenedorNavegador = (WebView) findViewById(R.id.wbvClimaMadrid);

        //Llamada al método que muestra la información climática de Madrid de la Web AEMET en el WebView
        irAlEnlace();
    }//Fin onCreate()-------------

    //Función que carga el enlace en el WebView contenedorNavegador:

    public void irAlEnlace(){
        this.contenedorNavegador.loadUrl(urlAEMET); //Carga del url, es decir, que enlace se debe cargar.
        this.contenedorNavegador.setWebViewClient(new MostrarEnLaAplicacion()); //Lugar donde se debe mostrar la web cargada, en este caso, el WebView de la actividad.

    }
    //Clase anidada que evita que se abra por defecto el navegador web del dispositivo del usuario. Esta clase se debe llamar desde el método
// irAlEnlace para que muestra la página web cargada en el WebView y no en el navegador del usuario.

    private class MostrarEnLaAplicacion extends WebViewClient{ //Es private ya que solo deberá ser accesible por la clase InformacionClimatica.java.
        public boolean shouldOverrideUrlLoading(WebView webView, String url){
            webView.loadUrl(url);
            return true;
        }
    }
}