package main.domain;

import java.util.ArrayList;

public class NivelDificultadAlto extends NivelDificultadMedio {
    public NivelDificultadAlto() {
    	numColors = 6;
   	    numcolumnas = 5;
   	    totalcombinacionesPosibles = new ArrayList<Combinacion>();
   	    solucionesEnviadas = new ArrayList<Combinacion>();
   	    possibleCodes = new ArrayList<Combinacion>();
   	    enviosCandidatos = new ArrayList<Combinacion>();
   	    turn = 1;
   	    NumNegras ="NNNNN";
    }
    @Override
    public int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker) {
        return  1000000 * numIntentCodeMaker + (10 - numIntentCodeBraker + 1)  * 1000000;
    }

    @Override
    public Integer getDificultad() {
       return 3;
    }

    @Override
    public Integer getNumColumnas() {
    	return this.numcolumnas;
    }
    @Override
    public Integer getNumColors() {
    	return this.numColors;
    }
    
    @Override
    public String getNumNegras() {
    	return this.NumNegras;
    }
}
