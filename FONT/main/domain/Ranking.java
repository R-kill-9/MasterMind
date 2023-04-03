package main.domain;
import java.util.HashMap;
import java.util.Map;


public class Ranking {

    /**
     * Mapa que tendrá como clave el nivel de dificultad y dentro otro mapa con el nombre del jugador y como valor la puntuación de la partida
     */
    private Map<Integer,Map<String, Integer>> posiciones;

    /**
     * Constructora de la clase Ranking
     * @param dificultat Nivel de dificultat que reflejará el ranking
     * @param posiciones Mapa que tendrá como clave el nombre del jugador y como valor la puntuación de la partida
     */ 
    public Ranking() {
    	this.posiciones = new HashMap<>();
        this.posiciones.put(1, new HashMap<>());
        this.posiciones.put(2, new HashMap<>());
        this.posiciones.put(3, new HashMap<>());
    }

    /**
     * Introduce una nueva posición en el mapa de posiciones
     */
    public void addPartida(String jugador, Integer puntuacion, Integer nivelDificultad) {
        Map<String, Integer> rankingNivel = posiciones.get(nivelDificultad);
        if (rankingNivel == null) {
            rankingNivel = new HashMap<>();
            posiciones.put(nivelDificultad, rankingNivel);
        }
        Integer currentScore = rankingNivel.get(jugador);
        if (currentScore != null && currentScore >= puntuacion) {
            return;
        }
        rankingNivel.put(jugador, puntuacion);
    }

    /**
     * Devuelve el mapa de posiciones
     */
    public Map<String, Integer> getRanking(Integer nivelDificultad) {
        return posiciones.get(nivelDificultad);
    }
}