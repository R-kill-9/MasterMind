package main.domain;

import java.util.ArrayList;


/** Clase Turno. **/
public class Turno {
	
	/** Atributos **/
    /** true = CodeMaker, false = CodeBreaker**/
	private boolean rol;
	public ArrayList<Combinacion> combinations;


	/** Constructora **/
	
	public Turno(boolean rolEscogido) {
		/** 1 = CodeMaker, 2 = CodeBreaker**/
		this.rol = rolEscogido;
	}
	
	/** Métodos privados **/
    
	/** Métodos públicos **/
	/*
	public changeTurno() {
		this.rol = !this.rol;
	}*/

	public boolean getRol(){
		return this.rol;
	}
	
	public boolean setCombinacion(ArrayList <Color> combination){
		Combinacion newCombinacion = new Combinacion(combination);
		combinations.add(newCombinacion);
		if(combinations.size() == 10) return true;
		return false;
	}

	public int getNumberComb() {
		return combinations.size();
	}


}