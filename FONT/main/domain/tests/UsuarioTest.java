package main.domain.tests;

import static org.junit.Assert.*;


import org.junit.Test;
import main.domain.Usuario;

public class UsuarioTest {

	@Test
	public void testUsuario() {
		Usuario usuario = new Usuario("Juan");
		assertEquals("Juan", usuario.getUsername());
	}

}
