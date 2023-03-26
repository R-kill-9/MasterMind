import java.util.Vector;


public interface NivelDificultad {
    int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker);
    Vector<Integer> comprobarCombinacion(Vector<Integer> code);
    Vector<Integer> comprobarCombinacionPistas(Vector<Integer> code);
    Vector<String> resolve(Vector<String> code);
}
