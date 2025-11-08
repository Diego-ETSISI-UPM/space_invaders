package org.space_invaders.functional;

import org.space_invaders.sprites.Alien;
import static org.junit.jupiter.api.Assertions.*;

public class InitAlienTests {
    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/InitAlienCases.csv")
    void testInitAlien(int inputX, int inputY, int expectedX, int expectedY) {
        Alien alien = new Alien(inputX, inputY);
        alien.initAlien(inputX, inputY);
        assertEquals(expectedX, alien.getX(), "La coordenada X no es la esperada.");
        assertEquals(expectedY, alien.getY(), "La coordenada Y no es la esperada.");
    }
}
