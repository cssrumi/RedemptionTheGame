package com.de.tilemap;

/**
 * Class that store information about tile dimensions
 */
public class Dim {
    private int x, y;
    private int col, row, tileSize;

    /**
     * Constructor of the class
     * @param col col number
     * @param row row number
     * @param tileSize size of the tiles
     */
    public Dim(int col, int row, int tileSize) {
        this.tileSize = tileSize;
        this.col = col;
        this.row = row;
        calculateXY();
    }

    /**
     * Function that calculate x and y
     */
    private void calculateXY() {
        x = col * tileSize + tileSize / 2;
        y = row * tileSize + tileSize / 2;
    }

    /**
     * Function that return x
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Function that return y
     * @return y coordinate
     */
    public int getY() {
        return y;
    }
}
