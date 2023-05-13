package main.persistence;

import java.io.*;
import java.util.*;
import main.domain.Color;
import main.domain.Combinacion;



public class UserPersistence{

    /**
     * Path donde se guardan los usuarios
     */
    private static String path = "";

    /**
     * Constructora
     */
    public UserPersistence() {
    }


    /**
     * Comprobamos si existe el usuario, sino creamos directorio con su nombre
     * y asignamos el path
     */
    public void checkUser(String username) {
        File dataDir = new File("./DATA/users/");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        File userDir = new File(dataDir, username);
        if (!userDir.exists()) {
            userDir.mkdir();
        }
       path = userDir.getPath();
    }

    /*
     * Dentro del directorio del usuario creamos un fichero con la partida
     * que queremos guardar e insertamos en el fichero la partida los datos de la partida
     */
    public void savePartida(Date idPartida, int nTurno, boolean rol,
                             ArrayList<Combinacion>solucion, boolean ayuda, 
                             int puntuacion, int dificultad,
                             ArrayList<ArrayList<Color>> combinaciones){ 
        String fileName = "partida" + idPartida.toString() + ".txt";
        File partidaFile = new File(path, fileName);
        try {
            FileWriter fw = new FileWriter(partidaFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("idPartida: " + idPartida.toString());
            bw.newLine();
            bw.write("nTurno: " + Integer.toString(nTurno));
            bw.newLine();
            bw.write("rol: " + Boolean.toString(rol));
            bw.newLine();
            bw.write("solucion: " + solucion.toString());
            bw.newLine();
            bw.write("ayuda: " + Boolean.toString(ayuda));
            bw.newLine();
            bw.write("puntuacion: " + Integer.toString(puntuacion));
            bw.newLine();
            bw.write("dificultad: " + Integer.toString(dificultad));
            bw.newLine();
            if (combinaciones.isEmpty()) bw.write("combinaciones: " + "null");
            else bw.write("combinaciones: " + combinaciones.toString());
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.err.println("Error al guardar la partida: " + e.getMessage());
        }
        

    }

    /**
     * Cargamos la partida con el idPartida dado
     */
    public void loadPartida(String idPartida){
        String fileName = "partida" + idPartida.toString() + ".txt";
        File partidaFile = new File(path, fileName);
        try {
            FileReader fr = new FileReader(partidaFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
        }
    }

    /**
     * Muestra el path (debug function borrar para el final)
     */
    public void showPath(){
        System.out.println(path);
    }



    /**
     * Main para probar la clase
     */
    public static void main (String[]args){
        UserPersistence up = new UserPersistence();
        up.checkUser("USer2");
        //Imprime la varibale path
        System.out.println(up.path);
        //Guarda una partida
        ArrayList<Color> solucion = new ArrayList<Color>();
        solucion.add(Color.BLUE);
        solucion.add(Color.GREEN);
        solucion.add(Color.RED);
        solucion.add(Color.CYAN);
        ArrayList<ArrayList<Color>> combinaciones = new ArrayList<ArrayList<Color>>();
        ArrayList<Color> combinacion1 = new ArrayList<Color>();
        combinacion1.add(Color.BLUE);
        combinacion1.add(Color.GREEN);
        combinacion1.add(Color.RED);
        combinacion1.add(Color.CYAN);
        combinaciones.add(combinacion1);
        ArrayList<Color> combinacion2 = new ArrayList<Color>();
        combinacion2.add(Color.BLUE);
        combinacion2.add(Color.GREEN);
        combinacion2.add(Color.ORANGE);
        combinacion2.add(Color.CYAN);
        combinaciones.add(combinacion2);
       // up.savePartida("1", 1, true, solucion, true, 100, 1, combinaciones);
       // Carga la partida
      //  up.loadPartida("1");

    }


    

}