package main.domain;



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
	public String getNsolucion() {
		return Nsolucion;
	}


       }
