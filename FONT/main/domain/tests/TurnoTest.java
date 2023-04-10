package main.domain.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import main.domain.Turno;
import main.domain.Combinacion;
import main.domain.Color;
import org.junit.Test;
import java.util.ArrayList;

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
}
