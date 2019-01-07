package com.rtg.gameobject;

import com.rtg.tilemap.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerStateManager {

    private int tileSize;
    private BufferedImage[] playerTiles;

    private PlayerStateEnum[] playerStates = PlayerStateEnum.values();
    private PlayerStateEnum currentState;


    public PlayerStateManager(int tileSize) {
        this.tileSize = tileSize;
        init();
    }

    public void init() {
        try {
            loadSprites("/player/player.gif");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setState(PlayerStateEnum state) {
        currentState = state;

    }

    public PlayerStateEnum getCurrentState() {
        return currentState;
    }

    public int getStateId() {
        return currentState.getId();
    }

    public void loadSprites(String s) {
        try {
            BufferedImage rawSprites = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
            playerTiles = new BufferedImage[playerStates.length];
            BufferedImage subimage;
            for (int i = 0; i < playerStates.length; i++) {
                subimage = rawSprites.getSubimage(0 * tileSize, i * tileSize, tileSize, tileSize);
                playerTiles[i] = subimage;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return playerTiles[currentState.getId()];
    }
}
