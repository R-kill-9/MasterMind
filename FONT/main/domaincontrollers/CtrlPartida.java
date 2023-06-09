package main.domaincontrollers;
import java.util.Date;

import java.util.HashMap;
import java.util.ArrayList;
import main.domain.Usuario;
import main.domain.Color;
import main.domain.ColorFeedBack;
import main.domain.Partida;
import main.domain.PossiblesEstadosPartida;
import main.domain.HistorialPartidas;
import main.domain.HistorialPartidasGuardadas;
import main.domain.Pair;

/**
 * Clase que representa el controlador de dominio de la clase Partida.
 */
public class CtrlPartida {
    private static  Partida partidaActual;
    /**
     * Constructora por defecto.
     */
    public CtrlPartida() {
    }
     /**
     * Crea una nueva partida y la añade a la lista de partidas.
     * 
     * @param dificultadEscogida la dificultad elegida para la partida
     * @param username el usuario que juega la partida
     * @param ayuda true si se activa la ayuda, false en caso contrario
     * @param rol true si el usuario es el CodeMaker, false si es el CodeBreaker
     */
    public static Partida crearPartida(int dificultadEscogida, String username, boolean ayuda, boolean rol) {
        Partida partida = new Partida(dificultadEscogida, username, ayuda, rol);
        Date dataPartida = partida.getData();
        
        HistorialPartidas.agregarPartida(username,dataPartida);
        partidaActual = partida;
        return partida;
    }
     
    /*
     * Crea una partida a partir de una partida guardada
     */
    public static Partida crearPartidaGuardada(String data, int nTurno, boolean rol, ArrayList<Color>solucion, boolean ayuda, String username,
	 				int puntuacion, int dificultadEscogida, ArrayList<ArrayList<Color>> combinaciones, int rondasMaquina) {
        Partida partida = new Partida(data, nTurno, rol, solucion, ayuda, username, puntuacion, dificultadEscogida, combinaciones, rondasMaquina);
        partidaActual = partida;
        return partida;
        }

    public static Boolean borrarPartida(String username, Date dataPartida) {
        return HistorialPartidas.borrarPartida(username,dataPartida);
    }

     /**
     * Obtiene la lista de partidas que hay en el historial.
     * 
     * @return la lista de partidas
     */
    public ArrayList<Pair<String, Date>> getPartidas() {
        return HistorialPartidas.getPartidas();
    }

     
    /**
     * Obtiene la partida actual del usuario.
     * @return la partida jugada por ese usuario, o null si no hay ninguna 
     */
    public Partida getPartidaActual()  {
    	return this.partidaActual;
    }
    
    /**
     * Devuelve un valor booleano dependiendo de la existencia de una partida actual.
     */
    public static Boolean existsPartidaActual()  {
        if(partidaActual != null) return true;
        else return false;
    }
    
    
    /**
     * Introduce una nueva combinacion.
     * 
     * @param Vector<Color> la combinacion de la partida
     * @return la partida jugada por ese usuario, o null si no hay ninguna
     * @throws Exception 
     */
    public static ArrayList<ColorFeedBack> newCombinacion(ArrayList<Color> combination) throws Exception{
        return partidaActual.setCombinacion(combination);
    }
    
    /**
     * Obtiene una la partida actual del usuario.
     * 
     * @param Vector<Color> la solucion de la partida
     * @return el numero de intentos en resolver la solucion
     * @throws Exception 
     */
    public static Integer setSolution(ArrayList<Color> combination) throws Exception{
        return partidaActual.setSolution(combination);
    }
    
    /**
     * Obtiene información de las partidas guardadas por el usuario.
     * 
     * @param usuario el usuario que ha jugado la partida
     * @param estado el estado de las partidas: guardadas o pausadas.
     * @return la partida jugada por ese usuario, o null si no hay ninguna
     */
    public static ArrayList<Pair<String, Date>> getInfoPartidasGuardadas(String usuario) {
        return HistorialPartidasGuardadas.getPartidas(usuario);
    }
    
    /**
     * Obtiene información del historial de partidas.s
     * 
     * @param usuario el usuario que ha jugado la partida
     * @param estado el estado de las partidas: guardadas o pausadas.
     * @return la partida jugada por ese usuario, o null si no hay ninguna
     */
    public static ArrayList<Pair<String, Date>> getPartidasHistorial() {
        return HistorialPartidas.getPartidas();
    }
    
    /**
     * Obtiene información de las partidas.
     * @param una partida
     * @return la informacion de la partida, cuando se ha jugado y la puntuacion.
     */
	public static Pair<Date, Integer> getInfoPartida(Partida partida) {
		Date dataP = partida.getData();
		Integer nivel = partida.getDificultad();
		Pair<Date, Integer> infoPartida = new Pair<>(dataP, nivel);
	    return infoPartida;
	}
	
	/*
	 * Activa la opción de ayuda en la partida actual
	 */
	public static void solicitarAyuda() {
		partidaActual.setAyuda();
	}
	
	/*
	 * Obtiene el valor de ayuda en la partida actual
	 */
	public static  boolean getAyuda() {
		return partidaActual.getAyuda();
	}

	
	/*
	 * Sale de la partida actual
	 */
	public static void salirPartida() {
		partidaActual = null;
	}
	public static Integer getNumRounds() {
		return partidaActual.getLastTurno();
	}
	
	public static ArrayList<ArrayList<Color>> getAllCombLastTurno() {
		return partidaActual.getAllCombLastTurno();
	}
	/*
	 * Reinicia la partida actual
	 */
	public static void reiniciarPartida() {
		partidaActual.reiniciarPartida();
		
	}
	public static void cambiarEstadoPartida(String estado) {
		partidaActual.setEstadoPartida(estado);
	}
	/*
	 * Devuelve el score de una partida
	 */
	public static int getScore() {
    	return partidaActual.getScore();
    }

    /*
     * Devuelve el rol de la partida
     */
    public static boolean getRol() {  
        return partidaActual.getRol();
    }

    /*
	 * Devuelve la fehca de la partida
	 */
	public static String getfechaIni(){
		return partidaActual.getFechaIni();
	}

    /*
     * Devuelve la solucion de la partida
     */
    public static ArrayList<Color> getSolution() {
        return partidaActual.getSolutions();
    }

    /*
     * Devuelve la dificultad de la partida
     */
    public static int getDificultad() {
        return partidaActual.getDificultad();
    }
    /*
	 * Devuelve el numero de rondas de la partia hechas por la maquina
	 */
    public static int getRondasMaquina() {
        return partidaActual.getRondasMaquina();
    }

   
    /*
	 * Devuelve las combinaciones enviadas
	 */
    public static ArrayList<ArrayList<Color>> getCombinacionesEnviadas() {
        return partidaActual.getCombinacionesEnviadas();
    }

    



}