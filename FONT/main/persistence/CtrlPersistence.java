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
    public void savePartida(String idPartida, int nTurno, boolean rol,
                             ArrayList<Color>solucion, boolean ayuda, 
                             int puntuacion, int dificultad,
                             ArrayList<ArrayList<Color>> combinaciones,
                             int rondasMaquina){ 
        userPer.savePartida(idPartida, nTurno, rol, solucion, ayuda, puntuacion, dificultad, combinaciones, rondasMaquina);
    }

   
    /*
     * Función que devuelve un array con las partidas guardadas
     */
    public ArrayList<String> getPartidasGuardadas(){
        return userPer.getPartidasGuardadas();
    }

    /*
     * Dado un id de partida, devuelve el turno
     */
    public int getNTurno(String idPartida){
        return userPer.getNTurno(idPartida);
    }

    /*
     * Dado un id de partida, devuelve el rol
     */
    public boolean getRol(String idPartida){
        return userPer.getRol(idPartida);
    }

    /*
     * Dado un id de partida, devuelve la solución
     */
    public ArrayList<Color> getSolucion(String idPartida){
        return userPer.getSolucion(idPartida);
    }

    /*
     * Dado un id de partida, devuelve la ayuda
     */
    public boolean getAyuda(String idPartida){
        return userPer.getAyuda(idPartida);
    }

    /*
     * Dado un id de partida, devuelve la puntuación
     */
    public int getPuntuacion(String idPartida){
        return userPer.getPuntuacion(idPartida);
    }

    /*
     * Dado un id de partida, devuelve la dificultad
     */
    public int getDificultad(String idPartida){
        return userPer.getDificultad(idPartida);
    }

    /*
     * Dado un id de partida, devuelve las combinaciones
     */
    public ArrayList<ArrayList<Color>> getCombinaciones(String idPartida){
        return userPer.getCombinaciones(idPartida);
    }

    /*
     * Dado un id de partida, devuelve las rondas de la máquina
     */
    public int getRondasMaquina(String idPartida){
        return userPer.getRondasMaquina(idPartida);
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
