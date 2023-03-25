package domain;

import java.util.Collection;
import java.util.HashMap;


/** Clase Turno. **/
public class Turno {
	
	/** Atributos **/
    /** 1 = CodeMaker, 2 = CodeBreaker**/
	private int rol;


	/** Constructora **/
	
	public Turno(String rolEscogido) {
		/** 1 = CodeMaker, 2 = CodeBreaker**/
		this.rol = rolEscogido;
	}
	
	/** Métodos privados **/
    private setTurno() {
		if (this.rol == 1) this.rol = 2;
		else this.rol = 1;
	}

	/** Métodos públicos **/
	

	public getDificultad() {
		return dificultad;
	}
}