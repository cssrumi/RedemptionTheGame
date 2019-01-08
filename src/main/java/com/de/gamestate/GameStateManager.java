package com.de.gamestate;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class that manage game states
 */
public class GameStateManager {

    private ArrayList<GameStateAbstract> gameStates;
    private int currentState;

    protected static final int MENUSTATE = 0;
    protected static final int LEVEL1STATE = 1;

    /**
     * Constructor of GameStateManager class
     */
    public GameStateManager() {
        gameStates = new ArrayList<>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Level1State(this));

    }

    /**
     * Function that set current state
     * @param state new state
     */
    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    /**
     * Function that update current state
     */
    public void update() {
        gameStates.get(currentState).update();
    }

    /**
     * Function that draw current state
     * @param g graphic
     */
    public void draw(Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    /**
     * Function that perform action of current state while key is pressed
     * @param k key id
     */
    public void keyPressed(int k) {
        gameStates.get(currentState).keyPressed(k);
    }

    /**
     * Function that perform action of current state while key is pressed
     * @param k key id
     */
    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }

}


