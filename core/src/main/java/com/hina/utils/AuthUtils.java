package com.hina.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.hina.dto.request.LoginRequest;

public class AuthUtils {
    public static void login(String username, String password, ResponseInterface responseInterface) {
        Net.HttpRequest httpRequest = authRequest("http://localhost:8080/login", username, password);

        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                if (httpResponse.getStatus().getStatusCode() != 200) {
                    throw new RuntimeException(httpResponse.getResultAsString());
                }
                Json json = new Json(JsonWriter.OutputType.json);
                Object responseMessage = json.fromJson(String.class, httpResponse.getResultAsString());
                System.out.println(responseMessage);

                responseInterface.response(responseMessage.toString());
            }

            @Override
            public void failed(Throwable throwable) {
                throwable.printStackTrace();
                responseInterface.error(throwable.getMessage());
                System.out.println("failed");
            }

            @Override
            public void cancelled() {
                System.out.println("cancel");
            }
        });
    }

    public static void register(String username, String password, ResponseInterface responseInterface) {
        Net.HttpRequest httpRequest = authRequest("http://localhost:8080/register", username, password);

        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                if (httpResponse.getStatus().getStatusCode() != 200) {
                    throw new RuntimeException(httpResponse.getResultAsString());
                }
                Json json = new Json(JsonWriter.OutputType.json);
                Object responseMessage = json.fromJson(String.class, httpResponse.getResultAsString());
                System.out.println(responseMessage);

                responseInterface.response(responseMessage.toString());
            }

            @Override
            public void failed(Throwable throwable) {
                throwable.printStackTrace();
                responseInterface.error(throwable.getMessage());
                System.out.println("failed");
            }

            @Override
            public void cancelled() {

            }
        });
    }

    private static Net.HttpRequest authRequest(String url, String username, String password) {
        Json json = new Json(JsonWriter.OutputType.json);
        LoginRequest loginRequest = new LoginRequest(username, password);
        String requestJson = json.toJson(loginRequest);

        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        return requestBuilder
            .newRequest()
            .url(url)
            .method(Net.HttpMethods.POST)
            .header("Content-Type", "application/json")
            .content(requestJson)
            .build();
    }
}
