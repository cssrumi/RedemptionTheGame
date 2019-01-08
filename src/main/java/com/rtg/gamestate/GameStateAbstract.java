package com.rtg.gamestate;

import java.awt.*;

public abstract class GameStateAbstract {

    protected GameStateManager gsm;

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics2D g);

    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);

}
