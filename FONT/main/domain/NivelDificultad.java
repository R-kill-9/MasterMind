package main.domain;
import java.util.ArrayList;
import java.util.Collections;
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
    static public List< Combinacion> possibleCodes;
    static public List< Combinacion> totalcombinacionesPosibles;
    static public List< Combinacion> solucionesEnviadas;
    static public List< Combinacion> enviosCandidatos;
    
    
    
    public void setSolucion(Combinacion solution){
       this.solucion = solution;
    }
    
    public NivelDificultad() {};
    
    public abstract int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker);

    public abstract Integer getDificultad();

    public abstract Integer getNumColumnas();

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
  
    public int resolve(Combinacion solucionUsuario) {
    	setSolucion(solucionUsuario);
        
        envioActual = genCombinacion();
        totalcombinacionesPosibles.addAll(inicializarPosiblesCodigos());
		possibleCodes=totalcombinacionesPosibles;
		while(  turn < 10 ){
			enviosCandidatos = new ArrayList<>();
			solucionesEnviadas.add(envioActual);
            possibleCodes.remove(envioActual);
            totalcombinacionesPosibles.remove(envioActual);
            String respuestaComprobacion = comprobarCombinacion(envioActual, solucion );
            System.out.println(respuestaComprobacion);
            if(respuestaComprobacion.equals("NNNN")) return turn;
            else eliminaCombinacions(respuestaComprobacion);

            generaNuevoEnvio();

            envioActual = obtenSiguienteEnvio();

            turn++;
        }
        return turn ;
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

	
	public List<Combinacion> generarCombinaciones(Boolean[] visto, int i, ArrayList<Color> sol ){
	    List<Combinacion> combinaciones = new ArrayList<>();

	    if(i >= getNumColumnas()) {
	    	ArrayList<Color> ac = new ArrayList<>(sol);
	        Combinacion combi = new Combinacion(ac);
	        combinaciones.add(combi);
	        return combinaciones;
	    }

	    for(int j = 0; j < getNumColors(); j++) {
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


private List<Combinacion> inicializarPosiblesCodigos() {
    Boolean[] visto = {false,false,false,false,false,false};
    ArrayList<Color> sol  = new ArrayList<Color>();
    int i = 0;
    return generarCombinaciones(visto, i, sol);
}



 private void eliminaCombinacions(  String respuestaComprobacion) {
    for(int i = 0; i < possibleCodes.size() ; i++){	  
        if(!comprobarCombinacion(envioActual, possibleCodes.get(i)).equals(respuestaComprobacion)){
        possibleCodes.remove(i);
        i--;
        }
    }
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

          String resutltadoFicha = comprobarCombinacion(possibleCodes.get(j), totalcombinacionesPosibles.get(i));
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
        System.out.print(max+" ");
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
	        ArrayList<Boolean> visto = new ArrayList<Boolean>(Collections.nCopies(6, false));
	        while(!doneComb){
	        	Integer randomNumber = random.nextInt(6);
	        	System.out.println(randomNumber);
	        	if(!visto.get(randomNumber)) {
		        	combinacion.add(getColorNumber(randomNumber));
		        	visto.set(randomNumber,true);
	        	}
	        	if(combinacion.size() == getNumColumnas()) doneComb = true;
	        }
	        return new Combinacion(combinacion);
	}
}
