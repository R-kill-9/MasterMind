package main.domain.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.domain.Partida;
import main.domain.PossiblesEstadosPartida;

public class PartidaTestTest {
	private Partida testPartidaAyuda;
    private Partida testPartidaSinAyuda;

	@Before
	public void setUp() throws Exception {
		 testPartidaAyuda = new Partida(1, "Juan", true, false);
	     testPartidaSinAyuda = new Partida(1, "Juan", false, false);
	}

	@Test
	public void test() {
		assertEquals("The username should be the one given", "Juan" ,testPartidaAyuda.getUsuario());
		assertEquals("The difficulty should be the one given", 1, testPartidaAyuda.getDificultad(), 0);
		assertEquals("The ayuda should be true",  true, testPartidaAyuda.getAyuda());
		assertEquals("The ayuda should be false", false, testPartidaSinAyuda.getAyuda());
		assertEquals("The estado Partida should be running", testPartidaSinAyuda.getEstadoPartida(), PossiblesEstadosPartida.RUNNING);
	}

}
