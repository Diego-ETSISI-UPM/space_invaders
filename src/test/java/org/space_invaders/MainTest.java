package org.space_invaders;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import space_invaders.sprites.Alien;

class MainTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void crearAlienPosicCorrecta(){
        int posH = 10;
        int posV = 10;
        Alien alien = new Alien(posH, posV);
        boolean resultado = alien.getX() == posH && alien.getY() == posV;
        assertTrue(resultado);
    }
}