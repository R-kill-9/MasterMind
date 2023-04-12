package main.domain.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import java.util.ArrayList;
import org.junit.Test;
import main.domain.Combinacion;
import main.domain.HistorialPartidasGuardadas;
import main.domain.Partida;
import main.domain.Color;


public class CombinacionTest {

	/*Atributos*/
	private Combinacion newCombinacion;
	private ArrayList<Color> colores;
	
	@Before
    public void setUp() {
		colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.BLUE);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
		newCombinacion = new Combinacion(colores);
    }
    /*
     * Comprueba que se obtenga correctamente una Combinacion
     */
    @Test
	public void testGetCombination() {
		assertEquals("The combination obtained should be the one created", colores, newCombinacion.getCombination());
	}

    /*
     * Comprueba que se obtiene correctamente el valor para una posición
     */
    @Test
    public void testGetCombinationPositions() {
        assertEquals("The color obtained should be red", Color.RED, newCombinacion.getPosition(0));
        assertEquals("The color obtained should be blue", Color.BLUE, newCombinacion.getPosition(1));
        assertEquals("The color obtained should be green", Color.GREEN, newCombinacion.getPosition(2));
        assertEquals("The color obtained should be yellow", Color.YELLOW, newCombinacion.getPosition(3));
    }
    
    /*
     * Comprueba que se pueda quitar un color de una combinación
     */
    @Test
    public void testRemoveColor() throws Exception {
    	Integer sizeBefore = newCombinacion.getCombination().size();
        newCombinacion.remove(Color.RED);
        Integer sizeAfter = newCombinacion.getCombination().size();
        assertNotEquals("The size should be not equal before and after", sizeBefore, sizeAfter, 0);
        assertEquals("The size after should be one less than before", sizeBefore-1, sizeAfter, 0);
    }
    
    @Test
    public void testRemoveNotExistColorThrowsException() throws Exception {
    	Integer sizeBefore = newCombinacion.getCombination().size();
    	Exception exception = assertThrows(Exception.class, () -> {
        	newCombinacion.remove(Color.ORANGE);
	    });
		String expectedMessage = "The color does not exists";
	    String actualMessage = exception.getMessage();
	    assertTrue("It should raise the exception if I try to remove a non existing color", actualMessage.contains(expectedMessage));
	    Integer sizeAfter = newCombinacion.getCombination().size();
        assertEquals("The size should have not changed", sizeBefore, sizeAfter, 0);
    }
    

}
