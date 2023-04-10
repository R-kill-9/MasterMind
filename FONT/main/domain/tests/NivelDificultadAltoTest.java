
package main.domain.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.NivelDificultadMedio;

import java.util.ArrayList;
import main.domain.Combinacion;
import main.domain.NivelDificultadAlto;
import main.domain.Color;

public class NivelDificultadAltoTest {
	 NivelDificultadAlto nda;

	  @Before public void setUp() {
		  nda = new NivelDificultadAlto();
	  }
	  
	

	@Test
	public void testPuntuacio() {
		assertEquals( 7000000 /*puntos*/, nda.calculaPuntuacion(3,7) ); 
	}
	
	@Test
	public void testGetNumeroColumnas() {
		Integer numcol = 5;
		assertEquals( numcol, nda.getNumColumnas() ); 
	}
	
	@Test
	public void TestSolveWinRoundX() {
		ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
		colores.add(Color.PURPLE);
		colores.add(Color.ORANGE);
        Combinacion solucionUsuario = new Combinacion(colores);
        int resultado = nda.resolve(solucionUsuario).size();
        assertTrue(resultado >= 1 && resultado <= 10);
	}

}
