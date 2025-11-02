package org.space_invaders;

import org.space_invaders.main.Board;
import org.space_invaders.main.Commons;
import org.space_invaders.space_invaders.sprites.Alien;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateAliensTests {

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/UpdateAliensCases.csv")
    void testUpdateAliensDerrota(boolean todoOK) {
        assertEquals(testUpdateAliensDerrota(), todoOK, "derrota no termina el juego");
    }

    boolean testUpdateAliensDerrota() {
        Board board = new Board();
        List<Alien> aliens = board.getAliens();
        boolean correcto = false;
        System.out.println("testUpdateAliensDerrota()");
        while (aliens.getLast().getY() <= Commons.GROUND + Commons.ALIEN_HEIGHT){
            board.update_aliens();
        }
        if (aliens.getLast().getY() >= Commons.GROUND + Commons.ALIEN_HEIGHT) {
            System.out.println("derrotaY = " + (Commons.GROUND + Commons.ALIEN_HEIGHT) + ", posY = " + aliens.getLast().getY());
            correcto = false;
        }
        if (board.isInGame()) {
            System.out.println("estado del juego = "+ board.isInGame());
            correcto = false;
        }
        return correcto;
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/UpdateAliensCases.csv")
    void testUpdateAliensBordeDCH(boolean todoOK) {
        assertEquals(testUpdateAliensBordeDCH(), todoOK, "bordeDCH no funciona");
    }

    boolean testUpdateAliensBordeDCH(){
        boolean correcto = true;
        Board board = new Board();
        List<Alien> aliens = board.getAliens();
        int posX = 0;
        int posY = 0;
        System.out.println("testUpdateAliensBordeDCH()");
        while (aliens.getLast().getX() <= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT){
            if (aliens.getLast().getX() == Commons.BOARD_WIDTH - Commons.BORDER_RIGHT) {
                posY = aliens.getLast().getY();
                posX = aliens.getLast().getX();
            }
            board.update_aliens();
        }
        if (aliens.getLast().getX() >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT) {
            System.out.println("borde DCH = " + (Commons.BOARD_WIDTH - Commons.BORDER_RIGHT) + ", posX = " + aliens.getLast().getX());
            correcto = false;
        }
        if (aliens.getLast().getX() != posX - 1) {
            System.out.println("aliens.getLast().getX() = " + aliens.getLast().getX() + ", posX = " + posX);
            correcto = false;
        }
        if (aliens.getLast().getY() != posY + Commons.GO_DOWN) {
            System.out.println("posY + Commons.GO_DOWN = " + (posY + Commons.GO_DOWN) + ", posY = " + aliens.getLast().getY());
            correcto = false;
        }
        if (board.getDirection() == 1){
            System.out.println("board.getDirection() = " + board.getDirection());
            correcto = false;
        }
        return correcto;
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/UpdateAliensCases.csv")
    void testUpdateAliensBordeIZQ(boolean todoOK) {
        assertEquals(testUpdateAliensBordeIZQ(), todoOK, "bordeIZQ no funciona");
    }

    boolean testUpdateAliensBordeIZQ(){
        boolean correcto = true;
        Board board = new Board();
        int posY = 0;
        int posX = 0;
        board.setDirection(1);
        // @TODO setDirection tiene lógica inversa!
        List<Alien> aliens = board.getAliens();
        while (aliens.getFirst().getX() >= Commons.BORDER_LEFT){
            if (aliens.getFirst().getX() == Commons.BORDER_LEFT) {
                posY = aliens.getLast().getY();
                posX = aliens.getFirst().getX();
            }
            board.update_aliens();
        }
        if (Commons.BORDER_LEFT > aliens.getFirst().getX()) {
            correcto = false;
            System.out.println("borde IZQ = " + Commons.BORDER_LEFT + ", posX = " + posX);
        }
        if (aliens.getFirst().getX() != posX + 1) {
            System.out.println("aliens.getFirst().getX() = " + aliens.getFirst().getX() + ", posX + 1 = " + (posX + 1));
            correcto = false;
        }
        if (aliens.getLast().getY() != posY + Commons.GO_DOWN) {
            correcto = false;
            System.out.println("altura antes del borde = " + posY + ", después = " + aliens.getLast().getY());
        }
        if (board.getDirection() == -1){
            System.out.println("board.getDirection() = " + board.getDirection());
            correcto = false;
        }
        return correcto;
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/UpdateAliensCases.csv")
    void testUpdateAliensNormalDCH(boolean todoOK) {
        assertEquals(testUpdateAliensNormalDCH(), todoOK, "normalDCH no funciona");
    }

    boolean testUpdateAliensNormalDCH(){
        boolean correcto = true;
        Board board = new Board();
        board.update_aliens();
        List<Alien> aliens = board.getAliens();
        int posX = aliens.getFirst().getX();
        board.update_aliens();
        if (aliens.getFirst().getX() != posX + 1) {
            System.out.println("aliens.getFirst().getX() = " + aliens.getFirst().getX() + ", posX + 1 = " + (posX + 1));
            correcto = false;
        }
        return correcto;
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/UpdateAliensCases.csv")
    void testUpdateAliensNormalIZQ(boolean todoOK) {
        assertEquals(testUpdateAliensNormalIZQ(), todoOK, "normalIZQ no funciona");
    }

    boolean testUpdateAliensNormalIZQ(){
        boolean correcto = true;
        Board board = new Board();
        board.update_aliens();
        board.setDirection(-1);
        // @TODO setDirection tiene lógica inversa!
        board.update_aliens();
        List<Alien> aliens = board.getAliens();
        int posX = aliens.getFirst().getX();
        if (aliens.getFirst().getX() != posX - 1) {
            System.out.println("aliens.getFirst().getX() = " + aliens.getFirst().getX() + ", posX - 1 = " + (posX - 1));
            correcto = false;
        }
        return correcto;
    }

}