package main.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
/** 
*Clase Partida
*/
public class Partida {
	
	/** 
	* Atributos 
	*/
	private Date data;
	private int puntos;
	private boolean ayuda; 
	private EstadoPartida estadoPartida;
	public Combinacion solution;
	private NivelDificultad nivel;
	private String username;
	private ArrayList<Turno> turnos;

	/** 
	*Constructora 
     * @param dificultadEscogida la dificultad elegida para la partida
     * @param usuario el usuario que juega la partida
     * @param ayuda true si se activa la ayuda, false en caso contrario
     * @param rol true si el usuario es el CodeMaker, false si es el CodeBreaker
	*/
	public Partida(int dificultadEscogida, String usuario, boolean ayuda, boolean rol) {
		this.data = getFechaIni();
		/** 
		* 1 = Facil, 2 = Medio, 3 = Dificil
		*/
		switch(dificultadEscogida) {
			case 1:
				this.nivel = new NivelDificultadBajo();
				break;
			case 2:
				this.nivel = new NivelDificultadMedio();
				break;
			case 3:
				this.nivel = new NivelDificultadAlto();
				break;
			default:
				throw new  IllegalArgumentException("Nivel de dificultad: 1, 2 o 3");
		}
		this.turnos = new ArrayList<Turno>();
		this.turnos.add(new Turno(rol));
		if(!rol) this.solution = nivel.genCombinacion();
		this.ayuda = ayuda;
		this.puntos = 0;
		this.username = usuario;
		String estado = "running";
		this.estadoPartida = new EstadoPartida(estado);
	}
	
	/** 
	 * Métodos privados 
	 */
	private Date getFechaIni() {
		LocalDateTime fechaHoraActual = LocalDateTime.now();
		long millis = fechaHoraActual.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		return new Date(millis);
	}
	
	private void donePartida(){
		if(turnos.size() == 1) {
			boolean choosenRol = this.turnos.get(0).getRol();
			this.turnos.add(new Turno(!choosenRol));
		}
		else {
			Integer turnosCM = turnos.get(0).getNumberComb();
			Integer turnosCB = turnos.get(0).getNumberComb();
			if(!turnos.get(0).getRol()){
				Integer aux = turnosCM;
				turnosCM = turnosCB;
				turnosCB = aux;
			}
			nivel.calculaPuntuacion(turnosCM, turnosCB);
			HistorialPartidasGuardadas.agregarPartidaGuardada(username, data);
		}
	}
	private void checkIfReps(ArrayList<Color> combinacion) throws Exception  {
		 HashSet<Color> seenColors = new HashSet<Color>();
		    for (Color color : combinacion) {
		        if (seenColors.contains(color)) throw new Exception("For level 1 it's not allowed to repeat colors");
		        seenColors.add(color);
		    }
	}
	
	private void checkLevelExceptions(ArrayList<Color> combinacion) throws Exception {
		Integer nivelDif = nivel.getDificultad();
		if(nivelDif == 1) checkIfReps(combinacion);
		Integer numColums = combinacion.size();
		if(numColums > 4 && nivelDif < 3) throw new Exception("For level 1 and 2 only 4 colors are allowed");
	}
	private boolean checkIfAllCorrects(ArrayList<ColorFeedBack> feedBackSolution){
		ColorFeedBack firstElem = feedBackSolution.get(0);
		if(firstElem != ColorFeedBack.BLACK) return false;
		for(int i = 1; i < feedBackSolution.size(); i++){
			if(firstElem != feedBackSolution.get(i)) return false;
		}
		return true;
	}

	/** 
	*Métodos públicos 
	*/

	/**
	*Devuelve el estado de la partida 
	*/
	public PossiblesEstadosPartida getEstadoPartida() {
        return estadoPartida.getEstado();
    }

	/**
	*Devuelve la data de la partida 
	*/
	public Date getData() {
        return this.data;
    }

	/**
	*Devuelve el jugador de la partida 
	*/
	public String getUsuario() {
        return this.username;
    }


	/**
	*Activa el modo ayuda dentro de la partida
	 * @return 
	*/
	public void setAyuda() { 
		this.ayuda = ayuda ? this.ayuda : !this.ayuda;
	}
	/**
	*Devuelve la dificultad de la partida 
	 * @return 
	*/
	public Integer getDificultad() {
		return this.nivel.getDificultad();
	}

	/**
	* 	Devuelve la solución para este turno 
	*/
	public Combinacion getSolution() {
		return this.solution;
	}
	


	/**
	*Introduce la solución para este turno 
	 * @return 
	 * @throws Exception 
	*/
	public Integer setSolution(ArrayList<Color> combSolution) throws Exception{
		Combinacion newCombinacion = new Combinacion(combSolution);
		Turno lastTurno = this.turnos.get(turnos.size() - 1);
		checkLevelExceptions(combSolution);
		if(lastTurno.getRol()) this.solution = newCombinacion;
		else throw new Exception("Sólo el CodeBreaker puede hacer la solucion");
		Integer numIntentos = nivel.resolve(newCombinacion);
		donePartida();
		return numIntentos;
	}
	/**
	* Introduce un intento para este turno 
	*/
	public ArrayList<ColorFeedBack> setCombinacion(ArrayList<Color> combSolution) throws Exception{
		Turno lastTurno = this.turnos.get(turnos.size() -1);
		lastTurno.setCombinacion(combSolution);
		Combinacion lastComb = lastTurno.getLastCombinacion();
		checkLevelExceptions(combSolution);
		if(!lastTurno.getRol()){
			ArrayList<ColorFeedBack> feedBackSolution = new ArrayList<ColorFeedBack>(); 

			if(!ayuda) {
				String feedBack = nivel.comprobarCombinacion(this.solution, lastComb);
				for(char bola : feedBack.toCharArray()) {
				    ColorFeedBack cb = bola == 'n' ? ColorFeedBack.BLACK : ColorFeedBack.WHITE;
				    feedBackSolution.add(cb);
				}
				while(feedBackSolution.size() < nivel.getNumColumnas()) {
				    feedBackSolution.add(ColorFeedBack.GREY);
				}
			}
			else {
				String feedBack = nivel.comprobarCombinacionPista(this.solution, lastComb);
				for(char bola : feedBack.toCharArray()) {
					ColorFeedBack cb;
					if(bola == ' ') cb = ColorFeedBack.GREY;
					else cb = bola == 'n' ? ColorFeedBack.BLACK : ColorFeedBack.WHITE;
					feedBackSolution.add(cb);
				}
			}
			Boolean lastChance = lastTurno.getNumberComb() - 1 == 10 ? true : false;
			if(lastChance || checkIfAllCorrects(feedBackSolution)) donePartida();
			return feedBackSolution;
		}
		else throw new Exception("Sólo el CodeMaker puede hacer combinaciones");
	}

	/**
	*Devuelve la puntuación de la partida 
	 * @return 
	*/
	public int getPuntuacion() {
        return this.puntos;
	}

	/**
	*Devuelve la fecha de la partida 
	 * @return 
	*/
	public Date getFecha() {
        return this.data;
	}

	public Boolean getAyuda() {
		return this.ayuda;
	}

	public void reiniciarPartida() {
		Turno lastTurno = turnos.get(turnos.size() - 1);
		lastTurno.eraseCombinations();
	}
}