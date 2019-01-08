package com.de.gamestate;

import com.de.tilemap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Class uses to manage menu state
 */
public class MenuState extends GameStateAbstract {

    private Background bg;

    private int selectNumber = 0;
    private String[] options = {
            "Start",
            "Quit"
    };

    private Color titleColor;
    private Font titleFont;

    private Font font;

    /**
     * Constructor of the class
     * @param gsm reference to GameStateManager
     */
    public MenuState(GameStateManager gsm) {

        this.gsm = gsm;

        try {

            bg = new Background("/background/menubg.gif");
            bg.setDelta(-0.1, 0);

            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);
            font = new Font("Arial", Font.PLAIN, 16);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Function that initialize class
     */
    @Override
    public void init() {

    }

    /**
     * Function that update menu
     */
    @Override
    public void update() {
        bg.update();
    }

    /**
     * Fuction that draw menu
     * @param g graphic
     */
    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);

        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Drug effect", 80, 70);

        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == selectNumber)
                g.setColor(Color.BLACK);
            else
                g.setColor(Color.RED);
            g.drawString(options[i], 145, 140 + i * 15);
        }

    }

    /**
     * Function that select new game state
     */
    private void select() {
        switch (selectNumber) {
            case 0:
                gsm.setState(GameStateManager.LEVEL1STATE);
                break;
            case 1:
                System.exit(0);
                break;
        }
    }

    /**
     * Function that set state to selected while key is pressed
     * @param k key id
     */
    @Override
    public void keyPressed(int k) {
        switch (k) {
            case KeyEvent.VK_ENTER:
                select();
                break;
            case KeyEvent.VK_UP:
                if (selectNumber > 0) selectNumber--;
                break;
            case KeyEvent.VK_DOWN:
                if (selectNumber < options.length - 1) selectNumber++;
                break;
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
