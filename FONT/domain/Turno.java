package domain;

import java.util.Collection;
import domain.Combinacion;


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
	
	public setSecuencia() {
		Combinacion newCombinacion = new Combinacion();
		return setCombinacion;
	}

}