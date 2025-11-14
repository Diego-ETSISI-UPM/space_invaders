package org.space_invaders.structural;

import org.space_invaders.space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InitAlienTests {
    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/InitAlienPaths.csv")
    void testCamino(int x, int y) {
        assertTrue(probarPos(x, y), "no se recorrido el camino");
    }

    public boolean probarPos(int x, int y){
        Alien alien = new Alien(x, y);
        System.out.println("(" + alien.getX() + "," + alien.getY() + ")");
        return true;
    }

}
