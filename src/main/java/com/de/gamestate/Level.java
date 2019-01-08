package com.de.gamestate;

/**
 * Interface of Levels object
 */
public interface Level {
    public void checkFinish();
    public void startTimer();
    public long getDeltaTime();
    public void checkIfFallen();
    public void checkPills();
}
