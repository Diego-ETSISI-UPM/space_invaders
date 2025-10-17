package org.space_invaders;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import space_invaders.sprites.Alien;

class MainTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testInitBomb() {
        // Coordenadas iniciales del Alien
        int posX = 10;
        int posY = 20;

        // Creamos el Alien
        Alien alien = new Alien(posX, posY);

        // Obtenemos la bomba
        Alien.Bomb bomb = alien.getBomb();

        // Comprobamos que la bomba existe
        assertNotNull(bomb, "La bomba debería haberse creado al inicializar el alien.");

        // Verificamos que la bomba está inicialmente marcada como destruida
        assertTrue(bomb.isDestroyed(), "La bomba debería estar inicialmente destruida.");

        // Comprobamos que la posición coincide
        assertEquals(posX, bomb.getX(), "La coordenada X de la bomba debería coincidir con la del alien.");
        assertEquals(posY, bomb.getY(), "La coordenada Y de la bomba debería coincidir con la del alien.");

    }
    @Test
    void crearAlienPosCorrecta(){
        int posH = 10;
        int posV = 10;
        Alien alien = new Alien(posH, posV);
        boolean resultado = alien.getX() == posH && alien.getY() == posV;
        assertTrue(resultado);
    }
    @Test
    void testMainOutput() {
        // Redirigir la salida estándar a un stream para capturarla
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // Ejecutar el main
            Main.main(new String[]{});

            // Recuperar la salida como cadena
            String output = outContent.toString().trim();

            // Comprobar que contiene lo esperado
            assertTrue(output.contains("Hello and welcome!"));
            for (int i = 1; i <= 5; i++) {
                assertTrue(output.contains("i = " + i));
            }

        } finally {
            // Restaurar la salida estándar original
            System.setOut(originalOut);
        }
    }
}