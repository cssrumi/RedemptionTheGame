package com.rtg.main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        gameInit();
    }

    public static void gameInit() {
        JFrame window = new JFrame("Redemption The Game");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.setSize(gamePanel.getSize());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }
}
