package main.domain.tests;

import static org.junit.Assert.*;
import main.domain.EstadoPartida;
import org.junit.Test;

public class EstadoPartidaTest {
	
	/*
	 * Comprueba que se introduzcan correctamente los cambios de estado de la partida	
	 */
	@Test
    public void testSetEstado() {
        EstadoPartida newEstadoPartida = new EstadoPartida("Running");
        newEstadoPartida.setEstado("Paused");
        assertEquals("Paused", newEstadoPartida.getEstado());
        newEstadoPartida.setEstado("Saved");
        assertEquals("Saved", newEstadoPartida.getEstado());
        newEstadoPartida.setEstado("Running");
        assertEquals("Running", newEstadoPartida.getEstado());
    }
	
	/*
	 * Comprueba que se obtengan correctamente los cambios de estado de la partida	
	 */
	@Test
    public void testGetEstado() {
        EstadoPartida newEstadoPartida = new EstadoPartida("Running");
        assertEquals("Running", newEstadoPartida.getEstado());
    }
}
