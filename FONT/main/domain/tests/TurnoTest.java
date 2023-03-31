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
     * Comprueba que la función changeTurno cambie su variable privada Rol correctamente
     */
    @Test
    public void testChangeTurno() {
    	turnoActual.changeTurno();
        assertFalse(this.turnoActual.getRol());
        turnoActual.changeTurno();
        assertTrue(this.turnoActual.getRol());
    }
	
    /*
     * Comprueba que se haga bien el get del Rol del turno
     */
    @Test
    public void testGetRol() {
        assertTrue(this.turnoActual.getRol());
        this.turnoActual.changeTurno();
        assertFalse(this.turnoActual.getRol());
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
        
        assertEquals("Tiene que devolver el último valor del totalde combinaciones guardadas, que debe coincidir con el recién añadido", createdCombination.getCombination(), lastCombination.getCombination());
        assertEquals(1, turnoActual.getNumberComb());
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
        
        assertEquals(1, turnoActual.getNumberComb());
    }
}
