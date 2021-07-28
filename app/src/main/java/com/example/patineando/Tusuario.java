package com.example.patineando;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Tusuario implements Serializable {
    //Nota: Desconozco si es necesario que las clases que van a permitir crear los objetos que subir a la Base de Datos deben o no implementar Serializable:

    //Propiedades que no pueden ser nulas (practicamente todas se extraen de la tabla Authentication de Firebase).
    private String idUsuario;
    private String correoUsuario;
    private LocalDateTime fechaCreacionUsuario;
    private String tipoUsuario; //Esta es la unica propiedad que no se extrae de la tabla Auth. Por defecto tendrá un valor de "alumno". Esta propiedad podrá ser cambaiada por usuarios superiores para dar permisos de administracion(de la escuela, no administracion de la app y profesorado.
    private boolean matriculaActivaUsuario; //Esta tampoco se extrae de la tabla Auth. Cuando se registra un usuario, obviamente no está matriculado de nada, por lo que por defecto en la creación tomará el valor de "false".

    //Propiedades que a la hora de crear el registro en la tabla Usuarios serán nulas, pudiendo ser modificadas por los usuarios desde su perfil:
    //Estas propiedades son las que se muestran en el perfil del alumno (//TODO; los perfiles de los alumnos solo son accesibles por profesores y administradores). Los perfiles de los profesores son accesibles por los alumnos.

    private String nombreUsuario;
    private String apellidosUsuario;
    private String apodoUsuario;
    private String patinesUsuario;
    private String imagenUsuario; //Nota: igual tiene que ser int, pero como será un enlace a Cloud FireStore
    private String descripcionUsuario; //TODO esto no puede ser String, tiene que ser algo mas grande pero ahora mismo no se me ocurre.

    //Contructores

    //El constructor vacio siempre viene bien tenerlo, además en este caso es necesario para poder realizar llamadas a DataSnapshot.getValue(Tusuario.class)
    public Tusuario() {
    }

    public Tusuario(String idUsuario, String correoUsuario, LocalDateTime fechaCreacionUsuario, String tipoUsuario, boolean matriculaActivaUsuario, String nombreUsuario, String apellidosUsuario, String apodoUsuario, String patinesUsuario, String imagenUsuario, String descripcionUsuario) {
        this.idUsuario = idUsuario;
        this.correoUsuario = correoUsuario;
        this.fechaCreacionUsuario = fechaCreacionUsuario;
        this.tipoUsuario = tipoUsuario;
        this.matriculaActivaUsuario = matriculaActivaUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.apodoUsuario = apodoUsuario;
        this.patinesUsuario = patinesUsuario;
        this.imagenUsuario = imagenUsuario;
        this.descripcionUsuario = descripcionUsuario;
    }

    //Getters y Setters


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

    public LocalDateTime getFechaCreacionUsuario() {
        return fechaCreacionUsuario;
    }

    public void setFechaCreacionUsuario(LocalDateTime fechaCreacionUsuario) {
        this.fechaCreacionUsuario = fechaCreacionUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isMatriculaActivaUsuario() {
        return matriculaActivaUsuario;
    }

    public void setMatriculaActivaUsuario(boolean matriculaActivaUsuario) {
        this.matriculaActivaUsuario = matriculaActivaUsuario;
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

    public String getApodoUsuario() {
        return apodoUsuario;
    }

    public void setApodoUsuario(String apodoUsuario) {
        this.apodoUsuario = apodoUsuario;
    }

    public String getPatinesUsuario() {
        return patinesUsuario;
    }

    public void setPatinesUsuario(String patinesUsuario) {
        this.patinesUsuario = patinesUsuario;
    }

    public String getImagenUsuario() {
        return imagenUsuario;
    }

    public void setImagenUsuario(String imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }

    public String getDescripcionUsuario() {
        return descripcionUsuario;
    }

    public void setDescripcionUsuario(String descripcionUsuario) {
        this.descripcionUsuario = descripcionUsuario;
    }

    //Sobreescritura del método toString para poder leer de forma sencilla los objetos durante la programación del aplicativo:


    @Override
    public String toString() {
        return "Objeto con información del "+ tipoUsuario + ": " + nombreUsuario + " " +apellidosUsuario;

    }
}
