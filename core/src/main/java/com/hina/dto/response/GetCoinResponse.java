package com.hina.dto.response;

import com.hina.manager.CoinManager;
import com.hina.utils.CoinUtils;


import static com.hina.constant.GameConst.FILE_COIN;

public class GetCoinResponse implements ResponseInterface {
    @Override
    public void response(String response) {
        int coin = Integer.parseInt(response);
        CoinUtils.saveCoinToFile(coin, FILE_COIN);
        CoinManager.setCoin(coin);
    }

    @Override
    public void error(String error) {
        System.out.println(error);
    }
}
