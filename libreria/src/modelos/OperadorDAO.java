package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.BDConexion;

public class OperadorDAO {

    private Connection conexion;

    public OperadorDAO() {
        this.conexion = BDConexion.conectar().bd();
    }

    public ArrayList<Operador> seleccionarTodos() throws SQLException {
        ArrayList<Operador> datos = new ArrayList<>();
        String sql = "SELECT * FROM PERSONAJES";
        Statement stmt = conexion.createStatement();
        ResultSet resultado = stmt.executeQuery(sql);

        while (resultado.next()) {
            datos.add(new Operador(
                resultado.getString("nombreOperador"),
                resultado.getString("habilidad")
            ));
        }

        resultado.close();
        stmt.close();

        return datos;
    }

    public boolean insertar(Operador operador) throws SQLException {
        String sql = "INSERT INTO operadores (nombre) VALUES (?);";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        pstmt.setString(1, operador.getNombre());
        int resultado = pstmt.executeUpdate();
        pstmt.close();
        return resultado > 0;
    }

    public Operador buscarPorNombre(String nombre) throws SQLException {
        Operador operador = null;
        String sql = "SELECT * FROM operadores WHERE nombre = ?;";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        pstmt.setString(1, nombre);
        ResultSet resultado = pstmt.executeQuery();

        if (resultado.next()) {
            operador = new Operador(resultado.getString("nombre"), "");
        }

        resultado.close();
        pstmt.close();
        return operador;
    }

    public boolean borrar(String nombre) throws SQLException {
        String sql = "DELETE FROM operadores WHERE nombre = ?;";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        pstmt.setString(1, nombre);
        int resultado = pstmt.executeUpdate();
        pstmt.close();
        return resultado > 0;
    }

    public boolean actualizar(String nombre, String nuevoNombre) throws SQLException {
        String sql = "UPDATE operadores SET nombre = ? WHERE nombre = ?;";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        pstmt.setString(1, nuevoNombre);
        pstmt.setString(2, nombre);
        int resultado = pstmt.executeUpdate();
        pstmt.close();
        return resultado > 0;
    }
}
