package main.domain.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.domain.Color;
import main.domain.Combinacion;
import main.domain.NivelDificultad;
import main.domain.NivelDificultadBajo;

public class NivelDificultadTest {
	NivelDificultad nd;
	
	@Before
	public void setUp() throws Exception {
		nd = new NivelDificultad();
		}	

		@Test
		public void testCombinacionesIguales() {

			ArrayList<Color> colores = new ArrayList<Color>();
			colores.add(Color.RED);
			colores.add(Color.BLUE);
			colores.add(Color.GREEN);
			colores.add(Color.YELLOW);
	        Combinacion solucion = new Combinacion(colores);

	        Combinacion envio = new Combinacion(colores);
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

	}

}
