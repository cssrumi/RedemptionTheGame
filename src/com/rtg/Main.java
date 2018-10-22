package com.rtg;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame("Odkupienie");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }
}
