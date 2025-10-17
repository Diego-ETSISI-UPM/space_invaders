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
    void crearAlienPosicCorrecta(){
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