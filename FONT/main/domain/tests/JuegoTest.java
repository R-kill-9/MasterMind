package main.domain.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import main.domain.Juego;
import org.junit.Before;


public class JuegoTest {
	
	/*
	 * Atributos
	 */
	private Juego juego;
	
	/*
	 * Método que se ejecuta antes de cada test
	 */
	@Before
	public void setUp() {
        juego = Juego.getInstance();
	}
	
	/*
	 * TESTS
	 */
	/*
	 * Comprueba la correcta creación de la instancia
	 */
	@Test
    public void testGetInstance() {
        assertNotNull(juego);
        assertSame(juego, Juego.getInstance());
    }

	/*
	 * Comprueba que se obtiene y se introduce correctamente la informacion de sistema
	 */
    @Test
    public void testSetAndGetInformacionSistema() {
        String informacionSistema = "informacion del sistema";
        juego.setInformacionSistema(informacionSistema);
        assertEquals(informacionSistema, juego.getInformacionSistema());
    }

	/*
	 * Comprueba que se obtiene y se introduce correctamente la informacion de puntuacion
	 */
    @Test
    public void testSetAndGetInformacionPuntuacion() {
        String informacionPuntuacion = "informacion de la puntuacion";
        juego.setInformacionPuntuacion(informacionPuntuacion);
        assertEquals(informacionPuntuacion, juego.getInformacionPuntuacion());
    }

/*
 * Comprueba que se devuelva un string con la informacion del sistema y la de la puntuacion
 */
    @Test
    public void testToString() {
        Juego juego = Juego.getInstance();
        String informacionSistema = "informacion del sistema";
        String informacionPuntuacion = "informacion de la puntuacion";
        juego.setInformacionSistema(informacionSistema);
        juego.setInformacionPuntuacion(informacionPuntuacion);
        assertEquals("Juego [informacionSistema=" + informacionSistema + ", informacionPuntuacion=" + informacionPuntuacion + "]", juego.toString());
    }
}
