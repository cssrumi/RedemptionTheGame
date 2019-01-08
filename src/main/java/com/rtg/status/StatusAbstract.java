package com.rtg.status;

import com.rtg.main.GamePanel;

import java.awt.*;

public abstract class StatusAbstract {

    protected String message;
    protected int x, y;
    protected Color color;
    protected Font font;
    protected int fontSize;

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
        g.drawString(message, getX(), getY());
    }

    public void setFontSize(int fontSize) {
        font = new Font("Century Gothic", Font.PLAIN, fontSize);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
