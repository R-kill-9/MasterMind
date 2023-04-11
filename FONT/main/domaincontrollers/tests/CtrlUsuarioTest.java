package main.domaincontrollers.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import main.domain.Color;
import main.domain.ColorFeedBack;
import main.domain.Pair;
import main.domaincontrollers.CtrlUsuario;
import main.domaincontrollers.CtrlPartida;
import main.domain.HistorialPartidas;
import main.domain.HistorialPartidasGuardadas;

public class CtrlUsuarioTest {
	/*
	 * Atributos
	 */
    private CtrlUsuario ctrlUsuario;

    @Before
    public void setUp() throws Exception {
        ctrlUsuario = new CtrlUsuario();
        ctrlUsuario.loginUser("Pikachu");
        HistorialPartidas historial = new HistorialPartidas();
        HistorialPartidasGuardadas historialGuardadas = new HistorialPartidasGuardadas();
    }

    /*
     * Comprueba que se añada bien un usuario a la lista de usuarios mediante la funcion de loginUser
     */
    @Test
    public void testAddUsuario()  throws Exception {
        String username = "Levi";
    	ctrlUsuario.loginUser(username);
        ArrayList<String> usuariosExistentes = ctrlUsuario.getUsuarios();
        Boolean exists = false;
		for (String user : usuariosExistentes) {
			if (user.equals(username)) {
				exists = true;
			}
		}
        assertTrue("The boolean value exists should be true if the user has been added", exists);
    }
    
    /*
     * Comprueba que no se añada un usuario a la lista de usuarios mediante la funcion de loginUser si este usuario ya existe
     */
    @Test
    public void testAddUsuarioExistente()  throws Exception {
        String username = "Levi";
    	ctrlUsuario.loginUser(username);
    	String username2 = "Levi";
    	ctrlUsuario.loginUser(username2);
        ArrayList<String> usuariosExistentes = ctrlUsuario.getUsuarios();
        assertEquals("usuariosExistentes size should be 2 because Levi was already added", 2, usuariosExistentes.size());
    }

    /*
     * Comprueba que el useraname asignado al usuario actual sea el correcto
     */
    @Test
    public void testLoginUser() throws Exception {
        ctrlUsuario.loginUser("Pikachu");
        assertTrue("The actual User username should be the given one", ctrlUsuario.getUsuarioActual().equals("Pikachu"));
        ctrlUsuario.loginUser("Ash");
        assertTrue("The actual User username should be the given one", ctrlUsuario.getUsuarioActual().equals("Ash"));
    }

    /*
     * Comprueba que se modifique correctamente el record de un usuario
     */
    @Test
    public void testSetRecord() {
    	CtrlUsuario.setRecord(1000);
    	CtrlUsuario.setRecord(2000);
    	CtrlUsuario.setRecord(3000);
    	CtrlUsuario.setRecord(4000);
    	CtrlUsuario.setRecord(5000);
        int[] scores = CtrlUsuario.getRecord();
        assertEquals("It must return the first maxScore for the user, in this case 5000", 5000, scores[0]);
        assertEquals("It must return the second maxScore for the user, in this case 4000", 4000, scores[1]);
        assertEquals("It must return the third maxScore for the user, in this case 3000", 3000, scores[2]);
        assertEquals("It must return the fourth maxScore for the user, in this case 2000", 2000, scores[3]);
        assertEquals("It must return the fifth maxScore for the user, in this case 1000", 1000, scores[4]);
        CtrlUsuario.setRecord(22);
        CtrlUsuario.setRecord(6000);
        CtrlUsuario.setRecord(2500);
        int[] newScores = CtrlUsuario.getRecord();
        assertEquals("It must return the first maxScore for the user, in this case 5000", 6000, newScores[0]);
        assertEquals("It must return the second maxScore for the user, in this case 5000", 5000, newScores[1]);
        assertEquals("It must return the third maxScore for the user, in this case 5000", 4000, newScores[2]);
        assertEquals("It must return the fourth maxScore for the user, in this case 5000", 3000, newScores[3]);
        assertEquals("It must return the fifth maxScore for the user, in this case 5000", 2500, newScores[4]);
    }

    /*
     * Comprueba que se añada la fecha de creacion de una partida a la lista de partidas del usuario
     */
    @Test
    public void testCrearPartidaUsuario() {
    	CtrlUsuario.crearPartida(1, false, true);
		assertEquals("The size of partidas for the user should be one", 1, CtrlUsuario.getDataPartidasUsuario().size());
    }
    
    
    /*
     * Comprueba que se añada la partida al historial de partidas
     */
    @Test
    public void testCrearPartidaHistorial() {
    	CtrlUsuario.crearPartida(1, false, false);
    	Date dataPartida = CtrlUsuario.getDataPartidasUsuario().get(0);
    	Pair<String, Date> expectedPartida = new Pair<>(ctrlUsuario.getUsuarioActual(), dataPartida);
    	Pair<String, Date> historialPartida = CtrlUsuario.getPartidasHistorial().get(0);
    	
        assertEquals("The username for the created Partida should be the given one", expectedPartida.getFirst(), historialPartida.getFirst());
        assertEquals("The Date for the created Partida should be the given one", expectedPartida.getSecond(), historialPartida.getSecond());
        
        CtrlUsuario.crearPartida(1, false, false);
    	Date dataPartida2 = CtrlUsuario.getDataPartidasUsuario().get(1);
    	Pair<String, Date> expectedPartida2 = new Pair<>(ctrlUsuario.getUsuarioActual(), dataPartida2);
    	Pair<String, Date> historialPartida2 = CtrlUsuario.getPartidasHistorial().get(1);
    	
        assertEquals("The username for the created Partida should be the given one", expectedPartida2.getFirst(), historialPartida2.getFirst());
        assertEquals("The Date for the created Partida should be the given one", expectedPartida2.getSecond(), historialPartida2.getSecond());

    }
    
    /*
     * Comprueba que se añada la partida al historial de partidas guardadas una vex la partida ha finalizado
     */
    @Test
    public void testCrearPartidaHistorialGuardadas() throws Exception {
    	CtrlUsuario.crearPartida(1, false, false);
    	Date dataPartida = CtrlUsuario.getDataPartidasUsuario().get(0);
    	Pair<String, Date> expectedPartida = new Pair<>(ctrlUsuario.getUsuarioActual(), dataPartida);
    	ArrayList<Color> combination = new ArrayList<Color>();
		combination.add(Color.BLUE);
		combination.add(Color.RED);
		combination.add(Color.GREEN);
		combination.add(Color.YELLOW);
		int i = 0;
    	while (i < 10) {
    		ArrayList<ColorFeedBack> feedback = CtrlUsuario.newCombinacion(combination);
    		++i;
    	}
    	CtrlUsuario.setSolution(combination);
    	String expectedUsername = CtrlUsuario.getPartidasGuardadas().get(0).getFirst();
    	Date expectedDate = CtrlUsuario.getPartidasGuardadas().get(0).getSecond();
    	assertEquals("The saved username should be the same as the logged user, in this case Pikachu", expectedPartida.getFirst(), expectedUsername);
    	assertEquals("The saved Date should be the same as the logged one assigned to the created Partida", expectedPartida.getSecond(), expectedDate);
    }

    /*
     * Comprueba que se borre la fecha de creacion de una partida de la lista de partidas del usuario.
     */
    @Test
    public void testBorrarPartidaUsuario() throws Exception {
        CtrlUsuario.crearPartida(1, false, false);
        CtrlUsuario.crearPartida(1, true, false);
        
        ArrayList<Date> newPartidas = CtrlUsuario.getDataPartidasUsuario();
        assertEquals("The size of Partidas for the User should be 2", 2, newPartidas.size());
        ctrlUsuario.borrarPartida(newPartidas.get(1));
        ArrayList<Date> newPartidas2 = CtrlUsuario.getDataPartidasUsuario();
        assertEquals("The size of Partidas for the User should be 1", 1, newPartidas.size());
        ctrlUsuario.borrarPartida(newPartidas.get(0));
        ArrayList<Date> newPartidas3 = CtrlUsuario.getDataPartidasUsuario();
        assertEquals("The size of Partidas for the User should be 0", 0, newPartidas.size());
        
    }
    
    
    /*
     * Comprueba que se le añadala misma fecha a una partida en el array de partidas del usuario y en el historial de partidas
     */
    @Test
    public void testCorrectDate() throws Exception {
        CtrlUsuario.crearPartida(1, true, false);
        ArrayList<Date> newPartidas = CtrlUsuario.getDataPartidasUsuario();
        assertEquals("The Date for the user and HistorialPartida should match", newPartidas.get(0), CtrlUsuario.getPartidasHistorial().get(0).getSecond());
    }
    
    
    /*
     * Comprueba que se borre la fecha de creacion de una partida del historial de partidas 
     */
    @Test
    public void testBorrarPartidaHistorial() throws Exception {
        CtrlUsuario.crearPartida(1, false, false);
        CtrlUsuario.crearPartida(1, true, false);
        System.out.println(HistorialPartidas.getPartidas());
        ArrayList<Date> newPartidas = CtrlUsuario.getDataPartidasUsuario();
        ctrlUsuario.borrarPartida(newPartidas.get(0));
        
        assertEquals(1, CtrlUsuario.getPartidasHistorial().size());
    }

/*
    /*
     * Comprueba que se pueda salir de una partida
     */
    @Test
    public void testSalirPartida() {
        CtrlUsuario.crearPartida(1, false, false);
        assertTrue("There should be an actual Partida", CtrlUsuario.existsPartidaActual());
        CtrlUsuario.salirPartida();
        assertFalse("There shouldn't be an actual Partida", CtrlUsuario.existsPartidaActual());
    }

   
}
