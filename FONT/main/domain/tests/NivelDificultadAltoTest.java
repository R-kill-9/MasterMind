
package main.domain.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.NivelDificultadMedio;

import java.util.ArrayList;
import java.util.List;

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
	public void TestSolveWin() {
		ArrayList<Integer> solucion = new ArrayList<Integer>();
		solucion.add(0);
		solucion.add(5);
		solucion.add(2);
		solucion.add(3);
		solucion.add(2);
		
		int resultado = nda.resolve(solucion).size();
		assertTrue(resultado > 0 && resultado <= 10);
	}
	@Test
	public void TestSolveWinRepetidos() {
		ArrayList<Integer> solucion = new ArrayList<Integer>();
		solucion.add(1);
		solucion.add(1);
		solucion.add(1);
		solucion.add(1);
		solucion.add(1);

		int resultado = nda.resolve(solucion).size();
		assertTrue(resultado >0 && resultado <= 10);
	}
	
	

}
