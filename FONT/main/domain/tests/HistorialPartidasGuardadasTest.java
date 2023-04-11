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
	
	
	@Test
	public void testBorrarPartidaGuardada() {
		String username = "Murakami";
		Date dataIni = new Date();
		HistorialPartidasGuardadas.agregarPartidaGuardada(username, dataIni);
		HistorialPartidasGuardadas.borrarPartidaGuardada(username, dataIni);
		ArrayList<Pair<String,Date>> partidasUsuario = HistorialPartidasGuardadas.getPartidas(username);
		assertEquals("The size of Historial Partidas Guardadas should be 1", 0, partidasUsuario.size());
	}

}
