package com.de.main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        gameInit();
    }

    /**
     * Function that initialize entire game
     */
    public static void gameInit() {
        JFrame window = new JFrame("Drug Effect - The Game");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.setSize(gamePanel.getSize());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }
}
