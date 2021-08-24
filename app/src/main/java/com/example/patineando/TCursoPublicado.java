package com.example.patineando;

import java.io.Serializable;

public class TCursoPublicado implements Serializable {

        private String idCurso;
        private int imagenCurso;
        private String tipoCurso;
        private String nivelDificultad;
        private String diaClase;
        private String horaClase;
        private String localizacion;
        private String profesor;


    public TCursoPublicado() {
    }

    public TCursoPublicado(String idCurso, int imagenCurso, String tipoCurso, String nivelDificultad, String diaClase, String horaClase, String localizacion, String profesor) {
        this.idCurso = idCurso;
        this.imagenCurso = imagenCurso;
        this.tipoCurso = tipoCurso;
        this.nivelDificultad = nivelDificultad;
        this.diaClase = diaClase;
        this.horaClase = horaClase;
        this.localizacion = localizacion;
        this.profesor = profesor;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public int getImagenCurso() {
        return imagenCurso;
    }

    public void setImagenCurso(int imagenCurso) {
        this.imagenCurso = imagenCurso;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public String getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public String getDiaClase() {
        return diaClase;
    }

    public void setDiaClase(String diaClase) {
        this.diaClase = diaClase;
    }

    public String getHoraClase() {
        return horaClase;
    }

    public void setHoraClase(String horaClase) {
        this.horaClase = horaClase;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
