package main.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartidaTest {

    @Test
    void getEstadoPartida() {
    }

    @Test
    void getData() {
    }

    @Test
    void getUsuario() {
        Partida partida = new Partida(1, "usuario", false, true);
        assertEquals("usuario", partida.getUsuario());
    }

    @Test
    void setAyuda() {
    }

    @Test
    void getDificultad() {
    }

    @Test
    void getSolution() {
    }

    @Test
    void setSolution() {
    }

    @Test
    void setCombinacion() {
    }

    @Test
    void getPuntuacion() {
    }

    @Test
    void getFecha() {
    }
}