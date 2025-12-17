package org.space_invaders.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.space_invaders.main.Board;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class updateAliensAlienActIntTest {

    private static final Logger logger = Logger.getLogger(updateAliensAlienActIntTest.class.getName());

    @Test
    public void updateAliens_AlienAct_IntTest() throws IOException {
        FileHandler fileHandler = new FileHandler("log.xml");
        logger.addHandler(fileHandler);
        fileHandler.setFormatter(new LogsCustomFormatter());
        Board board = new Board();
        logger.info("antes de actualizar\tboard.getAliens().getFirst().getX() = " + board.getAliens().getFirst().getX() + "\tboard.getAliens().getFirst().getY() =  " + board.getAliens().getFirst().getY());
        board.setDirection(1);
        printAliens("situación inicial", board);
        int x = board.getAliens().getFirst().getX();
        int y = board.getAliens().getFirst().getY();
        board.update_aliens();
        printAliens("situación final", board);
        boolean movedInX = board.getAliens().getFirst().getX() != x;
        boolean movedInY = board.getAliens().getFirst().getY() != y;
        logger.info("después de actualizar\tboard.getAliens().getFirst().getX() = " + board.getAliens().getFirst().getX() + "\tboard.getAliens().getFirst().getY() =  " + board.getAliens().getFirst().getY());
        assertTrue(movedInX || movedInY, "El alien debe haber cambiado la coordenada X o la Y");
    }

    void printAliens(String mensaje, Board board) {
        System.out.println(mensaje);
        System.out.println(
                "firstAlien: pos = (" + board.getAliens().getFirst().getX()
                        + "," + board.getAliens().getFirst().getY()
                        + "), isVisible = " + board.getAliens().getFirst().isVisible()
                        + ", isDying = " + board.getAliens().getFirst().isDying() + " "
        );
        System.out.println(
                "LastAlien: pos = (" + board.getAliens().getLast().getX()
                        + "," + board.getAliens().getLast().getY()
                        + "), isVisible = " + board.getAliens().getLast().isVisible()
                        + ", isDying = " + board.getAliens().getLast().isDying() + " "
        );

    }
}
