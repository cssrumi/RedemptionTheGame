package com.rtg.gameobject;

import com.rtg.main.GamePanel;
import com.rtg.tilemap.Map;


import java.awt.*;

public class Player extends GameObject {

    private boolean dead;
    private PlayerStateManager psm;


    public Player(Map tm) {
        super(tm);
        init();
    }

    public void setCenter() {
        Dimension dim = GamePanel.getCenter();
        setPosition(dim.width, dim.height);
    }

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

    private void setSpeed() {
        setSpeed(1.0);
    }

    private void setSpeed(double times) {
        moveSpeed = 0.4 * times;
        maxSpeed = 2 * times;
        stopSpeed = 0.5 * times;
        fallSpeed = 0.2 * times;
        maxFallSpeed = 5.0 * times;
        jumpStart = -5.0 * times;
        stopJumpSpeed = 0.4 * times;
    }


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

    public void draw(Graphics2D g) {

        setMapPosition();
        g.drawImage(
                psm.getImage(),
                (int) (x + xmap - width / 2),
                (int) (y + ymap - height / 2),
                null
        );

    }

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

    public boolean isDead() {
        return dead;
    }

    public void setAlive() {
        dead = false;
    }

    public void died() {
        dead = true;
    }

}
