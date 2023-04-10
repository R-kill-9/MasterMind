package main.domain;

import java.util.ArrayList;
import java.util.List;


public class NivelDificultadMedio extends NivelDificultad{
    public NivelDificultadMedio() {
    	numColors = 6;
   	    numcolumnas = 4;
   	    turn = 1;
   	    Nsolucion ="NNNN";
   	    maquinaResolve = new FiveGuess(this);
       }
    @Override
    public Integer getNumColumnas() {
    	return this.numcolumnas;
    }
    @Override
    public Integer getNumColors() {
    	return this.numColors;
    }
    
       //NumIntentCodeMaker son los intentos que necesita el rival para obtener la solucion, siendo el jugador el codeMaker
       //NumIntentCodeBreaker son los intentos que necesita el jugador para obtener la solucion
       @Override
       public int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker) {
           return  10000 * numIntentCodeMaker + (10 - numIntentCodeBraker + 1)  * 10000;
        }
    // Given the solution code, the solve operation uses one of the proposed algorithms (either five guess or the genetic one) to create the list of codes that will lead to the solution. If the algorithm is unable to find the solution in less than maxSteps steps, the returned list will contain a list composed of maxSteps codes. The operation will throw an exception in case the secret code solution is not consistent with the parameters of the current game
       @Override
       public Integer getDificultad() {
           return 2;
       }
       
       @Override
   	public List<Combinacion> generarCombinaciones(Boolean[] visto, int i, ArrayList<Color> sol ){
   	    List<Combinacion> combinaciones = new ArrayList<>();

   	    if(i >= getNumColumnas()) {
   	    	ArrayList<Color> ac = new ArrayList<>(sol);
   	        Combinacion combi = new Combinacion(ac);
   	        combinaciones.add(combi);
   	        return combinaciones;
   	    }
   	    for(int j = 0; j < getNumColors(); j++) {
   	            Color c = null;
   	            switch (j) {
   	                case 0:
   	                    c = Color.RED;   break;
   	                case 1: 
   	                    c = Color.BLUE;  break;
   	                case 2:
   	                    c = Color.GREEN; break;
   	                case 3:
   	                    c = Color.YELLOW; break;
   	                case 4:
   	                    c = Color.PURPLE; break;
   	                case 5:
   	                    c = Color.ORANGE; break;
   	                default: break;
   	            }
   	            
   	            sol.add(c) ;// Se crea un objeto nuevo cada vez
   	            List<Combinacion> combinacionesSiguientes = generarCombinaciones(visto, i + 1, sol);
   	            combinaciones.addAll(combinacionesSiguientes);
   	            sol.remove(sol.size() - 1); // Se elimina la última referencia agregada
   	    }
   	    
   	    return combinaciones;
   	}
	@Override
	public String getNsolucion() {
		return Nsolucion;
	}


       }
