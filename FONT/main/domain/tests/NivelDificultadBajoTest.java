
package main.domain.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.NivelDificultadBajo;

import java.util.ArrayList;
import java.util.List;

import main.domain.Combinacion;
import main.domain.Color;


public class NivelDificultadBajoTest {
	 NivelDificultadBajo ndb;

	  @Before public void setUp() {
	    ndb = new NivelDificultadBajo();
	  }
	  
	

	@Test
	public void test() {
		assertEquals( 7000 /*puntos*/, ndb.calculaPuntuacion(3,7) ); 
	}
	
	@Test
	public void testGetNumeroColumnas() {
		Integer numcol = 4;
		assertEquals( numcol, ndb.getNumColumnas() ); 
	}
	

	@Test
	public void testCombinacionesIguales() {

		ArrayList<Integer> solucion = new ArrayList<Integer>();
		solucion.add(1);
		solucion.add(5);
		solucion.add(3);
		solucion.add(0);
        
		ArrayList<Integer> envio = new ArrayList<Integer>();
		envio.add(1);
		envio.add(5);
		envio.add(3);
		envio.add(0);
		String solucionIguales = new String("NNNN");
		assertEquals( solucionIguales, ndb.comprobarCombinacion(solucion, envio)); 
	}
	

	@Test
	public void testCombinaciones2Iguales1Semi() {

		ArrayList<Integer> solucion = new ArrayList<Integer>();
		solucion.add(1);
		solucion.add(5);
		solucion.add(3);
		solucion.add(0);
        
		ArrayList<Integer> envio = new ArrayList<Integer>();
		envio.add(1);
		envio.add(4);
		envio.add(3);
		envio.add(5);
		
		String solucionIguales = new String("NNB");
		assertEquals( solucionIguales, ndb.comprobarCombinacion(solucion, envio) ); 
	}
		
	@Test
	public void testCombinacionesIgualesConPista() {

		ArrayList<Integer> solucion = new ArrayList<Integer>();
		solucion.add(1);
		solucion.add(5);
		solucion.add(3);
		solucion.add(0);
		ArrayList<Integer> envio = solucion;
		String solucionIguales = new String("NNNN");
		assertEquals( solucionIguales, ndb.comprobaCombinacionPista(solucion, envio) ); 
	}

	@Test
	public void testCombinaciones2Iguales1SemiConPista() {

		ArrayList<Integer> solucion = new ArrayList<Integer>();
		solucion.add(1);
		solucion.add(5);
		solucion.add(3);
		solucion.add(0);
        
		ArrayList<Integer> envio = new ArrayList<Integer>();
		envio.add(1);
		envio.add(4);
		envio.add(3);
		envio.add(5);
		
		String solucionIguales = new String("N NB");
		assertEquals( solucionIguales, ndb.comprobaCombinacionPista(solucion, envio)); 
	}


	@Test
	public void TestSolveWinRoundV() {
		ArrayList<Integer> colores = new ArrayList<Integer>();
		colores.add(1);
		colores.add(2);
		colores.add(3);
		colores.add(4);
        List<Integer> solucion = new ArrayList<Integer>(colores);

		int resultado = ndb.resolve(solucion).size();
        assertTrue(resultado >= 1 && resultado <= 10);
	}
	
	


}
