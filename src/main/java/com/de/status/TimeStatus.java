package com.de.status;

import com.de.main.BestTime;

import java.awt.*;

/**
 * Class that store time status
 */
public class TimeStatus extends StatusAbstract {

    private long bestTime;
    private String bestTimeMessage;

    /**
     * Function that initialize time status and read best time
     */
    @Override
    public void init() {
        super.init();
        setFontSize(12);
        BestTime.readBestTime();
        bestTimeMessage = BestTime.getBestTimeFormatted();
    }

    /**
     * Function that set new message and format it
     * @param message new message
     */
    @Override
    public void setMessage(String message) {
        super.setMessage(message);
        this.message = "Current time: " + message;
    }

    /**
     * Function that set new best time
     * @param bestTime new best time in ms
     */
    public void setBestTime(long bestTime){
        this.bestTime = bestTime;
        BestTime.setBestTime(bestTime);
        bestTimeMessage = BestTime.getBestTimeFormatted();
    }

    /**
     * Function that draw current and best time
     * @param g graphic
     */
    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.drawString(bestTimeMessage, getX(), getY() + fontSize);
    }
}
