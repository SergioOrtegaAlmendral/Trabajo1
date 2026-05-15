package controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import modelos.Operador;
import modelos.OperadorDAO;
import vistas.OperadorView;

public class OperadorController {

    private OperadorDAO dao;
    private OperadorView vista;

    public OperadorController() throws SQLException {
        this.dao = new OperadorDAO();
        this.vista = new OperadorView();
    }

    public void listarOperadores() throws SQLException {
        ArrayList<Operador> datos = dao.seleccionarTodos();
        vista.mostrarOperadores(datos);
    }

    public void insertarOperador() throws SQLException {
        Operador operador = vista.solicitarOperador();
        if (operador == null) {
            return;
        }
        boolean creado = dao.insertar(operador);
        if (creado) {
            vista.mostrarMensaje(String.format("Se ha añadido el operador con nombre %s", operador.getNombre()));
        } else {
            vista.mostrarMensaje(String.format("No se ha podido añadir el operador con nombre %s", operador.getNombre()));
        }
    }

    public void buscarOperador() throws SQLException {
        String nombre = vista.solicitarNombre();
        if (nombre.isEmpty()) {
            return;
        }
        Operador operador = dao.buscarPorNombre(nombre);
        if (operador == null) {
            vista.mostrarMensaje(String.format("No se ha encontrado el operador con nombre %s", nombre));
        } else {
            vista.mostrarMensaje(String.format("Operador encontrado: %s", operador));
        }
    }

    public void editarOperador() throws SQLException {
        String nombre = vista.solicitarNombre();
        if (nombre.isEmpty()) {
            return;
        }
        System.out.print("Nuevo nombre (en blanco para no cambiar): ");
        String nuevoNombre = System.console().readLine();
        if (nuevoNombre.isEmpty()) {
            vista.mostrarMensaje("No se ha realizado ningún cambio.");
            return;
        }
        boolean actualizado = dao.actualizar(nombre, nuevoNombre);
        if (actualizado) {
            vista.mostrarMensaje(String.format("Se ha actualizado correctamente el operador con nombre %s", nombre));
        } else {
            vista.mostrarMensaje(String.format("No se ha encontrado el operador con nombre %s", nombre));
        }
    }

    public void borrarOperador() throws SQLException {
        String nombre = vista.solicitarNombre();
        if (nombre.isEmpty()) {
            return;
        }
        boolean borrado = dao.borrar(nombre);
        if (borrado) {
            vista.mostrarMensaje(String.format("Se ha eliminado el operador con nombre %s", nombre));
        } else {
            vista.mostrarMensaje(String.format("No se ha encontrado el operador con nombre %s", nombre));
        }
    }
}
