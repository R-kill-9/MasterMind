import java.util.Vector;

public class NivelDificultadBajo implements NivelDificultad {
    final private  int numcol = 4;
    final boolean  tieneBlancas = false;
    boolean  sePuedeRepetir = false;
    

    void resuelveConAlgoritmo5Guess(){
        
    }


    //NumIntentCodeMaker son los intentos que necesita el rival para obtener la solucion, siendo el jugador el codeMaker
    //NumIntentCodeBreaker son los intentos que necesita el jugador para obtener la solucion
    @Override
    public int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker) {
        return  1000 * numIntentCodeMaker + (10 - numIntentCodeBraker + 1)  * 1000
     }

    @Override
    public String getNivel() {
        return nivel;
    }

    @Override
    public Vector<Integer> resolve(Vector<Integer> code) {
        // Implementación de la función
        return new Vector<Integer>();
    }
    @Override
    Vector<String> comprobarCombinacion(Vector<Integer> combinacionJugador, Vector<Integer> solucion){
    	int aciertos = 0;
    	int semiaciertos = 0;
        for(int i = 0; i < 4; i++){
            if(combinacionJugador.get(i) == solucion.get(i)){
                aciertos++;
            }
            else if(combinacionJugador.contains(solucion.get(i))){
                semiaciertos++;
            }
        }  
        string[] feedback = new String[aciertos + semiaciertos];
        for(int i = 0; i < aciertos; i++){
            feedback[i] = "rojo";
        }
        for(int i = aciertos; i < aciertos + semiaciertos; i++){
            feedback[i] = "blanco";
        }
        return feedback;
    }
    
    Vector<String> comprobarCombinacionPista(Vector<Integer> combinacionJugador, Vector<Integer> solucion){
    	String[] feedback = new String[4];
        for(int i = 0; i < 4; i++){
            if(combinacionJugador.get(i) == solucion.get(i)){
               feedback[i] = "rojo";
            }
            else if(combinacionJugador.contains(solucion.get(i))){
                feedback[i] = "blanco";
            }
            else feedback[i] = null;
        }  
        return feedback;
    }
    
}
