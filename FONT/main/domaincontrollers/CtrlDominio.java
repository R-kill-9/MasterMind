package main.domaincontrollers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.text.SimpleDateFormat;

import main.domain.Color;
import main.domain.ColorFeedBack;
import main.domain.HistorialPartidas;
import main.domain.HistorialPartidasGuardadas;
import main.domain.Pair;
import main.domain.Juego;
import main.domain.Ranking;

import main.persistence.CtrlPersistence;

/** Ejemplo de Controlador de Dominio. **/
public class CtrlDominio {

	/** Atributos **/

	private static CtrlUsuario controladorUsuario;
	private static CtrlDominio instance;
	private static Juego juego;
	private static Ranking rankingGlobal;
	private static HistorialPartidas historialPartidas;
	private static HistorialPartidasGuardadas historialPartidasGuardadas;
	private static CtrlPersistence ctrlPersistence;

	/** Constructor y metodos de inicializacion **/

	private CtrlDominio() {
		setControladorUsuario(new CtrlUsuario());
		setRankingGlobal(new Ranking());
		setJuego(Juego.getInstance());
		historialPartidas = new HistorialPartidas();
		historialPartidasGuardadas = new HistorialPartidasGuardadas();
		ctrlPersistence = CtrlPersistence.getInstance();
	}

	public static CtrlDominio getInstance(){
		if (instance == null) {
			instance = new CtrlDominio();
		}
		return instance;
	}
	
	public static ArrayList<ArrayList<Color>> getAllCombLastTurno() {
		return CtrlUsuario.getAllCombLastTurno();
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

	/*
	 * Se encarga de la persistencia de los rankings
	 */
	public void restoreRanking() {
		ctrlPersistence.checkRanking();
		if (ctrlPersistence.existsRanking1()) {
			rankingGlobal.cargarRanking(1, ctrlPersistence.loadRanking(1));
		}
		if (ctrlPersistence.existsRanking2()) {
			rankingGlobal.cargarRanking(2, ctrlPersistence.loadRanking(2));
		}
		if (ctrlPersistence.existsRanking3()) {
			rankingGlobal.cargarRanking(3, ctrlPersistence.loadRanking(3));
		}

	}
	/*
	 * Restaura el record del usuario
	 */
	public void restoreRecord() {
		if (ctrlPersistence.existsRecord()) {
		int record[] = ctrlPersistence.loadRecord();
		for (int i = 0; i < 5; ++i) {
			CtrlUsuario.setRecord(record[i]);
		}
	}
	}

	public void loginUser(String username) throws Exception {
		controladorUsuario.loginUser(username);
		ctrlPersistence.checkUser(username);

	}

	public void newUser(String username){
		controladorUsuario.addUsuario(username);
	}

	public void crearPartida(int dificultadEscogida, boolean ayuda, boolean rol) {
		CtrlUsuario.crearPartida(dificultadEscogida, ayuda, rol);
	}

	/*
	 * Carga la partida con el id indicado
	 */
	public static ArrayList<ArrayList<Color>> cargarPartida(String id) {
		//Pedimos lo necesario a la persistencia
		int nTurno = ctrlPersistence.getNTurno(id);
		boolean rol = ctrlPersistence.getRol(id);
		ArrayList<Color> solucion = ctrlPersistence.getSolucion(id);
		boolean ayuda = ctrlPersistence.getAyuda(id);
		int puntuacion = ctrlPersistence.getPuntuacion(id);
		int dificultadEscogida = ctrlPersistence.getDificultad(id);
		ArrayList<ArrayList<Color>> combinaciones = ctrlPersistence.getCombinaciones(id);
		int rondasMaquina = ctrlPersistence.getRondasMaquina(id);
		CtrlUsuario.crearPartidaGuardada(id, nTurno, rol, solucion, ayuda, puntuacion, dificultadEscogida, combinaciones, rondasMaquina);
		return combinaciones;
		//ctrlPersistence.deletePartida(id);
	}

	public ArrayList<ColorFeedBack> newCombinacion(ArrayList<Color> combination) throws Exception{
		return controladorUsuario.newCombinacion(combination);
	}
	
	public static Integer getNumRounds() {
		return CtrlUsuario.getLastTurno();
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
	}
	*/
	public static ArrayList<String> getPartidasGuardadas(){
		return ctrlPersistence.getPartidasGuardadas();
	}

	public static TreeMap<String, Integer> getRankingGlobalUnNivel(Integer nivel) {
		return rankingGlobal.getRanking(nivel);
	}

	/*
	 * Set record del usuario
	 */
	public static void setRecord(int puntuacion){
		CtrlUsuario.setRecord(puntuacion);
		int record[] = CtrlUsuario.getRecord();
		ctrlPersistence.saveRecord(record);
	}

	

	/*
	 * Obtenemos los records del usuario
	 */
	public static int[] getRecord(){
		return CtrlUsuario.getRecord();
	}

	/*
	 * Obtenemos la puntuacion de la partida
	 */
	public static int  getScore(){
		return CtrlUsuario.getScore();
	}

	public static Map<Integer,TreeMap<String, Integer>> getAllRankingGlobal() {
		return rankingGlobal.getAllRanking();
	}


	public static void setRankingGlobal(Ranking rankingGlobal) {
		CtrlDominio.rankingGlobal = rankingGlobal;
	}

	public static boolean getRol() {
		return CtrlUsuario.getRol();
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

	/**
	 * Funcion para añadir partida al ranking
	 */
	public static void addPartidaRanking(String jugador, Integer puntuacion, Integer nivelDificultad) {
		rankingGlobal.addPartida(jugador,puntuacion,nivelDificultad);
		ctrlPersistence.saveRanking(nivelDificultad, rankingGlobal.getRanking(nivelDificultad));
		
	}

	/*
	 * Guarda la partida actual
	 */
	public static void guardarPartida() {
		String id = CtrlUsuario.getfechaIni();
		int nTurno = CtrlUsuario.getLastTurno();
		boolean rol = CtrlUsuario.getRol();
		ArrayList<Color> solucion = CtrlUsuario.getSolution();
		boolean ayuda = CtrlUsuario.getAyuda();
		int puntuacion = CtrlUsuario.getScore();
		int dificultad = CtrlUsuario.getDificultad();
		ArrayList<ArrayList<Color>> combinaciones = CtrlUsuario.getCombinacionesEnviadas();
		int rondasMaquina = CtrlUsuario.getRondasMaquina();

		ctrlPersistence.savePartida(id, nTurno, rol, solucion, ayuda, puntuacion, 
		dificultad, combinaciones, rondasMaquina);
	}
	
	public static void solicitarAyuda() {
		controladorUsuario.solicitarAyuda();
	}

	public static Boolean getAyuda() {
		return CtrlPartida.getAyuda();
	}

}

