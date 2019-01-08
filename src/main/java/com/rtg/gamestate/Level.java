package com.rtg.gamestate;

import java.util.concurrent.TimeUnit;

public interface Level {
    public void checkFinish();
    public void startTimer();
    public long getDeltaTime();
    public void checkIfFallen();
}
