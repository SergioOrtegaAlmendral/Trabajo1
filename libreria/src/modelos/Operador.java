package modelos;

public class Operador {

    private String nombre;
    private String habilidad;

    public Operador(String nombre, String habilidad) {
        this.nombre = nombre;
        this.habilidad = habilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHabilidad() {
        return habilidad;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", nombre, habilidad);
    }
}
