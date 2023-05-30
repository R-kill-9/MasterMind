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
        File dataDir2 = new File("./DATA/");
        if (!dataDir2.exists()) {
           dataDir2.mkdir();
        }
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
    public void savePartida(String idPartida, int nTurno, boolean rol,
                             ArrayList<Color>solucion, boolean ayuda, 
                             int puntuacion, int dificultad,
                             ArrayList<ArrayList<Color>> combinaciones,
                             int rondasMaquina){ 
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
            bw.write("rondasMaquina: " + Integer.toString(rondasMaquina));
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.err.println("Error al guardar la partida: " + e.getMessage());
        }
        

    }

    /*
     * Funcion que muestra las partidas guardadas del usuario pero sin
     * la palabra partida y sin la extension .txt
     */
    public ArrayList<String> getPartidasGuardadas(){
        ArrayList<String> partidas = new ArrayList<String>();
        File dataDir = new File(path);
        File[] files = dataDir.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.contains("partida")) {
                String idPartida = fileName.substring(7, fileName.length() - 4);
                partidas.add(idPartida);
            }
        }
        return partidas;
    }
    



    /**
     * Muestra el path (debug function borrar para el final)
     */
    public void showPath(){
        System.out.println(path);
    }

    /*
     * Dado un idPartida, devolvemos el numero de turno
     */
    public int getNTurno(String idPartida){
        String fileName = "partida" + idPartida.toString() + ".txt";
        File partidaFile = new File(path, fileName);
        int nTurno = 0;
        try {
            FileReader fr = new FileReader(partidaFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("nTurno: ")) {
                    nTurno = Integer.parseInt(line.substring(8));
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
        }
        return nTurno;
    }

    /*
     * Dado un idPartida, devolvemos el rol
     */
    public boolean getRol(String idPartida){
        String fileName = "partida" + idPartida.toString() + ".txt";
        File partidaFile = new File(path, fileName);
        boolean rol = false;
        try {
            FileReader fr = new FileReader(partidaFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("rol: ")) {
                    rol = Boolean.parseBoolean(line.substring(5));
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
        }
        return rol;
    }

    /*
     * Dado un idPartida, devolvemos la solucion
     */
    public ArrayList<Color> getSolucion(String idPartida) {
        String fileName = "partida" + idPartida.toString() + ".txt";
        File partidaFile = new File(path, fileName);
        ArrayList<Color> solucion = new ArrayList<Color>();
        try {
            FileReader fr = new FileReader(partidaFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("solucion: ")) {
                    String solucionString = line.substring(10).replaceAll("\\[|\\]", "");
                    String[] colores = solucionString.split(", ");
                    for (String color : colores) {
                        solucion.add(Color.valueOf(color.trim()));
                    }
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
        }
        return solucion;
    }

    /*
     * Dado un idPartida, devolvemos la ayuda
     */
    public boolean getAyuda(String idPartida){
        String fileName = "partida" + idPartida.toString() + ".txt";
        File partidaFile = new File(path, fileName);
        boolean ayuda = false;
        try {
            FileReader fr = new FileReader(partidaFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("ayuda: ")) {
                    ayuda = Boolean.parseBoolean(line.substring(7));
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
        }
        return ayuda;
    }

    /*
     * Dado un idPartida, devolvemos la puntuacion
     */
    public int getPuntuacion(String idPartida){
        String fileName = "partida" + idPartida.toString() + ".txt";
        File partidaFile = new File(path, fileName);
        int puntuacion = 0;
        try {
            FileReader fr = new FileReader(partidaFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("puntuacion: ")) {
                    puntuacion = Integer.parseInt(line.substring(12));
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
        }
        return puntuacion;
    }

    /*
     * Dado un idPartida, devolvemos la dificultad
     */
    public int getDificultad(String idPartida){
        String fileName = "partida" + idPartida.toString() + ".txt";
        File partidaFile = new File(path, fileName);
        int dificultad = 0;
        try {
            FileReader fr = new FileReader(partidaFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("dificultad: ")) {
                    dificultad = Integer.parseInt(line.substring(12));
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
        }
        return dificultad;
    }

    /*
     * Dado un idPartida, devolvemos las combinaciones
     */

     public ArrayList<ArrayList<Color>> getCombinaciones(String idPartida) {
        String fileName = "partida" + idPartida.toString() + ".txt";
        File partidaFile = new File(path, fileName);
        ArrayList<ArrayList<Color>> combinaciones = new ArrayList<ArrayList<Color>>();
        try {
            FileReader fr = new FileReader(partidaFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("combinaciones: ")) {
                    if (line.substring(15).equals("null")) {
                        combinaciones = null;
                    } else {
                        String[] combinacionesString = line.substring(15).split("], \\[");
                        for (String combinacion : combinacionesString) {
                            combinacion = combinacion.replaceAll("\\[|\\]", "");
                            String[] colores = combinacion.split(", ");
                            ArrayList<Color> combinacionArray = new ArrayList<Color>();
                            for (String color : colores) {
                                combinacionArray.add(Color.valueOf(color.trim()));
                            }
                            combinaciones.add(combinacionArray);
                        }
                    }
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
        }
        return combinaciones;
    }

    /*
     * Dado un idPartida, devolvemos las rondasMaquina
     */
    public int getRondasMaquina(String idPartida){
        String fileName = "partida" + idPartida.toString() + ".txt";
        File partidaFile = new File(path, fileName);
        int rondasMaquina = 0;
        try {
            FileReader fr = new FileReader(partidaFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("rondasMaquina: ")) {
                    rondasMaquina = Integer.parseInt(line.substring(15));
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
        }
        return rondasMaquina;
    }

    /*
     * Elimina el archivo de la partida
     */
    public void deletePartida(String idPartida) {
        String fileName = "partida" + idPartida.toString() + ".txt";
        File partidaFile = new File(path, fileName);
        partidaFile.delete();
    }

    /*
     * Devuelve true si existe el archivo record.txt
     */
    public boolean existsRecord() {
        String fileName = "record.txt";
        File recordFile = new File(path, fileName);
        return recordFile.exists();
    }

    /*
     * Guarda el record personal
     */
    public void saveRecord(int record[]){
        String fileName = "record.txt";
        File recordFile = new File(path, fileName);
        try {
            FileWriter fw = new FileWriter(recordFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("record: " + Arrays.toString(record));
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.err.println("Error al guardar el record: " + e.getMessage());
        }
    }

    /*
     * Carga el record personal
     */
    public int[] loadRecord(){
        String fileName = "record.txt";
        File recordFile = new File(path, fileName);
        int[] record = new int[5];
        try {
            FileReader fr = new FileReader(recordFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("record: ")) {
                    String recordString = line.substring(8).replaceAll("\\[|\\]", "");
                    String[] recordStringArray = recordString.split(", ");
                    for (int i = 0; i < 5; i++) {
                        record[i] = Integer.parseInt(recordStringArray[i]);
                    }
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println("Error al cargar el record: " + e.getMessage());
        }
        return record;
    }

    /**
     * Main para probar la clase
     */
    public static void main (String[]args){
        UserPersistence up = new UserPersistence();
        up.checkUser("Marcel");
        //Imprime la varibale path
        System.out.println(up.path);
        
        
       // Carga la partida
      //  up.loadPartida("1");
        //Test ver partidas guardadas
        ArrayList<String> partidas = up.getPartidasGuardadas();
        for (String partida : partidas) {
            System.out.println(partida);
        }
        
        //Test getrondasMaquina
        int rondasMaquina = up.getRondasMaquina("2023-05-16_17-12-18");
        System.out.println(rondasMaquina);
        //Test getCombinaciones
        ArrayList<ArrayList<Color>> combinaciones = up.getCombinaciones("2023-05-16_17-12-18");
        // System.out.println(combinaciones.toString());
        //Test getSolucion
        ArrayList<Color> solucion = up.getSolucion("2023-05-16_17-12-18");
        System.out.println(solucion.toString());
       

    }


    

}