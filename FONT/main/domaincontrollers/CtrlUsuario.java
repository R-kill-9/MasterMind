package main.domaincontrollers;

import java.util.ArrayList;
import java.util.Date;
import main.domain.Usuario;
import main.domain.Color;
import main.domain.ColorFeedBack;
import main.domain.Pair;
import main.domain.Partida;

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

	public void loginUser(String username) throws Exception {
		Boolean exists = false;
		for (String user : this.usuarios) {
			if (user.equals(username)) {
				exists = true;
			}
		}
		if (!exists) {
			userAct = new Usuario(username);
			usuarios.add(username);
		}
	}

	/**
	 * Obtenemos el usuario con el nombre de usuario dado.
	 * 
	 * @param username Nombre de usuario.
	 * @return Usuario con el nombre de usuario dado.
	 */
	public String getUsuarioActual(String username) {
		return userAct.getUsername();
	}

	/**
	 * En caso de que la puntuación sea mayor que la máxima que ha conseguido el
	 * usuario, se actualiza el record.
	 */
	public void setRecord(int puntuacion) {
		userAct.setMaxScore(puntuacion);
	}

	/**
	 * Obtenemos el record del usuario con el nombre de usuario dado.
	 * 
	 * @param username Nombre de usuario.
	 * @return Record del usuario con el nombre de usuario dado.
	 */
	public int getRecord() {
		return userAct.getMaxScore();
	}

	/**
	 * Añade una partida al usuario con el nombre de usuario dado.
	 * 
	 * @param username Nombre de usuario.
	 * @param p        Partida.
	 */
	public void addPartida(Date fecha) {
		userAct.addPartida(fecha);
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
	 * Borra la partida con el identificador dado del usuario con el nombre de
	 * usuario dado.
	 * 
	 * @param username Nombre de usuario.
	 * @param fecha    Fecha de la partida.
	 */
	public static void deletePartida(Date fecha) {
		userAct.deletePartida(fecha);
		CtrlPartida.borrarPartida(userAct.getUsername(), fecha);
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
	 * Borra una partida
	 * @param la fecha de la partida que queremos borrar
	 * @throws Exception
	 */
	public void borrarPartida(Date data) throws Exception {
		String username = userAct.getUsername();
		Boolean removedPartida = CtrlPartida.borrarPartida(username, data);
		if (removedPartida == null)
			throw new Exception("The partida does not exists");
		else {
			deletePartida(data);
			userAct.deletePartida(data);
		}
	}
	
	public void solicitarAyuda() {
		CtrlPartida.solicitarAyuda();
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

	public static void salirPartida() {
		CtrlPartida.salirPartida();
	}
	
	public static void reiniciarPartida() {
    	CtrlPartida.reiniciarPartida();
    }
	

}