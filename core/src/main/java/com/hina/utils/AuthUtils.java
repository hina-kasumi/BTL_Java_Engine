package com.hina.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;

import java.util.Arrays;

public class AuthUtils {
    public static void login(String username, String password) {
        System.out.println(username + " " + password);

        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest()
            .method(Net.HttpMethods.POST).url("http://localhost:8080").build();
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.println(httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable throwable) {

            }

            @Override
            public void cancelled() {

            }
        });
    }
}
