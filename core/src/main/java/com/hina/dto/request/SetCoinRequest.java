package com.hina.dto.request;

import com.badlogic.gdx.Net;

import static com.hina.constant.GameConst.getDOMAIN;

public class SetCoinRequest implements RequestInterface {
    private final int coin;
    public SetCoinRequest(int coin) {
        this.coin = coin;
    }
    @Override
    public String getURL() {
        return getDOMAIN() + "/coin";
    }

    @Override
    public String getHttpMethod() {
        return Net.HttpMethods.POST;
    }

    @Override
    public Object getBody() {
        return new InnerCoin(coin);
    }

    private record InnerCoin(int coin) {
    }
}
