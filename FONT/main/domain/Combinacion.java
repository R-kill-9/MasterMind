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
		this.combination.add(colorAñadido);
	}

    public void remove(Color colorEliminado) {
		this.combination.remove(colorEliminado);
    }





}