package main.domain.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import main.domain.HistorialPartidasGuardadas;
import main.domain.Pair;

public class HistorialPartidasGuardadasTest {

	@Before
	public void setUp() {
        HistorialPartidasGuardadas historial = new HistorialPartidasGuardadas();

	}
	
	/*
	 * Comprueba que se aregue correctamente una partida
	 */
	@Test
	public void testAgregarPartidaGuardada() {
		String username = "Murakami";
		Date dataIni = new Date();
		HistorialPartidasGuardadas.agregarPartidaGuardada(username, dataIni);
		ArrayList<Pair<String,Date>> partidasUsuario = HistorialPartidasGuardadas.getPartidas(username);
		assertEquals("The size of Historial Partidas Guardadas should be 1", 1, partidasUsuario.size());
		assertEquals("The username for the added partida should be the given one", username, partidasUsuario.get(0).getFirst());
		assertEquals("The Date for the added partida should be the given one", dataIni, partidasUsuario.get(0).getSecond());
	}
	
	/*
	 * Comprueba que se borre correctamente una partida
	 */
	@Test
	public void testBorrarPartidaGuardada() {
		String username = "Murakami";
		Date dataIni = new Date();
		HistorialPartidasGuardadas.agregarPartidaGuardada(username, dataIni);
		HistorialPartidasGuardadas.borrarPartidaGuardada(username, dataIni);
		ArrayList<Pair<String,Date>> partidasUsuario = HistorialPartidasGuardadas.getPartidas(username);
		assertEquals("The size of Historial Partidas Guardadas should be 1", 0, partidasUsuario.size());
	}

	/*
	 * Comprueba que se haga el get partidas de un usuario correctamente
	 */
	@Test
	public void testGetPartidas() {
		Date fecha1 = new Date();
		Date fecha2 = new Date(fecha1.getTime() + 60000);
		HistorialPartidasGuardadas.agregarPartidaGuardada("ricky", fecha1);
		HistorialPartidasGuardadas.agregarPartidaGuardada("marcel", fecha2);
		ArrayList<Pair<String, Date>> partidas1 = HistorialPartidasGuardadas.getPartidas("ricky");
		assertEquals("The size should be 1", 1, partidas1.size());
		assertEquals("The username should be the given one, in this case ricky", "ricky", partidas1.get(0).getFirst());
		assertEquals("The Date should be the given one, in this case fecha1", fecha1, partidas1.get(0).getSecond());
		ArrayList<Pair<String, Date>> partidas2 = HistorialPartidasGuardadas.getPartidas("marcel");
		assertEquals("The size should be 1", 1, partidas2.size());
		assertEquals("The username should be the given one, in this case marcel", "marcel", partidas2.get(0).getFirst());
		assertEquals("The Date should be the given one, in this case fecha2", fecha2, partidas2.get(0).getSecond());
	}
}
