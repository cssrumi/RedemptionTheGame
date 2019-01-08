package com.rtg.main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BestTime {

    private static long bestTime;
    private static String file = "BestTime.txt";
    private static File scoreFile = new File(file);
    private static String bestTimeMessage;

    public BestTime() {
    }

    public static void readBestTime() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            bestTime = Long.parseLong(br.readLine());
        } catch (Exception e) {
            e.printStackTrace();
            bestTime = 60_000;
        }
        createBestTimeMessage();
    }

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

    public static void setBestTime(long newBestTime) {
        bestTime = newBestTime;
        createBestTimeMessage();
        saveBestTime();
    }

    public static long getBestTime() {
        readBestTime();
        return bestTime;
    }


    public static String getBestTimeFormatted() {
        createBestTimeMessage();
        return bestTimeMessage;
    }

    private static void createBestTimeMessage() {
        bestTimeMessage = String.format("Best time: %d sec, %d ms",
                TimeUnit.MILLISECONDS.toSeconds(bestTime),
                TimeUnit.MILLISECONDS.toMillis(bestTime) -
                        1000 * TimeUnit.MILLISECONDS.toSeconds(bestTime)
        );
    }
}
