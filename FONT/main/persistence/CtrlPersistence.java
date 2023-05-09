package main.persistence;

import java.io.*;
import java.util.*;
import main.domain.Color;
import main.domain.Combinacion;


public class CtrlPersistence {
    
    /*
     * Relación con la clase UserPersistence
     */

    private UserPersistence userPer;

    /*
     * Relación con la clase RankingPersistence
     */
    private RankingPersistence rankPer;

    /*  
     * Instancia CtrlPersistenc
     */
    private static CtrlPersistence instance;

    /*  
     * Creadora
     */
    private CtrlPersistence() {
        userPer = new UserPersistence();
        rankPer = new RankingPersistence();
    }

    /*
     * Función que devuelve una instancia de la clase UserPersistence
     */
    public static CtrlPersistence getInstance() {
        if (instance == null) {
            instance = new CtrlPersistence();
        }
        return instance;
    }

    /*  
     * Función que crea una carpeta con el nombre del usuario
     */
    public void checkUser(String username) {
        userPer.checkUser(username);
    }

    /*
     * Función que guarda una partida
     */
    public void savePartida(Date idPartida, int nTurno, boolean rol,
                             ArrayList<Combinacion>solucion, boolean ayuda, 
                             int puntuacion, int dificultad,
                             ArrayList<ArrayList<Color>> combinaciones){ 
        userPer.savePartida(idPartida, nTurno, rol, solucion, ayuda, puntuacion, dificultad, combinaciones);
    }

    /*
     * Función que carga una partida
     */
    public void loadPartida(String idPartida){
        userPer.loadPartida(idPartida);
    }


    /*
     * Función que crea carpeta con el nombre ranking
     */
    public void checkRanking() {
        rankPer.checkRanking();
    }

    /*
     * Función que guarda el ranking
     */
    public void saveRanking(int dificultad, TreeMap<String, Integer> ranking) {
        rankPer.saveRanking(dificultad, ranking);
    }

    /*
     * Función que carga el ranking
     */ 
    public TreeMap<String, Integer> loadRanking(int dificultad){
       return rankPer.loadRanking(dificultad);
    }

    /*
     * Función que comprueba si existe el ranking1
     */
    public boolean existsRanking1() {
        return rankPer.existsRanking1();
    }

    /*
     * Función que comprueba si existe el ranking2
     */
    public boolean existsRanking2() {
        return rankPer.existsRanking2();
    }

    /*
     * Función que comprueba si existe el ranking3
     */
    public boolean existsRanking3() {
        return rankPer.existsRanking3();
    }



                    
                            





}
