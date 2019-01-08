package com.de.gameobject;

/**
 * Enumerate of player states
 */
public enum PlayerStateEnum {

    IDLE(0),
    RUN(1),
    JUMPING(2),
    FALLING(3);

    private int id;

    /**
     * Constructor of player state
     * @param id state id
     */
    PlayerStateEnum(int id) {
        this.id = id;
    }


    /**
     * Function that return id of player state
     * @return state id
     */
    public int getId() {
        return id;
    }

}
