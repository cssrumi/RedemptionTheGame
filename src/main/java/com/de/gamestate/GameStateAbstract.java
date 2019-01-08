package com.de.gamestate;

import java.awt.*;

/**
 * Abstract of GameState class
 */
public abstract class GameStateAbstract {

    protected GameStateManager gsm;

    /**
     * Function that initialize class
     */
    public abstract void init();

    /**
     * Function that update components
     */
    public abstract void update();

    /**
     * Function that draw current state
     * @param g graphic
     */
    public abstract void draw(Graphics2D g);

    /**
     * Function that perform action while key is pressed
     * @param k key id
     */
    public abstract void keyPressed(int k);

    /**
     * Function that perform action when key is released
     * @param k key id
     */
    public abstract void keyReleased(int k);

}
