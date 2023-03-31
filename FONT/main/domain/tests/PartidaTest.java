package main.domain.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import main.domain.Partida;

public class PartidaTest {

	@Test
	public void test() {
		Partida partida = new Partida(0, "Juan", false, false);
		assertEquals(partida.getUsuario(), "Juan");
	}

}
