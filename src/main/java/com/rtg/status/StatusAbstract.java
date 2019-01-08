package com.rtg.status;

import com.rtg.main.GamePanel;

import java.awt.*;

public abstract class StatusAbstract {

    private String message;
    private int x, y;
    protected Color color;
    private Font font;
    private int fontSize;

    public StatusAbstract() {
        init();
    }

    public void init() {
        calculatePosition();
        color = Color.WHITE;
        fontSize = 24;
        font = new Font("Century Gothic", Font.PLAIN, fontSize);
    }

    public void calculatePosition() {
        Dimension center = GamePanel.getCenter();
        if (message == null) {
            setPosition(
                    center.width,
                    center.height - center.height / 2
            );
        } else {
            setPosition(
                    center.width - message.length() * fontSize / 4,
                    center.height - center.height / 2
            );
        }

    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setMessage(String message) {
        this.message = message;
        calculatePosition();
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(message, x, y);
    }

}
