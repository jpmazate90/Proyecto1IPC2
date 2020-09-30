
package Entidades;

public class Accion {
    // VARIABLES O ATRIBUTOS DE LA CLASE
    private int id;
    private String nombreUsuario;
    private int idProyecto;
    private int idCaso;
    private int idEtapa;
    private String faseProyecto;
    private String tipoAccion;
    private String comentario;
    private boolean aprobado;
// constructor 
    public Accion(String nombreUsuario, int idProyecto, int idCaso, int idEtapa, String faseProyecto, String tipoAccion, String comentario, boolean aprobado) {
        this.nombreUsuario = nombreUsuario;
        this.idProyecto = idProyecto;
        this.idCaso = idCaso;
        this.idEtapa = idEtapa;
        this.faseProyecto = faseProyecto;
        this.tipoAccion = tipoAccion;
        this.comentario = comentario;
        this.aprobado = aprobado;
    }
// sobrecarga de constructor
    public Accion(String nombreUsuario, int idProyecto, int idCaso, String faseProyecto, String tipoAccion, String comentario, boolean aprobado) {
        this.nombreUsuario = nombreUsuario;
        this.idProyecto = idProyecto;
        this.idCaso = idCaso;
        this.faseProyecto = faseProyecto;
        this.tipoAccion = tipoAccion;
        this.comentario = comentario;
        this.aprobado = aprobado;
    }
    
    
// GETTERS Y SETTERS DE LA CLASE
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public int getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(int idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getFaseProyecto() {
        return faseProyecto;
    }

    public void setFaseProyecto(String faseProyecto) {
        this.faseProyecto = faseProyecto;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }
    
    
}
