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

}
