package domain;
import java.util.List;
import java.util.Vector;


public interface NivelDificultad {
    
    public void setSolucion(List<Integer> solucion);

    public int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker);

    public int resolve(List<Integer> solucion);

    public String comprobarCombinacion(List<Integer> intento, List<Integer> solucionProporcionada);

    public String comprobarCombinacionPista(List<Integer> combinacionJugador, List<Integer> solucionProporcionada);

    



}

