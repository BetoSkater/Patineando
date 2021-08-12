package com.example.patineando;

import java.io.Serializable;

public class AuxAdaptadorGestionPermisos implements Serializable {
    private String idUsuario;
    private String correoUsuario;
    private int imagenUsuario;
    private String tipoUsuario; //Esta es la unica propiedad que no se extrae de la tabla Auth. Por defecto tendrá un valor de "alumno". Esta propiedad podrá ser cambaiada por usuarios superiores para dar permisos de administracion(de la escuela, no administracion de la app y profesorado.
    private String nombreUsuario;
    private String apellidosUsuario;

    public AuxAdaptadorGestionPermisos() {
    }

    public AuxAdaptadorGestionPermisos(String idUsuario, String correoUsuario, int imagenUsuario, String tipoUsuario, String nombreUsuario, String apellidosUsuario) {
        this.idUsuario = idUsuario;
        this.correoUsuario = correoUsuario;
        this.imagenUsuario = imagenUsuario;
        this.tipoUsuario = tipoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public int getImagenUsuario() {
        return imagenUsuario;
    }

    public void setImagenUsuario(int imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }
}
