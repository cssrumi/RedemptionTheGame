package com.de.gameobject;

import com.de.main.GamePanel;
import com.de.tilemap.Map;


import java.awt.*;

/**
 * Player class uses to store information about player
 */
public class Player extends GameObject {

    private boolean dead;
    private PlayerStateManager psm;


    /**
     * Constructor of Player object
     * @param map references to Map object
     */
    public Player(Map map) {
        super(map);
        init();
    }

    /**
     * Function that set position to center of the game window
     */
    public void setCenter() {
        Dimension dim = GamePanel.getCenter();
        setPosition(dim.width, dim.height);
    }

    /**
     * Function that initialize class object
     */
    private void init() {
        dead = false;
        width = tileSize;
        height = tileSize;
        cWidth = 20;
        cHeight = 20;
        facingRight = true;
        setSpeed();
        try {
            psm = new PlayerStateManager(tileSize);
            psm.setState(PlayerStateEnum.IDLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that set speed to default values
     */
    private void setSpeed() {
        setSpeed(1.0);
    }

    /**
     * Function that set modified speed
     * @param times modifier of player speed
     */
    private void setSpeed(double times) {
        moveSpeed = 0.4 * times;
        maxSpeed = 2 * times;
        stopSpeed = 0.5 * times;
        fallSpeed = 0.2 * times;
        maxFallSpeed = 5.0 * times;
        jumpStart = -5.0 * times;
        stopJumpSpeed = 0.4 * times;
    }


    /**
     * Function that update information about player
     */
    public void update() {
        getNextPosition();
        checkTileMapCollision();
        setPosition(xTemp, yTemp);

        if (dy > 0) {
            psm.setState(PlayerStateEnum.FALLING);
        } else if (dy < 0) {
            psm.setState(PlayerStateEnum.JUMPING);
        } else if (left || right) {
            psm.setState(PlayerStateEnum.RUN);
        } else psm.setState(PlayerStateEnum.IDLE);
    }

    /**
     * Function that draw player
     * @param g graphic
     */
    public void draw(Graphics2D g) {

        setMapPosition();
        g.drawImage(
                psm.getImage(),
                (int) (x + xmap - width / 2),
                (int) (y + ymap - height / 2),
                null
        );

    }

    /**
     * Funtion that will get next position of the player
     */
    private void getNextPosition() {
        if (left) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) dx = -maxSpeed;
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) dx = maxSpeed;
        } else {
            if (dx > 0) {
                dx -= stopSpeed;
                if (dx < 0) dx = 0;
            } else if (dx < 0) {
                dx += stopSpeed;
                if (dx > 0) dx = 0;
            }
        }
        if (jumping && !falling) {
            dy = jumpStart;
            falling = true;
        }
        if (falling) {
            dy += fallSpeed;
            if (dy > 0) jumping = false;
            if (dy < 0 && !jumping) dy += stopJumpSpeed;
            if (dy > maxFallSpeed) dy = maxFallSpeed;
        }
    }

    /**
     * Function that return true if player is dead
     * @return dead value
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Function that revive player
     */
    public void setAlive() {
        dead = false;
    }

    /**
     * Function that set dead value to true
     */
    public void died() {
        dead = true;
    }

}
