package com.de.status;

import com.de.main.GamePanel;

import java.awt.*;

/**
 * Abstract class of status
 */
public abstract class StatusAbstract {

    protected String message;
    protected int x, y;
    protected Color color;
    protected Font font;
    protected int fontSize;

    /**
     * Default constructor of the class
     */
    public StatusAbstract() {
        init();
    }

    /**
     * Function that initialize class
     */
    public void init() {
        calculatePosition();
        color = Color.WHITE;
        fontSize = 24;
        font = new Font("Century Gothic", Font.PLAIN, fontSize);
    }

    /**
     * Function that calculate default position of the status
     */
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

    /**
     * Function that set position of the status
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Function that set message of the status
     * @param message new message
     */
    public void setMessage(String message) {
        this.message = message;
        calculatePosition();
    }

    /**
     * Function that draw status
     * @param g graphic
     */
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(message, getX(), getY());
    }

    /**
     * Function that set font size
     * @param fontSize font size
     */
    public void setFontSize(int fontSize) {
        font = new Font("Century Gothic", Font.PLAIN, fontSize);
    }

    /**
     * Function that get x
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Function that get y
     * @return y coordinate
     */
    public int getY() {
        return y;
    }
}
