
package main.domain.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.NivelDificultadMedio;

import java.util.ArrayList;
import main.domain.Combinacion;
import main.domain.Color;

public class NivelDificultadMedioTest {
	 NivelDificultadMedio ndm;

	  @Before public void setUp() {
	    ndm = new NivelDificultadMedio();
	  }
	  
	

	@Test
	public void test() {
		assertEquals( 70000 /*puntos*/, ndm.calculaPuntuacion(3,7) ); 
	}
	
	@Test
	public void testGetNumeroColumnas() {
		Integer numcol = 4;
		assertEquals( numcol, ndm.getNumColumnas() ); 
	}

	@Test
	public void TestSolveWinRoundX() {
		ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
		colores.add(Color.PURPLE);
        Combinacion solucionUsuario = new Combinacion(colores);
        int resultado = ndm.resolve(solucionUsuario).size();
        assertTrue(resultado >= 1 && resultado <= 10);
	}
	@Test
	public void TestSolveWinRepes() {
		ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
		colores.add(Color.RED);
        Combinacion solucionUsuario = new Combinacion(colores);
        int resultado = ndm.resolve(solucionUsuario).size();
        assertTrue(resultado >= 1 && resultado <= 10);
	}

}
