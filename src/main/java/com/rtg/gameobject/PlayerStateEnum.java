package com.rtg.gameobject;

public enum PlayerStateEnum {

    IDLE(0),
    RUN(1),
    JUMPING(2),
    FALLING(3);

    private int id;

    PlayerStateEnum(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

}
