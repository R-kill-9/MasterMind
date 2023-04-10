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
import main.domain.Combinacion;
import main.domain.HistorialPartidas;
import main.domain.HistorialPartidasGuardadas;
import main.domain.Pair;
import main.domain.Partida;
import main.domain.PossiblesEstadosPartida;
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
     * Comprueba que se cree una partida y se asigne como partida actual, haciendo correctamente su get
     */
    @Test
    public void crearPartidaTest() {
        Partida expectedPartida = ctrlPartida.getPartidaActual();
        assertNotNull("The value for expectedPartida shouldn't be null, since it has been created", expectedPartida);
        assertEquals("The actual Partida should be the created one", partida, expectedPartida);
        assertEquals("The username should be the given one", partida.getUsuario(), expectedPartida.getUsuario());
        assertEquals("The ayuda should be the given one", true, expectedPartida.getAyuda());
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
    	    	
        assertEquals("The username for Partida in historiaPartida should be the same as in Usuario", expectedPartida.getFirst(), historialPartida.get(0).getFirst());
        assertEquals("The Date for Partida in historiaPartida should be the same as in Usuario", expectedPartida.getSecond(), historialPartida.get(0).getSecond());
    }

    /*
     * Comprueba que se borre correctamente una partida del historil de partidas
     */
    @Test
	public void testBorrarPartidaHistorial() {
		Partida newPartida = CtrlPartida.crearPartida(1, "kill-9", true, true);
		Date fecha = newPartida.getData();
		assertEquals("It must return true when deletes a Partida", true, CtrlPartida.borrarPartida("kill-9", fecha));
	}

    /*
     * Comprueba que si se ha creado una partida exista partida actual y sea la correcta
     */
    @Test
	public void testExistsPartidaActual() {
		Partida newPartida = CtrlPartida.crearPartida(1, "kill-9", true, true);
		assertTrue("It must return true because there is an actual Partida", CtrlPartida.existsPartidaActual());
		assertEquals("The username of the actual Partida user should be the given one", "kill-9", ctrlPartida.getPartidaActual().getUsuario());
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
    	
    	assertEquals("The username for the first expected Partida should be the given one", expectedPartidas.get(0).getFirst(), ctrlPartida.getPartidas().get(0).getFirst());
    	assertEquals("The Date for the first expected Partida should be the given one", expectedPartidas.get(0).getSecond(), ctrlPartida.getPartidas().get(0).getSecond());
    	
    	assertEquals("The username for the second expected Partida should be the given one", expectedPartidas.get(1).getFirst(), ctrlPartida.getPartidas().get(1).getFirst());
    	assertEquals("The Date for the second expected Partida should be the given one", expectedPartidas.get(1).getSecond(), ctrlPartida.getPartidas().get(1).getSecond());
    	
    	assertEquals("The username for the third expected Partida should be the given one", expectedPartidas.get(2).getFirst(), ctrlPartida.getPartidas().get(2).getFirst());
    	assertEquals("The Date for the third expected Partida should be the given one", expectedPartidas.get(2).getSecond(), ctrlPartida.getPartidas().get(2).getSecond());
	}
    
    /*
     * Comprueba que se pueda salir de una partida actual y entrar a una nueva 
     */
    @Test
	public void testSalirPartida() {
    	CtrlPartida.salirPartida();
    	assertNull("Actual Partida should be null as we have exited from Partida", ctrlPartida.getPartidaActual());
    	Partida newPartida = ctrlPartida.crearPartida(1, "Condor", false, false);
    	assertNotNull("Actual Partida shouldn't be null as we have created a Partida", ctrlPartida.getPartidaActual());
    	CtrlPartida.salirPartida();
    	assertNull("Actual Partida should be null as we have exited from Partida", ctrlPartida.getPartidaActual());
	}
    
    /*
     * Comprueba que se pueda solicitar ayuda para la partida i cambiar su valor solo si la ayuda no estaba activada. También se comprueba 
     * que funncione correctamente el metodo getter de ayuda
     */
    @Test
	public void testSolicitarGetAyuda() {
    	CtrlPartida.solicitarAyuda();
		assertTrue("Ayuda value should be true as we have activated it", partida.getAyuda());
		partida = CtrlPartida.crearPartida(1, "Condor", false, false);
		assertFalse("Ayuda value should be false as we have created a new Partida with the value in false", partida.getAyuda());
		CtrlPartida.solicitarAyuda();
		assertTrue("Ayuda value should be true as we have activated it", partida.getAyuda());
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
        assertEquals("The username for the first Parida in historialPartida should be the expected one", expectedPartidas.get(0).getFirst(), historialPartidas.get(0).getFirst());
        assertEquals("The Date for the first Parida in historialPartida should be the expected one", expectedPartidas.get(0).getSecond(), historialPartidas.get(0).getSecond());
        assertEquals("The username for the second Parida in historialPartida should be the expected one", expectedPartidas.get(1).getFirst(), historialPartidas.get(1).getFirst());
        assertEquals("The Date for the second Parida in historialPartida should be the expected one", expectedPartidas.get(1).getSecond(), historialPartidas.get(1).getSecond());

    }
    
    @Test
	public void testNewCombinacion() throws Exception{
    	//creamos una nueva en la que iniciamos como codeBreaker
    	Partida newPartida = CtrlPartida.crearPartida(1, "ricky", false, false);
		
		//creamos las combinaciones que queremos insertar
		ArrayList<Color> combination = new ArrayList<Color>();
		combination.add(Color.BLUE);
		combination.add(Color.RED);
		combination.add(Color.GREEN);
		combination.add(Color.YELLOW);

		ArrayList<ColorFeedBack> feedbacks = CtrlPartida.newCombinacion(combination);
		assertEquals("Feedback size should be 4", 4, feedbacks.size());
	}
    
    /*
     * Comprueba que se añada correctamente un código solucion.
     */
    @Test
	public void testNewSolution() throws Exception{
    	//creamos una nueva en la que iniciamos como codeMaker
    	Partida newPartida = CtrlPartida.crearPartida(1, "ricky", true, true);
		
		//creamos la combinacion que queremos insertar
		ArrayList<Color> combination = new ArrayList<Color>();
		combination.add(Color.BLUE);
		combination.add(Color.RED);
		combination.add(Color.GREEN);
		combination.add(Color.YELLOW);
		Combinacion expectedSolution = new Combinacion(combination);
		CtrlPartida.setSolution(combination);
		assertEquals("The first position of solution should be the expected one", expectedSolution.getPosition(0), newPartida.getSolutionTorn(0).getPosition(0));
		assertEquals("The second position of solution should be the expected one", expectedSolution.getPosition(1), newPartida.getSolutionTorn(0).getPosition(1));
		assertEquals("The third position of solution should be the expected one", expectedSolution.getPosition(2), newPartida.getSolutionTorn(0).getPosition(2));
		assertEquals("The fourth position of solution should be the expected one", expectedSolution.getPosition(3), newPartida.getSolutionTorn(0).getPosition(3));
	}
    
    /*
     * Comprueba que se obtenga correctamente la informacion de la partida (Fecha y dificultad)
     */
    @Test
   	public void testGetInfoPartida() {
	    Pair<Date, Integer> info = CtrlPartida.getInfoPartida(partida);
	    assertEquals("The Date for the Partida should be the expected one", ctrlPartida.getPartidaActual().getData(), info.getFirst());
	    assertEquals("The difficulty for the Partida should be the expected one", (Integer)1, info.getSecond());
    
    }
	

    
}
   
