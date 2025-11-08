package org.space_invaders.functional;

import org.space_invaders.sprites.Alien;
import static org.junit.jupiter.api.Assertions.*;

public class InitBombTests {
    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/InitBombCases.csv")
    void testInitBomb(int posX, int posY, int expectedX, int expectedY) {
        Alien alien = new Alien(posX, posY);
        Alien.Bomb bomb = alien.getBomb();
        bomb.initBomb(posX, posY);
        assertEquals(expectedX, alien.getX(), "La coordenada X no es la esperada.");
        assertEquals(expectedY, alien.getY(), "La coordenada Y no es la esperada.");
    }
}
