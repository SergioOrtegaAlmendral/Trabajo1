import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


public class App {

    private static final String URL = "jdbc:mysql://localhost:3306/ejemplo?characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static void main(String[] args) throws Exception {
        
        int opcion;
        Connection conexion;

        try{
        conexion = DriverManager.getConnection(URL, USER, PASS);

        do{
            menu();
            opcion = Integer.parseInt(System.console().readLine());

            switch (opcion) {
                case 1:
                    listaPersonajes(conexion);
                    System.out.println("\nLISTADO DE OPERADORES\n=======================================================");
                    break;
                case 2: 
                    añadirOperador(conexion);
                    System.out.println("\nAÑADIR OPERADORES\n=======================================================");
                    break;
                case 3:
                    buscarOperador(conexion);
                    System.out.println("\nBUSCAR OPERADORES:\n=======================================================");
                    break;

                case 4: 
                    editarOperador(conexion);
                    System.out.println("\nEDITAR OPERADORES\n=======================================================");
                    break;
                case 5: 
                    borrarOperador(conexion);
                    System.out.println("\nBORRAR OPERADORES\n=======================================================");
                    break;
                case 0: break;
                default:
                    System.out.print("**ERROR: opción incorrecta.");
                    break;
            }
        }while(opcion != 0);

          // cerramos la conexión
          conexion.close() ; 

        } catch (SQLException sqle) {
            System.out.println("Se ha producido un error con la base de datos.");
        }
        
    }

    private static void pausa() {
        System.out.println("\nPULSA ENTER PARA CONTINUAR");
        System.out.println();
    }
 /**
     */
    private static void menu() {

        System.out.println("\033[H\033[2J") ;
        System.out.println("OPERADORES V 2.0\n================================") ;
        System.out.println("1. Listar operadores") ;
        System.out.println("2. Añadir nuevo operador") ;
        System.out.println("3. Buscar operador") ;
        System.out.println("4. Editar operador") ;
        System.out.println("5. Borrar operador") ;
        System.out.println("0. Salir") ;
        System.out.print("Opcion? ") ;
    }



    /**
     * @param conexion
     * @throws SQLException
     */
    private static void listadoLibros(Connection conexion) throws SQLException {
        
        // creamos sentencia
        String sql = "SELECT * FROM libro;";
        Statement stmt = conexion.createStatement();

        // lanzar la consulta
        ResultSet resultado = stmt.executeQuery(sql);

        // mostramos el resultado (si lo hay)
        while (resultado.next()) {

            String titulo = resultado.getString("titulo");
            String autor = resultado.getString("autor");

            System.out.printf(" - %s, %s\n", titulo, autor);

        }
    }

    private static void listaPersonajes(Connection conexion) throws SQLException{

        String sql = "SELECT * FROM PERSONAJES";
        Statement stmt = conexion.createStatement();

        ResultSet resultado = stmt.executeQuery(sql);

        while (resultado.next()){
            String nombre = resultado.getString("nombreOperador");
            String habilidad = resultado.getString("habilidad");

            System.out.printf("- %s %s\n", nombre, habilidad);

        }

    }

    private static void buscarOperador(Connection conexion) throws SQLException{
        String nombre;
        System.out.print("Nombre (en blanco para volver)");
        nombre = System.console().readLine();

        if (!nombre.isEmpty()){
            String sql = "SELECT * FROM operadores WHERE nombre = ?;" ;
            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setString(2, nombre);

            //Utilizando executeQuery, la variable resultado tendrá SIEMPRE un objeto de tipo RessulSet
            ResultSet resultado = stmt.executeQuery();

            if(resultado.next()){
                Operador operador = new operador(resultado.getString ("nombre"));
            }
        }
    }

    private static void borrarOperador(Connection conexion) throws SQLException{
        String nombre;

        System.out.print("Nombre (en blanco para volver)");
        nombre = System.console().readLine();

        if(!nombre.isEmpty()){
            String sql = "DELETE FROM operadores WHERE nombre = ?;";
            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setString(1, nombre);

            int operadoresEliminados = stmt.executeUpdate();

            if (operadoresEliminados > 0){
                System.out.printf("Se ha eliminado el operador con nombre %s \n", nombre);
            }else{
                System.out.printf("No se ha encontrado el operador con nombre %s \n", nombre);
            }
        }
    }

    private static void añadirOperador(Connection conexion) throws SQLException{
        String nombre;

        System.out.print("Nombre (en blanco para volver)");
        nombre = System.console().readLine();

        if(!nombre.isEmpty()){
            String sql = "INSERT INTO operadores (nombre) VALUES (?);";
            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setString(1, nombre);

            int operadoresAñadidos = stmt.executeUpdate();

            if (operadoresAñadidos > 0){
                System.out.printf("Se ha añadido el operador con nombre %s \n", nombre);
            }else{
                System.out.printf("No se ha encontrado el operador con nombre %s \n", nombre);
            }
        }
    }

    private static void editarOperador(Connection conexion) throws SQLException{
        //(Añadir el resto de campos cuando este completa la tabla)
        String nombre;
        String idOperador;

        HashMap<String, String> parametros = new HashMap<>();

        System.out.print("Nombre (en blanco para volver)");
        nombre = System.console().readLine();

       
        System.out.print("Nombre (en blanco para no editarlo): ");
        nombre = System.console().readLine();
        if(!nombre.isEmpty()){parametros.put("nombre = ?", nombre);}

        System.out.print("idOperador (en blanco para no editarlo): ");
        idOperador = System.console().readLine();

        //construimos la consulta sql
            
        String sql = "UPDATE operador SET" + String.join(",", parametros.keySet()) + "WHERE nombre = ?;";
        PreparedStatement stmt = conexion.prepareStatement(sql);

        for(String valor: parametros.values()){
            stmt.setString(posicion++, valor);
        }
        stmt.setString(0, sql);

        int operadoresActualizados = stmt.executeUpdate();

        if(operadoresActualizados == 0){
            System.out.printf("No se ha encontrado el operador con nombre %s\n", nombre);
        }else{
            System.out.printf("Se ha actualizado correctamente el operador con nombre %s\n", nombre);
        }
    }

    private void insertarOperador() throws SQLException{
        String nombre;

        System.out.print("Nombre (en blanco para volver)");
        nombre = System.console().readLine();

        if(!nombre.isEmpty()){
            System.out.print(("Nombre: "));
            nombre = System.console().readLine();
        }
    }
}

