package main.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	public ArrayList<Combinacion> solutions;
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
		this.solutions = new ArrayList<Combinacion>();
		if(!rol) solutions.add(convertIntegerToColor(nivel.genCombinacion()));
		this.ayuda = ayuda;
		this.puntos = 0;
		this.username = usuario;
		String estado = "running";
		this.estadoPartida = new EstadoPartida(estado);
	}
	
	private Combinacion convertIntegerToColor(List<Integer> combInteger) {
	    ArrayList<Color> result = new ArrayList<>(); 
	   	for (Integer num: combInteger) {
	        switch (num) {
	            case 0:
	                result.add(Color.RED);
	                break;
	            case 1:
	                result.add(Color.GREEN);
	                break;
	            case 2:
	                result.add(Color.BLUE);
	                break;
	            case 3:
	                result.add(Color.YELLOW);
	                break;
	            case 4:
	                result.add(Color.PURPLE);
	                break;
	            case 5:
	                result.add(Color.ORANGE);
	                break;
	            default:
	                break;
	        }
	    }
	    return new Combinacion(result);
	}
	
	private List<Integer> convertColorToInteger(Combinacion comb) {
	    List<Integer> result = new ArrayList<>(); 
	    ArrayList<Color> combination = comb.getCombination();
	    for (Color color : combination) {
	        switch (color) {
	            case RED:
	                result.add(0);
	                break;
	            case GREEN:
	                result.add(1);
	                break;
	            case BLUE:
	                result.add(2);
	                break;
	            case YELLOW:
	                result.add(3);
	                break;
	            case PURPLE:
	                result.add(4);
	                break;
	            case ORANGE:
	                result.add(5);
	                break;
	            default:
	                break;
	        }
	    }
	    return result;
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
			if(!this.turnos.get(1).getRol()) solutions.add(convertIntegerToColor(nivel.genCombinacion()));
		}
		else {
			Integer turnosCM = turnos.get(0).getNumberComb();
			Integer turnosCB = turnos.get(1).getNumberComb();
			System.out.println("A" + turnosCM);
			System.out.println("A" + turnosCB);
			if(!turnos.get(0).getRol()){
				Integer aux = turnosCM;
				turnosCM = turnosCB;
				turnosCB = aux;
			}
			this.puntos = ayuda ? nivel.calculaPuntuacion(turnosCM, turnosCB)/2 : nivel.calculaPuntuacion(turnosCM, turnosCB);
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
		if(numColums > 4 && nivelDif < 3) throw new Exception("For level 1 and 2 only 4 columns are allowed");
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
	public Combinacion getSolutionTorn(Integer numTorn) {
		return this.solutions.get(numTorn);
	}
	
	
	
	public void setEstadoPartida(String estado){
		this.estadoPartida.setEstado(estado);
	}
	

    public Integer getScore() {
    	return this.puntos;
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
		if(lastTurno.getRol()) solutions.add(newCombinacion);
		else throw new Exception("Sólo el CodeBreaker puede hacer la solucion");
		List<List<Integer>> combHechasInteger = nivel.resolve(convertColorToInteger(newCombinacion));
		ArrayList<Combinacion> combHechas = new ArrayList<Combinacion>();
		for(List<Integer> lista : combHechasInteger) {
			combHechas.add(convertIntegerToColor(lista));
		}
		turnos.get(getLastTurno() - 1).setAllComb(combHechas);
		donePartida();
		return combHechas.size();
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
				String feedBack = nivel.comprobarCombinacion(convertColorToInteger(solutions.get(solutions.size()-1)), convertColorToInteger(lastComb));
				for(char bola : feedBack.toCharArray()) {
				    ColorFeedBack cb = bola == 'N' ? ColorFeedBack.BLACK : ColorFeedBack.WHITE;
				    feedBackSolution.add(cb);
				}
				while(feedBackSolution.size() < nivel.getNumColumnas()) {
				    feedBackSolution.add(ColorFeedBack.GREY);
				}
			}
			else {
				String feedBack = nivel.comprobaCombinacionPista(convertColorToInteger(this.solutions.get(solutions.size()-1)), convertColorToInteger(lastComb));
				for(char bola : feedBack.toCharArray()) {
					ColorFeedBack cb;
					if(bola == 'N') cb = ColorFeedBack.BLACK;
					else if(bola == 'B') cb = ColorFeedBack.WHITE ;
					else cb = ColorFeedBack.GREY;
					feedBackSolution.add(cb);
				}
			}
			Boolean lastChance = lastTurno.getNumberComb() == 10 ? true : false;
			if(lastChance || checkIfAllCorrects(feedBackSolution)) donePartida();
			return feedBackSolution;
		}
		else throw new Exception("Sólo el CodeMaker puede hacer combinaciones");
	}
	
	public Combinacion getLastCombination() {
		if(turnos.size() == 0) return null;
		return this.turnos.get(turnos.size()-1).getLastCombinacion();
	}
	
	public Integer getNumCombinations() {
		if(turnos.size() == 0) return 0;
		return this.turnos.get(turnos.size()-1).getNumberComb();
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
		this.solutions.remove(solutions.size()-1);
		this.solutions.add(convertIntegerToColor(nivel.genCombinacion()));
		lastTurno.eraseCombinations();
	}
	
	public Integer getLastTurno() {
		return turnos.size();
	}

	/*
	 * Devuelve el rol actual
	 */
	public boolean getRol() {
		return this.turnos.get(turnos.size() - 1).getRol();
	}
}