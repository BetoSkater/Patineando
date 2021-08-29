package com.example.patineando;
//https://www.youtube.com/watch?v=s27S6H1bM0w
public class TVideos {
    //Clase que sirve de molde para la creación de la tabla de los videos:
    private String nombreVideo;
    private String enlaceVideo;
    private String disciplinaVideo;
    private String nivelVideo;
    private String tipoRecurso;
    private String criterioBusqueda;
    public TVideos() {
    }

    public TVideos(String nombreVideo, String enlaceVideo, String disciplinaVideo, String nivelVideo, String tipoRecurso, String criterioBusqueda ) {
        this.nombreVideo = nombreVideo;
        this.enlaceVideo = enlaceVideo;
        this.disciplinaVideo = disciplinaVideo;
        this.nivelVideo = nivelVideo;
        this.tipoRecurso = tipoRecurso;
        this.criterioBusqueda = criterioBusqueda;
    }


    public String getNombreVideo() {
        return nombreVideo;
    }

    public void setNombreVideo(String nombreVideo) {
        this.nombreVideo = nombreVideo;
    }

    public String getEnlaceVideo() {
        return enlaceVideo;
    }

    public void setEnlaceVideo(String enlaceVideo) {
        this.enlaceVideo = enlaceVideo;
    }

    public String getDisciplinaVideo() {
        return disciplinaVideo;
    }

    public void setDisciplinaVideo(String disciplinaVideo) {
        this.disciplinaVideo = disciplinaVideo;
    }

    public String getNivelVideo() {
        return nivelVideo;
    }

    public void setNivelVideo(String nivelVideo) {
        this.nivelVideo = nivelVideo;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }


    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }
}
