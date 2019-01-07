package com.rtg.gameobject;

import com.rtg.tilemap.TileMap2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends MapObject {

    private boolean dead;

    private ArrayList<BufferedImage[]> sprites;

    private PlayerStateEnum[] playerStates = PlayerStateEnum.values();
    private int currentState;

    public Player(TileMap2 tm) {
        super(tm);
        init();
    }

    private void init() {
        width = tileSize;
        height = tileSize;
        cwidth = 20;
        cheight = 20;
        facingRight = true;
        setSpeed();
    }

    private void setSpeed(){
        setSpeed(1);
    }

    private void setSpeed(int times){
        moveSpeed = 0.4 * times;
        maxSpeed = 2 * times;
        stopSpeed = 0.5 * times;
        fallSpeed = 0.2 * times;
        maxFallSpeed = 5.0 * times;
        jumpStart = -5.0 * times;
        stopJumpSpeed = 0.4 * times;
    }

    private void setSprites(String s){
        try{
            BufferedImage rawSprites = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );

            for (int i=0; i < playerStates.length; i++) {
                BufferedImage[] buffer = new BufferedImage[1];
                buffer[0].getSubimage(0 * tileSize, i * tileSize, tileSize, tileSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        getNextPosition();
        getCollision();
        setPosition(xtemp, ytemp);

        if(dy < 0) {

        }
    }

    private void getNextPosition() {

    }

    private void getCollision() {

    }

}
