package domain;

import java.util.Collection;
import domain.Partida;
import java.util.Scanner;
import domain.Color;

/** Clase Combinacion. **/
public class Combinacion {
	
	/** Atributos **/
	private Vector<Color> comb;


	/** Constructora **/
	
	public Combinacion(Color[] comb) {
		/** 1 = CodeMaker, 2 = CodeBreaker**/
		this.comb = comb;
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