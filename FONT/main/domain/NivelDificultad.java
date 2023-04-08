package main.domain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class NivelDificultad {
   
	protected boolean  tieneBlancas;
	protected boolean  sePuedeRepetir;
	protected int numColors;
	protected int numcolumnas;
    boolean solucionEncontrada;
	protected Integer turn;
    static public List< Combinacion> possibleCodes;
    static public List< Combinacion> totalcombinacionesPosibles;
    static private List< Combinacion> solucionesEnviadas;
    static private Combinacion solucion;
    private Combinacion envioActual;
    static private List< Combinacion> enviosCandidatos;
    
    public void setSolucion(Combinacion solution){
        solucion = solution;
    }

    public abstract int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker);

    public abstract Integer getDificultad();

	public abstract Integer getNumColumnas();
	
	public String comprobarCombinacion(Combinacion solution, Combinacion solEnviada){
    	
        int aciertos = 0;
    	int semiaciertos = 0;
        String feedback = "";
        
        for(int i = 0; i < numcolumnas; i++){
            if(solEnviada.getPosition(i) == solution.getPosition(i)){
                aciertos++;
                feedback += "N";
            }
            else if(solEnviada.contains(solution.getPosition(i))){
                semiaciertos++;
            }   
        }  
        if(aciertos == numcolumnas)  solucionEncontrada = true;
        for(int i = aciertos; i < aciertos + semiaciertos; i++){
            feedback += "B";
        }

        return feedback;
    }
    
    public String comprobarCombinacionPista(Combinacion solution, Combinacion solEnviada){
        int aciertos = 0;
    	String feedback = "";
        for(int i = 0; i < numcolumnas; i++){
            if(solution.getPosition(i) == solEnviada.getPosition(i)){
                feedback += "N";
               aciertos++;
            }
            else if(solution.contains(solEnviada.getPosition(i))){
                feedback += "B";
            }
            else {
                feedback += " ";
        }  }
        if(aciertos == numcolumnas)  solucionEncontrada = true;
        return feedback;
    }
  
    public int resolve(Combinacion solucionUsuario) {
        solucion = solucionUsuario;

        ArrayList<Color> colores = new ArrayList<Color>();
        
        colores.add(Color.RED);
        colores.add(Color.BLUE);
        colores.add(Color.GREEN);
        colores.add(Color.YELLOW);

        envioActual = new Combinacion(colores);

        totalcombinacionesPosibles.addAll(inicializarPosiblesCodigos());
        
System.out.print("AHORA IMPROME LISTA ");

		
		possibleCodes=totalcombinacionesPosibles;
        while( !solucionEncontrada && turn <= 10 ){
        	
        	
           solucionesEnviadas.add(envioActual);
           possibleCodes.remove(envioActual);
           totalcombinacionesPosibles.remove(envioActual);

            String respuestaComprobacion = comprobarCombinacion(envioActual, solucion);

            if(solucionEncontrada) return turn;
         
            
            else eliminaCombinacions(respuestaComprobacion);

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

   
    private List<Combinacion> generarCombinaciones(Boolean[] visto, int i, ArrayList<Color> sol ){
    List<Combinacion> combinaciones = new ArrayList<>();

    if(i >= numcolumnas) {
        Combinacion combi = new Combinacion(sol);
        combinaciones.add(combi);
        combi.print();
        totalcombinacionesPosibles.add(combi);
        return combinaciones;
    }

    for(int j = 0; j < visto.length; j++) {
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

            sol.add(c);
            List<Combinacion> combinacionesSiguientes = generarCombinaciones(visto, i + 1, sol);
            combinaciones.addAll(combinacionesSiguientes);
            sol.remove(sol.size() - 1);
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



 private void eliminaCombinacions(  String respuestaComprobacion){
System.out.print(respuestaComprobacion);
    for(int i = 0; i < possibleCodes.size() ; i++){	  
        System.out.print("PACO");
        possibleCodes.get(i).print();
System.out.print("Resultado" + comprobarCombinacion(solucion, possibleCodes.get(i)));
        if(comprobarCombinacion(solucion, possibleCodes.get(i)).equals(respuestaComprobacion)){
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
}
