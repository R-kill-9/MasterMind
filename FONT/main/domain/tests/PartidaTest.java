package main.domain.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.domain.Color;
import main.domain.Combinacion;
import main.domain.Partida;
import main.domain.PossiblesEstadosPartida;

public class PartidaTest {
	/*Atributos*/
    private Partida testPartidaAyuda;
    private Partida testPartidaSinAyuda;
    
    /*
     * Método que se aplica siempre antes de hacer un test
     * Creamos una partida nueva, de Juan sin ayuda y de rol codeMaker
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
	
	/*
	 * Comprueba que se deuvuelva el estado de la partida correctamente
	 */
	@Test
    public void testGetEstadoPartida() {
        assertEquals(PossiblesEstadosPartida.RUNNING, testPartidaSinAyuda.getEstadoPartida());
    }
    
	/*
	 * Comprueba que se introduzca una fecha para la partida
	 */
    @Test
    public void testGetData() {
        assertNotNull(testPartidaSinAyuda.getData());
    }
    
    /*
     * Comprueba que se cambie bien el valor de ayuda en una partida
     */
    @Test
    public void testSetAyuda() {
    	testPartidaSinAyuda.setAyuda();
    	testPartidaAyuda.setAyuda();
        assertTrue(testPartidaSinAyuda.getAyuda());
        assertFalse(testPartidaAyuda.getAyuda());
    }
    
    /*
     *Comprueba que la solucion que se le introduce a una partida se guarde correctamente 
     */
    @Test
    public void testGetSolution() {
        ArrayList<Color> colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.BLUE);
		colores.add(Color.GREEN);
		colores.add(Color.YELLOW);
		Combinacion comb = new Combinacion(colores);
        int a = testPartidaAyuda.setSolution(colores);
        assertEquals(testPartidaAyuda.getSolution(), comb);
    }
    
    /*
     *Comprueba que devuelva algun valor para puntuacion
     */
    @Test
    public void testGetPuntuacion() {
        assertNotNull(testPartidaAyuda.getPuntuacion());
    }
    


 
}
