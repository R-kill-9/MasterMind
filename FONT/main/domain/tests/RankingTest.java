package main.domain.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import java.util.Map;
import java.util.HashMap;
import org.junit.Test;
import main.domain.Ranking;

public class RankingTest {
	

	/*
	 * Comprueba que se añada correctamente una partida al ranking
	 */
	@Test
	public void addPartidaTest() {
		Ranking testRanking = new Ranking();
        testRanking.addPartida("Gaara", 200, 1);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Gaara", 200);
        assertEquals(expected, testRanking.getRanking(1));
        
        /*
         * Comprueba que se actualice el valor de Gaara
         */
        testRanking.addPartida("Gaara", 250, 1);
        testRanking.addPartida("Eren", 150, 1);
        testRanking.addPartida("Sasuke", 300, 1);
        expected.put("Sasuke", 300);
        expected.put("Gaara", 250);
        expected.put("Eren", 150);
        assertEquals(expected, testRanking.getRanking(1));
 
        /*
         * Comprueba que si a Sasuke se le da una puntuación menor en el ranking sigue estando la más alta
         */
        testRanking.addPartida("Sasuke", 100, 1);
        assertEquals(expected, testRanking.getRanking(1));
    }
	
	/*
	 * Comprueba que se devuelva correctamente el Map que guarda las posiciones de los distintos usuarios
	 */
	@Test
    public void testGetPosiciones() {
		Ranking testRanking = new Ranking();
        testRanking.addPartida("Gaara", 200, 1);
        testRanking.addPartida("Eren", 150, 1);
        testRanking.addPartida("Sasuke", 300, 1);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Sasuke", 300);
        expected.put("Gaara", 200);
        expected.put("Eren", 150);
        assertEquals(expected, testRanking.getRanking(1));
    }
}
