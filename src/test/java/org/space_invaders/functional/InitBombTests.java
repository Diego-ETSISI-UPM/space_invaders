package org.space_invaders.functional;

import org.space_invaders.main.Commons;
import org.space_invaders.space_invaders.sprites.Alien;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InitBombTests {
    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/InitBombCases.csv")
    void testInitBomb(int posX, int posY, int expectedX, int expectedY) {
        //TODO modificado la inicialización de alien: Alien alien = new Alien(inputX, inputY);
        Alien alien = new Alien(0, 0);
        Alien.Bomb bomb = alien.getBomb();
        //TODO añadido inicialización de bomb
        bomb.initBomb(posX, posY);
        //TODO modificados los valores de variables que se comprueban
        assertEquals(expectedX, alien.getBomb().getX(), "La coordenada X no es la esperada.");
        assertEquals(expectedY, alien.getBomb().getY(), "La coordenada Y no es la esperada.");
    }
}
