package com.de.gamestate;

import com.de.gameobject.Player;
import com.de.main.BestTime;
import com.de.main.GamePanel;
import com.de.status.DeadStatus;
import com.de.status.TimeStatus;
import com.de.status.WinStatus;
import com.de.tilemap.Background;
import com.de.tilemap.Dim;
import com.de.tilemap.Map;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Class uses to manage and store information about Level 1
 */
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

    /**
     * Constructor of the class
     * @param gsm reference to GameStateManager
     */
    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    /**
     * Function that initialize class
     */
    @Override
    public void init() {
        initMap();

        initPlayer();
        startTimer();
        win = false;

        initStatus();
    }

    /**
     * Function that initialize status
     */
    private void initStatus() {
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

    /**
     * Function that initialize map
     */
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

    /**
     * Function that initialize player
     */
    private void initPlayer() {
        player = new Player(map);
        player.setCenter();
        player.setAlive();
    }

    /**
     * Function that update game state
     */
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

    /**
     * Function that draw level
     * @param g graphic
     */
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

    /**
     * Function that perform action while key is pressed
     * @param k key id
     */
    @Override
    public void keyPressed(int k) {
        switch (k) {
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

    /**
     * Function that perform action when key is released
     * @param k key id
     */
    @Override
    public void keyReleased(int k) {
        switch (k) {
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

    /**
     * Function that check if player win
     */
    @Override
    public void checkFinish() {
        if ((player.getX() > finishX + 15) && (!win)) {
            winStatus.setMessage(getWinningMessage());
            win = true;
            long dt = getDeltaTime();
            if(BestTime.getBestTime() > dt) timeStatus.setBestTime(dt);
        }
    }

    /**
     * Function that start timer
     */
    @Override
    public void startTimer() {
        timer = System.currentTimeMillis();
    }

    /**
     * Function that return time in ms
     * @return time in ms
     */
    @Override
    public long getDeltaTime() {
        long dt = System.currentTimeMillis() - timer;
        return dt;
    }

    /**
     * Function that check if player fall down
     */
    @Override
    public void checkIfFallen() {
        if (player.getY() > GamePanel.HEIGHT - tileSize / 2) {
            player.died();
        }
    }

    /**
     * Function that check if player touch pill
     */
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
                bg.setImage("/background/pillbg.gif");
                player.died();
            }
        }
    }

    /**
     * Function that return winning message
     * @return message
     */
    public String getWinningMessage() {
        String message = "You win in " + getDeltaTimeString();
        return message;
    }

    /**
     * Function that return formatted delta time
     * @return formatted delta time
     */
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
