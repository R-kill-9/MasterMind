package main.domain;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


public class Ranking {

    /**
     * Mapa que tendrá como clave el nivel de dificultad y dentro otro mapa con el nombre del jugador y como valor la puntuación de la partida
     */
    private Map<Integer,TreeMap<String, Integer>> posiciones;
    /**
     * Constructora de la clase Ranking
     * @param dificultat Nivel de dificultat que reflejará el ranking
     * @param posiciones Mapa que tendrá como clave el nombre del jugador y como valor la puntuación de la partida
     */ 
    public Ranking() {
    	this.posiciones = new TreeMap<>();
        this.posiciones.put(1, new TreeMap<>(String::compareTo));
        this.posiciones.put(2, new TreeMap<>(String::compareTo));
        this.posiciones.put(3, new TreeMap<>(String::compareTo));
    }

    /**
     * Introduce una nueva posición en el mapa de posiciones
     * 
     * @param jugador         Nombre del jugador
     * @param puntuacion      Puntuación de la partida
     * @param nivelDificultad Nivel de dificultad de la partida
     */
    public void addPartida(String jugador, Integer puntuacion, Integer nivelDificultad) {
        TreeMap<String, Integer> rankingNivel = posiciones.get(nivelDificultad);
        if (rankingNivel == null) {
            rankingNivel = new TreeMap<>(String::compareTo);
            posiciones.put(nivelDificultad, rankingNivel);
        }
        // Comprueba si ya hay un jugador con el mismo nombre y si su puntuación es menor o igual a la nueva puntuación
        Integer currentScore = rankingNivel.get(jugador);
        if (currentScore == null || currentScore < puntuacion) {
            rankingNivel.put(jugador, puntuacion);
        }
        else if(currentScore < puntuacion){
        	
        }
    }

    /**
     * Devuelve el mapa de posiciones correspondiente a un nivel de dificultad determinado
     * @param nivelDificultad Nivel de dificultad cuyo ranking se desea obtener
     * @return Mapa de posiciones correspondiente al nivel de dificultad especificado
     */
    public TreeMap<String, Integer> getRanking(Integer nivelDificultad) {
        TreeMap<String, Integer> rankingNivel = posiciones.get(nivelDificultad);
        TreeMap<String, Integer> rankingOrdenado = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String jugador1, String jugador2) {
                int puntuacion1 = rankingNivel.get(jugador1);
                int puntuacion2 = rankingNivel.get(jugador2);
                return Integer.compare(puntuacion2, puntuacion1);
            }
        });
        rankingOrdenado.putAll(rankingNivel);
        return rankingOrdenado;
    }

	public Map<Integer,TreeMap<String, Integer>> getAllRanking() {
		return posiciones;
	}

}