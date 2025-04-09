package com.hina.dto.response;

public class SetCoinResponse implements ResponseInterface {
    @Override
    public void response(String response) {
        System.out.println(response);
    }

    @Override
    public void error(String error) {
        System.out.println(error);
    }
}
