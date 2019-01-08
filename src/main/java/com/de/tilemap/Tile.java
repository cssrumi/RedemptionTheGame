package com.de.tilemap;

import java.awt.image.BufferedImage;

/**
 * Class that store information about Tile
 */
public class Tile {

    private BufferedImage image;
    private int type;

    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;

    /**
     * Constructor of the class that store type and image of the tile
     * @param image tile image
     * @param type tile type
     */
    public Tile(BufferedImage image, int type) {
        this.image = image;
        this.type = type;
    }

    /**
     * Function that return tile image
     * @return image of the tile
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Function that return tile type
     * @return type of the tile
     */
    public int getType() {
        return type;
    }
}
