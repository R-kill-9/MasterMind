package main.domain.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.domain.Color;
import main.domain.Partida;
import main.domain.PossiblesEstadosPartida;

public class PartidaTest {
	
	/* Atributos */
    private Partida testPartidaAyuda;
    private Partida testPartidaSinAyuda;
    private ArrayList<Color> solutionGame;
    
    /*
     * Método que se aplica siempre antes de hacer un test
     * Creamos una partida nueva, de Juan sin ayuda y de primer rol CodeMaker
     */
    @Before
    public void setUp() {
        testPartidaAyuda = new Partida(1, "Juan", false, true);
        testPartidaSinAyuda = new Partida(1, "Juan", true, true);
        solutionGame = new ArrayList<Color>();
        for(Integer i = 0; i < 4; i++) {
        	solutionGame.add(Color.BLUE);
        }
    }
    
	@Test
	public void testParameters() {
		assertEquals("The username should be the one given", "Juan" ,testPartidaAyuda.getUsuario());
		assertEquals("The difficulty should be the one given", 1, testPartidaAyuda.getDificultad(), 0);
		assertEquals("The ayuda should be true",  false, testPartidaAyuda.getAyuda());
		assertEquals("The ayuda should be false", true, testPartidaSinAyuda.getAyuda());
		assertEquals("The estado Partida should be running", testPartidaSinAyuda.getEstadoPartida(), PossiblesEstadosPartida.RUNNING);
	}
	
	@Test 
	public void testSetSolutionAsCodeMaker() throws Exception{
		Integer numIntentos = testPartidaAyuda.setSolution(solutionGame);
		assertTrue(numIntentos >= 1 && numIntentos <= 10);
	}
	
	
	

}
