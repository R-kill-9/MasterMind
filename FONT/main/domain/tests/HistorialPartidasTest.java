package main.domain.tests;

import static org.junit.Assert.*;
import main.domain.HistorialPartidas;
import main.domain.Pair;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;

public class HistorialPartidasTest {
    
	/*
	 * Comprueba que se agregue correctamente una partida
	 */
    @Test
    public void testAgregarPartida() {
        HistorialPartidas historial = new HistorialPartidas();
        ArrayList<Pair<String,Date>> partidas = HistorialPartidas.getPartidas();
        assertEquals("The size of Historial Partidas should be 0", 0, partidas.size());
        
        Date fecha1 = new Date();
        HistorialPartidas.agregarPartida("Axel", fecha1);
        assertEquals("The size of Historial Partidas should be 1", 1, partidas.size());
        assertEquals("The username for the added partida should be the given one", "Axel", partidas.get(0).getFirst());
        assertEquals("The Date for the added partida should be the given one", fecha1, partidas.get(0).getSecond());
    }
    
    /*
     * Comprueba que se borre correctamente una partida
     */
    @Test
    public void testBorrarPartida() {
        HistorialPartidas historial = new HistorialPartidas();        
        Date fecha1 = new Date();
        HistorialPartidas.agregarPartida("Meliodas", fecha1);

        
        boolean deleted = HistorialPartidas.borrarPartida("Meliodas", fecha1);
        assertTrue("It must return true as the Partida inserted exists and it has been deleted", deleted);
        ArrayList<Pair<String,Date>> partidas = HistorialPartidas.getPartidas();
        assertEquals("It should be 0 because the added partida has been deleted", 0, partidas.size());
    }
    
    /*
     * Cpomprueba que el get de las partidas sea correcto
     */
    @Test
	public void testGetPartidas() {
		HistorialPartidas historial = new HistorialPartidas();
		Date fecha1 = new Date();
		Date fecha2 = new Date();
		
		HistorialPartidas.agregarPartida("Axel", fecha1);
		ArrayList<Pair<String,Date>> partidas = HistorialPartidas.getPartidas();
		assertEquals("The size of partidas should be 1", 1, partidas.size());
		assertEquals("The first partidas in partidasAxel should have username Axel", "Axel", partidas.get(0).getFirst());
		assertEquals("The first partidas in partidasAxel should have fecha1", fecha1, partidas.get(0).getSecond());
		
		HistorialPartidas.agregarPartida("Meliodas", fecha2);
		ArrayList<Pair<String,Date>> partidas2 = HistorialPartidas.getPartidas();
		assertEquals("The size of partidas2 should be 2", 2, partidas2.size());
		assertEquals("The second partidas2 in partidas should have username Meliodas", "Meliodas", partidas2.get(1).getFirst());
		assertEquals("The second partidas2 in partidas should have fecha2", fecha2, partidas2.get(1).getSecond());
	}
    
}