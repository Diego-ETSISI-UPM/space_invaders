package org.space_invaders.functional;

import org.space_invaders.main.Board;
import org.space_invaders.main.Commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateAliensTests {

    private Board board;
    private boolean correcto;
    int posX;
    int posY;

    @BeforeEach
    void setUp() {
        board = new Board();
        correcto = true;
        posX = 0;
        posY = 0;
    }

    @Test
    void testUpdateAliensDerrota() {
        System.out.println("\ntestUpdateAliensDerrota()");
        printAliens("situación inicial", board);
        //TODO si no se indica la dirección la lógica de actualización es inversa
        board.setDirection(1);
        while (board.getAliens().getLast().getY() <= Commons.GROUND - Commons.ALIEN_HEIGHT){
            board.update_aliens();
        }
        printAliens("situación final", board);
        System.out.println("estado del juego = "+ board.isInGame());
        //TODO ahora no hace falta comprobar el caso que sigue a continuación
        /*if (board.getAliens().getLast().getY() >= Commons.GROUND - Commons.ALIEN_HEIGHT) {
            System.out.println("derrotaY = " + (Commons.GROUND - Commons.ALIEN_HEIGHT) + ", posY = " + board.getAliens().getLast().getY());
            correcto = false;
        }*/
        if (board.isInGame()) {
            System.out.println("estado del juego = "+ board.isInGame());
            correcto = false;
        }
        assertTrue(correcto,"derrota no termina el juego");
    }

    @Test
    void testUpdateAliensBordeDCH() {
        System.out.println("testUpdateAliensBordeDCH()");
        printAliens("situación inicial", board);
        //TODO redefinido hasta línea 57
        while (board.getAliens().getLast().getX() < Commons.BOARD_WIDTH - Commons.BORDER_RIGHT){
            board.update_aliens();
        }
        posY = board.getAliens().getLast().getY();
        posX = board.getAliens().getLast().getX();
        board.update_aliens();
        printAliens("situación final", board);
        //TODO cambiado >= por >
        if (board.getAliens().getLast().getX() > Commons.BOARD_WIDTH - Commons.BORDER_RIGHT) {
            System.out.println("sobrepasado borde DCH = " + (Commons.BOARD_WIDTH - Commons.BORDER_RIGHT) + ", posX = " + board.getAliens().getLast().getX());
            correcto = false;
        }
        /*TODO no es necesario que se cumpla la condición por la redefinición
        if (board.getAliens().getLast().getX() != posX) {
            System.out.println("board.getAliens().getLast().getX() = " + board.getAliens().getLast().getX() + ", posX = " + posX);
            correcto = false;
        }*/
        if (board.getAliens().getLast().getY() != posY + Commons.GO_DOWN) {
            System.out.println("posY + Commons.GO_DOWN = " + (posY + Commons.GO_DOWN) + ", posY = " + board.getAliens().getLast().getY());
            correcto = false;
        }
        if (board.getDirection() == 1){
            System.out.println("board.getDirection() = " + board.getDirection());
            correcto = false;
        }
        assertTrue(correcto,"bordeDCH no funciona");
    }

    @Test
    void testUpdateAliensBordeIZQ() {
        System.out.println("testUpdateAliensBordeIZQ()");
        //TODO modificado el parámetro de setDirection, de acuerdo a la lógica corregida
        board.setDirection(-1);
        printAliens("situación inicial", board);
        //TODO setDirection tenía lógica inversa! & sustituido >= por > & reescrito hasta línea 93
        while (board.getAliens().getFirst().getX() > Commons.BORDER_LEFT){
            board.update_aliens();
        }
        posY = board.getAliens().getLast().getY();
        posX = board.getAliens().getFirst().getX();
        board.update_aliens();
        printAliens("situación final", board);
        if (Commons.BORDER_LEFT > board.getAliens().getFirst().getX()) {
            System.out.println("sobrepasado borde IZQ = " + Commons.BORDER_LEFT + ", posX = " + board.getAliens().getFirst().getX());
            correcto = false;
        }
        /*TODO no es necesario que se cumpla la condición por la redefinición
        if (board.getAliens().getFirst().getX() != posX) {
            System.out.println("board.getAliens().getFirst().getX() = " + board.getAliens().getFirst().getX() + ", anteriorX = " + posX);
            correcto = false;
        }*/
        if (board.getAliens().getLast().getY() != posY + Commons.GO_DOWN) {
            correcto = false;
            System.out.println("altura antes del borde = " + posY + ", después = " + board.getAliens().getLast().getY());
        }
        if (board.getDirection() == -1){
            System.out.println("board.getDirection() = " + board.getDirection());
            correcto = false;
        }
        assertTrue(correcto,"bordeIZQ no funciona");
    }

    @Test
    void testUpdateAliensNormalDCH() {
        System.out.println("testUpdateAliensNormalDCH()");
        printAliens("situación inicial", board);
        //TODO si no se indica la dirección la lógica de actualización es inversa
        board.setDirection(1);
        posX = board.getAliens().getFirst().getX();
        board.update_aliens();
        printAliens("situación final", board);
        if (board.getAliens().getFirst().getX() != posX + 1) {
            System.out.println("board.getAliens().getFirst().getX() = " + board.getAliens().getFirst().getX() + ", posX + 1 = " + (posX + 1));
            correcto = false;
        }
        assertTrue(correcto,"normalDCH no funciona");
    }

    @Test
    void testUpdateAliensNormalIZQ() {
        System.out.println("testUpdateAliensNormalIZQ()");
        board.setDirection(-1);
        // @TODO setDirection tiene lógica inversa!
        posX = board.getAliens().getFirst().getX();
        printAliens("situación inicial", board);
        board.update_aliens();
        printAliens("situación final", board);
        if ( board.getAliens().getFirst().getX() + 1 != posX ) {
            System.out.println("board.getAliens().getFirst().getX() = " + board.getAliens().getFirst().getX() + ", anterior = " + posX);
            correcto = false;
        }
        assertTrue(correcto,"normalIZQ no funciona");
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