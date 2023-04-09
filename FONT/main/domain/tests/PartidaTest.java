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
    
    /*
     * Comprueba que se introduzcan bien todos los parametros
     */
	@Test
	public void testParameters() {
		assertEquals("The username should be the one given", "Juan" ,testPartidaAyuda.getUsuario());
		assertEquals("The difficulty should be the one given", 1, testPartidaAyuda.getDificultad(), 0);
		assertEquals("The ayuda should be true",  true, testPartidaAyuda.getAyuda());
		assertEquals("The ayuda should be false", false, testPartidaSinAyuda.getAyuda());
		assertEquals("The estado Partida should be running", testPartidaSinAyuda.getEstadoPartida(), PossiblesEstadosPartida.RUNNING);
	}
	

}
