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
        assertTrue(exists);
    }

    /*
     * Comprueba que el useraname asignado al usuario actual sea correcto
     */
    @Test
    public void testLoginUser() throws Exception {
        ctrlUsuario.loginUser("Pikachu");
        assertTrue(ctrlUsuario.getUsuarioActual().equals("Pikachu"));
        ctrlUsuario.loginUser("Ash");
        assertTrue(ctrlUsuario.getUsuarioActual().equals("Ash"));
    }

    /*
     * Comprueba que se modifique correctamente el record de un usuario
     */
    @Test
    public void testSetRecord() {
        ctrlUsuario.setRecord(0);
        assertTrue(ctrlUsuario.getRecord() == 0);
        ctrlUsuario.setRecord(1000);
        assertTrue(ctrlUsuario.getRecord() == 1000);
        ctrlUsuario.setRecord(20);
        assertTrue(ctrlUsuario.getRecord() == 1000);
    }

    /*
     * Comprueba que se añada la fecha de creacion de una partida a la lista de partidas del usuario
     */
    @Test
    public void testCrearPartidaUsuario() {
    	CtrlUsuario.crearPartida(1, false, true);
		ArrayList<Date> dataPartidas = CtrlUsuario.getDataPartidasUsuario();
		assertEquals(1, CtrlUsuario.getDataPartidasUsuario().size());
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
    	
        assertEquals(expectedPartida.getFirst(), historialPartida.getFirst());
        assertEquals(expectedPartida.getSecond(), historialPartida.getSecond());
        
        CtrlUsuario.crearPartida(1, false, false);
    	Date dataPartida2 = CtrlUsuario.getDataPartidasUsuario().get(1);
    	Pair<String, Date> expectedPartida2 = new Pair<>(ctrlUsuario.getUsuarioActual(), dataPartida2);
    	Pair<String, Date> historialPartida2 = CtrlUsuario.getPartidasHistorial().get(1);
    	
        assertEquals(expectedPartida2.getFirst(), historialPartida2.getFirst());
        assertEquals(expectedPartida2.getSecond(), historialPartida2.getSecond());
    }

    /*
     * Comprueba que se borre la fecha de creacion de una partida de la lista de partidas del usuario.
     */
    @Test
    public void testBorrarPartidaUsuario() throws Exception {
        CtrlUsuario.crearPartida(1, false, false);
        CtrlUsuario.crearPartida(1, true, false);
        
        ArrayList<Date> newPartidas = CtrlUsuario.getDataPartidasUsuario();
        assertEquals(2, newPartidas.size());
        ctrlUsuario.borrarPartida(newPartidas.get(1));
        ArrayList<Date> newPartidas2 = CtrlUsuario.getDataPartidasUsuario();
        assertEquals(1, newPartidas.size());
        ctrlUsuario.borrarPartida(newPartidas.get(0));
        ArrayList<Date> newPartidas3 = CtrlUsuario.getDataPartidasUsuario();
        assertEquals(0, newPartidas.size());
        
    }
    
    
    /*
     * Comprueba que se le añadala misma fecha a una partida en el array de partidas del usuario y en el historial de partidas
     */
    @Test
    public void testCorrectDate() throws Exception {
        CtrlUsuario.crearPartida(1, true, false);
        ArrayList<Date> newPartidas = CtrlUsuario.getDataPartidasUsuario();
        assertEquals(newPartidas.get(0), CtrlUsuario.getPartidasHistorial().get(0).getSecond());
    }
    
    
    /*
     * Comprueba que se borre la fecha de creacion de una partida del historial de partidas 
     */
    @Test
    public void testBorrarPartidaHistorial() throws Exception {
        CtrlUsuario.crearPartida(1, false, false);
        CtrlUsuario.crearPartida(1, true, false);
        ArrayList<Date> newPartidas = CtrlUsuario.getDataPartidasUsuario();
        ctrlUsuario.borrarPartida(newPartidas.get(0));
        
        assertEquals(1, CtrlUsuario.getPartidasHistorial().size());
    }


    /*
     * Comprueba que se pueda salir de una partida
     */
    @Test
    public void testSalirPartida() {
        CtrlUsuario.crearPartida(1, false, false);
        assertTrue(CtrlUsuario.existsPartidaActual());
        CtrlUsuario.salirPartida();
        assertFalse(CtrlUsuario.existsPartidaActual());
    }

    @Test
    public void testReiniciarPartida() {
        CtrlUsuario.crearPartida(1, false, false);
        ArrayList<Color> comb = new ArrayList<Color>();
		comb.add(Color.BLUE);
		comb.add(Color.RED);
		comb.add(Color.GREEN);
		comb.add(Color.YELLOW);
        ctrlUsuario.newCombinacion(comb);

        CtrlUsuario.reiniciarPartida();
        assertTrue(ctrlPartida.getPartidaActual().getCombinacion().isEmpty());
    }
    
    
    
    /*
     * Comprueba que se introduzca bien una nueva solucion
     */
    @Test
    public void testsSetCombinacion() throws Exception{
    	CtrlUsuario.crearPartida(1, false, false);
    }
}
