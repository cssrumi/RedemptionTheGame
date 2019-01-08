package com.de.tilemap;

import com.de.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class that store information about Map
 */
public class Map {

    private double x, y;

    private int xmin, ymin;
    private int xmax, ymax;

    private double tween;

    private int[][] map;
    private int tileSize;
    private int numRows,numCols;
    private int width, height;

    private BufferedImage tileset;
    private int numTilesAcross;
    private Tile[][] tiles;
    private int finishTileNumber;
    private ArrayList<Dim> pills;

    private int rowOffset, colOffset;
    private int numRowsToDraw, numColsToDraw;

    /**
     * Constructor of the class that set size of tiles
     * @param tileSize size of tiles
     */
    public Map(int tileSize) {
        this.tileSize = tileSize;
        numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
        numColsToDraw = GamePanel.WIDTH / tileSize + 2;
        tween = 0.07;
    }

    /**
     * Function that return x coordinate of the meta tile
     * @return x coordinate of the meta
     */
    public int getFinishX() {
        int x = finishTileNumber * tileSize - (tileSize / 15);
        return x;
    }


    /**
     * Function that load tiles from file
     * @param s path to file
     */
    public void loadTiles(String s) {
        try {
            pills = new ArrayList<>();
            tileset = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
            numTilesAcross = tileset.getWidth() / tileSize;
            tiles = new Tile[2][numTilesAcross];

            BufferedImage subimage;
            for (int col = 0; col < numTilesAcross; col++) {
                subimage = tileset.getSubimage(
                        col * tileSize,
                        0,
                        tileSize,
                        tileSize
                );
                tiles[0][col] = new Tile(subimage, Tile.NORMAL);
                subimage = tileset.getSubimage(
                        col * tileSize,
                        tileSize,
                        tileSize,
                        tileSize
                );
                tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that load map from file
     * @param s path to file
     */
    public void loadMap(String s) {

        try {
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );
            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            xmin = GamePanel.WIDTH - width;
            xmax = 0;
            ymin = GamePanel.HEIGHT - height;
            ymax = 0;

            for (int row = 0; row < numRows; row++) {
                String line = br.readLine();
                int type = 0;
                char test;
                for (int col = 0; col < numCols; col++) {
                    test = line.charAt(col);
                    switch (test) {
                        case '.':
                            type = 0;
                            break;
                        case '#':
                            type = 1;
                            break;
                        case '|':
                            type = 2;
                            finishTileNumber = col;
                            break;
                        case '%':
                            type = 3;
                            pills.add(new Dim(col,row,tileSize));
                            break;
                        default:
                            type = 0;
                            break;
                    }
                    map[row][col] = type;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that return size of tiles
     * @return size of tiles
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Function that return x coordinate
     * @return x coordinate
     */
    public double getX() {
        return (int) x;
    }

    /**
     * Function that return y coordinate
     * @return y coordinate
     */
    public double getY() {
        return (int) y;
    }

    /**
     * Function that return type of selected tile
     * @param row row of the map
     * @param col col of the map
     * @return type of the tile
     */
    public int getType(int row, int col) {
        int r, c;
        int type = map[row][col];
        switch (type) {
            case 0:
                r = 0;
                c = 0;
                break;
            case 1:
                r = 1;
                c = 0;
                break;
            case 2:
                r = 0;
                c = 1;
                break;
            case 3:
                r = 0;
                c = 2;
                break;
            default:
                r = 0;
                c = 0;
                break;
        }

        return tiles[r][c].getType();
    }

    /**
     * Function that set position of the map
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setPosition(double x, double y) {

        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;

        fixBounds();

        colOffset = (int) -this.x / tileSize;
        rowOffset = (int) -this.y / tileSize;
    }

    /**
     * Function that fix bounds
     */
    private void fixBounds() {
        if (x < xmin) x = xmin;
        if (y < ymin) y = ymin;
        if (x > xmax) x = xmax;
        if (y > ymax) y = ymax;

    }

    /**
     * Function that draw the map
     * @param g graphic
     */
    public void draw(Graphics2D g) {
        int r, c;

        for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
            if (row >= numRows) break;
            for (int col = colOffset; col < colOffset + numColsToDraw; col++) {
                if (col >= numCols) break;
                if (map[row][col] == 0) continue;

                int type = map[row][col];
                switch (type) {
                    case 0:
                        r = 0;
                        c = 0;
                        break;
                    case 1:
                        r = 1;
                        c = 0;
                        break;
                    case 2:
                        r = 0;
                        c = 1;
                        break;
                    case 3:
                        r = 0;
                        c = 2;
                        break;
                    default:
                        r = 0;
                        c = 0;
                        break;
                }

                g.drawImage(
                        tiles[r][c].getImage(),
                        (int) x + col * tileSize,
                        (int) y + row * tileSize,
                        null);
            }
        }
    }

    /**
     * Function that return all pills Array
     * @return pills Array
     */
    public ArrayList<Dim> getPills() {
        return pills;
    }
}
