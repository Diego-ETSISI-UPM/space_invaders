package org.space_invaders.integration;

import org.junit.jupiter.api.Test;
import org.space_invaders.main.Board;
import org.space_invaders.space_invaders.sprites.Player;
import org.space_invaders.space_invaders.sprites.Shot;

import javax.swing.Timer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.*;

// OJO: usa clase interna de Java (sirve para prácticas)
import sun.reflect.ReflectionFactory;

public class UpdateIntegrationTest {

    static class TestLogHandler extends Handler {
        final List<String> msgs = new ArrayList<>();
        @Override public void publish(LogRecord r) { msgs.add(r.getMessage()); }
        @Override public void flush() {}
        @Override public void close() {}
    }

    @Test
    public void update_envia_mensajes_en_orden() throws Exception {
        Logger log = Logger.getLogger(Board.class.getName());
        TestLogHandler h = new TestLogHandler();
        log.setUseParentHandlers(false);
        log.addHandler(h);

        Board b = newBoardWithoutConstructor();

        Player player = new Player();
        b.setPlayer(player);

        b.setShot(new Shot());

        b.setAliens(new ArrayList<>());

        b.setTimer(new Timer(1000, e -> {})); // no lo arrancamos

        Method m = Board.class.getDeclaredMethod("update");
        m.setAccessible(true);
        m.invoke(b);

        log.removeHandler(h);

        int i1 = h.msgs.indexOf("MSG player.act");
        int i2 = h.msgs.indexOf("MSG update_shots");
        int i3 = h.msgs.indexOf("MSG update_aliens");
        int i4 = h.msgs.indexOf("MSG update_bomb");

        assertTrue(i1 != -1 && i2 != -1 && i3 != -1 && i4 != -1,
                "Faltan mensajes. Logs=" + h.msgs);
        assertTrue(i1 < i2 && i2 < i3 && i3 < i4,
                "Orden incorrecto. Logs=" + h.msgs);


         h.msgs.forEach(System.out::println);
    }

    private static Board newBoardWithoutConstructor() throws Exception {
        ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
        Constructor<Object> objCtor = Object.class.getDeclaredConstructor();
        @SuppressWarnings("unchecked")
        Constructor<Board> boardCtor =
                (Constructor<Board>) rf.newConstructorForSerialization(Board.class, objCtor);
        boardCtor.setAccessible(true);
        return boardCtor.newInstance();
    }
}
