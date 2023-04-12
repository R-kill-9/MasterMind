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
     * Comprueba que se obtenga correctamente una Combinacion
     */
    @Test
	public void testSetGetCombination() {
    	ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.BLUE);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
        newCombinacion = new Combinacion(colores);
		assertEquals("The combination obtained should be the one given", colores, newCombinacion.getCombination());
	}

    /*
     * Comprueba que se obtiene correctamente el valor para una posición
     */
    @Test
    public void testGetPosition() {
    	ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.BLUE);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
        Combinacion combinacion = new Combinacion(colores);
        assertEquals("The color obtained should be red", Color.RED, combinacion.getPosition(0));
        assertEquals("The color obtained should be blue", Color.BLUE, combinacion.getPosition(1));
        assertEquals("The color obtained should be green", Color.GREEN, combinacion.getPosition(2));
        assertEquals("The color obtained should be yellow", Color.YELLOW, combinacion.getPosition(3));
    }
    
    /*
     * Comprueba que se pueda quitar un color de una combinación
     */
    @Test
    public void testRemove() {
    	ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.BLUE);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
        Combinacion combinacion = new Combinacion(colores);
        combinacion.remove(Color.RED);
        assertFalse(combinacion.contains(Color.RED));
        assertEquals(3, combinacion.getCombination().size());
    }

}
