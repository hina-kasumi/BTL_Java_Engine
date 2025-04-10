package com.hina.utils;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.hina.dto.request.LoginRequest;
import com.hina.dto.request.RegisterRequest;
import com.hina.dto.request.SendRequestInterface;
import com.hina.dto.response.ResponseInterface;

import static com.hina.constant.GameConst.DOMAIN;
import static com.hina.constant.GameConst.FILE_COIN;

public class AuthUtils implements SendRequestInterface {
    public static void login(String username, String password, ResponseInterface responseInterface) {
        Net.HttpRequest httpRequest = authRequest(DOMAIN + "/login", new LoginRequest(username, password));
        new AuthUtils().sendRequest(httpRequest, responseInterface);
    }

    public static void register(String username, String password, ResponseInterface responseInterface) {
        Net.HttpRequest httpRequest = authRequest(DOMAIN + "/register",
            new RegisterRequest(username, password, CoinUtils.getCoinFromFile(FILE_COIN)));
        new AuthUtils().sendRequest(httpRequest, responseInterface);
    }

    private static Net.HttpRequest authRequest(String url, Object object) {
        Json json = new Json(JsonWriter.OutputType.json);
        String requestJson = json.toJson(object);

        // Mã hóa username và password theo dạng "username:password" → Base64
//        String basicAuth = Base64.getEncoder().encodeToString(("hina:hina").getBytes());
//        System.out.println(basicAuth);
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        return requestBuilder
            .newRequest()
            .url(url)
            .method(Net.HttpMethods.POST)
            .header("Content-Type", "application/json")
//            .header("Authorization", "Basic " + basicAuth)
            .content(requestJson)
            .build();
    }
}
