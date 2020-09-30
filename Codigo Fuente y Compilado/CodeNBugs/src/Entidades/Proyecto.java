
package Entidades;

import java.sql.Date;
// VARIABLES O ATRIBUTOS DE LA CLASE
public class Proyecto {
    private int id;
    private String nombre;
    private Date fechaLimite;
    private boolean activo;
    private String administradorProyecto;
// CONSTRUCTOR DE LA CLASE
    public Proyecto(String nombre, Date fechaLimite, boolean activo, String administradorProyecto) {
        this.nombre = nombre;
        this.fechaLimite = fechaLimite;
        this.activo = activo;
        this.administradorProyecto = administradorProyecto;
    }
// SOBRECARGA DEL CONSTRUCTOR
    public Proyecto(int id, String nombre, boolean activo, String administradorProyecto) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.administradorProyecto = administradorProyecto;
    }
    

    // GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }// GETTERS Y SETTERS

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
// GETTERS Y SETTERS
    public Date getFechaLimite() {
        return fechaLimite;
    }
// GETTERS Y SETTERS
    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
// GETTERS Y SETTERS
    public boolean isActivo() {
        return activo;
    }
// GETTERS Y SETTERS
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
// GETTERS Y SETTERS
    public String getAdministradorProyecto() {
        return administradorProyecto;
    }
// GETTERS Y SETTERS
    public void setAdministradorProyecto(String administradorProyecto) {
        this.administradorProyecto = administradorProyecto;
    }
// GETTERS Y SETTERS
    public int getId() {
        return id;
    }
// GETTERS Y SETTERS
    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
