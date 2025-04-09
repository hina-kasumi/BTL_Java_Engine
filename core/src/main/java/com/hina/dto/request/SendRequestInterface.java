package com.hina.dto.request;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.hina.dto.response.ResponseInterface;

public interface SendRequestInterface {
    default void sendRequest(Net.HttpRequest httpRequest, ResponseInterface responseInterface) {
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                if (httpResponse.getStatus().getStatusCode() != 200) {
                    throw new RuntimeException(
                        httpResponse.getStatus().getStatusCode() + ": " +
                            httpResponse.getResultAsString());
                }
                Json json = new Json(JsonWriter.OutputType.json);
                Object responseMessage = json.fromJson(String.class, httpResponse.getResultAsString());
                System.out.println(responseMessage);

                responseInterface.response(responseMessage.toString());
            }

            @Override
            public void failed(Throwable throwable) {
//                throwable.printStackTrace();
                responseInterface.error(throwable.getMessage());
                System.err.println("failed: " + throwable.getMessage());
            }

            @Override
            public void cancelled() {
                System.out.println("cancel");
            }
        });
    }
}
