package com.de.main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * Static class uses to read/save best time from file
 */
public class BestTime {

    private static long bestTime;
    private static String file = "BestTime.txt";
    private static File scoreFile = new File(file);
    private static String bestTimeMessage;

    public BestTime() {
    }

    /**
     * Function that read best time from file
     * If file not exist then set default best time
     */
    public static void readBestTime() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            bestTime = Long.parseLong(br.readLine());
        } catch (Exception e) {
//            e.printStackTrace();
            bestTime = 60_000;
        }
        createBestTimeMessage();
    }

    /**
     * Function that save best time
     */
    public static void saveBestTime() {
        if(!scoreFile.exists()) {
            try {
                scoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file))){
                writer.write(String.valueOf(bestTime));
            } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Function that set new best time
     * @param newBestTime new best time
     */
    public static void setBestTime(long newBestTime) {
        bestTime = newBestTime;
        createBestTimeMessage();
        saveBestTime();
    }

    /**
     * Function that get best time
     * @return best time in ms
     */
    public static long getBestTime() {
        readBestTime();
        return bestTime;
    }


    /**
     * Function that return formatted best time
     * @return formatted best time
     */
    public static String getBestTimeFormatted() {
        createBestTimeMessage();
        return bestTimeMessage;
    }

    /**
     * Function that create best time message
     */
    private static void createBestTimeMessage() {
        bestTimeMessage = String.format("Best time: %d sec, %d ms",
                TimeUnit.MILLISECONDS.toSeconds(bestTime),
                TimeUnit.MILLISECONDS.toMillis(bestTime) -
                        1000 * TimeUnit.MILLISECONDS.toSeconds(bestTime)
        );
    }
}
