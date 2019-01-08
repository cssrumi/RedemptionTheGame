package com.de.status;

import java.awt.*;

/**
 * Class that store win status
 */
public class WinStatus extends StatusAbstract {

    /**
     * Function that initialize class and set color
     */
    @Override
    public void init() {
        super.init();
        color = Color.CYAN;
    }
}
