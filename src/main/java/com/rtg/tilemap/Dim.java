package com.rtg.tilemap;

public class Dim {
    private int x, y;
    private int col, row, tileSize;

    public Dim(int col, int row, int tileSize) {
        this.tileSize = tileSize;
        this.col = col;
        this.row = row;
        calculateXY();
    }

    private void calculateXY() {
        x = col * tileSize + tileSize / 2;
        y = row * tileSize + tileSize / 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
