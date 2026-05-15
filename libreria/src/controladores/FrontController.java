package controladores;

import java.sql.SQLException;

public class FrontController {

    private OperadorController operadorController ;

    public FrontController() throws SQLException {
        this.operadorController = new OperadorController() ;
    }

    public void despachar(int opcion) throws SQLException {

        switch(opcion) {
            case 1:
                operadorController.listarOperadores();
                break ;
            case 2:
                operadorController.insertarOperador();
                break ;
            case 3:
                operadorController.buscarOperador();
                break ;
            case 4:
                operadorController.editarOperador();
                break ;
            case 5:
                operadorController.borrarOperador();
                break ;
            case 0: break ;
            default:
                System.out.println("**ERROR: opción incorrecta.");
        }
    }
}
