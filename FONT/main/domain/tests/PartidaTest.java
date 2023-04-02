package main.domain.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.Partida;

public class PartidaTest {
	/*Atributos*/
    private Partida testPartidaAyuda;
    private Partida testPartidaSinAyuda;
    
    /*
     * Método que se aplica siempre antes de hacer un test
     * Creamos una partida nueva, de Juan sin ayuda y de rol
     */
    @Before
    public void setUp() {
        testPartidaAyuda = new Partida(1, "Juan", false, false);
        testPartidaSinAyuda = new Partida(1, "Juan", false, false);
    }
    
	@Test
	public void test() {
		Partida partida = new Partida(0, "Juan", false, false);
		assertEquals(partida.getUsuario(), "Juan");
	}

}
