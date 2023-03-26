package domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import domain.Combinacion;
import domain.EstadoPartida;

/** Clase Partida. **/
public class Partida {
	
	/** Atributos **/
	private String fecha;
	private int puntos;
	private int dificultad; 
	private boolean ayuda; 
	private EstadoPartida estadoPartida;
	/** Constructora **/
	
	public Partida(int dificultadEscogida) {
		this.fecha = getFechaIni();
		/** 1 = Facil, 2 = Medio, 3 = Dificil**/
		this.dificultad = dificultadEscogida;
		this.ayuda = false;
		this.puntos = 0;
		this.estadoPartida = new EstadoPartida();
	}
	
	/** Métodos privados **/
	private getFechaIni() {
		
		/**Instanciamos el objeto Calendar en fecha obtenemos la fecha y hora del sistema **/
        Calendar fecha = new GregorianCalendar();
  
        /**Obtenemos el valor del año, mes, día,
        hora, minuto y segundo del sistema
        usando el método get y el parámetro correspondiente **/
		int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
		
		/**Creamos el String ordenando la fecha en este orden**/
		String fechaRetorno = año + ':' + mes + ':' + dia + ':' hora + ':' + minuto + ':' + segundo;
		return fechaRetorno;
	}

	/** Métodos públicos **/
	public String getEstadoPartida() {
        return estadoPartida.getEstado();
    }

	public setAyuda() {
		if (ayuda == false) this.ayuda = true;
		else this.ayuda = false;
	}

	public getDificultad() {
		return dificultad;
	}

	/**Introduce la solución para este turno **/
	public getSolution() {
        /**Dependiendo de la dificultad el tmaño será 4(fácil, medi) o 5(difícil) **/
        int tamaño;
        if (this.dificultad == 1 or this.dificultad == 2) tamaño = 4;
        else tamaño = 5;
        int comb[] = new int[tamaño]
        
		Combinacion newCombinacion = new Combinacion();
		newCombinacion.setCombinacion(comb);
	}
}