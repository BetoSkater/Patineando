package com.example.patineando;

import java.io.Serializable;

public class TRutas implements Serializable {
    private String nombreRuta;
    private String localizacionRuta;
    private String dificultadRuta;
    private String uriMapa;


    public TRutas() {
    }

    public TRutas(String nombreRuta, String localizacionRuta, String dificultadRuta, String uriMapa) {
        this.nombreRuta = nombreRuta;
        this.localizacionRuta = localizacionRuta;
        this.dificultadRuta = dificultadRuta;
        this.uriMapa = uriMapa;
    }


    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getLocalizacionRuta() {
        return localizacionRuta;
    }

    public void setLocalizacionRuta(String localizacionRuta) {
        this.localizacionRuta = localizacionRuta;
    }

    public String getDificultadRuta() {
        return dificultadRuta;
    }

    public void setDificultadRuta(String dificultadRuta) {
        this.dificultadRuta = dificultadRuta;
    }

    public String getUriMapa() {
        return uriMapa;
    }

    public void setUriMapa(String uriMapa) {
        this.uriMapa = uriMapa;
    }
}
