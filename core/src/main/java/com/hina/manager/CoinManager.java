package com.hina.manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.hina.constant.GameConst.FILE_COIN;

public class CoinManager {
    private static int coin;

    static {
        try {
            File file = new File(FILE_COIN);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Could not create file " + FILE_COIN);
                }
            }
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextInt()) {
                coin = scanner.nextInt();
            } else {
                scanner.close();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                fw.write("0");
                fw.close();
                coin = 0;
            }
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
            coin = 0;
        }
    }

    public CoinManager() {
    }

    public static int getCoin() {
        return coin;
    }

    public static void upCoin(int dCoin) {
        coin += dCoin;
    }

    public static void downCoin(int dCoin) {
        if (coin >= dCoin) {
            coin -= dCoin;
        } else {
            System.out.println("không đủ coin");
        }
    }
}
