package com.hina.dto.request;

import com.badlogic.gdx.Net;

import static com.hina.constant.GameConst.getDOMAIN;

public class GetCoinRequest implements RequestInterface{
    @Override
    public String getURL() {
        return getDOMAIN() + "/coin";
    }

    @Override
    public String getHttpMethod() {
        return Net.HttpMethods.GET;
    }

    @Override
    public Object getBody() {
        return null;
    }
}
