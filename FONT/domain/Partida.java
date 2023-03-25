package domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;

/** Clase Partida. **/
public class Partida {
	
	/** Atributos **/
	
	private String fecha;
	private int puntos;
	private int dificultad; 
	private boolean ayuda; 

	/** Constructora **/
	
	public Partida(int dificultadEscogida) {
		this.fecha = getFechaIni();
		/** 1 = Facil, 2 = Medio, 3 = Dificil**/
		this.dificultad = dificultadEscogida;
		this.ayuda = false;
		this.puntos = 0;
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
	public setAyuda() {
		if (ayuda == false) this.ayuda = true;
		else this.ayuda = false;
	}

	public getDificultad() {
		return dificultad;
	}
}