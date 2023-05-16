package main.domain;

import java.util.ArrayList;



/** Clase Combinacion **/
public class Combinacion {
	
	/* 
	 * Atributos 
	 */
	private ArrayList<Color> combination;


	/* 
	 * Constructora 
	 * */
	
	public Combinacion(ArrayList<Color> combination) {
		
		this.combination = combination; 
	}
	

	/*
	 * Métodos públicos
	 */
	public ArrayList<Color> getCombination() {
		return combination;
	}


	public void setCombination(ArrayList<Color> combination) {
		this.combination = combination;
	}

	public Color getPosition(Integer i) {
		return combination.get(i);
	}

	public boolean contains(Color color) {
		return combination.contains(color);
	}

	public void add(Color colorAñadido) {
		combination.add(colorAñadido);
	}

    public void remove(Color colorEliminado) throws Exception {
    	boolean removed = this.combination.remove(colorEliminado);
    	if(!removed) throw new Exception("The color does not exists");
    }

	public void print() {
		System.out.print(" COMBI: " + combination.get(0)+ " "+ combination.get(1)+" "+combination.get(2)+" "+combination.get(3)+ "\n");
	
	}
	public void printL() {
		System.out.print(" COMBI: " + combination.get(0)+ " "+ combination.get(1)+" "+combination.get(2)+" "+combination.get(3)+" "+ combination.get(4)+ "\n");
	
	}

	/*
	 * Devuelve la combinación en formato Color
	 */
	public ArrayList<Color> getCombinationColor() {
		return combination;
	}


}