package main.domain.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import java.util.ArrayList;
import org.junit.Test;
import main.domain.Combinacion;
import main.domain.Color;


public class CombinacionTest {

	/*Atributos*/
	private Combinacion newCombinacion;
    
    /*
     * Método que se aplica siempre antes de hacer un test
     */
    @Before
    public void setUp() {
    	ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.BLUE);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
        newCombinacion = new Combinacion(colores);

    }
	
    /*
     * TESTS
     */
    /*
     * Comprueba que se obtenga correctamente una Combinacion
     */
    @Test
	public void testGetCombination() {
    	ArrayList<Color> newColores = new ArrayList<Color>();
		newColores.add(Color.RED);
		newColores.add(Color.BLUE);
		newColores.add(Color.GREEN);
		newColores.add(Color.YELLOW);
		assertEquals(newCombinacion.getCombination(), newColores);
	}
    
    /*
     * Comprueba que se introduce correctamente una Combinacion
     */
    @Test
	public void testSetCombination() {
    	ArrayList<Color> newColores = new ArrayList<Color>();
		newColores.add(Color.BLUE);
		newColores.add(Color.RED);
		newColores.add(Color.YELLOW);
		newColores.add(Color.GREEN);
		newCombinacion.setCombination(newColores);
		assertEquals(newCombinacion.getCombination(), newColores);
	}

}
