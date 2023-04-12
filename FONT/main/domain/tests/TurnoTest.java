package main.domain.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import main.domain.Turno;
import main.domain.Combinacion;
import main.domain.Color;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class TurnoTest {
	/*
	 * Atributos
	 */
	private Turno turnoActual;
    
	/*
	 * Método que se aplica siempre antes de hacer un test
	 */
    @Before
    public void setUp() {
        turnoActual = new Turno(true);
    }
    
    /*
     * TESTS
     */
    /*
     * Comprueba que la función changeTurno cambie su variable privada Rol correctamente y que se haga bien el get de rol.
     */
    @Test
    public void testChangeTurno() {
    	turnoActual.changeTurno();
        assertFalse("The actual Rol should be CodeBreaker(false)", this.turnoActual.getRol());
        turnoActual.changeTurno();
        assertTrue("The actual Rol should be CodeBreaker(true)",this.turnoActual.getRol());
    }
	
    
    /*
     * Comprueba que se añada la combinacion de colores a la lista de combinaciones
     */
    @Test
    public void testSetCombinacion() {
    	ArrayList<Color> colores = new ArrayList<Color>();
        colores.add(Color.RED);
        colores.add(Color.BLUE);
        colores.add(Color.GREEN);
        colores.add(Color.YELLOW);
        
        Combinacion createdCombination = new Combinacion(colores);
        turnoActual.setCombinacion(colores);
        ArrayList<Combinacion> totalComb = turnoActual.getCombinaciones();
        Combinacion lastCombination = totalComb.get(turnoActual.getNumberComb()-1);
        
        assertEquals("It must return the last value of the total saved combinations, which should match the newly added one", createdCombination.getCombination(), lastCombination.getCombination());
        
    }
    
    /*
     * Comprueba que se obtenga bien la última combinacion añadida
     */
    @Test
    public void testGetLastCombinacion() {
    	ArrayList<Color> colores1 = new ArrayList<Color>();
        colores1.add(Color.RED);
        colores1.add(Color.BLUE);
        colores1.add(Color.GREEN);
        colores1.add(Color.YELLOW);
        ArrayList<Color> colores2 = new ArrayList<Color>();
        colores1.add(Color.BLUE);
        colores1.add(Color.RED);
        colores1.add(Color.GREEN);
        colores1.add(Color.YELLOW);
    	turnoActual.setCombinacion(colores1);
    	turnoActual.setCombinacion(colores2);
    	Combinacion ultimaCombinacion = turnoActual.getLastCombinacion();
    	assertEquals("It should return colores2, the las combination set", colores2, ultimaCombinacion.getCombination());
    }
    
    
    /*
     * Comprueba que se devuelva la cantidad de combinaciones almacenadas correcta
     */
    @Test
    public void testGetNumberComb() {
    	assertEquals(0, turnoActual.getNumberComb());
    	
    	ArrayList<Color> colores = new ArrayList<Color>();
        colores.add(Color.RED);
        colores.add(Color.BLUE);
        colores.add(Color.GREEN);
        colores.add(Color.YELLOW);
        turnoActual.setCombinacion(colores);
        
        assertEquals("It should return the number of combinations, in this case 1", 1, turnoActual.getNumberComb());
    }
    
    /*
     * Comprueba que se añadan todas las combinaciones
     */
    @Test
	public void testSetAllComb() {
		Turno turno = new Turno(false);
		ArrayList<Color> colores1 = new ArrayList<Color>();
		colores1.add(Color.RED);
        colores1.add(Color.BLUE);
        colores1.add(Color.GREEN);
        colores1.add(Color.YELLOW);
        ArrayList<Color> colores2 = new ArrayList<Color>();
        colores1.add(Color.BLUE);
        colores1.add(Color.RED);
        colores1.add(Color.GREEN);
        colores1.add(Color.YELLOW);
        ArrayList<Color> colores3 = new ArrayList<Color>();
        colores1.add(Color.BLUE);
        colores1.add(Color.RED);
        colores1.add(Color.YELLOW);
        colores1.add(Color.GREEN);
		Combinacion comb1 = new Combinacion(colores1);
		Combinacion comb2 = new Combinacion(colores2);
		Combinacion comb3 = new Combinacion(colores3);
		List<Combinacion> combHechas = new ArrayList<Combinacion>();
		combHechas.add(comb1);
		combHechas.add(comb2);
		combHechas.add(comb3);
		
		turno.setAllComb(combHechas);
		
		ArrayList<Combinacion> combinaciones = turno.getCombinaciones();
		assertEquals("The size of the combinations list should be 3", 3, combinaciones.size());
		assertEquals("The first combination should be the same as the first one set", comb1, combinaciones.get(0));
		assertEquals("The second combination should be the same as the second one set", comb2, combinaciones.get(1));
		assertEquals("The third combination should be the same as the third one set", comb3, combinaciones.get(2));
	}
    
    /*
     * Comprueba que se hace un borrado de todas las combinaciones
     */
    @Test
	public void testEraseCombinations() {
    	Turno turno = new Turno(false);
		ArrayList<Color> colores1 = new ArrayList<Color>();
		colores1.add(Color.RED);
        colores1.add(Color.BLUE);
        colores1.add(Color.GREEN);
        colores1.add(Color.YELLOW);
        ArrayList<Color> colores2 = new ArrayList<Color>();
        colores1.add(Color.BLUE);
        colores1.add(Color.RED);
        colores1.add(Color.GREEN);
        colores1.add(Color.YELLOW);
        ArrayList<Color> colores3 = new ArrayList<Color>();
        colores1.add(Color.BLUE);
        colores1.add(Color.RED);
        colores1.add(Color.YELLOW);
        colores1.add(Color.GREEN);
	
		turno.setCombinacion(colores1);
		turno.setCombinacion(colores2);
		turno.setCombinacion(colores3);
		
		turno.eraseCombinations();
		
		ArrayList<Combinacion> combinaciones = turno.getCombinaciones();
		assertEquals("The size of the combinations list should be 0", 0, combinaciones.size());
	}
}
