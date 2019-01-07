package com.rtg.gameobject;

import com.rtg.main.GamePanel;
import com.rtg.tilemap.TileMap2;


import java.awt.*;

public class Player extends MapObject {

    private boolean dead;
    private PlayerStateManager psm;


    public Player(TileMap2 tm) {
        super(tm);
        init();
    }

    public void setCenter(){
        Dimension dim = GamePanel.getCenter();
        setPosition(dim.width, dim.height);
    }

    private void init() {
        width = tileSize;
        height = tileSize;
        cwidth = 20;
        cheight = 20;
        facingRight = true;
        setSpeed();
//        setPosition(100, 100);
        try {
            psm = new PlayerStateManager(tileSize);
            psm.setState(PlayerStateEnum.IDLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSpeed() {
        setSpeed(0.5);
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
        setPosition(xtemp, ytemp);

        if (dy > 0) {
            psm.setState(PlayerStateEnum.FALLING);
        } else if (dy < 0) {
            psm.setState(PlayerStateEnum.JUMPING);
        } else if (left || right) {
            psm.setState(PlayerStateEnum.RUN);
        } else psm.setState(PlayerStateEnum.IDLE);
    }

    public void draw(Graphics2D g) {
        //26.12
        //34.35
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
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = moveSpeed;
            }
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

    private void getCollision() {

    }

}
