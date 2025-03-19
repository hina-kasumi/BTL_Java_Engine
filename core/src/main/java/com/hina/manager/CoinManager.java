package com.hina.manager;

public class CoinManager {
    private static int coin = 0;

    public CoinManager() {
    }

    public static int getCoin() {
        return coin;
    }

    public static void upCoin(long dCoin) {
        coin += dCoin;
    }

    public static void downCoin(long dCoin) {
        if (coin >= dCoin) {
            coin -= dCoin;
        }
        System.out.println("không đủ coin");
    }
}
