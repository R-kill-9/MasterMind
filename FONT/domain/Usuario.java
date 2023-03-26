package domain;
import java.util.ArrayList;

/**
*Clase Usuario.
*/
public class Usuario {
	
	/** 
<<<<<<< HEAD
	*Atributos 
	*/
	
	private String username;

	/** 
	*Constructora 
	*/
=======
	 * Nombre de usuario.
	 */
	
	private String username;

	/**
	 * Record personal de cada usua
	 */
	private int maxScore;

	/**
	 * ArrayList de partidas del usuario
	 */

	 private final ArrayList<Partida> partidas;


	/** 
	 * Constructora por defecto.
	 * El record en un principio es 0
	 * El ArrayList de partidas se inicializa vacío
	 */
>>>>>>> 2dceb22c6494b782ccdb34293c13d5025d7cf810
	
	public Usuario(String username) {
		this.username = username;
		this.maxScore = 0;
		this.partidas = new ArrayList<>();
	}


	/**
	 * Devuelve el nombre de usuario.
	 */
	public String getUsername() {
		return username;
	}

	/**
     * Devuelve la puntuación más alta que ha conseguido el jugador
     */
    public int getMaxScore() {
        return maxScore;
    }

	/**
     * Establece la puntuación más alta que ha conseguido el jugador
     */
    public void setMaxScore(int maxScore) {
        if (maxScore > this.maxScore){
        this.maxScore = maxScore;
        }
    }

	/**
	 * Añade una partida al ArrayList de partidas del usuario
	 */
	public void addPartida(Partida p) {
		this.partidas.add(p);
	}

	
	/**
	 * Borra una partida del ArrayList de partidas del usuario
	 */
	public void deletePartida(String fecha){
		Partida p = this.getPartida(fecha);
		if (p != null) {
			this.partidas.remove(p);
		}
	}

	/**
	 * Devuelve una partida del ArrayList de partidas del usuario
	 */
	public Partida getPartida(String fecha){
		for (Partida p : this.partidas) {
			if (p.getFecha().equals(fecha)) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Devuelve el ArrayList de partidas del usuario
	 */
	public ArrayList<Partida> getPartidasGuardadas() {
		return this.partidas;
	}
	
<<<<<<< HEAD
	/** 
	*Métodos públicos 
	*/
=======

}
>>>>>>> 2dceb22c6494b782ccdb34293c13d5025d7cf810
	
