package org.space_invaders.structural;

import org.space_invaders.main.Board;
import org.space_invaders.main.Commons;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateAliensTests {

    @Test
    void updateAliensCamino4() {
        System.out.println("\nupdateAliensCamino4()");
        boolean resultado = false;
        Board board = new Board();
        printAliens("situación inicial:", board);
        int contador = 0;
        while (contador <= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT - Commons.ALIEN_INIT_X
                && board.getAliens().getLast().getX() <= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT) {
            board.update_aliens();
            if (contador == 328) board.setDirection(-1);
            contador++;
        }
        printAliens("situación borde derecho:", board);
        int y = board.getAliens().getFirst().getY();
        board.update_aliens();
        System.out.println("movimiento: " + contador + ", direction = " + board.getDirection());
        printAliens("situación final:", board);
        if (board.getAliens().getFirst().getY() > y) resultado = true;
        assertTrue(resultado, "mensaje de error");
    }

    @Test
    void updateAliensCamino6() {
        System.out.println("\nupdateAliensCamino6()");
        boolean resultado = false;
        Board board = new Board();
        printAliens("situación inicial:", board);
        int contador = 0;
        board.update_aliens();
        board.setDirection(1);
        contador++;
        System.out.println("movimiento: " + contador + ", direction = " + board.getDirection());
        printAliens("situación 1:", board);
        while (contador <= ((Commons.BOARD_WIDTH - Commons.BORDER_RIGHT - Commons.ALIEN_INIT_X)*2)
                && board.getAliens().getFirst().getX() >= 0) {
            board.update_aliens();
            contador++;
        }
        board.setDirection(-1);
        printAliens("situación borde izquierdo:", board);
        int y = board.getAliens().getFirst().getY();
        board.update_aliens();
        System.out.println("movimiento: " + contador + ", direction = " + board.getDirection());
        printAliens("situación final:", board);
        if (board.getAliens().getFirst().getY() > y) resultado = true;
        assertTrue(resultado, "mensaje de error, recuerda board.setDirection();");
    }

    @Test
    void updateAliensCamino8() {
        System.out.println("\nupdateAliensCamino8()");
        boolean resultado = false;
        Board board = new Board();
        printAliens("situación inicial:", board);
        System.out.println("isVisible() = " + board.getAliens().getFirst().isVisible()
                + ", isInGame() = " + board.isInGame());
        board.update_aliens();
        printAliens("situación final:", board);
        resultado = true;
        System.out.println("isVisible() = " + board.getAliens().getFirst().isVisible()
                + ", isInGame() = " + board.isInGame());
        assertTrue(resultado, "mensaje de error, recuerda board.setDirection();");
    }


    @Test
    void updateAliensCamino9() {
        System.out.println("\nupdateAliensCamino9()");
        boolean resultado = false;
        Board board = new Board();
        printAliens("situación inicial:", board);
        System.out.println("isVisible() = " + board.getAliens().getFirst().isVisible()
                + ", isInGame() = " + board.isInGame());

        int contador = 0;
        while (contador <= (Commons.BOARD_WIDTH - Commons.BORDER_RIGHT - Commons.ALIEN_INIT_X)*((Commons.BOARD_HEIGHT - Commons.ALIEN_HEIGHT)/Commons.GO_DOWN)
                && board.getAliens().getLast().getY() <= Commons.BOARD_HEIGHT - Commons.ALIEN_HEIGHT) {
            board.update_aliens();
            contador++;
        }

        printAliens("situación " + contador + ":", board);
        resultado = true;
        System.out.println("isVisible() = " + board.getAliens().getFirst().isVisible()
                + ", isInGame() = " + board.isInGame());
        assertTrue(resultado, "mensaje de error");
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
