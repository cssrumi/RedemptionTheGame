package com.rtg.tilemap;

import com.rtg.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {

    private BufferedImage image;

    private double x;
    private double y;
    private double dx;
    private double dy;

    public Background(String s) {
        setImage(s);
    }

    public void setImage(String s){
        try {
            image = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDelta(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
        if(x > GamePanel.WIDTH) x = 0;
        if(x < -GamePanel.WIDTH) x = 0;
    }

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
