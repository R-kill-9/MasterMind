package main.domain.tests;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import main.domain.Partida;
import main.domain.Usuario;


public class UsuarioTest {
	
	/*Atributos*/
	private Usuario user;
    private Partida partida1, partida2;
    
    /*
     * Método que se aplica siempre antes de hacer un test
     */
    @Before
    public void setUp() {
        user = new Usuario("Sasuke");
        partida1 = new Partida(1, user.getUsername(), false, false);
        partida2 = new Partida(2, user.getUsername(), true, true);
        user.addPartida(partida1.getData());
        user.addPartida(partida2.getData());
    }
	
    /*
     * TESTS
     */
    
    /*
     * Comprueba que se introduzca bien el nombre del usuario al crearlo
     */
	@Test
	public void testSetUsername() {
		Usuario usuario = new Usuario("Alonso");
		assertEquals("Alonso", usuario.getUsername());
	}
	
	/*
	 * Comprueba que se devuelva bien el username del usuario
	 */
	@Test
    public void testGetUsername() {
        assertEquals("Sasuke", user.getUsername());
    }
	

	/*
	 * Comprueba que la introducción de un nuevo maxScore funciona
	 */
	@Test
    public void testSetMaxScore() {
        user.setMaxScore(1000);
        assertEquals(1000, user.getMaxScore());
        user.setMaxScore(22);
        assertEquals(1000, user.getMaxScore());
    }
	
	/*
	 * Comprueba que el get de MaxScore sea correcto
	 */
	@Test
    public void testGetMaxScore() {
        assertEquals(0, user.getMaxScore());
        user.setMaxScore(89);
        assertEquals(89, user.getMaxScore());
    }
	
	/*
	 * Comprueba que se añaden las fechas de las partidas correctamente al ArrayList donde se guardan
	 */
	@Test
    public void testAddPartida() {
		Partida partida3 = new Partida(1, user.getUsername(), true, false);
		Date dataPartida3 = partida3.getData();
        user.addPartida(dataPartida3);
        ArrayList<Date> totalPartidas = user.getDataPartidasGuardadas();
        assertEquals("Tiene que devolver la fecha de la última partida añadida", dataPartida3, totalPartidas.get(totalPartidas.size()-1));
        
    }
	
	/*
	 * Comprueba que las partidas se borren correctamente
	 */
	@Test
    public void testDeletePartida() {
		int totalPartidas = user.getDataPartidasGuardadas().size();
		--totalPartidas;
		user.deletePartida(partida2.getData());
		int newPartidas = user.getDataPartidasGuardadas().size();
        assertEquals("Tiene que devolver el tamaño de un ArrayList suprimiendo la Data que se ha borrado", totalPartidas, newPartidas);
        
    }
	
	/*
	 * Comprueba que el tamaño de partidas guardadas del usuario sea correcto y que devuelva las partidas que debe
	 */
	@Test
    public void testGetDataPartidasGuardadas() {
        ArrayList<Date> partidasGuardadas = user.getDataPartidasGuardadas();
        assertTrue("Tiene que devolver un valor igual a la cantidad de partidas guardadas, en este caso 2", partidasGuardadas.size() == 2);
        assertTrue("Tiene que devolver la fecha de la última fecha, en estes caso la de partida2", partida2.getData() == partidasGuardadas.get(partidasGuardadas.size()-1));
        assertTrue("Tiene que devolver la fecha de la penúltima fecha, en estes caso la de partida1", partida1.getData() == partidasGuardadas.get(partidasGuardadas.size()-2));
    }
}
