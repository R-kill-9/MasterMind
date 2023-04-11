
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

		ArrayList<Integer> colores = new ArrayList<Integer>();
		colores.add(1);
		colores.add(2);
		colores.add(3);
		colores.add(4);
        List<Integer> solucion = new ArrayList<Integer>(colores);

        List<Integer> envio = new ArrayList<Integer>(colores);
		String solucionIguales = new String("NNNN");
		assertEquals( solucionIguales, ndb.comprobarCombinacion(solucion, envio)); 
	}

	@Test
	public void testCombinaciones2Iguales1Semi() {

		ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.BLUE);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
        Combinacion solucion = new Combinacion(colores);
		ArrayList<Color> colores2 = new ArrayList<Color>();
		colores2.add(Color.RED);
		colores2.add(Color.BLUE);
		colores2.add(Color.RED);
		colores2.add(Color.GREEN);
        Combinacion envio = new Combinacion(colores2);
		String solucionIguales = new String("NNB");
		assertEquals( solucionIguales, ndb.comprobarCombinacion(solucion, envio) ); 
	}
	@Test
	public void testCombinacionesDiferentes() {

		ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.RED);
		colores.add(Color.RED);
		colores.add(Color.RED);
        Combinacion solucion = new Combinacion(colores);
		ArrayList<Color> colores2 = new ArrayList<Color>();
		colores2.add(Color.BLUE);
		colores2.add(Color.BLUE);
		colores2.add(Color.BLUE);
		colores2.add(Color.BLUE);
        Combinacion envio = new Combinacion(colores2);
		String solucionIguales = new String("");
		assertEquals( solucionIguales, ndb.comprobarCombinacion(solucion, envio) ); 
	}
	@Test
	public void testCombinacionesIgualesConPista() {

		ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.BLUE);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
        Combinacion solucion = new Combinacion(colores);

        Combinacion envio = new Combinacion(colores);
		String solucionIguales = new String("NNNN");
		assertEquals( solucionIguales, ndb.comprobarCombinacion(solucion, envio) ); 
	}

	@Test
	public void testCombinaciones2Iguales1SemiConPista() {

		ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.BLUE);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
        Combinacion solucion = new Combinacion(colores);
		ArrayList<Color> colores2 = new ArrayList<Color>();
		colores2.add(Color.RED);
		colores2.add(Color.BLUE);
		colores2.add(Color.PURPLE);
		colores2.add(Color.GREEN);
        Combinacion envio = new Combinacion(colores2);
		String solucionIguales = new String("NN B");
		assertEquals( solucionIguales, ndb.comprobarCombinacionPista(solucion, envio)); 
	}


	@Test
	public void TestSolveWinRoundV() {
		ArrayList<Integer> colores = new ArrayList<Integer>();
		colores.add(1);
		colores.add(2);
		colores.add(3);
		colores.add(4);
        List<Integer> solucion = new ArrayList<Integer>(colores);

        List<Integer> envio = new ArrayList<Integer>(colores);
		int resultado = ndb.resolve(solucion).size();
        assertTrue(resultado >= 1 && resultado <= 10);
	}

}
