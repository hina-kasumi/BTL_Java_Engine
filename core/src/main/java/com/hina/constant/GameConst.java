package com.hina.constant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
    public static boolean CHEAT = false;
    private static final String DOMAIN;
    private static final boolean IS_DEBUG;

    static {
        Map<String, String> map = getFromInit();
        try {
            DOMAIN = get(map, "domain", "http://localhost:8080");
            IS_DEBUG = Boolean.parseBoolean(get(map, "debug", "false"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDOMAIN() {
        System.out.println(DOMAIN);
        return DOMAIN;
    }

    public static boolean isDEBUG() {
        return IS_DEBUG;
    }

    private static String get(Map<String, String> map, String key, String defaultVal) throws IOException {
        String val = map.get(key);
        if (val == null) {
            putToFile(key, defaultVal);
            return defaultVal;
        }
        return val;
    }

    private static void putToFile(String key, String val) throws IOException {
        String line = key + "=" + val;
        FileWriter fileWriter = new FileWriter(INIT_FILE, true);
        fileWriter.write(line + "\n");
        fileWriter.close();
    }

    private static Map<String, String> getFromInit() {
        File file = new File(INIT_FILE);
        Map<String, String> map = new HashMap<>();
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                }
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("=");
                map.put(line[0].trim(), line[1].trim());
            }
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return map;
    }
}
