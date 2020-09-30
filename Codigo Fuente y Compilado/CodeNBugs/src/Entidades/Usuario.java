
package Entidades;

import java.sql.Date;
// CONSTRUCTOR DE LA CLASE
public class Usuario {
    private String usuario;
    private String contraseña;
    private String nombre;
    private String apellido;
    private String dpi;
    private Date fechaIngreso;
    private int dineroHora;
    private String tipoUsuario;
// CONSTRUCTOR DE LA CLASE
    public Usuario(String usuario, String contraseña, String nombre, String apellido, String dpi, Date fechaIngreso, int dineroHora, String tipoUsuario) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dpi = dpi;
        this.fechaIngreso = fechaIngreso;
        this.dineroHora = dineroHora;
        this.tipoUsuario = tipoUsuario;
    }
// GETTERS Y SETTERS
    public String getUsuario() {
        return usuario;
    }
// GETTERS Y SETTERS
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
// GETTERS Y SETTERS
    public String getContraseña() {
        return contraseña;
    }
// GETTERS Y SETTERS
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
// GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }// GETTERS Y SETTERS

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }// GETTERS Y SETTERS

    public String getApellido() {
        return apellido;
    }// GETTERS Y SETTERS

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }// GETTERS Y SETTERS

    public String getDpi() {
        return dpi;
    }
// GETTERS Y SETTERS
    public void setDpi(String dpi) {
        this.dpi = dpi;
    }
// GETTERS Y SETTERS
    public Date getFechaIngreso() {
        return fechaIngreso;
    }// GETTERS Y SETTERS

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
// GETTERS Y SETTERS
    public int getDineroHora() {
        return dineroHora;
    }// GETTERS Y SETTERS

    public void setDineroHora(int dineroHora) {
        this.dineroHora = dineroHora;
    }
// GETTERS Y SETTERS
    public String getTipoUsuario() {
        return tipoUsuario;
    }// GETTERS Y SETTERS

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    
}
