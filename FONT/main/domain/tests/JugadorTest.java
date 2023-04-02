package main.domain.tests;

import static org.junit.Assert.*;
import main.domain.Jugador;
import org.junit.Test;

public class JugadorTest {
	
	/*
	 * Comprueba que se devuelva correctamente el username de un jugador
	 */
	@Test
    public void testGetUsername() {
        Jugador newJugador = new Jugador("MrRobot");
        assertEquals("MrRobot", newJugador.getUsername());
    }

}
