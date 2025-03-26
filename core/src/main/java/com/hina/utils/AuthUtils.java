package com.hina.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.hina.dto.request.LoginRequest;
import com.hina.dto.request.Request;

public class AuthUtils {
    public static void login(String username, String password, Label label) {
        Json json = new Json(JsonWriter.OutputType.json);
        Request loginRequest = new LoginRequest(username, password);
        String requestJson = json.toJson(loginRequest);

        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder
            .newRequest()
            .url("http://localhost:8080/login")
            .method(Net.HttpMethods.POST)
            .header("Content-Type", "application/json")
            .content(requestJson)
            .build();


        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                if (httpResponse.getStatus().getStatusCode() != 200) {
                    throw new RuntimeException(httpResponse.getResultAsString());
                }
                Json json = new Json(JsonWriter.OutputType.json);
                Object responseMessage = json.fromJson(String.class, httpResponse.getResultAsString());
                System.out.println(responseMessage);
                label.setText(responseMessage.toString());
            }

            @Override
            public void failed(Throwable throwable) {
                throwable.printStackTrace();
                label.setText(throwable.getMessage());
                System.out.println("failed");
            }

            @Override
            public void cancelled() {
                System.out.println("cancel");
            }
        });
    }
}
