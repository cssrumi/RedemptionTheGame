package com.de.tilemap;

import com.de.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class uses to store information about background
 */
public class Background {

    private BufferedImage image;

    private double x;
    private double y;
    private double dx;
    private double dy;

    /**
     * Constructor of the class
     * @param s path to image
     */
    public Background(String s) {
        setImage(s);
    }

    /**
     * Function that set background image
     * @param s path to image
     */
    public void setImage(String s){
        try {
            image = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that set deltas
     * @param dx delta x
     * @param dy delta y
     */
    public void setDelta(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Function that update background state
     */
    public void update() {
        x += dx;
        y += dy;
        if(x > GamePanel.WIDTH) x = 0;
        if(x < -GamePanel.WIDTH) x = 0;
    }

    /**
     * Function that draw background
     * @param g graphic
     */
    public void draw(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, null);
        if (x < 0) {
            g.drawImage(
                    image,
                    (int) x + GamePanel.WIDTH,
                    (int) y,
                    null
            );
        }
        if (x >= 0) {
            g.drawImage(
                    image,
                    (int) x - GamePanel.WIDTH,
                    (int) y,
                    null
            );
        }
    }

}
