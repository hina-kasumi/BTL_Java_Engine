package com.hina.constant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GameConst {
    public static final float PPM = 100f;
    public static final float MAP_SCALE = 4f;
    public static final String GROUND_TAG = "ground";
    //    public static final String FLY_GROUND_TAG = "fly-ground";
    public static final String DEATH_ZONE_TAG = "death-zone";
    public static final String WIN_ZONE_TAG = "win-zone";
    public static final String FILE_AUTH_INFO = "assets/files/auth.txt";
    public static final String FILE_COIN = "assets/files/coin.txt";
    public static final String INIT_FILE = "assets/files/config.init";
    private static String DOMAIN;
    public static boolean CHEAT = false;

    static {
        File file = new File(INIT_FILE);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                }
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("DOMAIN".toLowerCase())) {
                    DOMAIN = Arrays.stream(line.split("=")).toList().getLast().trim();
                    break;
                }
            }
            scanner.close();
            if (DOMAIN == null) {
                FileWriter fileWriter = new FileWriter(INIT_FILE, true);
                DOMAIN = "http://localhost:8080";
                fileWriter.write("domain = " + DOMAIN);
                fileWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String getDOMAIN() {
        System.out.println(DOMAIN);
        return DOMAIN;
    }
}
