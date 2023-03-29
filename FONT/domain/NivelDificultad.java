package domain;
import java.util.Vector;


public interface NivelDificultad {
    
    public void setSolucion(Vector<Integer> solucion);

    public int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker);

    public int resolve(Vector<Integer> solucion);

    public String comprobarCombinacion(Vector<Integer> intento, Vector<Integer> solucionProporcionada);

    public String comprobarCombinacionPista(Vector<Integer> combinacionJugador, Vector<Integer> solucionProporcionada);

    



}

