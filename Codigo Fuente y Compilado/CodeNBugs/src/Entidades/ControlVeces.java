
package Entidades;
//
public class ControlVeces {
    // atributos de la clase
    private String nombre;
    private int veces;
// constructor
    public ControlVeces(String nombre) {
        this.nombre=nombre;
        this.veces= 1;
    }
    public ControlVeces(String nombre, int veces){
        this.nombre=nombre;
        this.veces=veces;
    }
// getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVeces() {
        return veces;
    }

    public void setVeces(int veces) {
        this.veces = veces;
    }
}
