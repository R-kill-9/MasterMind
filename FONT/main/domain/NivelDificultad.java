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
    public Combinacion solucion;
    public Combinacion envioActual;
    protected Maquina maquinaResolve;
    protected String Nsolucion;
        
    
    public void setSolucion(Combinacion solution){
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
	
	public String comprobarCombinacion(Combinacion solution, Combinacion solEnviada){
    	
        int aciertos = 0;
    	int semiaciertos = 0;
        String feedback = "";
        
        for(int i = 0; i < getNumColumnas(); i++){
            if(solEnviada.getPosition(i) == solution.getPosition(i)){
                aciertos++;
                feedback += "N";
            }
            else if(solEnviada.contains(solution.getPosition(i))){
                semiaciertos++;
            }   
        }  
        for(int i = aciertos; i < aciertos + semiaciertos; i++){
            feedback += "B";
        }

        return feedback;
    }
    
    public String comprobarCombinacionPista(Combinacion solution, Combinacion solEnviada){
    	String feedback = "";
        for(int i = 0; i < getNumColumnas(); i++){
            if(solution.getPosition(i) == solEnviada.getPosition(i)){
                feedback += "N";
            }
            else if(solution.contains(solEnviada.getPosition(i))){
                feedback += "B";
            }
            else {
                feedback += " ";
        }  }
        return feedback;
    }
  
    public List<Combinacion> resolve(Combinacion solucionUsuario) {
    	return maquinaResolve.resolve(solucionUsuario);
    }

 
	protected static Color getColorNumber(Integer randomNumber) {
		switch (randomNumber) {
			case 1:
				return Color.RED;
			case 2:
				return Color.GREEN;
			case 3:
				return Color.BLUE;
			case 4:
				return Color.YELLOW;
			case 5:
				return Color.PURPLE;
			case 6:
				return Color.ORANGE;
		}
		return null;
	}
	
	/**
	 * Genera una combinación aleatoria que se usará como solución
	 */
	
	public Combinacion genCombinacion() {
		 	Random random = new Random();
	        ArrayList<Color> combinacion = new ArrayList<Color>();
	        boolean doneComb = false;
	        while(!doneComb){
	        	Integer randomNumber = random.nextInt(5) + 1;
		        combinacion.add(getColorNumber(randomNumber));
	        	if(combinacion.size() == getNumColumnas()) doneComb = true;
	        }
	        return new Combinacion(combinacion);
	}

	public abstract List<Combinacion> generarCombinaciones(Boolean[] visto, int i, ArrayList<Color> sol);
}
