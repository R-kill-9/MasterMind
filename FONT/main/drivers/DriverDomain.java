package main.drivers;
import main.domain.Color;
import main.domain.ColorFeedBack;
import main.domain.Combinacion;
import main.domaincontrollers.CtrlDominio;
import java.util.*;
import main.persistence.*;

public class DriverDomain {
    /**
     * Controlador de dominio
     */
    private final CtrlDominio cDominio;

    /*
     * Controlador de persistencia de usuario
     */
    private final UserPersistence userPer;

    /**
     * Scanner para leer de la entrada estandar
     */
    public Scanner input;

    /**
     * Boolean para saber el rol inicial del usuario
     */
    private boolean rol;

    /**
     * Boolean para saber si el usuario ha ganado
     */
    private boolean win;

    /**
     * int para saber la ronda
     */
    private int ronda;

    /*
     * Int para saber el número de ronda de codeBreaker
     */
    private int rondaCB;

    /** 
     * int para saber la dificultad
     */
    int dificultad;

    /**
     * String para saber el nombre del usuario
     */
    private String username;

    /**
     * String para la estetica de la consola
     */
    private static final String separator = "----------------------------------------";

    /*
     * Inicializamos el driver
     */
    public DriverDomain() {
        cDominio = CtrlDominio.getInstance();
        userPer= new UserPersistence();
        //cDominio.iniDomainContoller();
    }

    /**
     * Funcion para hacer login
     */
    public void testLogin() {
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
            System.out.println(e);
        }
    }


    /**
     * Funcion para crear partida
     */
    public void testCrearPartida() {
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
                if (color < 1 || color > 7) System.out.println("El color debe ser 1, 2, 3, 4, 5 o 6");
            } while (color < 1 || color > 7);
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
                case 7:
                    cDominio.guardarPartida();
                    break;
            }
        }
        try {
            ArrayList<ColorFeedBack> feedback = cDominio.newCombinacion(colores);
            System.out.println(separator);
            System.out.println("Combinacion enviada");
            System.out.println("Feedback: ");
            int aciertos=0;
            for (int i = 0; i < feedback.size(); ++i) {
                System.out.println(feedback.get(i));
                if (feedback.get(i) == ColorFeedBack.BLACK) ++aciertos;
            }
            if (aciertos == 4) win = true;

        } catch (Exception e) {
            System.out.println(separator);
            System.out.println(e);
            --rondaCB;
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
                if (color < 1 || color > 7) System.out.println("El color debe ser 1, 2, 3, 4, 5 o 6, 9 para guardar partida");
            } while (color < 1 || color > 7);
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
                case 7:
                    cDominio.guardarPartida();
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
            System.out.println(e);
            --ronda;
            return;
        }
    }

    /*
     * Función de jugar
     */
    public void jugar() {
        ronda =0;
        System.out.println("CONFIGURACIÓN DE LA PARTIDA");
        testCrearPartida();
        System.out.println("Empiezas siendo " + (cDominio.getRol() ? "CodeMaker" : "CodeBreaker"));
        while (ronda <2 && !win){
        if (cDominio.getRol()) {
            ++ronda;
            testSetSolucion();
        }
        else {
            ++ronda;
            for (rondaCB = 0; rondaCB < 10 && !win; ++rondaCB) {
                System.out.println("Ronda " + (rondaCB + 1) + " de 10");
                testnewCombinacion();
            }
        }
    }
    cDominio.addPartidaRanking(username,cDominio.getScore(),dificultad);
     
}
    /**
     * Funcion para imprimir el ranking
     */

    public void testPrintRanking() {
        int dificultad;
        System.out.println(separator);
        System.out.println("Introduce la dificultad del ranking");
        System.out.println("1. Facil");
        System.out.println("2. Medio");
        System.out.println("3. Dificil");
        do {
            dificultad = input.nextInt();
            if (dificultad < 1 || dificultad > 3) System.out.println("La dificultad debe ser 1, 2 o 3");
        } while (dificultad < 1 || dificultad > 3);
        System.out.println("El ranking es: ");
        TreeMap<String, Integer> ranking = cDominio.getRankingGlobalUnNivel(dificultad);
        for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }


    /**
     * Funcion para imprimir la puntuacion de la partida
     */
    public void testPrintPuntuacion() {
        System.out.println(separator);
        System.out.println("La puntuación de la partida ha sido de: " + cDominio.getScore());

    }

    public void testPrintRecords() {
        System.out.println(separator);
        System.out.println("Los records son: ");
        int records[] = cDominio.getRecord();
        for (int i = 0; i < records.length; ++i) {
            System.out.println(records[i]);
        }
    }

    public void restoreRanking(){
        cDominio.restoreRanking();
    }


            /**
             * Main del driver primero pide Login y despues jugar
             */
            public static void main (String[]args){
                DriverDomain driver = new DriverDomain();
                driver.input = new Scanner(System.in);
                driver.testLogin();
                driver.restoreRanking();
                driver.jugar();
                driver.testPrintPuntuacion();
                //Una vez acabada la partida se pregunta al usuario que quiere hacer
                int option;
                do {
                    System.out.println(separator);
                    System.out.println("1. Jugar otra partida");
                    System.out.println("2. Ver records");
                    System.out.println("3. Ver ranking");
                    System.out.println("4. Salir");
                    System.out.println(separator);
                    option = driver.input.nextInt();
                    switch (option) {
                        case 1:
                            driver.jugar();
                            driver.testPrintPuntuacion();
                            break;
                        case 2:
                            driver.testPrintRecords();
                            break;
                        case 3:
                            driver.testPrintRanking();
                            break;
                        case 4:
                            System.out.println("Saliendo...");
                            break;
                        default:
                            System.out.println("Opcion no valida");
                            break;
                    }
                } while (option != 4);

                
            }
        }

     