package main.domain;

import java.util.Map;
import java.util.List;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import main.domain.Combinacion;
import static org.junit.Assert.*;


public class NivelDificultadBajo extends NivelDificultad {


    static private List< Combinacion> possibleCodes;
    static private List< Combinacion> totalcombinacionesPosibles;
    static private List< Combinacion> solucionesEnviadas;
    static private Combinacion solucion;
    static private Combinacion envioActual;
    static private List< Combinacion> enviosCandidatos;
    
    
    public NivelDificultadBajo() {
	    tieneBlancas = false;
	    sePuedeRepetir = false;
	    numColors = 6;
	    numcolumnas = 4;
	    solucionEncontrada = false;
    }
    public Integer getNumColumnas() {
    	return this.numcolumnas;
    }
    
    @Override
    public void setSolucion(Combinacion solucionProporcionada) {
     solucion = solucionProporcionada;
    }

    //NumIntentCodeMaker son los intentos que necesita el rival para obtener la solucion, siendo el jugador el codeMaker
    //NumIntentCodeBreaker son los intentos que necesita el jugador para obtener la solucion
    @Override
    public int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker) {
        return  1000 * numIntentCodeMaker + (10 - numIntentCodeBraker + 1)  * 1000;
     }
 // Given the solution code, the solve operation uses one of the proposed algorithms (either five guess or the genetic one) to create the list of codes that will lead to the solution. If the algorithm is unable to find the solution in less than maxSteps steps, the returned list will contain a list composed of maxSteps codes. The operation will throw an exception in case the secret code solution is not consistent with the parameters of the current game

   
    @Override
    public int resolve(Combinacion solucionUsuario) {
        solucion = solucionUsuario;
        turn = 1;
        ArrayList<Color> colores = new ArrayList<Color>();
        
        colores.add(Color.RED);
        colores.add(Color.RED);
        colores.add(Color.BLUE);
        colores.add(Color.BLUE);

        envioActual.setCombination(colores);

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


    private Combinacion obtenSiguienteEnvio() {

    	for (int i = 0; i < enviosCandidatos.size(); ++i) {
    	
    		if (possibleCodes.indexOf(enviosCandidatos.get(i)) >= 0) return enviosCandidatos.get(i);
    	}
    	for (int j = 0; j < enviosCandidatos.size(); ++j) {
    		
    		if (totalcombinacionesPosibles.indexOf(enviosCandidatos.get(j)) >= 0 ) return enviosCandidatos.get(j);
        }
        return null;
    }

   
    private void inicialize(Boolean[] visto,Integer i, ArrayList<Color> sol ){
        if(i >= numcolumnas ){
            Combinacion combi = new Combinacion(sol); 
            totalcombinacionesPosibles.add(combi);
            return;
        }
        if(i < numcolumnas) {
            for(int j = 0; j < visto.length; j++){
                if(!visto[j]){
                    visto[j] = true;
                    Color c;

                    switch (j) {
                        case 0:
                            c = Color.RED;   break;
                        case 1: 
                            c = Color.BLUE;  break;
                        case 2:
                            c = Color.GREEN; break;
                        case 3:
                            c = Color.YELLOW; break;
                        case 4:
                            c = Color.PURPLE; break;
                        default:
                            break;
                    }
                    
                    sol.add(c);
                    int z = i;
                    inicialize(visto, i + 1, sol );
                    i = z;
                    sol.remove(c);
                    visto[j] = false;
                }   
            }
        }
    }
    
    private void incializarPosiblesCodigos(){
        Boolean[] visto = {false,false,false,false,false,false};
        ArrayList<Color> sol  = new ArrayList<Color>();
        Integer i = numColors;
        inicialize(visto, i, sol );
    }


	 private void eliminaCombinacions( List< Combinacion> conjunto, String respuestaComprobacion){
	    for(int i = 0; i < conjunto.size() ; i++){
	        if(!NivelDificultad.comprobarCombinacion(conjunto.get(i), solucion).equals(respuestaComprobacion)){
	            possibleCodes.remove(i);
	        }
	    }
	  }


	 private  void generaNuevoEnvio() {

	    //Aqui iremos contando para cada posible solucion cuantas veces aparece
	    Map<String, Integer> contadorPuntuaciones = new HashMap<String, Integer>() ;
	    //Aqui guardaremos el maixmo de apariciones que haya de una solucion
	    Map<Combinacion, Integer> puntuaciones = new HashMap<Combinacion, Integer>(); 
	    // Aqui guardamos las posibles soluciones que nos interesaria probar a enviar
	    List<Combinacion> enviosCandidatos = new ArrayList<Combinacion>() ;
	
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
	
	    for (Map.Entry<Combinacion, Integer> elem : puntuaciones.entrySet()) {
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
	    
	private static int getMinScore(Map<Combinacion, Integer> m){
	        int minimo = Integer.MAX_VALUE;
	        for (Map.Entry<Combinacion, Integer> elem : m.entrySet()){
	            if (elem.getValue() < minimo )  minimo = elem.getValue();
	        }
	        return minimo;
    }

	@Override
	public Integer getDificultad() {
		return 1;
	}

}

