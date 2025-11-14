package org.space_invaders.structural;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.space_invaders.space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActAlienTest {
    private Alien alien;

    @BeforeEach
    void setUp(){
        int dirX = 170;
        int dirY = 170;

        alien = new Alien(dirX, dirY);
    }
    @Test
    void actAlienCaminoUnico(){
        System.out.println("actAlienCaminoUnico()");
        int posX = alien.getX();
        alien.act(1);
        assertTrue(posX != alien.getX(), "no se ha modificado la posición");
    }

}
