package vistas;

import java.util.ArrayList;
import modelos.Operador;

public class OperadorView {

    public void mostrarOperadores(ArrayList<Operador> operadores) {
        System.out.println("\nLISTADO DE OPERADORES\n=======================================================");
        for (Operador operador : operadores) {
            System.out.println(operador);
        }
    }

    public Operador solicitarOperador() {
        String nombre;

        System.out.print("Nombre (en blanco para volver): ");
        nombre = System.console().readLine();

        if (nombre.isEmpty()) {
            return null;
        }

        return new Operador(nombre, "");
    }

    public String solicitarNombre() {
        System.out.print("Nombre (en blanco para volver): ");
        return System.console().readLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
