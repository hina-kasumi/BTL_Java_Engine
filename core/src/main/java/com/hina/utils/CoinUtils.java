package com.hina.utils;

import com.hina.dto.request.GetCoinRequest;
import com.hina.dto.request.SetCoinRequest;
import com.hina.dto.response.GetCoinResponse;
import com.hina.dto.response.SetCoinResponse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CoinUtils {
    public static void saveCoinToFile(int coin, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(coin + "");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveCoinToDatabase(int coin) {
        RequestUtils.apiNeedLogin(new SetCoinRequest(coin), new SetCoinResponse());
    }

    public static int getCoinFromFile(String fileName) {
        int coin = 0;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Could not create file " + fileName);
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
            }
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
            coin = 0;
        }
        return coin;
    }

    public static void getCoinFromDatabase() {
        RequestUtils.apiNeedLogin(new GetCoinRequest(), new GetCoinResponse());
    }
}
