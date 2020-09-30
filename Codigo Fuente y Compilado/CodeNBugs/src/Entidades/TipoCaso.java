
package Entidades;



public class TipoCaso {
    // VARIABLES O ATRIBUTOS DE LA CLASE
private String nombre;
private int etapas;
// CONSTRUCTOR DE LA CLASE
    public TipoCaso(String nombre, int etapas) {
        this.nombre = nombre;
        this.etapas = etapas;
    }
// GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
// GETTERS Y SETTERS
    public int getEtapas() {
        return etapas;
    }
// GETTERS Y SETTERS
    public void setEtapas(int etapas) {
        this.etapas = etapas;
    }



}