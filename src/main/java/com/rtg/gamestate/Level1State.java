package com.rtg.gamestate;

import com.rtg.gameobject.Player;
import com.rtg.main.BestTime;
import com.rtg.main.GamePanel;
import com.rtg.status.DeadStatus;
import com.rtg.status.TimeStatus;
import com.rtg.status.WinStatus;
import com.rtg.tilemap.Background;
import com.rtg.tilemap.Dim;
import com.rtg.tilemap.Map;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
    private TimeStatus timeStatus;
    private ArrayList<Dim> pills;
    private int tileSize;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        initMap();

        initPlayer();
        startTimer();
        win = false;

        winStatus = new WinStatus();
        deadStatus = new DeadStatus();
        deadStatus.setMessage("You are dead");
        timeStatus = new TimeStatus();
        timeStatus.setMessage(getDeltaTimeString());
        timeStatus.setPosition(
                0,
                0
        );
    }

    private void initMap() {
        map = new Map(30);
        map.loadTiles("/tilesets/tilemap.gif");
        map.loadMap("/maps/lvl1.map");
        map.setPosition(0, 0);
        finishX = map.getFinishX();

        tileSize = map.getTileSize();

        bg = new Background("/background/bg1.gif");

        pills = map.getPills();
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
            checkPills();
            timeStatus.setMessage(getDeltaTimeString());
        }
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        map.draw(g);
        player.draw(g);
        if (win) {
            winStatus.draw(g);
        } else if (player.isDead()) {
            deadStatus.draw(g);
        } else {
            timeStatus.draw(g);
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
            long dt = getDeltaTime();
            if(BestTime.getBestTime() > dt) timeStatus.setBestTime(dt);
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
        if (player.getY() > GamePanel.HEIGHT - tileSize / 2) {
            player.died();
        }
    }

    @Override
    public void checkPills() {
        int px = player.getX();
        int py = player.getY();
        for (Dim pill : pills) {
            if (
                    (pill.getX() > px - tileSize / 2) &&
                    (pill.getX() < px + tileSize / 2) &&
                    (pill.getY() > py - tileSize / 2) &&
                    (pill.getY() < py + tileSize / 2)
            ) {
                player.died();
            }
        }
    }

    public String getWinningMessage() {
        String message = "You win in " + getDeltaTimeString();
        return message;
    }

    public String getDeltaTimeString(){
        long dt = getDeltaTime();
        String message = String.format("%d sec, %d ms",
                TimeUnit.MILLISECONDS.toSeconds(dt),
                TimeUnit.MILLISECONDS.toMillis(dt) -
                        1000 * TimeUnit.MILLISECONDS.toSeconds(dt)
        );
        return message;
    }
}
