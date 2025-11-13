package org.space_invaders;

import org.space_invaders.space_invaders.sprites.Player;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class keyPressedTests {
    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/KeyPressedCases.csv")
    void testKeyPressed(String keyName, int expectedDx){

        int keyCode = switch (keyName) {
            case "VK_LEFT" -> KeyEvent.VK_LEFT;
            case "VK_RIGHT" -> KeyEvent.VK_RIGHT;
            case "VK_UP" -> KeyEvent.VK_UP;
            default -> KeyEvent.VK_UNDEFINED;

        };

        Component dummy = new Panel();
        KeyEvent keyEvent = new KeyEvent(dummy, KeyEvent.KEY_PRESSED,0, 0, keyCode, (char) keyCode);
        Player player = new Player();
        player.keyPressed(keyEvent);
        player.keyPressed(keyEvent);
        assertEquals(player.getDx(), expectedDx);

    }

}
