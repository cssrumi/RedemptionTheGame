package com.rtg.gamestate;

import com.rtg.gameobject.Player;
import com.rtg.tilemap.Background;
import com.rtg.tilemap.TileMap2;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {

    private TileMap2 tileMap;
    private Background bg;
    private Player player;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap2(30);
        tileMap.loadTiles("/tilesets/tiles.gif");
        tileMap.loadMap("/maps/lvl1.map");
        tileMap.setPosition(0, 0);

        bg = new Background("/background/bg1.gif", 0.1);

        player = new Player(tileMap);
        player.setCenter();
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void draw(Graphics2D g) {

        bg.draw(g);

        tileMap.draw(g);
        player.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        switch (k) {
            case KeyEvent.VK_DOWN:
                player.setDown(true);
                break;
            case KeyEvent.VK_UP:
                player.setUp(true);
                break;
            case KeyEvent.VK_LEFT:
                player.setLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJumping(true);
                break;
        }

    }

    @Override
    public void keyReleased(int k) {
        switch (k) {
            case KeyEvent.VK_DOWN:
                player.setDown(false);
                break;
            case KeyEvent.VK_UP:
                player.setUp(false);
                break;
            case KeyEvent.VK_LEFT:
                player.setLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJumping(false);
                break;
        }

    }
}
