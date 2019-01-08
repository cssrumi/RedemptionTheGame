package com.rtg.gamestate;

import com.rtg.gameobject.Player;
import com.rtg.main.GamePanel;
import com.rtg.status.DeadStatus;
import com.rtg.status.WinStatus;
import com.rtg.tilemap.Background;
import com.rtg.tilemap.Map;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Level1State extends GameStateAbstract implements Level {

    private Map map;
    private Background bg;
    private Player player;
    private int finishX;
    private long timer;
    private boolean win;
    private WinStatus winStatus;
    private DeadStatus deadStatus;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        map = new Map(30);
        map.loadTiles("/tilesets/tilemap.gif");
        map.loadMap("/maps/lvl1.map");
        map.setPosition(0, 0);
        finishX = map.getFinishX();

        bg = new Background("/background/bg1.gif");

        initPlayer();
        startTimer();
        win = false;

        winStatus = new WinStatus();
        deadStatus = new DeadStatus();
        deadStatus.setMessage("You are dead");
    }

    private void initPlayer() {
        player = new Player(map);
        player.setCenter();
        player.setAlive();
    }

    @Override
    public void update() {
        if (!win && !player.isDead()) {
            player.update();
            map.setPosition(
                    GamePanel.WIDTH / 2 - player.getX(),
                    GamePanel.HEIGHT / 2 - player.getY()
            );
            checkFinish();
            checkIfFallen();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        map.draw(g);
        player.draw(g);
        if (win) {
            winStatus.draw(g);
        }
        if (player.isDead()) {
            deadStatus.draw(g);
        }
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
            case KeyEvent.VK_ESCAPE:
                gsm.setState(GameStateManager.MENUSTATE);
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

    @Override
    public void checkFinish() {
        if ((player.getX() > finishX + 15) && (!win)) {
            winStatus.setMessage(getWinningMessage());
            win = true;
        }
    }

    @Override
    public void startTimer() {
        timer = System.currentTimeMillis();
    }

    @Override
    public long getDeltaTime() {
        long dt = System.currentTimeMillis() - timer;
        return dt;
    }

    @Override
    public void checkIfFallen() {
        if (player.getY() > GamePanel.HEIGHT - map.getTileSize() / 2) {
            player.died();
        }
    }

    public String getWinningMessage() {
        long dt = getDeltaTime();
        String message = String.format("You Win in %d sec, %d ms",
                TimeUnit.MILLISECONDS.toSeconds(dt),
                TimeUnit.MILLISECONDS.toMillis(dt) -
                        1000 * TimeUnit.MILLISECONDS.toSeconds(dt)
        );
        return message;
    }
}
