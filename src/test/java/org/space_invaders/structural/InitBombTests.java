package org.space_invaders.structural;

import org.space_invaders.main.Commons;
import org.space_invaders.sprites.Alien;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InitBombTests {
    @Test
    void testCamino1() {
        assertTrue(probarPos(Commons.BOARD_WIDTH + 1, Commons.BOARD_HEIGHT + 1), "no se ha recorrido el camino 1");
    }

    @Test
    void testCamino2() {
        assertTrue(probarPos(Commons.BOARD_WIDTH - 1, Commons.BOARD_HEIGHT + 1), "no se ha recorrido el camino 2");
    }

     public boolean probarPos(int x, int y){
        boolean completado = false;
        Alien alien = new Alien(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT, Commons.GROUND - Commons.ALIEN_HEIGHT);
        Alien.Bomb bomb = alien.getBomb();
        bomb.initBomb(x,y);
        if (alien.getBomb().getX() <= Commons.BOARD_WIDTH && alien.getBomb().getY() <= Commons.BOARD_HEIGHT) { completado = true; }
        return completado;
    }
}
