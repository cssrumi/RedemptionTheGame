package com.de.main;

import com.de.gamestate.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Class uses to store window information
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private BufferedImage image;
    private Graphics2D g;

    private GameStateManager gsm;

    /**
     * Constructor of the class
     */
    public GamePanel() {
        super();
        setSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    /**
     * Function that create and start main thread if empty
     */
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    /**
     * Function that initialize Game Panel
     */
    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        running = true;

        gsm = new GameStateManager();
    }

    /**
     * Function that run the entire game
     */
    public void run() {
        init();

        long start;
        long elapsed;
        long wait;

        //gameloop
        while (running) {

            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;
            if (wait < 0)
                wait = 5;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Function that update current game state
     */
    private void update() {
        gsm.update();
    }

    /**
     * Function that draw current game state
     */
    private void draw() {
        gsm.draw(g);
    }

    /**
     * Function that draw entire game
     */
    public void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key) {
    }

    /**
     * Function that perform current state action while key is pressed
     * @param key key id
     */
    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }

    /**
     * Function that perform current state action when key is released
     * @param key key id
     */
    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }

    /**
     * Function that return center dimension of the screen
     * @return center dimension
     */
    public static Dimension getCenter() {
        Dimension dim = new Dimension(WIDTH / 2, HEIGHT / 2);
        return dim;
    }
}
