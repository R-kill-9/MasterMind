package main.domaincontrollers;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import main.domain.Usuario;
import main.domain.Color;
import main.domain.ColorFeedBack;
import main.domain.Partida;
import main.domain.PossiblesEstadosPartida;

/**
 * Clase que representa el controlador de dominio de la clase Partida.
 */
public class CtrlPartida {
    private static ArrayList<Partida> partidas;
    private static Partida partidaActual;
    /**
     * Constructora por defecto.
     */
    public CtrlPartida() {
        partidas = new ArrayList<>();
    }
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
        partidas.add(partida);
        partidaActual = partida;
        return partida;
    }
     /**
     * Crea una nueva partida y la añade a la lista de partidas.
     * 
     * @param data la fecha de la partida
     * @param usuario el usuario que quiere borrar la partida
     */
    public static Partida borrarPartida(String username, Date data) {
        for (Partida partida : partidas) {
            String userPartida = partida.getUsuario();
            Date dataPartida = partida.getData();
            if (userPartida == username && dataPartida == data) {
            	partidas.remove(partida);
            	return partida;
            }
        }
        return null;
    }

     /**
     * Obtiene la lista de partidas.
     * 
     * @return la lista de partidas
     */
    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

     /**
     * Obtiene las partidas en función del usuario que la ha jugado.
     * 
     * @param usuario el usuario que ha jugado la partida
     * @return la partida jugada por ese usuario, o null si no hay ninguna
     */
    public ArrayList<Partida> getPartidasGuardadas(Usuario usuario) {
        ArrayList<Partida> result = new ArrayList<Partida>();
        for (Partida partida : partidas) {
            if (partida.getUsuario().equals(usuario)) {
                result.add(partida);
            }
        }
        return result.size() == 0 ? null : partidas;
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
    public ArrayList<Partida> getInfoPartidaSegunEstado(Usuario usuario, PossiblesEstadosPartida estado) {
    	ArrayList<Partida> result = new ArrayList<>();
        for (Partida partida : partidas) {
        	if (partida.getUsuario().equals(usuario)) {
        		PossiblesEstadosPartida stateP = partida.getEstadoPartida();
                if (estado == stateP) result.add(partida);
            }
        }
        return result;
    }
	public HashMap<Date,Integer> getInfoPartida(Partida partida) {
		Date dataP = partida.getData();
		Integer nivel = partida.getDificultad();
		HashMap<Date, Integer> infoPartida = new HashMap<>();
		infoPartida.put(dataP, nivel);
	    return infoPartida;
	}
}