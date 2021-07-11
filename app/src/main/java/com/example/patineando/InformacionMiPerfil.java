package com.example.patineando;

import java.io.BufferedReader;
import java.io.Serializable;
//CLASE QUE SIRVE DE MOLDE PARA CREAR PERFILES DE ALUMNOS QUE SE VAN A MOSTRAR EN MIPERFIL
public class InformacionMiPerfil implements Serializable {
    private int imagenMiPerfil; //TODO, si la imagen se recupera de la base de datos, seria mejor un tipo String o url?
    private String nombreMiPerfil;
    private String apodoMiPerfil;
    private String patinesMiPerfil;
    private BufferedReader descripcionMiPerfil;

    //Constructores:


    public InformacionMiPerfil() {
    }

    public InformacionMiPerfil(int imagenMiPerfil, String nombreMiPerfil, String apodoMiPerfil, String patinesMiPerfil, BufferedReader descripcionMiPerfil) {
        this.imagenMiPerfil = imagenMiPerfil;
        this.nombreMiPerfil = nombreMiPerfil;
        this.apodoMiPerfil = apodoMiPerfil;
        this.patinesMiPerfil = patinesMiPerfil;
        this.descripcionMiPerfil = descripcionMiPerfil;
    }

    public int getImagenMiPerfil() {
        return imagenMiPerfil;
    }

    public void setImagenMiPerfil(int imagenMiPerfil) {
        this.imagenMiPerfil = imagenMiPerfil;
    }

    public String getNombreMiPerfil() {
        return nombreMiPerfil;
    }

    public void setNombreMiPerfil(String nombreMiPerfil) {
        this.nombreMiPerfil = nombreMiPerfil;
    }

    public String getApodoMiPerfil() {
        return apodoMiPerfil;
    }

    public void setApodoMiPerfil(String apodoMiPerfil) {
        this.apodoMiPerfil = apodoMiPerfil;
    }

    public String getPatinesMiPerfil() {
        return patinesMiPerfil;
    }

    public void setPatinesMiPerfil(String patinesMiPerfil) {
        this.patinesMiPerfil = patinesMiPerfil;
    }

    public BufferedReader getDescripcionMiPerfil() {
        return descripcionMiPerfil;
    }

    public void setDescripcionMiPerfil(BufferedReader descripcionMiPerfil) {
        this.descripcionMiPerfil = descripcionMiPerfil;
    }



}
