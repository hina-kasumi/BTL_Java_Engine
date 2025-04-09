package com.hina.utils;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.hina.dto.request.RequestInterface;
import com.hina.dto.request.SendRequestInterface;
import com.hina.dto.response.ResponseInterface;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static com.hina.constant.GameConst.FILE_AUTH_INFO;

public class RequestUtils implements SendRequestInterface {
    public static void apiNeedLogin(RequestInterface requestInterface, ResponseInterface responseInterface) {
        Net.HttpRequest httpRequest = buildHttpRequest(requestInterface);

        String basicAuth = null;

        try {
            File file = new File(FILE_AUTH_INFO);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                basicAuth = scanner.nextLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        httpRequest.setHeader("Authorization", "Basic " + basicAuth);
        response(httpRequest, responseInterface);
    }

    public static void api(RequestInterface requestInterface, ResponseInterface responseInterface) {
        Net.HttpRequest httpRequest = buildHttpRequest(requestInterface);
        response(httpRequest, responseInterface);
    }

    private static Net.HttpRequest buildHttpRequest(RequestInterface requestInterface) {
        Json json = new Json(JsonWriter.OutputType.json);
        String requestJson = json.toJson(requestInterface.getBody());

        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        return requestBuilder.newRequest()
            .url(requestInterface.getURL())
            .method(requestInterface.getHttpMethod())
            .header("Content-Type", "application/json")
            .content(requestJson)
            .build();

    }

    private static void response(Net.HttpRequest httpRequest, ResponseInterface responseInterface) {
        new RequestUtils().sendRequest(httpRequest, responseInterface);
    }
}
