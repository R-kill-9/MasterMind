package main.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiveGuess implements Maquina {
	  static public List< List<Integer>> possibleCodes;
	  static public List< List<Integer>> totalcombinacionesPosibles;
	  static public List< List<Integer>> solucionesEnviadas;
	  static public List< List<Integer>> enviosCandidatos;
	  protected Integer turn;
	  public List<Integer> envioActual;
	  public List<Integer> solucion;
	  public NivelDificultad nivel;

	  
	  public FiveGuess(NivelDificultad nivel) {
		  this.nivel = nivel;
		  totalcombinacionesPosibles = new ArrayList<List<Integer>>();
	   	  solucionesEnviadas = new ArrayList<List<Integer>>();
	   	  possibleCodes = new ArrayList<List<Integer>>();
	      enviosCandidatos = new ArrayList<List<Integer>>();
	 	  turn = 1;

	  }
	  
	  private static int getMinScore(Map<List<Integer>, Integer> m){
	        int minimo = Integer.MAX_VALUE;
	        for (Map.Entry<List<Integer>, Integer> elem : m.entrySet()){
	            if (elem.getValue() < minimo )  minimo = elem.getValue();
	        }
	        return minimo;
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
	  private  void generaNuevoEnvio() {

		    //Aqui iremos contando para cada posible solucion cuantas veces aparece
			 
		    Map<String, Integer> contadorPuntuaciones = new HashMap<String, Integer>() ;
		    
		    //Aqui guardaremos el maixmo de apariciones que haya de una solucion
		    
		    Map<List<Integer>, Integer> puntuaciones = new HashMap<List<Integer>, Integer>(); 
		    
		    // Aqui guardamos las posibles soluciones que nos interesaria probar a enviar
		    
		    int max, min;

		    for (int i = 0; i < totalcombinacionesPosibles.size(); ++i) {

		        for (int j = 0; j < possibleCodes.size(); ++j) {

		          String resutltadoFicha = nivel.comprobarCombinacion(possibleCodes.get(j), totalcombinacionesPosibles.get(i));
		            // Si existe se incrementa las veces que aparece
		            if(contadorPuntuaciones.get(resutltadoFicha) != null ){
		                int num = contadorPuntuaciones.get(resutltadoFicha);
		                contadorPuntuaciones.put(resutltadoFicha, num + 1 );
		            } 
		            // Si no se inicializa a 1
		            else{
		            	String s = new String(resutltadoFicha);
		                contadorPuntuaciones.put(s, 1);
		            }
		        }

		        max = getMaxScore(contadorPuntuaciones);
		        puntuaciones.put(totalcombinacionesPosibles.get(i), max);
		        contadorPuntuaciones.clear();   
		    }

		    min = getMinScore(puntuaciones);

		    for (Map.Entry<List<Integer>, Integer> elem : puntuaciones.entrySet()) {
		        if (elem.getValue() == min) {
		        	//Combinacion comb = new Combinacion(elem.getKey().getCombination());
		            enviosCandidatos.add(elem.getKey());
		        }
		    }
		    return;
			
			}
	  
	  public void setSolucion(List<Integer> solution){
	       this.solucion = solution;
	  }
	  public List<List<Integer>> generarCombinaciones(Boolean[] visto, int i, List<Integer> sol ){
		    List<List<Integer>> combinaciones = new ArrayList<>();

		    if(i >= nivel.getNumColumnas()) {
		    	List<Integer> combi = new ArrayList<>(sol);
		        combinaciones.add(combi);
		        return combinaciones;
		    }

		    for(int j = 0; j < nivel.getNumColors(); j++) {
		        if(!visto[j]) {
		            visto[j] = true;   
		            sol.add(j) ;// Se crea un objeto nuevo cada vez
		            List<List<Integer>> combinacionesSiguientes = generarCombinaciones(visto, i + 1, sol);
		            combinaciones.addAll(combinacionesSiguientes);
		            sol.remove(sol.size() - 1); // Se elimina la última referencia agregada
		            visto[j] = false;   
		        }   
		    }
		    
		    return combinaciones;
		}
	  public List<List<Integer>> generarCombinacionesRepes(Boolean[] visto, int i, List<Integer> sol ){
		    List<List<Integer>> combinaciones = new ArrayList<>();

		    if(i >= nivel.getNumColumnas()) {
		    	List<Integer> combi = new ArrayList<Integer>(sol);
		        combinaciones.add(combi);
		        return combinaciones;
		    }

		    for(int j = 0; j < nivel.getNumColors(); j++) {		   
		            sol.add(j) ;// Se crea un objeto nuevo cada vez
		            List<List<Integer>> combinacionesSiguientes = generarCombinaciones(visto, i + 1, sol);
		            combinaciones.addAll(combinacionesSiguientes);
		            sol.remove(sol.size() - 1); // Se elimina la última referencia agregada
		           
		        
		    }
		    
		    return combinaciones;
		}
	  public String comprobarCombinacion(List<Integer> solution, List<Integer> solEnviada){
	    	
	        int aciertos = 0;
	    	int semiaciertos = 0;
	        String feedback = "";
	        
	        for(int i = 0; i < nivel.getNumColumnas(); i++){
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
	  private void eliminaCombinacions(  String respuestaComprobacion) {
		    for(int i = 0; i < possibleCodes.size() ; i++){	  
		        if(!comprobarCombinacion(envioActual, possibleCodes.get(i)).equals(respuestaComprobacion)){
		        possibleCodes.remove(i);
		        i--;
		        }
		    }
	  }
	  private List<List<Integer>> inicializarPosiblesCodigos() {
		    Boolean[] visto = {false,false,false,false,false,false};
		    ArrayList<Integer> sol  = new ArrayList<Integer>();
		    int i = 0;
		    if(nivel.getDificultad() == 1) return generarCombinaciones(visto, i, sol);
		    else return generarCombinacionesRepes(visto, i, sol);
	  }
	  
	  private List<Integer> obtenSiguienteEnvio() {

	    	for (int i = 0; i < enviosCandidatos.size(); ++i) {
	    	
	    		if (possibleCodes.contains(enviosCandidatos.get(i))) {
	    			return enviosCandidatos.get(i);
	    		}
	    	}
	    	for (int j = 0; j < enviosCandidatos.size(); ++j) {
	    		
	    		if (totalcombinacionesPosibles.contains(enviosCandidatos.get(j))) return enviosCandidatos.get(j);
	        }
	        return enviosCandidatos.get(0);
	  }
	  
	  public List<List<Integer>> resolve(List<Integer> solucionUsuario) {
    	setSolucion(solucionUsuario);
        envioActual = nivel.genCombinacion();
        totalcombinacionesPosibles.addAll(inicializarPosiblesCodigos());
		possibleCodes=totalcombinacionesPosibles;
		while(  turn <= 10 ){
			
			enviosCandidatos = new ArrayList<>();
			solucionesEnviadas.add( new ArrayList<Integer>(envioActual));

            possibleCodes.remove(envioActual);
            totalcombinacionesPosibles.remove(envioActual);
            String respuestaComprobacion = comprobarCombinacion(envioActual, solucion );
           
            
            if(respuestaComprobacion.equals(nivel.getNsolucion())) return solucionesEnviadas;
            else eliminaCombinacions(respuestaComprobacion);

            generaNuevoEnvio();

            envioActual = obtenSiguienteEnvio();
            turn++;
        }
        return solucionesEnviadas;
    }

}
