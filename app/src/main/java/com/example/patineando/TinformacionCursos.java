package com.example.patineando;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class TinformacionCursos implements Serializable {

    private String idCurso;
    private String urlImagenCurso;
    private String nombreCurso;
    private String dificultadCurso;
    private LocalDate diaClase;
    private LocalTime horaClase;
    private String lugarClase;
    private String nombreProfesor;
    private String apodoProfesor;

    public TinformacionCursos() {
    }

    public TinformacionCursos(String idCurso, String urlImagenCurso, String nombreCurso, String dificultadCurso, LocalDate diaClase, LocalTime horaClase, String lugarClase, String nombreProfesor, String apodoProfesor) {
        this.idCurso = idCurso;
        this.urlImagenCurso = urlImagenCurso;
        this.nombreCurso = nombreCurso;
        this.dificultadCurso = dificultadCurso;
        this.diaClase = diaClase;
        this.horaClase = horaClase;
        this.lugarClase = lugarClase;
        this.nombreProfesor = nombreProfesor;
        this.apodoProfesor = apodoProfesor;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getUrlImagenCurso() {
        return urlImagenCurso;
    }

    public void setUrlImagenCurso(String urlImagenCurso) {
        this.urlImagenCurso = urlImagenCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDificultadCurso() {
        return dificultadCurso;
    }

    public void setDificultadCurso(String dificultadCurso) {
        this.dificultadCurso = dificultadCurso;
    }

    public LocalDate getDiaClase() {
        return diaClase;
    }

    public void setDiaClase(LocalDate diaClase) {
        this.diaClase = diaClase;
    }

    public LocalTime getHoraClase() {
        return horaClase;
    }

    public void setHoraClase(LocalTime horaClase) {
        this.horaClase = horaClase;
    }

    public String getLugarClase() {
        return lugarClase;
    }

    public void setLugarClase(String lugarClase) {
        this.lugarClase = lugarClase;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getApodoProfesor() {
        return apodoProfesor;
    }

    public void setApodoProfesor(String apodoProfesor) {
        this.apodoProfesor = apodoProfesor;
    }
}
