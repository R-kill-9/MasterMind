package main.domain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class NivelDificultadBajo extends NivelDificultad {

    protected boolean  tieneBlancas;
	protected int numcolumnas;
    
    public NivelDificultadBajo() {
	    sePuedeRepetir = false;
	    numcolumnas = 4;
	    turn = 1;
        Nsolucion = "NNNN";
	    maquinaResolve = new FiveGuess(this);
    }
    @Override
    public Integer getNumColumnas() {
    	return this.numcolumnas;
    }

    //NumIntentCodeMaker son los intentos que necesita el rival para obtener la solucion, siendo el jugador el codeMaker
    //NumIntentCodeBreaker son los intentos que necesita el jugador para obtener la solucion
    @Override
    public int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker) {
        return  1000 * numIntentCodeMaker + (10 - numIntentCodeBraker + 1)  * 1000;
     }
    
    // Given the solution code, the solve operation uses one of the proposed algorithms (either five guess or the genetic one) to create the list of codes that will lead to the solution. If the algorithm is unable to find the solution in less than maxSteps steps, the returned list will contain a list composed of maxSteps codes. The operation will throw an exception in case the secret code solution is not consistent with the parameters of the current game
    @Override
    public Integer getDificultad() {
        return 1;
    }
    @Override
	public String getNsolucion() {
		return Nsolucion;
	}
    
    @Override
    public Combinacion genCombinacion() {
	 	Random random = new Random();
        ArrayList<Color> combinacion = new ArrayList<Color>();
        boolean doneComb = false;
        ArrayList<Boolean> visto = new ArrayList<Boolean>(Collections.nCopies(6, false));
        while(!doneComb){
        	Integer randomNumber = random.nextInt(5) + 1;
        	if(!visto.get(randomNumber)) {
	        	combinacion.add(getColorNumber(randomNumber));
	        	visto.set(randomNumber,true);
        	}
        	if(combinacion.size() == getNumColumnas()) doneComb = true;
        }
        return new Combinacion(combinacion);
    }
}
