
package main.domain.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.NivelDificultadMedio;

import java.util.ArrayList;
import java.util.List;

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
	public void TestSolveWin() {
		ArrayList<Integer> colores = new ArrayList<Integer>();
		colores.add(1);
		colores.add(2);
		colores.add(3);
		colores.add(4);
        List<Integer> solucion = new ArrayList<Integer>(colores);

		int resultado = ndm.resolve(solucion).size();
        assertTrue(resultado >= 1 && resultado <= 10);
	}
	@Test
	public void TestSolveWinRepetidos() {
		ArrayList<Integer> colores = new ArrayList<Integer>();
		colores.add(1);
		colores.add(1);
		colores.add(1);
		colores.add(2);
        List<Integer> solucion = new ArrayList<Integer>(colores);

		int resultado = ndm.resolve(solucion).size();
        assertTrue(resultado >= 1 && resultado <= 10);
	}
	
	

}
