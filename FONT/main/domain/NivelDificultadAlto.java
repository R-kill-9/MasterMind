package main.domain;

import java.util.ArrayList;

public class NivelDificultadAlto extends NivelDificultadMedio {
    public NivelDificultadAlto() {
   	    numcolumnas = 5;
   	    sePuedeRepetir = true;
   	    totalcombinacionesPosibles = new ArrayList<Combinacion>();
   	    solucionesEnviadas = new ArrayList<Combinacion>();
   	    possibleCodes = new ArrayList<Combinacion>();
   	    enviosCandidatos = new ArrayList<Combinacion>();
   	    turn = 1;
   	    NumNegras ="NNNNN";
    }
    /**
     * @param puntuacion obtenida por el codemaker
     * @param puntuacion obtenida por el codebreaker
     * @return calcula la puntuacion de la partida
     */
    @Override
    public int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker) {
        return  1000000 * numIntentCodeMaker + (10 - numIntentCodeBraker + 1)  * 1000000;
    }
    
    /**
     * @return el nivel de dificultad
     */
    @Override
    public Integer getDificultad() {
       return 3;
    }
    
    /**
     * @return numero de columnas permitido en el nivel
     */
    @Override
    public Integer getNumColumnas() {
    	return this.numcolumnas;
    }
    
    @Override
    public String getNumNegras() {
    	return this.NumNegras;
    }
}
