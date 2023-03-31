package main.domain;

import java.util.ArrayList;



/** Clase Combinacion. **/
public class Combinacion {
	
	/** Atributos **/
	private ArrayList<Color> combination;


	/** Constructora **/
	
	public Combinacion(ArrayList<Color> combination) {
		
		this.combination = combination; 
	}


	public ArrayList<Color> getCombination() {
		return combination;
	}


	public void setCombination(ArrayList<Color> combination) {
		this.combination = combination;
	}
	
	/** Métodos privados **/

	/** Métodos públicos **/
	/*
	public setCombinacion(int *comb) {
        //Creamos esta clase para leer valores por consola
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < tamaño; ++i) {
            int comb[i] = scanner.nextInt();
        }
        return comb;
	}*/

}