package main.domain.tests;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import main.domain.Partida;
import main.domain.Usuario;


public class UsuarioTest2 {
	
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
}