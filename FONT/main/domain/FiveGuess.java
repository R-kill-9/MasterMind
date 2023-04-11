package main.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiveGuess implements Maquina {
	  static public List< Combinacion> possibleCodes;
	  static public List< Combinacion> totalcombinacionesPosibles;
	  static public List< Combinacion> solucionesEnviadas;
	  static public List< Combinacion> enviosCandidatos;
	  protected Integer turn;
	  public Combinacion envioActual;
	  public Combinacion solucion;
	  public NivelDificultad nivel;

	  
	  public FiveGuess(NivelDificultad nivel) {
		  this.nivel = nivel;
		  totalcombinacionesPosibles = new ArrayList<Combinacion>();
	   	  solucionesEnviadas = new ArrayList<Combinacion>();
	   	  possibleCodes = new ArrayList<Combinacion>();
	      enviosCandidatos = new ArrayList<Combinacion>();
	 	  turn = 1;

	  }
	  
	  private static int getMinScore(Map<Combinacion, Integer> m){
	        int minimo = Integer.MAX_VALUE;
	        for (Map.Entry<Combinacion, Integer> elem : m.entrySet()){
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
		    
		    Map<Combinacion, Integer> puntuaciones = new HashMap<Combinacion, Integer>(); 
		    
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

		    for (Map.Entry<Combinacion, Integer> elem : puntuaciones.entrySet()) {
		        if (elem.getValue() == min) {
		        	//Combinacion comb = new Combinacion(elem.getKey().getCombination());
		            enviosCandidatos.add(elem.getKey());
		        }
		    }
		    return;
			
			}
	  
	  public void setSolucion(Combinacion solution){
	       this.solucion = solution;
	  }
	  public List<Combinacion> generarCombinaciones(Boolean[] visto, int i, ArrayList<Color> sol ){
		    List<Combinacion> combinaciones = new ArrayList<>();

		    if(i >= nivel.getNumColumnas()) {
		    	ArrayList<Color> ac = new ArrayList<>(sol);
		        Combinacion combi = new Combinacion(ac);
		        combinaciones.add(combi);
		        return combinaciones;
		    }

		    for(int j = 0; j < nivel.getNumColors(); j++) {
		        if(!visto[j]) {
		            visto[j] = true;
		            Color c = null;
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
		                case 5:
		                    c = Color.ORANGE; break;
		                default: break;
		            }      
		            sol.add(c) ;// Se crea un objeto nuevo cada vez
		            List<Combinacion> combinacionesSiguientes = generarCombinaciones(visto, i + 1, sol);
		            combinaciones.addAll(combinacionesSiguientes);
		            sol.remove(sol.size() - 1); // Se elimina la última referencia agregada
		            visto[j] = false;   
		        }   
		    }
		    
		    return combinaciones;
		}
	  public List<Combinacion> generarCombinacionesRepes(Boolean[] visto, int i, ArrayList<Color> sol ){
		    List<Combinacion> combinaciones = new ArrayList<>();

		    if(i >= nivel.getNumColumnas()) {
		    	ArrayList<Color> ac = new ArrayList<>(sol);
		        Combinacion combi = new Combinacion(ac);
		        combinaciones.add(combi);
		        return combinaciones;
		    }

		    for(int j = 0; j < nivel.getNumColors(); j++) {
		        
		            Color c = null;
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
		                case 5:
		                    c = Color.ORANGE; break;
		                default: break;
		            }      
		            sol.add(c) ;// Se crea un objeto nuevo cada vez
		            List<Combinacion> combinacionesSiguientes = generarCombinaciones(visto, i + 1, sol);
		            combinaciones.addAll(combinacionesSiguientes);
		            sol.remove(sol.size() - 1); // Se elimina la última referencia agregada
		           
		        
		    }
		    
		    return combinaciones;
		}
	  public String comprobarCombinacion(Combinacion solution, Combinacion solEnviada){
	    	
	        int aciertos = 0;
	    	int semiaciertos = 0;
	        String feedback = "";
	        
	        for(int i = 0; i < nivel.getNumColumnas(); i++){
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
	  private void eliminaCombinacions(  String respuestaComprobacion) {
		    for(int i = 0; i < possibleCodes.size() ; i++){	  
		        if(!comprobarCombinacion(envioActual, possibleCodes.get(i)).equals(respuestaComprobacion)){
		        possibleCodes.remove(i);
		        i--;
		        }
		    }
	  }
	  private List<Combinacion> inicializarPosiblesCodigos() {
		    Boolean[] visto = {false,false,false,false,false,false};
		    ArrayList<Color> sol  = new ArrayList<Color>();
		    int i = 0;
		    if(nivel.getDificultad() == 1) return generarCombinaciones(visto, i, sol);
		    else return generarCombinacionesRepes(visto, i, sol);
	  }
	  
	  private Combinacion obtenSiguienteEnvio() {

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
	  
	  public List<Combinacion> resolve(Combinacion solucionUsuario) {
    	setSolucion(solucionUsuario);
        envioActual = nivel.genCombinacion();
        solucionUsuario.print();
        totalcombinacionesPosibles.addAll(inicializarPosiblesCodigos());
		possibleCodes=totalcombinacionesPosibles;
		while(  turn <= 10 ){
			
			enviosCandidatos = new ArrayList<>();
			solucionesEnviadas.add( new Combinacion(envioActual.getCombination()));

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
