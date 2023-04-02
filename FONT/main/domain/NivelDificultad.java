package main.domain;
import java.util.ArrayList;
import java.util.List;


public abstract class NivelDificultad {
   
	protected boolean  tieneBlancas;
	protected boolean  sePuedeRepetir;
	protected int numColors;
	protected int numcolumnas;
	protected boolean solucionEncontrada;
	protected Integer turn;
    
    public abstract void setSolucion(List<Integer> solucion);

    public abstract int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker);

    public abstract int resolve(List<Integer> solucion);

	public abstract Integer getDificultad();
	
	public abstract Integer getNumColumnas();
	
	public String comprobarCombinacion(Combinacion solution, ArrayList<Color> combSolution){
    	
        int aciertos = 0;
    	int semiaciertos = 0;
        String feedback = "";
        
        for(int i = 0; i < numcolumnas; i++){
            if(combSolution.get(i) == solution.getPosition(i)){
                aciertos++;
                feedback += "N";
            }
            else if(combSolution.contains(solution.getPosition(i))){
                semiaciertos++;
            }
        }  
        if(aciertos == numcolumnas)  solucionEncontrada = true;
        for(int i = aciertos; i < aciertos + semiaciertos; i++){
            feedback += "B";
        }
        return feedback;
    }
    
    public String comprobarCombinacionPista(Combinacion solution, ArrayList<Color> combSolution){
        int aciertos = 0;
    	String feedback = "";
        for(int i = 0; i < numcolumnas; i++){
            if(combSolution.get(i) == solution.getPosition(i)){
                feedback += "N";
               aciertos++;
            }
            else if(combSolution.contains(solution.getPosition(i))){
                feedback += "B";
            }
            else feedback += " ";
        }  
        if(aciertos == numcolumnas)  solucionEncontrada = true;
        return feedback;
    }
    
	
	
}

