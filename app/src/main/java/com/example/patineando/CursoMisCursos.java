package com.example.patineando;

import java.io.Serializable;


//TODO desfasado, no se utiliza, se tiene que eliminar, la buena es TCursoPublicado
public class CursoMisCursos implements Serializable {
    private int imagenMiCurso;
    private String nombreMiCurso; //TODO poner el private en todo para encapsularlo!!!
    private String nivelMiCurso;
    private String diaMiCurso; //TODO lo pongo como String LocalDate
    private String horaMiCurso; //TODO de momento lo pongo como string, estaba como LocalTime
    private String localizacionMiCurso;
    private String profesorMiCurso;
    private String apodoProfesorMiCurso;

    //Constructor vacío
    public CursoMisCursos() {
    }

//Constructor con parámetros:


    public CursoMisCursos(int imagenMiCurso, String nombreMiCurso, String nivelMiCurso, String diaMiCurso, String horaMiCurso, String localizacionMiCurso, String profesorMiCurso, String apodoProfesorMiCurso) {
        this.imagenMiCurso = imagenMiCurso;
        this.nombreMiCurso = nombreMiCurso;
        this.nivelMiCurso = nivelMiCurso;
        this.diaMiCurso = diaMiCurso;
        this.horaMiCurso = horaMiCurso;
        this.localizacionMiCurso = localizacionMiCurso;
        this.profesorMiCurso = profesorMiCurso;
        this.apodoProfesorMiCurso = apodoProfesorMiCurso;
    }

    //Getter y setter para poder hablar de propiedades y bi de variables


    public int getImagenMiCurso() {
        return imagenMiCurso;
    }

    public void setImagenMiCurso(int imagenMiCurso) {
        this.imagenMiCurso = imagenMiCurso;
    }

    public String getNombreMiCurso() {
        return nombreMiCurso;
    }

    public void setNombreMiCurso(String nombreMiCurso) {
        this.nombreMiCurso = nombreMiCurso;
    }

    public String getNivelMiCurso() {
        return nivelMiCurso;
    }

    public void setNivelMiCurso(String nivelMiCurso) {
        this.nivelMiCurso = nivelMiCurso;
    }

    public String getDiaMiCurso() {
        return diaMiCurso;
    }

    public void setDiaMiCurso(String diaMiCurso) {
        this.diaMiCurso = diaMiCurso;
    }

    public String getHoraMiCurso() {
        return horaMiCurso;
    }

    public void setHoraMiCurso(String horaMiCurso) {
        this.horaMiCurso = horaMiCurso;
    }

    public String getLocalizacionMiCurso() {
        return localizacionMiCurso;
    }

    public void setLocalizacionMiCurso(String localizacionMiCurso) {
        this.localizacionMiCurso = localizacionMiCurso;
    }

    public String getProfesorMiCurso() {
        return profesorMiCurso;
    }

    public void setProfesorMiCurso(String profesorMiCurso) {
        this.profesorMiCurso = profesorMiCurso;
    }

    public String getApodoProfesorMiCurso() {
        return apodoProfesorMiCurso;
    }

    public void setApodoProfesorMiCurso(String apodoProfesorMiCurso) {
        this.apodoProfesorMiCurso = apodoProfesorMiCurso;
    }
}
