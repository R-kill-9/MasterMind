
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
	public void TestSolveWinRound1() {
		ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.BLUE);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
        Combinacion solucionUsuario = new Combinacion(colores);
        
		assertEquals( 1 ,ndm.resolve(solucionUsuario));
		
	}
	@Test
	public void TestSolveWinRoundX() {
		ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
		colores.add(Color.PURPLE);
        Combinacion solucionUsuario = new Combinacion(colores);
		assertEquals( 5 ,ndm.resolve(solucionUsuario));
		
	}

}
