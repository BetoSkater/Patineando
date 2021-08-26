package com.example.patineando;

public class TCursoUsuario {
    private String idCurso;
    private String idUsuario;
    private long fechaMatricula;


    public TCursoUsuario() {
    }

    public TCursoUsuario(String idCurso, String idUsuario, long fechaMatricula) {
        this.idCurso = idCurso;
        this.idUsuario = idUsuario;
        this.fechaMatricula = fechaMatricula;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(long fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }
}
