package main.domaincontrollers;
import java.util.Date;

import java.util.HashMap;
import java.util.ArrayList;
import main.domain.Usuario;
import main.domain.Color;
import main.domain.ColorFeedBack;
import main.domain.Partida;
import main.domain.PossiblesEstadosPartida;
import main.domain.HistorialPartidas;
import main.domain.HistorialPartidasGuardadas;
import main.domain.Pair;

/**
 * Clase que representa el controlador de dominio de la clase Partida.
 */
public class CtrlPartida {
    private static Partida partidaActual;
    /**
     * Constructora por defecto.
     */
    public CtrlPartida() {}
     /**
     * Crea una nueva partida y la añade a la lista de partidas.
     * 
     * @param dificultadEscogida la dificultad elegida para la partida
     * @param username el usuario que juega la partida
     * @param ayuda true si se activa la ayuda, false en caso contrario
     * @param rol true si el usuario es el CodeMaker, false si es el CodeBreaker
     */
    public static Partida crearPartida(int dificultadEscogida, String username, boolean ayuda, boolean rol) {
        Partida partida = new Partida(dificultadEscogida, username, ayuda, rol);
        Date dataPartida = partida.getData();
        HistorialPartidas.agregarPartida(username,dataPartida);
        partidaActual = partida;
        return partida;
    }
     /**
     * Crea una nueva partida y la añade a la lista de partidas.
     * 
     * @param data la fecha de la partida
     * @param usuario el usuario que quiere borrar la partida
     * @return 
     */
    public static Boolean borrarPartida(String username, Date dataPartida) {
        return HistorialPartidas.borrarPartida(username,dataPartida);
    }

     /**
     * Obtiene la lista de partidas.
     * 
     * @return la lista de partidas
     */
    public ArrayList<Pair<String, Date>> getPartidas() {
        return HistorialPartidas.getPartidas();
    }

     
    /**
     * Obtiene una la partida actual del usuario.
     * 
     * @param usuario el usuario que ha jugado la partida
     * @return la partida jugada por ese usuario, o null si no hay ninguna
     * @throws Exception 
     */
    public Partida getPartidaActual(Usuario usuario) throws Exception {
        if(partidaActual != null) return partidaActual;
        else throw new Exception("No hay ninguna partida actual");
    }
    /**
     * Obtiene una la partida actual del usuario.
     * 
     * @param Vector<Color> la combinacion de la partida
     * @return la partida jugada por ese usuario, o null si no hay ninguna
     * @throws Exception 
     */
    public ArrayList<ColorFeedBack> newCombinacion(ArrayList<Color> combination) throws Exception{
        return partidaActual.setCombinacion(combination);
    }
    /**
     * Obtiene información de las partidas guardadas por el usuario.
     * 
     * @param usuario el usuario que ha jugado la partida
     * @param estado el estado de las partidas: guardadas o pausadas.
     * @return la partida jugada por ese usuario, o null si no hay ninguna
     */
    public static ArrayList<Pair<String, Date>> getInfoPartidasGuardadas(String usuario) {
        return HistorialPartidasGuardadas.getPartidas(usuario);
    }
	public static HashMap<Date,Integer> getInfoPartida(Partida partida) {
		Date dataP = partida.getData();
		Integer nivel = partida.getDificultad();
		HashMap<Date, Integer> infoPartida = new HashMap<>();
		infoPartida.put(dataP, nivel);
	    return infoPartida;
	}
}