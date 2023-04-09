package main.domain.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


import org.junit.Before;
import org.junit.Test;

import main.domain.Color;
import main.domain.Combinacion;
import main.domain.Partida;
import main.domain.PossiblesEstadosPartida;

public class PartidaTest {
	
	/* Atributos */
    private Partida testPartidaAyuda;
    private Partida testPartidaSinAyuda;
    
    /*
     * Método que se aplica siempre antes de hacer un test
     * Creamos una partida nueva, de Juan sin ayuda y de primer rol CodeMaker
     */
    @Before
    public void setUp() {
        testPartidaAyuda = new Partida(1, "Juan", true, false);
        testPartidaSinAyuda = new Partida(1, "Juan", false, false);
    }
    
	@Test
	public void testParameters() {
		assertEquals("The username should be the one given", "Juan" ,testPartidaAyuda.getUsuario());
		assertEquals("The difficulty should be the one given", 1, testPartidaAyuda.getDificultad(), 0);
		assertEquals("The ayuda should be true",  true, testPartidaAyuda.getAyuda());
		assertEquals("The ayuda should be false", false, testPartidaSinAyuda.getAyuda());
		assertEquals("The estado Partida should be running", testPartidaSinAyuda.getEstadoPartida(), PossiblesEstadosPartida.RUNNING);
	}
	
	@Test
	public void testSetSolutionAsCodeMakerLevel1() throws Exception{
		Random random = new Random();
        ArrayList<Color> combinacion = new ArrayList<Color>();
        boolean doneComb = false;
        ArrayList<Boolean> visto = new ArrayList<Boolean>(Collections.nCopies(6, false));
        while(!doneComb){
        	Integer randomNumber = random.nextInt(6);
        	if(!visto.get(randomNumber)) {
	        	switch (randomNumber) {
	        		case 1:
	        			combinacion.add(Color.RED);
	        			break;
	        		case 2:
	        			combinacion.add(Color.GREEN);
	        			break;
	        		case 3:
	        			combinacion.add(Color.BLUE);
	        			break;
	        		case 4:
	        			combinacion.add(Color.YELLOW);
	        			break;
	        		case 5:
	        			combinacion.add(Color.PURPLE);
	        			break;
	        		case 6:
	        			combinacion.add(Color.ORANGE);
	        			break;
	        	}
	        	visto.set(randomNumber,true);
        	}
        	if(combinacion.size() - 1 == 4) doneComb = true;
        }
		System.out.println(combinacion);
		testPartidaAyuda.setSolution(combinacion);
		Combinacion solutionPartida = testPartidaAyuda.getSolution();
		assertEquals("The partida should have saved the combination", new Combinacion(combinacion), solutionPartida);
	}

}
