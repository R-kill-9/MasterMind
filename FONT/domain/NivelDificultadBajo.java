package domain;

import java.util.Map;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



public class NivelDificultadBajo implements NivelDificultad {

    static final boolean  tieneBlancas = false;
    static final boolean  sePuedeRepetir = false;
    static final private int numColors = 6;
    static final private  int numcolumnas = 4;
    static boolean solucionEncontrada = false;
    static private Integer turn;

    static private List< Vector<Integer>> possibleCodes;
    static private List< Vector<Integer>> totalcombinacionesPosibles;
    static private List< Vector<Integer>> solucionesEnviadas;
    static private Vector<Integer> solucion;
    static Vector<Integer> envioActual = new Vector<Integer>(Arrays.asList(1, 1, 2, 2));
    static List< Vector<Integer>> enviosCandidatos;
    


    @Override
    public void setSolucion(Vector<Integer> solucionProporcionada) {
     solucion = solucionProporcionada;
    }

    //NumIntentCodeMaker son los intentos que necesita el rival para obtener la solucion, siendo el jugador el codeMaker
    //NumIntentCodeBreaker son los intentos que necesita el jugador para obtener la solucion
    @Override
    public int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker) {
        return  1000 * numIntentCodeMaker + (10 - numIntentCodeBraker + 1)  * 1000;
     }

    @Override
    public int resolve(Vector<Integer> solucion) {
        this.setSolucion(solucion);
        turn = 1;
        incializarPosiblesCodigos();
        while( !solucionEncontrada && turn <= 10 ){
            solucionesEnviadas.add(envioActual);
            possibleCodes.remove(envioActual);
            totalcombinacionesPosibles.remove(envioActual);

            String respuestaComprobacion = comprobarCombinacion(envioActual, solucion);
           
            if(solucionEncontrada) return turn;

            else eliminaCombinacions(possibleCodes, respuestaComprobacion);

            generaNuevoEnvio();

            envioActual = obtenSiguienteEnvio();

            turn++;
        }
        return turn ;
    }


    private Vector<Integer> obtenSiguienteEnvio() {

    for (int i = 0; i < enviosCandidatos.size(); ++i) {
        if (possibleCodes.indexOf(enviosCandidatos.get(i)) >= 0) {
            return enviosCandidatos.get(i);
        }
    }
    for (int j = 0; j < enviosCandidatos.size(); ++j) {
        if (totalcombinacionesPosibles.indexOf(enviosCandidatos.get(j)) >= 0 ) {
            return enviosCandidatos.get(j);
        }
    }
        return null;
    }

    @Override
    public String comprobarCombinacion(Vector<Integer> intento, Vector<Integer> solucionProporcionada){
        int aciertos = 0;
    	int semiaciertos = 0;
        String feedback = "";
        for(int i = 0; i < numcolumnas; i++){
            if(intento.get(i) == solucion.get(i)){
                aciertos++;
                feedback += "N";
            }
            else if(intento.contains(solucion.get(i))){
                semiaciertos++;
            }
        }  
        if(aciertos == numcolumnas)  solucionEncontrada = true;
        for(int i = aciertos; i < aciertos + semiaciertos; i++){
            feedback += "B";
        }
        return feedback;
    }
    
    public String comprobarCombinacionPista(Vector<Integer> combinacionJugador, Vector<Integer> solucionProporcionada){
        int aciertos = 0;
    	String feedback = "";
        for(int i = 0; i < numcolumnas; i++){
            if(combinacionJugador.get(i) == solucion.get(i)){
                feedback += "N";
               aciertos++;
            }
            else if(combinacionJugador.contains(solucion.get(i))){
                feedback += "B";
            }
            else feedback += " ";
        }  
        if(aciertos == numcolumnas)  solucionEncontrada = true;
        return feedback;
    }
    
    private void inicialize(Boolean[] visto,Integer i, Vector<Integer> sol ){
        if(i >= numcolumnas ){
            totalcombinacionesPosibles.add(sol);
            return;
        }
        if(i < numcolumnas) {
            for(int j = 0; j < visto.length; j++){
                if(!visto[j]){
                    visto[j] = true;
                    sol.add(j);
                    int z = i;
                    inicialize(visto, i + 1, sol );
                    i = z;
                    sol.remove(j);
                    visto[j] = false;
                }   
            }
        }
    }
    
    private void incializarPosiblesCodigos(){
        Boolean[] visto = {false,false,false,false,false,false};
        Vector<Integer> sol = new Vector<Integer>();
        Integer i = numColors;
        inicialize(visto, i, sol );
    }


  private void eliminaCombinacions( List< Vector<Integer>> conjunto, String respuestaComprobacion){
    for(int i = 0; i < conjunto.size() ; i++){
        if(!comprobarCombinacion(conjunto.get(i),solucion).equals(respuestaComprobacion)){
            possibleCodes.remove(i);
        }
    }
  }



  private  void generaNuevoEnvio() {

    //Aqui iremos contando para cada posible solucion cuantas veces aparece
    Map<String, Integer> contadorPuntuaciones = new HashMap<String, Integer>() ;
    //Aqui guardaremos el maixmo de apariciones que haya de una solucion
    Map<Vector<Integer>, Integer> puntuaciones = new HashMap<Vector<Integer>, Integer>(); 
    // Aqui guardamos las posibles soluciones que nos interesaria probar a enviar
    List<List<Integer>> enviosCandidatos = new ArrayList<List<Integer>>() ;

    int max, min;

    for (int i = 0; i < totalcombinacionesPosibles.size(); ++i) {

        for (int j = 0; j < possibleCodes.size(); ++j) {

            String resutltadoFicha = comprobarCombinacion(totalcombinacionesPosibles.get(i), possibleCodes.get(j));
          
            // Si existe se incrementa las veces que aparece
            if(contadorPuntuaciones.get(resutltadoFicha) > 0 ){
                int num =  contadorPuntuaciones.get(resutltadoFicha);
                contadorPuntuaciones.put(resutltadoFicha, num + 1 );
            } 
            // Si no se inicializa a 1
            else{
                contadorPuntuaciones.put(resutltadoFicha, 1);
            }
        }

        max = getMaxScore(contadorPuntuaciones);
        puntuaciones.put(totalcombinacionesPosibles.get(i), max);
        contadorPuntuaciones.clear();   
    }

    min = getMinScore(puntuaciones);

    for (Map.Entry<Vector<Integer>, Integer> elem : puntuaciones.entrySet()) {
        if (elem.getValue() == min) {
            enviosCandidatos.add(elem.getKey());
        }
    }
    return;
}

private int getMaxScore( Map<String, Integer> m){
    int maximo = 0;
    for (Map.Entry<String, Integer> elem : m.entrySet()) {
        if (elem.getValue() > maximo) {
            maximo = elem.getValue();
        }
    }
    return maximo;
}
    
private static int getMinScore(Map<Vector<Integer>, Integer> m){
        int minimo = Integer.MAX_VALUE;
        for (Map.Entry<Vector<Integer>, Integer> elem : m.entrySet()){
            if (elem.getValue() < minimo )  minimo = elem.getValue();
        }
        return minimo;
    }


}

