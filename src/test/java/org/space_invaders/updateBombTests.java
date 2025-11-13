package org.space_invaders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.space_invaders.main.Board;
import org.space_invaders.main.Commons;
import org.space_invaders.space_invaders.sprites.Alien;
import org.space_invaders.space_invaders.sprites.Player;
import static org.junit.jupiter.api.Assertions.*;

public class updateBombTests {

    private Board board;
    private Player player;
    private Alien alien;

    @BeforeEach
    void setUp() {
        board = new Board();
        player = board.getPlayer();
        alien = board.getAliens().get(0);

        player.setVisible(true);
        alien.setVisible(true);

        player.setDying(false);
    }


   void test_R1(){
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(true);
        // PREGUNTAR A PROFE COMO TESTEAR ESTO
        //Falta por testear como hago para hacer que el rand sea lo mismo que chance.
   }

   void testR2(){
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        //Falta por como testear hago para hacer que el rand no sea lo mismo que chance
   }

    @Test
    void test_R3_ColisionConJugador_DestruyeAmbos() {
        // Condición 1: Bomba Activa = True
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);

        // Condición 2: Colisión con jugador = True
        player.setX(150);
        player.setY(150);
        bomb.setX(150); // Misma X
        bomb.setY(150); // Misma Y

        board.update_bomb();

        assertTrue(bomb.isDestroyed(), "R3 Fallida: La bomba debería destruirse.");
        assertTrue(player.isDying(), "R3 Fallida: El jugador debería morir.");
    }

    @Test
    void test_R4_TocaSuelo_DeberiaDestruirBomba() {
        // Condición 1: Bomba Activa = True
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);

        // Condición 2: Colisión con jugador = False
        player.setX(100);
        player.setY(100);
        bomb.setX(500);

        // Condición 3: Toco Suelo = True
        int y= (Commons.GROUND - Commons.BOMB_HEIGHT);
        bomb.setY(y);

        board.update_bomb();
        assertTrue(bomb.isDestroyed(), "R4 FALLIDA (BUG ENCONTRADO): La bomba debería destruirse al tocar el suelo, pero se movió hacia arriba.");
        assertFalse(player.isDying(), "R4 Fallida: El jugador no debería morir");
    }

    @Test
    void test_R5_MoverHaciaAbajo_NoColision_NoSuelo() {
        // Condición 1: Bomba Activa = True
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);

        // Condición 2: Colisión con jugador = False
        player.setX(100);
        player.setY(100);
        bomb.setX(500); // X muy lejana

        // Condición 3: Toco Suelo = False
        int y_inicial = 200;
        bomb.setY(y_inicial);

        board.update_bomb();


        int y_esperada = y_inicial + Commons.BOMB_SPEED;


        assertEquals(y_esperada, bomb.getY(),
                "R5 FALLIDA (BUG ENCONTRADO): La bomba debería moverse hacia abajo (Y+), pero se movió hacia arriba (Y-).");

        assertFalse(bomb.isDestroyed(), "R5 Fallida: La bomba no debería destruirse");
        assertFalse(player.isDying(), "R5 Fallida: El jugador no debería morir");
    }
}