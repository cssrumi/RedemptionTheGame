package com.de.gameobject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Class uses to manage player states
 */
public class PlayerStateManager {

    private int tileSize;
    private BufferedImage[] playerTiles;

    private PlayerStateEnum[] playerStates = PlayerStateEnum.values();
    private PlayerStateEnum currentState;


    /**
     * Constructor of PlayerStateManager class
     * @param tileSize size of map tile
     */
    public PlayerStateManager(int tileSize) {
        this.tileSize = tileSize;
        init();
    }

    /**
     * Function that initialize class
     */
    public void init() {
        try {
            loadSprites("/player/player.gif");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that set new state
     * @param state new state
     */
    public void setState(PlayerStateEnum state) {
        currentState = state;

    }

    /**
     * Function that return current player state
     * @return current player state
     */
    public PlayerStateEnum getCurrentState() {
        return currentState;
    }

    /**
     * Function that load sprites from path
     * @param s path to resource
     */
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

    /**
     * Function that return current player image
     * @return player image
     */
    public BufferedImage getImage() {
        return playerTiles[currentState.getId()];
    }
}
