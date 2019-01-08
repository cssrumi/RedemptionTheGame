package com.rtg.status;

import com.rtg.main.BestTime;

import java.awt.*;

public class TimeStatus extends StatusAbstract {

    private long bestTime;
    private String bestTimeMessage;

    @Override
    public void init() {
        super.init();
        setFontSize(12);
        BestTime.readBestTime();
        bestTimeMessage = BestTime.getBestTimeFormatted();
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);
        this.message = "Current time: " + message;
    }

    public void setBestTime(long bestTime){
        this.bestTime = bestTime;
        BestTime.setBestTime(bestTime);
        bestTimeMessage = BestTime.getBestTimeFormatted();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.drawString(bestTimeMessage, getX(), getY() + fontSize);
    }
}
