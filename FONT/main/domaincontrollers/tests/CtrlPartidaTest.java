package main.domaincontrollers.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import main.domaincontrollers.CtrlPartida;
import main.domaincontrollers.CtrlUsuario;
import main.domain.Color;
import main.domain.ColorFeedBack;
import main.domain.HistorialPartidas;
import main.domain.HistorialPartidasGuardadas;
import main.domain.Pair;
import main.domain.Partida;
import main.domain.Usuario;

public class CtrlPartidaTest {

    private CtrlPartida ctrlPartida;
    private Partida partida;
    
    @Before
    public void setUp() throws Exception {
        ctrlPartida = new CtrlPartida();
        HistorialPartidas historial = new HistorialPartidas();
        HistorialPartidasGuardadas historialGuardadas = new HistorialPartidasGuardadas();
        partida = CtrlPartida.crearPartida(1, "ricky", true, true);
    }

    /*
     * Comprueba que se cree una partida y se asigne como partida actual
     */
    @Test
    public void crearPartidaTest() {
        Partida expectedPartida = ctrlPartida.getPartidaActual();
        assertNotNull(expectedPartida);
        assertEquals(partida, expectedPartida);
        assertEquals(partida.getUsuario(), expectedPartida.getUsuario());
        assertEquals(true, expectedPartida.getAyuda());
        
        Partida newPartida = CtrlPartida.crearPartida(1, "kill-9", true, true);
        int tamaño = ctrlPartida.getPartidas().size();
		assertEquals(2, tamaño);
        }
    
    /*
     * Comprueba que se añada la partida al historial de partidas
     */
    @Test
    	public void crearPartidaHistorialTest() {
    	Partida partidaActual = ctrlPartida.getPartidaActual();
    	Date fecha = partidaActual.getData();
    	Pair<String, Date> expectedPartida = new Pair<String, Date>("ricky", fecha);
    	ArrayList<Pair<String, Date>> historialPartida = ctrlPartida.getPartidas();
    	    	
        assertEquals(expectedPartida.getFirst(), historialPartida.get(0).getFirst());
        assertEquals(expectedPartida.getSecond(), historialPartida.get(0).getSecond());
    }

    /*
     * Comprueba que se obtenga correctamente cual es el valor de la partida actual
     */
    @Test
    public void getPartidaActualTest() throws Exception {
        assertNotNull(ctrlPartida.getPartidaActual());
        partida = CtrlPartida.crearPartida(1, "ricky2", true, true);
        assertNotNull(ctrlPartida.getPartidaActual());
        assertEquals(ctrlPartida.getPartidaActual(), partida);
    }

    /*
     * Comprueba que se borre correctamente una partida del historil de partidas
     */
    @Test
	public void testBorrarPartidaHistorial() {
		Partida newPartida = CtrlPartida.crearPartida(1, "kill-9", true, true);
		Date fecha = newPartida.getData();
		assertEquals(true, CtrlPartida.borrarPartida("kill-9", fecha));
		assertEquals(false, CtrlPartida.borrarPartida("kill-9", fecha));
	}

    /*
     * Comprueba que si se ha creado una partida exista partida actual y sea la correcta
     */
    @Test
	public void testExistsPartidaActual() {
        assertTrue(CtrlPartida.existsPartidaActual());
		Partida newPartida = CtrlPartida.crearPartida(1, "kill-9", true, true);
		assertTrue(CtrlPartida.existsPartidaActual());
		assertEquals("kill-9", ctrlPartida.getPartidaActual().getUsuario());
	}
    
    /*
     * Comprueba que se introduzcan y obtengan correctamente las partidas en el historial de partidas
     */
    @Test
	public void testGetPartidas() {
    	Partida newPartida2 = CtrlPartida.crearPartida(1, "ricky2", true, true);
    	Partida newPartida3 = CtrlPartida.crearPartida(1, "ricky3", true, true);
    	
    	Date fecha1 = partida.getData();
    	Date fecha2 = newPartida2.getData();
    	Date fecha3 = newPartida3.getData();
    	Pair<String, Date> expectedPartida1 = new Pair<String, Date>("ricky", fecha1);
    	Pair<String, Date> expectedPartida2 = new Pair<String, Date>("ricky2", fecha2);
    	Pair<String, Date> expectedPartida3 = new Pair<String, Date>("ricky3", fecha3);
    	ArrayList<Pair<String, Date>> expectedPartidas = new ArrayList<Pair<String, Date>>();
    	expectedPartidas.add(expectedPartida1);
    	expectedPartidas.add(expectedPartida2);
    	expectedPartidas.add(expectedPartida3);
    	
    	assertEquals(expectedPartidas.get(0).getFirst(), ctrlPartida.getPartidas().get(0).getFirst());
    	assertEquals(expectedPartidas.get(0).getSecond(), ctrlPartida.getPartidas().get(0).getSecond());
    	
    	assertEquals(expectedPartidas.get(1).getFirst(), ctrlPartida.getPartidas().get(1).getFirst());
    	assertEquals(expectedPartidas.get(1).getSecond(), ctrlPartida.getPartidas().get(1).getSecond());
    	
    	assertEquals(expectedPartidas.get(2).getFirst(), ctrlPartida.getPartidas().get(2).getFirst());
    	assertEquals(expectedPartidas.get(2).getSecond(), ctrlPartida.getPartidas().get(2).getSecond());
	}
    
    @Test
	public void testNewCombinacion() {
    	//creamos una nueva en la que iniciamos como codeBreaker
    	Partida newPartida = CtrlPartida.crearPartida(1, "ricky", false, false);
		ArrayList<Color> combination = new ArrayList<Color>();
		combination.add(Color.BLUE);
		combination.add(Color.RED);
		combination.add(Color.GREEN);
		combination.add(Color.YELLOW);
		ArrayList<ColorFeedBack> feedbacks = CtrlPartida.newCombinacion(combination);
		assertNotNull(feedbacks);
		assertEquals(ctrlPartida.getPartidaActual().getNumIntentos(), feedbacks.size());
		ArrayList<ColorFeedBack> feedbacks2 = CtrlPartida.newCombinacion(combination);
		assertEquals(2, feedbacks2.size());
	}
    
    @Test
	public void testSetSolution() throws Exception {
		Partida partida = CtrlPartida.crearPartida(1, "Condor", true, true);
		assertEquals(false, ctrlPartida.getPartidaActual().existsSolution());
		ArrayList<Color> solution = new ArrayList<Color>();
		solution.add(Color.BLUE);
		solution.add(Color.RED);
		solution.add(Color.GREEN);
		solution.add(Color.YELLOW);
		CtrlPartida.setSolution(solution);
		assertEquals(true, ctrlPartida.getPartidaActual().existsSolution());
	}
 
    /*
     * Comprueba que se pueda salir de una partida actual y entrar a una nueva 
     */
    @Test
	public void testSalirPartida() {
    	CtrlPartida.salirPartida();
    	assertNull(ctrlPartida.getPartidaActual());
    	Partida newPartida = ctrlPartida.crearPartida(1, "Condor", false, false);
    	assertNotNull(ctrlPartida.getPartidaActual());
    	CtrlPartida.salirPartida();
    	assertNull(ctrlPartida.getPartidaActual());
	}
    
    /*
     * Comprueba que se pueda solicitar ayuda para la partida i cambiar su valor solo si la ayuda no estaba activada. También se comprueba 
     * que funncione correctamente el metodo getter de ayuda
     */
    @Test
	public void testSolicitarAyuda() {
    	CtrlPartida.solicitarAyuda();
		assertTrue(partida.getAyuda());
		CtrlPartida.solicitarAyuda();
		partida = CtrlPartida.crearPartida(1, "Condor", false, false);
		assertFalse(partida.getAyuda());
		ctrlPartida.solicitarAyuda();
		assertTrue(partida.getAyuda());
    }
    
    @Test
	public void testReiniciarPartida() {
 
    }
    
    /*
     * Comprueba que se obtenga correctamente la informacion de la partida (Fecha y dificultad)
     */
    @Test
   	public void testGetInfoPartida() {
    Pair<Date, Integer> info = CtrlPartida.getInfoPartida(partida);
    assertEquals(ctrlPartida.getPartidaActual().getData(), info.getFirst());
    assertEquals((Integer)1, info.getSecond());
    
    //De momento nivel dificultad solo puede ser 1 debido a que es el único implementado
    Partida partida2 = CtrlPartida.crearPartida(2, "ricky", false, false);
    Pair<Date, Integer> info2 = CtrlPartida.getInfoPartida(partida2);
    assertEquals(ctrlPartida.getPartidaActual().getData(), info2.getFirst());
    assertEquals((Integer)1, info2.getSecond());
    
    }
    
    /*
     * Comprueba que se obtenga correctamente la informacion del historial de partidas
     */
    @Test
   	public void testGetPartidasHistorial() {
    	Partida partida2 = CtrlPartida.crearPartida(1, "joel", false, false);
        
    	ArrayList<Pair<String, Date>> expectedPartidas = new ArrayList<Pair<String,Date>>();
    	Pair<String, Date> pair1 = new Pair<String,Date>("ricky", partida.getData());
    	Pair<String, Date> pair2 = new Pair<String,Date>("joel", partida2.getData());

        expectedPartidas.add(pair1);
        expectedPartidas.add(pair2);
        
        ArrayList<Pair<String, Date>> historialPartidas = CtrlPartida.getPartidasHistorial();
        assertEquals(expectedPartidas.get(0).getFirst(), historialPartidas.get(0).getFirst());
        assertEquals(expectedPartidas.get(0).getSecond(), historialPartidas.get(0).getSecond());
        assertEquals(expectedPartidas.get(1).getFirst(), historialPartidas.get(1).getFirst());
        assertEquals(expectedPartidas.get(1).getSecond(), historialPartidas.get(1).getSecond());

    }
    
    
}
   
