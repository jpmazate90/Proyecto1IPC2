
package Entidades;

import java.sql.Date;
// VARIABLES O ATRIBUTOS DE LA CLASE
public class Etapa {
    private String nombre;
    private String desarrolladorACargo;
    private boolean activo;
    private int tiempoEnHoras;
    private boolean aprobado;
    private Date fechaLimite;
    private int idCaso;
    private int total;
// CONSTRUCTOR DE LA CLASE
    public Etapa( String nombre, String desarrolladorACargo, boolean activo, int tiempoEnHoras, boolean aprobado, Date fechaLimite, int idCaso, int total) {
     
        this.nombre = nombre;
        this.desarrolladorACargo = desarrolladorACargo;
        this.activo = activo;
        this.tiempoEnHoras = tiempoEnHoras;
        this.aprobado = aprobado;
        this.fechaLimite = fechaLimite;
        this.idCaso = idCaso;
        this.total = total;
    }
// SOBRECARGA DEL CONSTRUCTOR
    public Etapa(String nombre, int idCaso) {
        this.nombre = nombre;
        this.idCaso = idCaso;
    }
    
    

// GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }
// GETTERS Y SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
// GETTERS Y SETTERS
    public String getDesarrolladorACargo() {
        return desarrolladorACargo;
    }// GETTERS Y SETTERS

    public void setDesarrolladorACargo(String desarrolladorACargo) {
        this.desarrolladorACargo = desarrolladorACargo;
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
    public int getTiempoEnHoras() {
        return tiempoEnHoras;
    }
// GETTERS Y SETTERS
    public void setTiempoEnHoras(int tiempoEnHoras) {
        this.tiempoEnHoras = tiempoEnHoras;
    }
// GETTERS Y SETTERS
    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
