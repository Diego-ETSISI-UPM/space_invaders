package org.space_invaders;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.space_invaders.space_invaders.sprites.Player;

import java.awt.Component;
import java.awt.Panel;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeyReleasedTests {

    private final Component dummy = new Panel();

    private int getKeyCode(String keyName) {
        return switch (keyName) {
            case "VK_LEFT" -> KeyEvent.VK_LEFT;
            case "VK_RIGHT" -> KeyEvent.VK_RIGHT;
            case "VK_UP" -> KeyEvent.VK_UP;
            default -> KeyEvent.VK_UNDEFINED; //
        };
    }

    private KeyEvent createPressEvent(String keyName) {
        int keyCode = getKeyCode(keyName);
        return new KeyEvent(dummy, KeyEvent.KEY_PRESSED, 0, 0, keyCode, (char) keyCode);
    }

    private KeyEvent createReleaseEvent(String keyName) {
        int keyCode = getKeyCode(keyName);
        return new KeyEvent(dummy, KeyEvent.KEY_RELEASED, 0, 0, keyCode, (char) keyCode);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/KeyReleasedCases.csv")
    void testKeyReleased(String keyPressName, String keyReleaseName, int expectedDx) {

        Player player = new Player();

        if (!keyPressName.equals("NONE")) {
            KeyEvent pressEvent = createPressEvent(keyPressName);
            player.keyPressed(pressEvent);
        }

        KeyEvent releaseEvent = createReleaseEvent(keyReleaseName);
        player.keyReleased(releaseEvent);

        assertEquals(expectedDx, player.getDx());
    }
}