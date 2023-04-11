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
     * Comprueba que se introduzca bien el nombre del usuario al crearlo y se haga correctamente el get.
     */
	@Test
	public void testSetUsername() {
		Usuario usuario = new Usuario("Alonso");
		assertEquals("It must return the given username", "Alonso", usuario.getUsername());
	}
	
	
	/*
	 * Comprueba que la introducción de un nuevo maxScore funciona y el get se hace correctamente
	 */
	@Test
    public void testSetMaxScore() {
        user.setMaxScore(1000);
        user.setMaxScore(2000);
        user.setMaxScore(3000);
        user.setMaxScore(4000);
        user.setMaxScore(5000);
        int[] scores = user.getMaxScore();
        System.out.println(scores[0]);
        System.out.println(scores[1]);
        System.out.println(scores[2]);
        System.out.println(scores[3]);
        System.out.println(scores[4]);
        assertEquals("It must return the first maxScore for the user, in this case 5000", 5000, scores[0]);
        assertEquals("It must return the second maxScore for the user, in this case 4000", 4000, scores[1]);
        assertEquals("It must return the third maxScore for the user, in this case 3000", 3000, scores[2]);
        assertEquals("It must return the fourth maxScore for the user, in this case 2000", 2000, scores[3]);
        assertEquals("It must return the fifth maxScore for the user, in this case 1000", 1000, scores[4]);
        user.setMaxScore(22);
        user.setMaxScore(6000);
        user.setMaxScore(2500);
        int[] newScores = user.getMaxScore();
        assertEquals("It must return the first maxScore for the user, in this case 5000", 6000, newScores[0]);
        assertEquals("It must return the second maxScore for the user, in this case 5000", 5000, newScores[1]);
        assertEquals("It must return the third maxScore for the user, in this case 5000", 4000, newScores[2]);
        assertEquals("It must return the fourth maxScore for the user, in this case 5000", 3000, newScores[3]);
        assertEquals("It must return the fifth maxScore for the user, in this case 5000", 2500, newScores[4]);
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
        assertEquals("It must return the date of the last added game.", dataPartida3, totalPartidas.get(totalPartidas.size()-1));
        
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
        assertEquals("It must return the size of an ArrayList by removing the deleted data.", totalPartidas, newPartidas);
        
    }
	
	/*
	 * Comprueba que el tamaño de partidas guardadas del usuario sea correcto y que devuelva las partidas que debe
	 */
	@Test
    public void testGetDataPartidasGuardadas() {
        ArrayList<Date> partidasGuardadas = user.getDataPartidasGuardadas();
        assertTrue("It must return a value equal to the number of saved games, in this case 2", partidasGuardadas.size() == 2);
        assertTrue("It must return the date of the last game, in this case that of game2", partida2.getData() == partidasGuardadas.get(partidasGuardadas.size()-1));
        assertTrue("It must return the date of the penultimate game, in this case that of game1", partida1.getData() == partidasGuardadas.get(partidasGuardadas.size()-2));
    }
}
