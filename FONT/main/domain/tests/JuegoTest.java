package main.domain.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import main.domain.Juego;
import org.junit.Before;


public class JuegoTest {
	
	/*
	 * Atributos
	 */
	private Juego newJuego;
	
	/*
	 * Método que se ejecuta antes de cada test
	 */
	@Before
	public void setUp() {
        newJuego = Juego.getInstance();
	}
	
	/*
	 * TESTS
	 */
	/*
	 * Comprueba que se introduzca bien la información de la puntuación
	 */
	@Test
    public void testSetInformacionPuntuacion() {
        newJuego.setInformacionPuntuacion("Nueva informacion de la puntuacion");
        assertEquals("Nueva informacion de la puntuacion", newJuego.getInformacionPuntuacion());
    }
	
	/*
	 * Comprueba que se introduzca bien la información del sistema
	 */
	@Test
    public void testSetInformacionSistema() {
        newJuego.setInformacionSistema("Reimplementación de la información del sistema");
        assertEquals("Reimplementación de la información del sistema", newJuego.getInformacionSistema());
    }
	
	/*
	 * Comprueba que se obtenga bien la información de puntuación
	 */
	@Test
	public void testGetInformacionPuntuacion() {
	    assertEquals("Ejemplo información de la puntuación", newJuego.getInformacionPuntuacion());
	    newJuego.setInformacionPuntuacion("Otra versión de informacion de la puntuacion");
        assertEquals("Otra versión de informacion de la puntuacion", newJuego.getInformacionPuntuacion());
	}
	
	/*
	 * Comprueba que se obtenga bien la información de sistema
	 */
	@Test
    public void testGetInformacionSistema() {
        assertEquals("Ejemplo información del sistema", newJuego.getInformacionSistema());
        newJuego.setInformacionSistema("Versión mejorada de la información del sistema");
        assertEquals("Versión mejorada de la información del sistema", newJuego.getInformacionSistema());
    }
	
	/*
	 * Comprueba que al pasar a string devuelva la información correcta
	 */
	@Test
    public void testToString() {
        assertEquals("Juego [informacionSistema=Ejemplo información del sistema, informacionPuntuacion=Ejemplo información de la puntuación]", newJuego.toString());
    }
}
