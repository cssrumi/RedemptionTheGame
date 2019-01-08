package com.rtg.gamestate;

import com.rtg.tilemap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

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


    @Override
    public void init() {

    }

    @Override
    public void update() {
        bg.update();
    }

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
