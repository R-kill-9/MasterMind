package main.domaincontrollers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import main.domain.Color;
import main.domain.ColorFeedBack;
import main.domain.Pair;
import main.domain.Juego;
import main.domain.Ranking;

/** Ejemplo de Controlador de Dominio. **/
public class CtrlDominio {

	/** Atributos **/
	
	private CtrlUsuario controladorUsuario;
	private static CtrlDominio instance;
	private static Juego juego;
	private static Ranking rankingGlobal;

	/** Constructor y metodos de inicializacion **/

	private CtrlDominio() {
		setControladorUsuario(new CtrlUsuario());
		setRankingGlobal(new Ranking());
		setJuego(Juego.getInstance());
	}
	
	public static CtrlDominio getInstance(){
		if (instance == null) {
			instance = new CtrlDominio();
		}
		return instance;
	}

	/**
	 * Funciones que se llaman desde el controlador de presentacion. Por
	 * convención, únicamente se usan Strings para la comunicación entre las dos
	 * capas.
	 **/
	
	public CtrlUsuario getControladorUsuario() {
		return controladorUsuario;
	}
	
	public void setControladorUsuario(CtrlUsuario controladorUsuario) {
		this.controladorUsuario = controladorUsuario;
	}
	
	public void loginUser(String username) throws Exception {
		controladorUsuario.loginUser(username);
	}
	
	public void newUser(String username){
		controladorUsuario.addUsuario(username);
	}
	
	public void crearPartida(int dificultadEscogida, boolean ayuda, boolean rol) {
		CtrlUsuario.crearPartida(dificultadEscogida, ayuda, rol);
	}
	
	public ArrayList<ColorFeedBack> newCombinacion(ArrayList<Color> combination) throws Exception{
       return controladorUsuario.newCombinacion(combination);
	}
	
	/**
     * Obtiene una la partida actual del usuario.
     * 
     * @param Vector<Color> la solucion de la partida
     * @return el numero de intentos en resolver la solucion
     * @throws Exception 
     */
    public static Integer setSolution(ArrayList<Color> combination) throws Exception{
        return CtrlUsuario.setSolution(combination);
    }
    
    public static void salirPartida() {
		CtrlUsuario.salirPartida();
	}
	
    public static void reiniciarPartida() {
    	CtrlUsuario.reiniciarPartida();
    }
    /**
	 * Obtenemos el ArrayList de partidas del usuario actual.
	 * @return ArrayList<Pair<String, Date>> where string = username, date =
	 *         dateCreation
	 */
	public static ArrayList<Pair<String, Date>> getPartidasGuardadas() {
		return CtrlUsuario.getPartidasGuardadas();
	}
	
	public static TreeMap<String, Integer> getRankingGlobalUnNivel(Integer nivel) {
		return rankingGlobal.getRanking(nivel);
	}
	

	public static Map<Integer,TreeMap<String, Integer>> getAllRankingGlobal() {
		return rankingGlobal.getAllRanking();
	}


	public static void setRankingGlobal(Ranking rankingGlobal) {
		CtrlDominio.rankingGlobal = rankingGlobal;
	}

	public static String getJuegoInfo() {
		return juego.getInformacionSistema();
	}
	public static String getJuegoInfoPuntuacion() {
		return juego.getInformacionPuntuacion();
	}

	public static void setJuego(Juego juego) {
		CtrlDominio.juego = juego;
	}
	
	public static void cambiarEstadoPartida(String estado) {
		CtrlUsuario.cambiarEstadoPartida(estado);;
	}
}
