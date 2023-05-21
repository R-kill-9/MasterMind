package main.domain;

import java.util.ArrayList;
import java.util.List;

public class NivelDificultadAlto extends NivelDificultad {
    
	public NivelDificultadAlto() {
   	    numcolumnas = 5;
   	    sePuedeRepetir = true;
   	    turn = 1;
        Nsolucion = "NNNNN";
   	    maquinaResolve = new Genetic(this);
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
    @Override
	public String getNsolucion() {
		return Nsolucion;
	}
    /**
     * @return numero de columnas permitido en el nivel
     */
    @Override
    public Integer getNumColumnas() {
    	return numcolumnas;
    }
    

      
}
