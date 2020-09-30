
package Entidades;

import java.sql.Date;
// VARIABLES O ATRIBUTOS DE LA CLASE
public class Caso {
    private int id;
    private String nombre;
    private Date fechaLimite;
    private int idProyecto;
    private int porcentajeAvance;
    private boolean activo;
    private String tipo;
// CONSTRUCTOR DE LA CALSE
    public Caso(int id, String nombre, Date fechaLimite, int idProyecto, int porcentajeAvance, boolean activo, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.fechaLimite = fechaLimite;
        this.idProyecto = idProyecto;
        this.porcentajeAvance = porcentajeAvance;
        this.activo = activo;
        this.tipo = tipo;
    }
// SOBRECARGA DEL CONSTRUCTOR
    public Caso(String nombre, Date fechaLimite, int idProyecto, int porcentajeAvance, boolean activo, String tipo) {
        this.nombre = nombre;
        this.fechaLimite = fechaLimite;
        this.idProyecto = idProyecto;
        this.porcentajeAvance = porcentajeAvance;
        this.activo = activo;
        this.tipo = tipo;
    }
    
// GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(int porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    

}
