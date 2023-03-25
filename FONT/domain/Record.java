package domain.classes;

public class Record {
    /**
     * Puntuación más alta que ha conseguido el jugador
     */
    private int maxScore;


    /**
     * Constructora de la clase Record
     * @param maxScore Puntuación más alta que ha conseguido el jugador
     */
    public Record(int maxScore) {
        this.maxScore = maxScore;
    }

    /**
     * Devuelve la puntuación más alta que ha conseguido el jugador
     */
    public int getMaxScore() {
        return maxScore;
    }

    /**
     * Introduce la puntuación más alta que ha conseguido el jugador
     */
    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
    
}
