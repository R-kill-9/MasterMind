package main.domain.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import java.util.Map;
import java.util.HashMap;
import org.junit.Test;
import main.domain.Ranking;

public class RankingTest {
	
	/*
	 * Atributos
	 */
	private Ranking newRanking;
	
	/*
	 * Método que se ejecuta antes de cada test
	 */
	@Before
	public void setUp() {
		 Map<String, Integer> posicionesActual = new HashMap<String, Integer>();
	     posicionesActual.put("Eren", 100);
	     posicionesActual.put("Gaara", 200);
	     newRanking = new Ranking("Fácil", posicionesActual);
	}
	
	/*
	 * TESTS
	 */
	/*
	 * Comprueba que se añada correctamente una partida al ranking
	 */
	@Test
    public void testAddPartida() {
		newRanking.addPartida("Kirito", 400);
        assertTrue(newRanking.getPosiciones().containsKey("Kirito"));
        assertTrue(newRanking.getPosiciones().containsValue(400));
    }
	
	/*
	 * Comprueba que se devuelva correctamente el Map que guarda las posiciones de los distintos usuarios
	 */
	@Test
    public void testGetPosiciones() {
        Map<String, Integer> posiciones = new HashMap<String, Integer>();
        posiciones.put("Eren", 100);
        posiciones.put("Gaara", 200);

        assertEquals(posiciones, newRanking.getPosiciones());
        assertTrue(newRanking.getPosiciones().size() == 2);
    }
}
