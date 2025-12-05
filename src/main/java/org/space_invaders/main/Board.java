package org.space_invaders.main;

import org.space_invaders.space_invaders.sprites.Alien;
import org.space_invaders.space_invaders.sprites.Player;
import org.space_invaders.space_invaders.sprites.Shot;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * {@summary Tablero principal del juego Space Invaders que gestiona la lógica del juego y la interfaz gráfica.}
 * <p>
 * Esta clase extiende JPanel y maneja:
 * </p>
 * <ul>
 *   <li><strong>Inicialización:</strong> {@link #gameInit()} - Configura aliens, jugador y disparos</li>
 *   <li><strong>Renderizado<span class="alert-small">⛔🧪</span>:</strong> {@link #drawAliens(Graphics)}, {@link #drawPlayer(Graphics)}, {@link #drawShot(Graphics)} - Dibuja elementos del juego</li>
 *   <li><strong>Actualización:</strong> {@link #update()}, {@link #update_shots()}, {@link #update_aliens()}, {@link #update_bomb()} - Actualiza estado del juego</li>
 *   <li><strong>Gestión de eventos <span class="alert-small">⛔🧪</span>:</strong> {@link TAdapter} - Maneja entrada del teclado</li>
 *   <li><strong>Setters y Getters <span class="alert-small">⛔🧪</span>:</strong> {@link #getPlayer()}, {@link #getAliens()}, {@link #getShot()} - Obtiene elementos del juego</li>
 * </ul>
 * 
 * @author Space Invaders Team
 * @version 1.0
 */
public class Board extends JPanel {

    private Dimension d;
    private List<Alien> aliens;
    private Player player;
    private Shot shot;

    private int direction = -1;
    private int deaths = 0;

    private boolean inGame = true;
    private String explImg = "src/main/resources/images/explosion.png";
    private String message = "Game Over";

    private Timer timer;

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Obtiene el objeto jugador del tablero.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @return el objeto Player actual
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Obtiene la lista de aliens del tablero.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @return la lista de objetos Alien
     */
    public List<Alien> getAliens(){
        return this.aliens;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Obtiene el objeto disparo actual del tablero.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @return el objeto Shot actual
     */
    public Shot getShot() {
        return this.shot;
    }

    /**
     * {@summary Inicializa el tablero y comienza la partida.}
     * 
     * El constructor configura el tablero de juego, inicializa los componentes del juego
     * y comienza la partida llamando a {@link #initBoard()} y {@link #gameInit()}.
     */
    public Board() {

        initBoard();
        gameInit();
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Inicializa un nuevo tablero con las dimensiones predefinidas, le asigna un fondo de color negro, inicializa el contador de juego e inicia la partida.}
     * <br><br><span class="alert">⛔🧪 No es necesario probar este método mediante pruebas unitarias.</span>
     */
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }



    private void gameInit() {

        this.aliens = new ArrayList<>();

        for (int i = 0; i < Commons.ALIEN_ROWS; i++) {
            for (int j = 0; j < Commons.ALIEN_COLUMNS; j++) {

                var alien = new Alien(Commons.ALIEN_INIT_X + Commons.ALIEN_SEPARATOR * j,
                        Commons.ALIEN_INIT_Y + Commons.ALIEN_SEPARATOR * i);
                this.aliens.add(alien);
            }
        }

        this.player = new Player();
        this.shot = new Shot();
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Genera gráficamente los aliens en la interfaz en las posiciones indicadas.}
     * Si el alien es disparado, ejecuta la acción correspondiente (explota y desaparece de la pantalla).
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     */
    private void drawAliens(Graphics g) {

        for (Alien alien : this.aliens) {

            if (alien.isVisible()) {

                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {

                alien.die();
            }
        }
    }

    /**
     * {@summary <span class="alert-small">⛔🧪 </span> Genera gráficamente el jugador en la interfaz en las posiciones indicadas.}
     * Si el jugador es disparado, el jugador muere y termina.
     * <br><br><span class="alert">⛔🧪 No es necesario probar este método mediante pruebas unitarias.</span>
     */
    private void drawPlayer(Graphics g) {

        if (this.player.isVisible()) {

            g.drawImage(this.player.getImage(), this.player.getX(), this.player.getY(), this);
        }

        if (this.player.isDying()) {

            this.player.die();
            inGame = false;
        }
    }

    /**
     * {@summary <span class="alert-small">⛔🧪 </span> Genera gráficamente los disparos en las posiciones indicadas.}
     * <br><br><span class="alert">⛔🧪 No es necesario probar este método mediante pruebas unitarias.</span>
     */
    private void drawShot(Graphics g) {

        if (this.shot.isVisible()) {

            g.drawImage(this.shot.getImage(), this.shot.getX(), this.shot.getY(), this);
        }
    }

    /**
     * {@summary <span class="alert-small">⛔🧪 </span> Genera gráficamente las explosiones de los aliens.}
     * <br><br><span class="alert">⛔🧪 No es necesario probar este método mediante pruebas unitarias.</span>
     */
    private void drawBombing(Graphics g) {

        for (Alien a : this.aliens) {

            Alien.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    /**
     * {@summary <span class="alert-small">⛔🧪 </span> Actualiza los componentes de la interfaz después de que se ejecute una acción.}
     * <br><br><span class="alert">⛔🧪 No es necesario probar este método mediante pruebas unitarias.</span>
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    /**
     * {@summary <span class="alert-small">⛔🧪 </span> Genera y coloca todos los elementos en la interfaz gráfica.}
     * <br><br><span class="alert">⛔🧪 No es necesario probar este método mediante pruebas unitarias.</span>
     */
    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {

            g.drawLine(0, Commons.GROUND,
                    Commons.BOARD_WIDTH, Commons.GROUND);

            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);

        } else {

            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * {@summary <span class="alert-small">⛔🧪 </span> Genera en la interfaz un mensaje indicando que se ha perdido la partida.}
     * <br><br><span class="alert">⛔🧪 No es necesario probar este método mediante pruebas unitarias.</span>
     */
    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
                Commons.BOARD_WIDTH / 2);
    }


    private void update() {

        if (deaths == Commons.NUMBER_OF_ALIENS_TO_DESTROY) {

            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // player
        this.player.act();
        update_shots();
        update_aliens();
        update_bomb();
    }


    private void update_shots() {
        if (this.shot.isVisible()) {

            int shotX = this.shot.getX();
            int shotY = this.shot.getY();

            for (Alien alien : this.aliens) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && this.shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + Commons.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + Commons.ALIEN_HEIGHT)) {

                        var ii = new ImageIcon(explImg);
                        alien.setImage(ii.getImage());
                        alien.setDying(true);
                        deaths++;
                        
                    }
                }
            }

            int y = this.shot.getY();
            y -= Commons.SHOT_SPEED;

            if (y < 0) {
                this.shot.die();
            } else {
                this.shot.setY(y);
                this.shot.setX(y);
            }
        }
    }

    public void update_aliens(){
        for (Alien alien : this.aliens) {

            int x = alien.getX();
            //@TODO EDIT5.1: modificada la condición del valor de "direction" a 1, en lugar de -1
            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction == 1) {

                direction = -1;

                Iterator<Alien> i1 = this.aliens.iterator();

                while (i1.hasNext()) {

                    Alien a2 = i1.next();
                    a2.setY(a2.getY() + Commons.GO_DOWN);
                }
            }

            if (x <= Commons.BORDER_LEFT && direction != 1) {

                direction = 1;

                Iterator<Alien> i2 = this.aliens.iterator();

                while (i2.hasNext()) {

                    Alien a = i2.next();
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }
        }

        Iterator<Alien> it = this.aliens.iterator();

        while (it.hasNext()) {

            Alien alien = it.next();

            if (alien.isVisible()) {

                int y = alien.getY();
                //TODO EDIT5.2: modificada el signo + por un - en la condición, para evitar sobrepasar el tablero
                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    //TODO EDIT6: modificada la asignación de binario "inGame" a "false" cuando se produce la invasión
                    inGame = false;
                    message = "Invasion!";
                }

                alien.act(direction);
            }
        }


    }


    public void update_bomb(){
        var generator = new Random();

        for (Alien alien : this.aliens) {

            int rand = generator.nextInt(15);
            Alien.Bomb bomb = alien.getBomb();

            if (rand != Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {

                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = this.player.getX();
            int playerY = this.player.getY();

            if (this.player.isVisible() && !bomb.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + Commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Commons.PLAYER_HEIGHT)) {

                    var ii = new ImageIcon(explImg);
                    this.player.setImage(ii.getImage());
                    this.player.setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {

                bomb.setY(bomb.getY() - Commons.BOMB_SPEED);

                if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) {

                    bomb.setDestroyed(true);
                }
            }
        }

    }

    /**
     * {@summary <span class="alert-small">⛔🧪 </span> Función relacionada con la gestión de la interfaz.}
     * <br><br><span class="alert">⛔🧪 No es necesario probar este método mediante pruebas unitarias.</span>
     */
    private void doGameCycle() {

        update();
        repaint();
    }

    /**
     * {@summary <span class="alert-small">⛔🧪 </span> Clase relacionada con la gestión de la interfaz.}
     * <br><br><span class="alert">⛔🧪 No es necesario probar este método mediante pruebas unitarias.</span>
     */
    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    /**
     * {@summary <span class="alert-small">⛔🧪 </span> Clase relacionada con la gestión de la interfaz.}
     * <br><br><span class="alert">⛔🧪 No es necesario probar este método mediante pruebas unitarias.</span>
     */
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (inGame) {

                    if (!shot.isVisible()) {

                        shot = new Shot(y, x);
                    }
                }
            }
        }
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Obtiene las dimensiones del tablero de juego.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @return las dimensiones del tablero
     */
    public Dimension getD() {
        return d;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Establece las dimensiones del tablero de juego.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @param d las nuevas dimensiones del tablero
     */
    public void setD(Dimension d) {
        this.d = d;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Establece la lista de aliens del tablero.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @param aliens la nueva lista de objetos Alien
     */
    public void setAliens(List<Alien> aliens) {
        this.aliens = aliens;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Establece el objeto jugador del tablero.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @param player el nuevo objeto Player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Establece el objeto disparo del tablero.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @param shot el nuevo objeto Shot
     */
    public void setShot(Shot shot) {
        this.shot = shot;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Obtiene la dirección actual del movimiento de los aliens.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @return la dirección actual (-1 para izquierda, 1 para derecha)
     */
    public int getDirection() {
        return direction;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Establece la dirección del movimiento de los aliens.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @param direction la nueva dirección (-1 para izquierda, 1 para derecha)
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Obtiene el número actual de aliens eliminados.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @return el contador actual de aliens destruidos
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Establece el contador de aliens eliminados.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @param deaths el nuevo valor del contador de aliens destruidos
     */
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Indica si el juego está actualmente en progreso.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @return true si el juego está activo, false si ha terminado
     */
    public boolean isInGame() {
        return inGame;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Establece el estado del juego.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @param inGame true para activar el juego, false para terminarlo
     */
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Obtiene la ruta de la imagen de explosión.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @return la ruta del archivo de imagen de explosión
     */
    public String getExplImg() {
        return explImg;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Establece la ruta de la imagen de explosión.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @param explImg la nueva ruta del archivo de imagen de explosión
     */
    public void setExplImg(String explImg) {
        this.explImg = explImg;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Obtiene el mensaje actual del juego.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @return el mensaje actual (ej: "Game Over", "Game won!", "Invasion!")
     */
    public String getMessage() {
        return message;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Establece el mensaje del juego.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @param message el nuevo mensaje del juego
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Obtiene el timer del ciclo de juego.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @return el objeto Timer que controla el ciclo del juego
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * {@summary <span class="alert-small">⛔🧪</span> Establece el timer del ciclo de juego.}
     * 
     * <br><br><span class="alert">⛔🧪 No es necesario probar el método mediante pruebas unitarias.</span>
     * @param timer el nuevo objeto Timer para controlar el ciclo del juego
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}


