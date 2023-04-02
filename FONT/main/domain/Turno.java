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
		this.combinations = new ArrayList<>();
	}
	
	/** Métodos privados **/
    
	/** Métodos públicos **/
	
	public void changeTurno() {
		this.rol = !this.rol;
	}

	public boolean getRol(){
		return this.rol;
	}
	
	public boolean setCombinacion(ArrayList<Color> combination){
		Combinacion newCombinacion = new Combinacion(combination);
		combinations.add(newCombinacion);
		if(combinations.size() == 10) return true;
		return false;
	}
	
	public ArrayList<Combinacion> getCombinaciones() {
		return this.combinations;
	}

	public int getNumberComb() {
		return combinations.size();
	}


}