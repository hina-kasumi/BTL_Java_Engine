package com.hina.manager;

public class CoinManager {
    private static int coin = 0;

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
