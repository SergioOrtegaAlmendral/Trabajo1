import controladores.FrontController;

public class App {

    public static void main(String[] args) throws Exception {

        int opcion;

        try {
            FrontController controlador = new FrontController();

            do {
                menu();
                opcion = Integer.parseInt(System.console().readLine());
                controlador.despachar(opcion);
                pausa();
            } while (opcion != 0);

        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }

    private static void pausa() {
        System.out.println("\nPULSA ENTER PARA CONTINUAR");
        System.console().readLine();
    }

    private static void menu() {
        System.out.println("\033[H\033[2J");
        System.out.println("OPERADORES V 2.0\n================================");
        System.out.println("1. Listar operadores");
        System.out.println("2. Añadir nuevo operador");
        System.out.println("3. Buscar operador");
        System.out.println("4. Editar operador");
        System.out.println("5. Borrar operador");
        System.out.println("0. Salir");
        System.out.print("Opcion? ");
    }
}

