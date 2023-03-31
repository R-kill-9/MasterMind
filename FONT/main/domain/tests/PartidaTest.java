package main.domain.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.domain.Partida;

class PartidaTest {

	@Test
	void test() {
		Partida partida = new Partida(0, null, false, false);
		assertEquals(partida.getUsuario(), null);
	}

}
