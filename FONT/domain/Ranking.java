package domain.classes;
import java.util.Map;


public class Ranking {

    /**
     * Nivel de dificultat que reflejará el ranking
     */
    private String dificultat;

    /**
     * Mapa que tendrá como clave el nombre del jugador y como valor la puntuación de la partida
     */
    private Map<String, Integer> posiciones;

    /**
     * Constructora de la clase Ranking
     * @param dificultat Nivel de dificultat que reflejará el ranking
     * @param posiciones Mapa que tendrá como clave el nombre del jugador y como valor la puntuación de la partida
     */ 
    public Ranking(String dificultat, Map<String, Integer> posiciones) {
        this.dificultat = dificultat;
        this.posiciones = posiciones;
    }

    /**
     * Introduce una nueva posición en el mapa de posiciones
     */
    public void addPartida(String jugador, Integer puntuacion) {
        posiciones.put(jugador, puntuacion);
    }

    /**
     * Devuelve el mapa de posiciones
     */
    public Map<String, Integer> getPosiciones() {
        return posiciones;
    }
}