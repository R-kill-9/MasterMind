package main.domaincontrollers;

import java.util.ArrayList;
import java.util.Date;
import main.domain.Usuario;
import main.domain.Color;
import main.domain.ColorFeedBack;
import main.domain.Pair;
import main.domain.Partida;
import main.domain.PossiblesEstadosPartida;

/**
 * Clase que representa el controlador de dominio de la clase Usuario.
 */
public class CtrlUsuario {

	/**
	 * Array que contiene los usuarios del sistema.
	 */
	private final ArrayList<String> usuarios;
	private static Usuario userAct;

	/**
	 * Constructora por defecto.
	 */
	public CtrlUsuario() {
		this.usuarios = new ArrayList<>();
	}

	/**
	 * Añade un usuario al sistema.
	 *
	 * @param username Nombre de usuario.
	 */
	public void addUsuario(String username) {
		this.usuarios.add(username);
	}

	
	/**
	 * Devuele la lista de usuarios existentes
	 */
	public ArrayList<String> getUsuarios() {
		return this.usuarios;
	}

	public void loginUser(String username) throws Exception {
		Boolean exists = false;
		for (String user : this.usuarios) {
			if (user.equals(username)) {
				exists = true;
			}
		}
		if (!exists) {
			usuarios.add(username);
		}
		userAct = new Usuario(username);
		System.out.println(userAct);
	}

	/**
	 * Obtenemos el usuario con el nombre de usuario dado.
	 *
	 * @param username Nombre de usuario.
	 * @return Usuario con el nombre de usuario dado.
	 */
	public String getUsuarioActual() {
		return userAct.getUsername();
	}

	/**
	 * En caso de que la puntuación sea mayor que la máxima que ha conseguido el
	 * usuario, se actualiza el record.
	 */
	public static void setRecord(int puntuacion) {
		userAct.setMaxScore(puntuacion);
	}

	/**
	 * Obtenemos el record del usuario con el nombre de usuario dado.
	 *
	 * @param username Nombre de usuario.
	 * @return Record del usuario con el nombre de usuario dado.
	 */
	public static int[] getRecord() {
		return userAct.getMaxScore();
	}

	/**
	 * Obtenemos el ArrayList de partidas del usuario.
	 * @return ArrayList<Date> where date =  dateCreation
	 */
	public static ArrayList<Date> getDataPartidasUsuario() {
		return userAct.getDataPartidasGuardadas();
	}

	/**
	 * Obtenemos el ArrayList de partidas del usuario con el nombre de usuario dado.
	 * @return ArrayList<Pair<String, Date>> where string = username, date =
	 *         dateCreation
	 */
	public static ArrayList<Pair<String, Date>> getPartidasGuardadas() {
		String username = userAct.getUsername();
		return CtrlPartida.getInfoPartidasGuardadas(username);
	}

	/**
	 * Obtenemos el ArrayList de partidas guardadas en el historial
	 * @return ArrayList<Pair<String, Date>> where string = username, date =
	 *         dateCreation
	 */
	public static ArrayList<Pair<String, Date>> getPartidasHistorial() {
		return CtrlPartida.getPartidasHistorial();
	}

	/**
	 * Crea una nueva partida
	 */
	public static void crearPartida(int dificultadEscogida, boolean ayuda, boolean rol) {
		Partida newPartida = CtrlPartida.crearPartida(dificultadEscogida, userAct.getUsername(), ayuda, rol);
		Date dataPartida = newPartida.getData();
		userAct.addPartida(dataPartida);
		// Se tendria que añadir la partida al usuario falta funcion para devolver la
		// fecha de la partida
	}

	/**
	 * Borra una partida tanto del historial de partidas como de  la lista de partidas de usuario
	 * @param la fecha de la partida que queremos borrar
	 * @throws Exception
	 */
	public void borrarPartida(Date data) throws Exception {
		String username = userAct.getUsername();
		Boolean removedPartida = CtrlPartida.borrarPartida(username, data);
		if (removedPartida == false)
			throw new Exception("The partida does not exists");
		else {
			userAct.deletePartida(data);
		}
	}

	/*
	 * Solicita ayuda en la partida actual
	 */
	public void solicitarAyuda() {
		CtrlPartida.solicitarAyuda();
	}

	/*
	 * Obtiene elvalor de ayuda en la partida actual
	 */
	public static boolean getAyuda() {
		return CtrlPartida.getAyuda();
	}

	/**
	 * Obtiene una la partida actual del usuario.
	 *
	 * @param Vector<Color> la combinacion de la partida
	 * @return la partida jugada por ese usuario, o null si no hay ninguna
	 * @throws Exception
	 */
	public ArrayList<ColorFeedBack> newCombinacion(ArrayList<Color> combination) throws Exception{
		return CtrlPartida.newCombinacion(combination);
	}

	/**
	 * Obtiene una la partida actual del usuario.
	 *
	 * @param Vector<Color> la solucion de la partida
	 * @return el numero de intentos en resolver la solucion
	 * @throws Exception
	 */
	public static Integer setSolution(ArrayList<Color> combination) throws Exception{
		return CtrlPartida.setSolution(combination);
	}

	/*
	 * Sale de la partida actual
	 */
	public static void salirPartida() {
		CtrlPartida.salirPartida();
	}

	/*
	 * Devuelve un valor booleano dependiend de si existe una partida actuals
	 */
	public static Boolean existsPartidaActual() {
		return CtrlPartida.existsPartidaActual();
	}

	/*
	 * Reinicia los valores para el mismo turno
	 */
	public static void reiniciarPartida() {
		CtrlPartida.reiniciarPartida();
	}


	public static void cambiarEstadoPartida(String estado) {
		CtrlPartida.cambiarEstadoPartida(estado);
	}

	/*
	 * Devuelve el rol de la partida
	 */
	public static boolean getRol() {
		return CtrlPartida.getRol();
	}

	/*
	 * Devuelve la puntuacion de la partida
	 */
	public static int getScore() {
		int puntuacion= CtrlPartida.getScore();

		setRecord(puntuacion);
		return puntuacion;
		//return CtrlPartida.getScore();
	}
}
