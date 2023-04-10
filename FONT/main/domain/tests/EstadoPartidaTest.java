package main.domain.tests;

import static org.junit.Assert.*;
import main.domain.EstadoPartida;
import main.domain.PossiblesEstadosPartida;
import org.junit.Test;


public class EstadoPartidaTest {
	
	/*
	 * Comprueba que se introduzcan correctamente los cambios de estado de la partida	
	 */
	@Test
    public void testSetEstado() {
		PossiblesEstadosPartida paused = PossiblesEstadosPartida.PAUSED;
		PossiblesEstadosPartida saved = PossiblesEstadosPartida.SAVED;
		PossiblesEstadosPartida running = PossiblesEstadosPartida.RUNNING;
        EstadoPartida newEstadoPartida = new EstadoPartida("running");
        newEstadoPartida.setEstado("paused");
        assertEquals("The estado Partida should be paused", paused, newEstadoPartida.getEstado());
        newEstadoPartida.setEstado("saved");
        assertEquals("The estado Partida should be saved", saved, newEstadoPartida.getEstado());
        newEstadoPartida.setEstado("running");
        assertEquals("The estado Partida should be running", running, newEstadoPartida.getEstado());
    }
	
	/*
	 * Comprueba que se obtengan correctamente los cambios de estado de la partida	
	 */
	@Test
    public void testGetEstado() {
		PossiblesEstadosPartida running = PossiblesEstadosPartida.RUNNING;
        EstadoPartida newEstadoPartida = new EstadoPartida("running");
        assertEquals("The estado Partida should be running", running, newEstadoPartida.getEstado());
    }
}
