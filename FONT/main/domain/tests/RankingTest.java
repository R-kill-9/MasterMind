package main.domain.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import java.util.Map;
import java.util.TreeMap;
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
	     newRanking = new Ranking();
	     newRanking.addPartida("Test-Player1", 100, 1);
	     newRanking.addPartida("Test-Player1", 200, 2);
	     newRanking.addPartida("Test-Player1", 400, 3);
	}
	
	/*
	 * TESTS
	 */
	/*
	 * Comprueba que se añada correctamente una partida de nivel de dificultad 1 al ranking
	 */
	@Test
    public void testAddPartida() {
        assertTrue("The first ranking should contain the given Partida",newRanking.getRanking(1).containsKey("Test-Player1"));
        assertTrue("The second ranking should contain the given Partida",newRanking.getRanking(2).containsKey("Test-Player1"));
        assertTrue("The third ranking should contain the given Partida",newRanking.getRanking(3).containsKey("Test-Player1"));
    }
	
	/*
	 * Comprueba que se devuelva correctamente el TreeMap que guarda las posiciones de los distintos usuarios
	 */
	@Test
    public void testOrderCorrect() {
		newRanking.addPartida("Test-Player2", 50, 1);
		
        TreeMap<String, Integer> expectedOrder = new TreeMap<String, Integer>();
        expectedOrder.put("Test-Player1", 100);
        expectedOrder.put("Test-Player2", 50);
        
        assertEquals("The ranking should order correctly", expectedOrder, newRanking.getRanking(1));
    }
	
	@Test
    public void testNewMaxPuntuation() {
		newRanking.addPartida("Test-Player1", 150, 1);
		
        TreeMap<String, Integer> expectedPlayer = new TreeMap<String, Integer>();
        expectedPlayer.put("Test-Player1", 150);
        
        assertEquals("The puntuation of the player should be 150", expectedPlayer, newRanking.getRanking(1));
    }
}

