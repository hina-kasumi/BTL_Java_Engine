package com.hina.manager;

import com.hina.utils.CoinUtils;

import static com.hina.constant.GameConst.FILE_COIN;

public class CoinManager {
    private static int coin;

    static {
        coin = CoinUtils.getCoinFromFile(FILE_COIN);
        CoinUtils.getCoinFromDatabase();
    }

    public static void setCoin(int coin) {
        CoinManager.coin = coin;
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
