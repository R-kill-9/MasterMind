package main.domaincontrollers.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.Before;
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
        partida = ctrlPartida.crearPartida(1, "ricky", true, true);
        HistorialPartidas historial = new HistorialPartidas();
        HistorialPartidasGuardadas historialGuardadas = new HistorialPartidasGuardadas();
    }

    /*
     * Comprueba que se cree una partida y se asigne como partidssa actual
     */
    @Test
    public void crearPartidaTest() {
        Partida expectedPartida = ctrlPartida.getPartidaActual();
        assertNotNull(expectedPartida);
        /*assertEquals(partida, expectedPartida);
        assertEquals(partida.getUsuario(), expectedPartida.getUsuario());
        assertEquals(true, expectedPartida.getAyuda());
        }
    
    /*
     * Comprueba que se añada la partida al historial de partidas
     */
    @Test
    	public void testCrearPartidaHistorial() {
    	Date dataPartida = CtrlUsuario.getDataPartidasGuardadas().get(0);
    	Pair expectedPartida = new Pair<>("ricky", dataPartida);
    	Pair historialPartida = ctrlUsuario.getPartidasHistorial().get(0);
    	
        assertEquals(expectedPartida.getFirst(), historialPartida.getFirst());
        assertEquals(expectedPartida.getSecond(), historialPartida.getSecond());
        
        ctrlUsuario.crearPartida(1, false, false);
    	Date dataPartida2 = CtrlUsuario.getDataPartidasGuardadas().get(1);
    	Pair expectedPartida2 = new Pair<>(ctrlUsuario.getUsuarioActual(), dataPartida2);
    	Pair historialPartida2 = ctrlUsuario.getPartidasHistorial().get(1);
    	
        assertEquals(expectedPartida2.getFirst(), historialPartida2.getFirst());
        assertEquals(expectedPartida2.getSecond(), historialPartida2.getSecond());
    }


    @Test
    public void getPartidaActualTest() throws Exception {
        assertNotNull(ctrlPartida.getPartidaActual());
        partida = ctrlPartida.crearPartida(1, "test", true, true);
        assertNotNull(ctrlPartida.getPartidaActual());
    }

 

}
   
