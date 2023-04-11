package main.domain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public abstract class NivelDificultad {
 
	
	protected int numcolumnas;
	protected int numColors = 6;
	protected boolean  sePuedeRepetir;
	protected Integer turn;
    public List<Integer> solucion;
    public List<Integer> envioActual;
    protected Maquina maquinaResolve;
    protected String Nsolucion;
        
    
    public void setSolucion(List<Integer> solution){
       this.solucion = solution;
    }
    
    public NivelDificultad() {};
    
    public abstract int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker);

    public abstract Integer getDificultad();

    public abstract Integer getNumColumnas();
    public abstract String getNsolucion();
    public Integer getNumColors() {
    	return numColors;
    }
	
	public String comprobarCombinacion(List<Integer> solution, List<Integer> solEnviada){
    	
        int aciertos = 0;
    	int semiaciertos = 0;
        String feedback = "";
        
        for(int i = 0; i < getNumColumnas(); i++){
            if(solEnviada.get(i) == solution.get(i)){
                aciertos++;
                feedback += "N";
            }
            else if(solEnviada.contains(solution.get(i))){
                semiaciertos++;
            }   
        }  
        for(int i = aciertos; i < aciertos + semiaciertos; i++){
            feedback += "B";
        }

        return feedback;
    }
    
    public String comprobaCombinacionPista(List<Integer> solution, List<Integer> solEnviada){
    	String feedback = "";
        for(int i = 0; i < getNumColumnas(); i++){
            if(solution.get(i) == solEnviada.get(i)){
                feedback += "N";
            }
            else if(solution.contains(solEnviada.get(i))){
                feedback += "B";
            }
            else {
                feedback += " ";
        }  }
        return feedback;
    }
  
    public List<List<Integer>> resolve(List<Integer> solucionUsuario) {
    	return maquinaResolve.resolve(solucionUsuario);
    }

 
	
	/**
	 * Genera una combinación aleatoria que se usará como solución
	 */
	
	public List<Integer> genCombinacion() {
		 	Random random = new Random();
	        List<Integer> Combinacion= new ArrayList<Integer>();
	        for(int i= 0; i< getNumColumnas(); i++) {
	        	Combinacion.add(random.nextInt(5));
	        }
	
	        return new ArrayList<Integer>(Combinacion);
	}


}
