package main.drivers;
import main.domain.Color;
import main.domain.ColorFeedBack;
import main.domain.Combinacion;
import main.domaincontrollers.CtrlDominio;
import java.util.*;

public class DriverDomain {
    /**
     * Controlador de dominio
     */
    private final CtrlDominio cDominio;

    /**
     * Scanner para leer de la entrada estandar
     */
    public Scanner input;

    /**
     * Boolean para saber el rol inicial del usuario
     */
    private boolean rol;


    /**
     * String para la estetica de la consola
     */
    private static final String separator = "----------------------------------------";

    /*
     * Inicializamos el driver
     */
    public DriverDomain() {
        cDominio = CtrlDominio.getInstance();
        //cDominio.iniDomainContoller();
    }

    /**
     * Funcion para hacer login
     */
    public void testLogin() {
        String username;
        do {
            System.out.println("Introduce el nombre de usuario");
            username = input.nextLine();
            if (username.length() < 3) System.out.println("El nombre de usuario debe tener al menos 3 caracteres");
        } while (username.length() < 3);
        try {
            //  cDominio.newUser(username);
            cDominio.loginUser(username);
            System.out.println(separator);
            System.out.println("Login correcto");

        } catch (Exception e) {
            System.out.println(separator);
            System.out.println("Login incorrecto");
        }
    }


    /**
     * Funcion para crear partida
     */
    public void testCrearPartida() {
        int dificultad;
        int aux; //Para leer el booleano
        boolean ayuda;

        do {
            System.out.println("Introduce la dificultad: 1 para facil, 2 para normal, 3 para dificil");
            dificultad = input.nextInt();
            if (dificultad < 1 || dificultad > 3) System.out.println("La dificultad debe ser 1, 2 o 3");
        } while (dificultad < 1 || dificultad > 3);
        do {
            System.out.println("Introduce si quieres ayuda: 1 para si, 0 para no");
            aux = input.nextInt();
            if (aux != 0 && aux != 1) System.out.println("La ayuda debe ser 1 o 0");
        } while (aux != 0 && aux != 1);
        ayuda = (aux == 1); //Si aux es 1, ayuda es true, si no, ayuda es false
        do {
            System.out.println("Introduce si quieres rol: 1 para CodeMaker, 0 para CodeBreaker");
            aux = input.nextInt();
            if (aux != 0 && aux != 1) System.out.println("El rol debe ser 1 o 0");
        } while (aux != 0 && aux != 1);
        rol = (aux == 1); //Si aux es 1, rol es true, si no, rol es false
        cDominio.crearPartida(dificultad, ayuda, rol);
        System.out.println(separator);
        System.out.println("Partida creada");
    }

    /**
     * Funcion para enviar una combinacion
     */
    public void testnewCombinacion() {
        ArrayList<Color> colores = new ArrayList<Color>();
        System.out.println("Introduce los colores de la combinación");
        System.out.println("1. Azul");
        System.out.println("2. Verde");
        System.out.println("3. Rojo");
        System.out.println("4. Morado");
        System.out.println("5. Amarillo");
        System.out.println("6. Naranja");
        for (int i = 0; i < 4; ++i) {
            int color;
            do {
                System.out.println("Introduce el color " + (i + 1));
                color = input.nextInt();
                if (color < 1 || color > 6) System.out.println("El color debe ser 1, 2, 3, 4, 5 o 6");
            } while (color < 1 || color > 6);
            switch (color) {
                case 1:
                    colores.add(Color.BLUE);
                    break;
                case 2:
                    colores.add(Color.GREEN);
                    break;
                case 3:
                    colores.add(Color.RED);
                    break;
                case 4:
                    colores.add(Color.PURPLE);
                    break;
                case 5:
                    colores.add(Color.YELLOW);
                    break;
                case 6:
                    colores.add(Color.ORANGE);
                    break;
            }
        }
        try {
            ArrayList<ColorFeedBack> feedback = cDominio.newCombinacion(colores);
            System.out.println(separator);
            System.out.println("Combinacion enviada");
            System.out.println("Feedback: ");
            for (int i = 0; i < feedback.size(); ++i) {
                System.out.println(feedback.get(i));
            }

        } catch (Exception e) {
            System.out.println(separator);
            System.out.println("Combinacion incorrecta");
            return;
        }

    }

    /**
     * Funcion para enviar solucion
     */
    public void testSetSolucion() {
        ArrayList<Color> colores = new ArrayList<Color>();

        System.out.println("Introduce los colores de la solución");
        System.out.println("1. Azul");
        System.out.println("2. Verde");
        System.out.println("3. Rojo");
        System.out.println("4. Morado");
        System.out.println("5. Amarillo");
        System.out.println("6. Naranja");
        for (int i = 0; i < 4; ++i) {
            int color;
            do {
                System.out.println("Introduce el color " + (i + 1));
                color = input.nextInt();
                if (color < 1 || color > 6) System.out.println("El color debe ser 1, 2, 3, 4, 5 o 6");
            } while (color < 1 || color > 6);
            switch (color) {
                case 1:
                    colores.add(Color.BLUE);
                    break;
                case 2:
                    colores.add(Color.GREEN);
                    break;
                case 3:
                    colores.add(Color.RED);
                    break;
                case 4:
                    colores.add(Color.PURPLE);
                    break;
                case 5:
                    colores.add(Color.YELLOW);
                    break;
                case 6:
                    colores.add(Color.ORANGE);
                    break;
            }
        }

        try {
            int rondas = cDominio.setSolution(colores);
            System.out.println(separator);
            System.out.println("Solucion enviada");
            System.out.println("Se ha resuelto en un número de rondas de : " + rondas);
        } catch (Exception e) {
            System.out.println(separator);
            System.out.println("Solucion incorrecta");
            return;
        }
    }

    /*
     * Función de jugar
     */
    public void jugar() {
        testCrearPartida();
        System.out.println("Empiezas siendo " + (rol ? "CodeMaker" : "CodeBreaker"));
        
        if (rol) {
            rol = !rol;
            testSetSolucion();
        }
        else {
            rol =!rol;
            for (int i = 0; i < 10; ++i) {
                System.out.println("Ronda " + (i + 1) + " de 10");
                testnewCombinacion();
            }
        }
        System.out.println("Cambio de turno, ahora eres " + (rol ? "CodeMaker" : "CodeBreaker"));
        if (rol) testSetSolucion();
        else {
            for (int i = 0; i < 10; ++i) {
                System.out.println("Ronda " + (i + 1) + " de 10");
                testnewCombinacion();

            }
        }
    }


            /**
             * Main del driver primero pide Login y despues jugar
             */
            public static void main (String[]args){
                DriverDomain driver = new DriverDomain();
                driver.input = new Scanner(System.in);
                driver.testLogin();
                driver.jugar();
            }
        }

     
    


    /**
     * Main del driver
     */
    /* public static void main(String[] args) {
        DriverDomain driver = new DriverDomain();
        driver.input = new Scanner(System.in);
        int option;
        do{
            System.out.println(separator);
            System.out.println("1. Login");
            System.out.println("2. Crear partida");
            System.out.println("3. Enviar combinacion");
            System.out.println("4. Enviar solucion");
            System.out.println("5. Salir");
            System.out.println(separator);
            option = driver.input.nextInt();
            switch (option){
                case 1:
                    driver.testLogin();
                    break;
                case 2:
                    driver.testCrearPartida();
                    break;
                case 3:
                    driver.testnewCombinacion();
                    break;
                case 4:
                    driver.testSetSolucion();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (option != 5);
    }
}
*/



