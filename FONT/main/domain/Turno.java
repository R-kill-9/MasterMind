package main.domain;

import java.util.ArrayList;
import java.util.List;


/** Clase Turno. **/
public class Turno {
	
	/** Atributos **/
    /** true = CodeMaker, false = CodeBreaker**/
	
	private boolean rol;
	public ArrayList<Combinacion> combinations;


	/** Constructora **/
	
	public Turno(boolean rolEscogido) {
		this.rol = rolEscogido;
		this.combinations = new ArrayList<>();
	}
    
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
	
	public Combinacion getLastCombinacion(){
		Combinacion lastCombinacion = combinations.get(getNumberComb()-1);
		return lastCombinacion;
	}

	public int getNumberComb() {
		return combinations.size();
	}
	
	public void setAllComb(List<Combinacion> combHechas) {
		combinations.addAll(combHechas);
	}
	public void eraseCombinations() {
		combinations.clear();
	}


}