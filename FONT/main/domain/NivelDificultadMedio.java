package main.domain;

import java.util.ArrayList;

public class NivelDificultadMedio extends NivelDificultad{
    public NivelDificultadMedio() {
   	    numcolumnas = 4;
   	    totalcombinacionesPosibles = new ArrayList<Combinacion>();
   	    solucionesEnviadas = new ArrayList<Combinacion>();
   	    possibleCodes = new ArrayList<Combinacion>();
   	    enviosCandidatos = new ArrayList<Combinacion>();
   	    turn = 1;
   	    
       }
    
    /**
     * 
     * @return numero de columnas permitido en el nivel
     */
    @Override
    public Integer getNumColumnas() {
       return this.numcolumnas;
    }
         
    /**
     * @param puntuacion obtenida por el codemaker
     * @param puntuacion obtenida por el codebreaker
     * @return numero de columnas permitido en el nivel
     */
    @Override
    public int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker) {
        return  10000 * numIntentCodeMaker + (10 - numIntentCodeBraker + 1)  * 10000;
     }
    
    /**
     * @return nivel de dificultad
     */
     @Override
     public Integer getDificultad() {
        return 2;
     }
       

}
