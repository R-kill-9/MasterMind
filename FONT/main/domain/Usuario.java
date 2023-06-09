package main.domain;

import java.util.Date;
import java.util.ArrayList;


/**
 *Clase Usuario.
 */
public class Usuario {

	/**
	 *Atributos
	 */

	private String username;
	private int[] maxScore;
	private ArrayList<Date> fechaPartidas;

	/**
	 *Constructora
	 */

	public Usuario(String username) {
		this.username = username;
		this.maxScore = new int[5];
		for (int i = 0; i < 5; ++i) {
			this.maxScore[i] = 0;
		}
		this.fechaPartidas = new ArrayList<>();
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
	public int[] getMaxScore() {
		return maxScore;
	}

	/**
	 * Establece la puntuación más alta que ha conseguido el jugador
	 */
	public void setMaxScore(int Score) {
		int pos;
		//Busca la posición en la que se debe insertar la puntuación
		for (pos = 0; pos < maxScore.length && maxScore[pos]> Score; ++pos);
		//Desplazamos los valores a la derecha
		for (int i = maxScore.length-1; i > pos; --i) {
			maxScore[i] = maxScore[i-1];
		}
		if (pos < maxScore.length)
			maxScore[pos] = Score;
	}

	/**
	 * Añade una partida al ArrayList de partidas del usuario
	 */
	public void addPartida(Date data) {
		this.fechaPartidas.add(data);
	}


	/**
	 * Borra una partida del ArrayList de partidas del usuario
	 */
	public void deletePartida(Date fecha){
		Integer posPartida = this.fechaPartidas.indexOf(fecha);
		if (posPartida != null) {
			this.fechaPartidas.remove(fecha);
		}
	}

	/**
	 * Devuelve una partida del ArrayList de partidas del usuario
	 */
	public Date getDataPartida(Date fecha){
		for (Date fechaPartida : this.fechaPartidas) {
			if (fechaPartida == fecha) {
				return fechaPartida;
			}
		}
		return null;
	}

	/**
	 * Devuelve el ArrayList de partidas del usuario
	 */
	public ArrayList<Date> getDataPartidasGuardadas() {
		return this.fechaPartidas;
	}

}
