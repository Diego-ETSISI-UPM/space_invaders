package org.space_invaders.functional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.space_invaders.space_invaders.sprites.Alien;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActAlienTest {
    private Alien alien;

    @BeforeEach
    void setUp(){
        int dirX = 170;
        int dirY = 170;

        alien = new Alien(dirX, dirY);
    }
    @Test
    void actAlienMovimientoIzq(){
        alien.act(5);
        assertEquals(165, alien.getX(), "El alien deberia moverse 5 unidades a la izquierda");
    }

    @Test
    void actAlienMovimientoDrch(){
        alien.act(5);
        assertEquals(175, alien.getX(), "El alien deberia moverse 5 unidades a la derecha");
    }

    @Test
    void actAlienSinMovimiento(){
        alien.act(0);
        assertEquals(175, alien.getX(), "El alien no deberia moverse");
    }

}
