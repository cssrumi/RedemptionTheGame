package com.rtg.gameobject;

import com.rtg.tilemap.Tile;
import com.rtg.tilemap.Map;

public abstract class GameObject {

    protected Map map;
    protected int tileSize;
    protected double xmap, ymap;

    protected double x, y;
    protected double dx, dy;
    protected double xDest, yDest;
    protected double xTemp, yTemp;
    protected int width, height;
    protected int cWidth, cHeight;
    protected int currRow, currCol;

    protected boolean topLeft, topRight, bottomLeft, bottomRight;
    protected boolean facingRight;
    protected boolean left, right, up, down, jumping, falling;
    protected double moveSpeed, maxSpeed, stopSpeed, fallSpeed, maxFallSpeed, stopJumpSpeed;
    protected double jumpStart;


    public GameObject(Map map) {
        this.map = map;
        tileSize = map.getTileSize();
    }

    public void calculateCorners(double x, double y) {

        int leftTile = (int) (x - cWidth / 2) / tileSize;
        int rightTile = (int) (x + cWidth / 2 - 1) / tileSize;
        int topTile = (int) (y - cHeight / 2) / tileSize;
        int bottomTile = (int) (y + cHeight / 2 - 1) / tileSize;

        int tl = map.getType(topTile, leftTile);
        int tr = map.getType(topTile, rightTile);
        int bl = map.getType(bottomTile, leftTile);
        int br = map.getType(bottomTile, rightTile);

        topLeft = tl == Tile.BLOCKED;
        topRight = tr == Tile.BLOCKED;
        bottomLeft = bl == Tile.BLOCKED;
        bottomRight = br == Tile.BLOCKED;

    }

    public void checkTileMapCollision() {

        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;

        xDest = x + dx;
        yDest = y + dy;

        xTemp = x;
        yTemp = y;

        calculateCorners(x, yDest);
        if (dy < 0) {
            if (topLeft || topRight) {
                dy = 0;
                yTemp = currRow * tileSize + cHeight / 2;
            } else {
                yTemp += dy;
            }
        }
        if (dy > 0) {
            if (bottomLeft || bottomRight) {
                dy = 0;
                falling = false;
                yTemp = (currRow + 1) * tileSize - cHeight / 2;
            } else {
                yTemp += dy;
            }
        }

        calculateCorners(xDest, y);
        if (dx < 0) {
            if (topLeft || bottomLeft) {
                dx = 0;
                xTemp = currCol * tileSize + cWidth / 2;
            } else {
                xTemp += dx;
            }
        }
        if (dx > 0) {
            if (topRight || bottomRight) {
                dx = 0;
                xTemp = (currCol + 1) * tileSize - cWidth / 2;
            } else {
                xTemp += dx;
            }
        }

        if (!falling) {
            calculateCorners(x, yDest + 1);
            if (!bottomLeft && !bottomRight) {
                falling = true;
            }
        }

    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setMapPosition() {
        xmap = map.getX();
        ymap = map.getY();
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setDown(boolean b) {
        down = b;
    }

    public void setJumping(boolean b) {
        jumping = b;
    }

}
