package main.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Genetic implements Maquina {

	private final int POPULATION_SIZE = 150;
	private final int GENERATION_SIZE = 100;
    /**
     * Max number of feasible codes. When the FEASIBLE_CODES_MAX is reached, we stop producing feasible codes
     * and we choose one randomly
     */
    private static final int NMAX_CANDIDATOS = 20;

    /**
     * Array with the GeneticComputer previous attempts
     */
    private final List< List<Integer>> solucionesEnviadas = new ArrayList<>();
    /**
     * Array with all the feasible codes found.
     */
    private List< Pair<List<Integer>, Integer >>  enviosCandidatos = new ArrayList<>();
    /**
     * Array with the population generated
     */
    private List< Pair<List<Integer>, Integer >> poblacion = new ArrayList<>(POPULATION_SIZE);
    /**
     * Integer used to choose 2 ColorRows for the crossover
     */
    private Integer parentPos = 0;
    private List<Integer >negrasSol= new ArrayList<>();
    private List<Integer >blancasSol= new ArrayList<>();
    
    public NivelDificultad nivel;
    

	
	public  Genetic(NivelDificultad nivelJugador) {
		nivel = nivelJugador;
	}
	
	private List<Integer> generaRandom() {
	    List<Integer> combinacion = new ArrayList<>();
	    Random rand = new Random();
	    for (int i = 0; i < nivel.getNumColumnas(); i++) {
	        int random = rand.nextInt(nivel.getNumColors());
	        combinacion.add(random);
	    }
	  return combinacion;
	}
	
	private void inicializarPoblacion() {
		enviosCandidatos.clear();
	    poblacion.clear();
	    for (int i = 0; i < POPULATION_SIZE; ++i) {
	    	@SuppressWarnings("unchecked")
			Pair<List<Integer>, Integer >p = new Pair(generaRandom(),0);
	    	poblacion.add(i, p);
	        }
	    }
	
	
	private List< Pair<List<Integer>, Integer >> ordenarPorAptitud(List< Pair<List<Integer>, Integer >>  listaDesordenada) {
	    Collections.sort(listaDesordenada, new Comparator<Pair<List<Integer>, Integer>>() {
	        @Override
	        public int compare(Pair<List<Integer>, Integer> p1, Pair<List<Integer>, Integer> p2) {
	            return Integer.compare(p1.getSecond(), p2.getSecond());
	        }
	    });
	    return listaDesordenada;
	}
	
// Evolucionar la población a través de generaciones
	private  void evolucionarPoblacion() {
		List< Pair<List<Integer>, Integer >> NuevaPoblacion = new ArrayList<>(POPULATION_SIZE);
		for (int i = 0; i < POPULATION_SIZE; i++) {
			Pair<List<Integer>, Integer >p = new Pair(generaRandom(),0);
			NuevaPoblacion.add(i, p);
        }
	//	Cruce
		 for (int i = 0; i < POPULATION_SIZE; i += 2) {
	            if ((new Random().nextInt(4)) == 0) {
	            	cruce1pos(NuevaPoblacion, i, i + 1);
	            } 
		 }
	//	 Mutacion
		 for (int i = 0; i < POPULATION_SIZE; i++) {
		        if (new Random().nextInt(100) < 3) {
	            	mutar(NuevaPoblacion, i);

	            } else if (new Random().nextInt(100) < 3) {
	            	intercambiar(NuevaPoblacion, i);
	        		
	            } else if (new Random().nextInt(100) < 2) {
	            	invertir(NuevaPoblacion, i);

	            }
	        }
		 
		 	poblacion = NuevaPoblacion;
		
	}
	//  Mutación
    private void mutar(List<Pair<List<Integer>, Integer>> nuevaPoblacion, int pos) {
        List<Integer> individuo = nuevaPoblacion.get(pos).getFirst();
        int posMutacion = new Random().nextInt(nivel.getNumColumnas());
        int nuevoColor = new Random().nextInt(nivel.getNumColors());
        individuo.set(posMutacion, nuevoColor);
        nuevaPoblacion.set(pos, new Pair<>(individuo, 0));
    }
    private void intercambiar(List<Pair<List<Integer>, Integer>> nuevaPoblacion, int pos) {
        List<Integer> individuo = nuevaPoblacion.get(pos).getFirst();
        int pos1 = new Random().nextInt(nivel.getNumColumnas());
        int pos2 = new Random().nextInt(nivel.getNumColumnas());
        while (pos1 == pos2) {
            pos2 = new Random().nextInt(nivel.getNumColumnas());
        }
        int color1 = individuo.get(pos1);
        int color2 = individuo.get(pos2);
        individuo.set(pos1, color2);
        individuo.set(pos2, color1);
        nuevaPoblacion.set(pos, new Pair<>(individuo, 0));
    }
	 private void invertir(List< Pair<List<Integer>, Integer >> NuevaPoblacion, int index) {
		 int pos1 = new Random().nextInt(nivel.getNumColumnas());
		 int pos2 = pos1 + new Random().nextInt(nivel.getNumColumnas() - pos1);
		 List<Integer> combinacion = NuevaPoblacion.get(index).getFirst();
		 
		    for (int i = 0; i < (pos2 - pos1) / 2; i++) {
		        int tmp = combinacion.get(pos1 + i);
		        combinacion.set(pos1 + i, combinacion.get(pos2 - i - 1));
		        combinacion.set(pos2 - i - 1, tmp);
		    }
		    NuevaPoblacion.set(index, new Pair<>(combinacion, NuevaPoblacion.get(index).getSecond()));
	}

	 private void cruce1pos(List< Pair<List<Integer>, Integer >>  NuevaPoblacion, int posHijo1, int posHijo2) {
	        int padre = generaPosicionPadres();
	        int madre = generaPosicionPadres();
	        int separacion = ((int) (Math.random() * nivel.getNumColumnas())) + 1;

	        for (int j = 0; j <  nivel.getNumColumnas(); j++) {
	            if (j <= separacion) {
//	            	Esto lo que hace es coger la combinacion que se encuentra en posHijo1/2
//	            	Seguidamente coge la posicion j de esa combinacion y la cambia por el color de la posicion j
//	            	de la posicion madre/padre de la poblacion inical
	            	NuevaPoblacion.get(posHijo1).getFirst().set(j, poblacion.get(madre).getFirst().get(j));
	            	NuevaPoblacion.get(posHijo2).getFirst().set(j, poblacion.get(padre).getFirst().get(j));
	            } else {
	            	NuevaPoblacion.get(posHijo1).getFirst().set(j, poblacion.get(padre).getFirst().get(j));
	            	NuevaPoblacion.get(posHijo2).getFirst().set(j, poblacion.get(madre).getFirst().get(j));
		              }
	        }
	    }


	 private boolean añadirCandidatos() {
		    outer:
		    for (int i = 0; i < POPULATION_SIZE; i++) {
		        for (int j = 0; j < negrasSol.size() && j < blancasSol.size(); j++) {
		            String feed = comprobarCombinacion(solucionesEnviadas.get(j), poblacion.get(i).getFirst());
		            Pair<Integer, Integer> numNegrasBlancas = cuentaNB(feed);
		            if (numNegrasBlancas.getFirst() != negrasSol.get(j) || numNegrasBlancas.getSecond() != blancasSol.get(j)) {
		                continue outer;
		            }
		        }

		        boolean esta = false;
		        if (enviosCandidatos.size() < NMAX_CANDIDATOS) {
		            for (int pos = 0; pos < enviosCandidatos.size() && !esta; pos++) {
		                if (enviosCandidatos.get(pos).getFirst().equals(poblacion.get(i).getFirst())) {
		                    esta = true;
		                }
		            }
		            if (!esta && !solucionesEnviadas.contains(poblacion.get(i).getFirst())) {    	
		                enviosCandidatos.add(poblacion.get(i));
		            }
		        } 
		        else {
//		             E is full
		            return false;
		        }
		    }

		   

		    enviosCandidatos = ordenarPorAptitud(enviosCandidatos);
		    if (enviosCandidatos.size() < NMAX_CANDIDATOS) {
		        return false;
		    }
//		     Mantener solo los mejores NMAX_CANDIDATOS
		    enviosCandidatos = new ArrayList<>(enviosCandidatos.subList(0, NMAX_CANDIDATOS));
		    return true;
		}


	

	private int generaPosicionPadres() {
	        parentPos += (int) (new Random().nextInt(10));
	        if (parentPos < POPULATION_SIZE / 5) {
	            return parentPos;
	        } else {
	            parentPos = parentPos/2;
	        }
	        return parentPos;
	    }
	 
	 
	@Override
	public List<List<Integer>> resolve(List<Integer> solucionUsuario) {
		int numGeneracion = 0;
		solucionesEnviadas.clear();
        if(primerEnvio(solucionUsuario)) return solucionesEnviadas;
        inicializarPoblacion();
        calcularAptitud();
        poblacion = ordenarPorAptitud(poblacion);
	    while(solucionesEnviadas.size() < 10 ) {
	    	enviosCandidatos.clear();
	    	numGeneracion = 0;
	    	while(numGeneracion <= GENERATION_SIZE )  {
	    		evolucionarPoblacion();
	    		calcularAptitud();
	    		poblacion = ordenarPorAptitud(poblacion);
	    		añadirCandidatos();

	    		numGeneracion += 1; 
	    	}
	    	
	    	if( enviosCandidatos.size() > 0 && envia(solucionUsuario, enviosCandidatos.get(0).getFirst())) {
			    	System.out.print("SOLUCION ENCOTNRATADA \n");
			    	return solucionesEnviadas;
			    }
	    }
	   
	    return solucionesEnviadas;
	    
	}
	 private boolean envia(List<Integer> solucionUsuario, List<Integer> bestEnvio) {
		 String feedback = comprobarCombinacion(solucionUsuario, bestEnvio);
		    Pair<Integer, Integer> nb = cuentaNB(feedback);	
		    negrasSol.add(nb.getFirst());
		    blancasSol.add(nb.getSecond());
		    solucionesEnviadas.add(bestEnvio);
		    System.out.print("  ENVIO"+ bestEnvio +" en el intento " + solucionesEnviadas.size()+ "\n");
if(bestEnvio.equals(solucionUsuario)) {
	return true;
}

		    return false;
		    
	}


	private Pair<Integer, Integer> cuentaNB(String feedback) {
		    int n = 0;
		    int b = 0;
		    
		    for (int pos = 0; pos < feedback.length(); pos++) {
		        if (feedback.charAt(pos) == 'N') {
		            n++;
		        } else if (feedback.charAt(pos) == 'B') {
		            b++;
		        }
		    }
		    
		    return new Pair<>(n, b);
		    
		}
	
	

	private boolean primerEnvio(List<Integer> solucionUsuario) {
		List<Integer> combi =new ArrayList<>();
		combi.add(0);
		combi.add(0);
		combi.add(1);
		combi.add(1);
		combi.add(2);
		solucionesEnviadas.add(combi);
		String f = comprobarCombinacion(solucionUsuario, combi);
		Pair<Integer, Integer> nb = cuentaNB(f);	
		negrasSol.add(nb.getFirst());
		blancasSol.add(nb.getSecond());
		
		return  f == "NNNNN";
	}


//	 Calcular la aptitud de una solución candidata
	private void calcularAptitud() {
		        for (int i = 0; i < POPULATION_SIZE; i++) {
		            int x = 0;
		            int y = 0;
		            for (int j = 0; j < solucionesEnviadas.size(); j++) {
		                String feedback = comprobarCombinacion(poblacion.get(i).getFirst(), solucionesEnviadas.get(j));
		                Pair<Integer, Integer> nb = cuentaNB(feedback);	
		                x += Math.abs(nb.getFirst() - negrasSol.get(j));
		                y += Math.abs(nb.getSecond() - blancasSol.get(j));
		            }
		            poblacion.get(i).setSecond(x + y);
		        }

		    }



	public String comprobarCombinacion(List<Integer> solution, List<Integer> solEnviada) {
	    int aciertos = 0;
	    int semiaciertos = 0;
	    String feedback = "";

	    List<Integer> sol = new ArrayList<>(solution);
	    List<Integer> env = new ArrayList<>(solEnviada);

	    for (int i = 0; i < sol.size(); i++) {
	        if (env.get(i).equals(sol.get(i))) {
	            aciertos++;
	            feedback += "N";
	            sol.set(i, -1);
	            env.set(i, -1);
	        }
	    }

	    for (int posEnv = 0; posEnv < sol.size(); posEnv++) {
	    	if (env.get(posEnv) != -1) {
	            for (int posSol = 0; posSol < sol.size(); posSol++) {
	                if (posSol != posEnv && env.get(posEnv).equals(sol.get(posSol))) {
	                    semiaciertos++;
	                    feedback += "B";
	                    sol.set(posSol, -1);
	                    break; 
	                }
	            }
	        }
	    }

	    return feedback;
	}


	@Override
	public void setSolucion(List<Integer> solution) {
//		 TODO Auto-generated method stub
		
	}

}
