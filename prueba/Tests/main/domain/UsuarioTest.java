package main.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void getUsername() {
    }

    @Test
    void getUsuario() {
    }

    @Test
    void getMaxScore() {
        int maxscore = 20;
        Usuario a = new Usuario( "ricard");
        a.setMaxScore(maxscore);
        assertTrue(a.getMaxScore() == 20);
    }

    @Test
    void setMaxScore() {
    }

    @Test
    void addPartida() {
    }

    @Test
    void deletePartida() {
    }

    @Test
    void getPartida() {
    }

    @Test
    void getPartidasGuardadas() {
    }
}