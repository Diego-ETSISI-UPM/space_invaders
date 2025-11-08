package org.space_invaders.structural;

import org.space_invaders.main.Commons;
import org.space_invaders.sprites.Alien;
import static org.junit.jupiter.api.Assertions.*;

public class InitBombTests {
    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/UpdateAliensCases.csv")
    void testCamino1(boolean todoOK) {
        assertEquals(probarPos(Commons.BOARD_WIDTH + 1, Commons.BOARD_HEIGHT + 1), todoOK, "no se recorrido el camino 1");
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/UpdateAliensCases.csv")
    void testCamino2(boolean todoOK) {
        assertEquals(probarPos(Commons.BOARD_WIDTH - 1, Commons.BOARD_HEIGHT + 1), todoOK, "no se recorrido el camino 2");
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
