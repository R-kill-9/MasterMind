package domain;

import java.util.Collection;
import domain.Partida;
import java.util.Scanner;


/** Clase Combinacion. **/
public class Combinacion {
	
	/** Atributos **/
	private int comb[];


	/** Constructora **/
	
	public Combinacion() {
		/** 1 = CodeMaker, 2 = CodeBreaker**/
		this.comb = [];
	}
	
	/** Métodos privados **/

	/** Métodos públicos **/
	
	public setCombinacion(int *comb) {
        //Creamos esta clase para leer valores por consola
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < tamaño; ++i) {
            int comb[i] = scanner.nextInt();
        }
        return comb;
	}

}