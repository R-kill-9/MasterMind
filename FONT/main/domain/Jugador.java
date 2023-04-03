package main.domain;

public class Jugador {

	/*
	 * Username del jugador
	 */

	private String username;

	/*
	 * Constructora de la clase Jugador
	 */

	public Jugador(String username) {
		this.username = username;
	}

	/*
	 * Devuelve el username del jugador
	 */

	public String getUsername() {
		return username;
	}
}