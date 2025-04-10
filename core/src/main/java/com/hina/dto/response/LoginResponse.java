package com.hina.dto.response;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.screens.MainMenuScreen;
import com.hina.utils.CoinUtils;

import java.io.IOException;

import static com.hina.constant.GameConst.FILE_COIN;

public class LoginResponse implements AuthInterface {
    private final String username;
    private final String password;
    private final Game game;
    private final FitViewport viewport;
    private final OrthographicCamera camera;
    private final Label messageLabel;

    public LoginResponse(String username, String password, Game game,
                         FitViewport viewport, OrthographicCamera camera, Label messageLabel) {
        this.username = username;
        this.password = password;
        this.game = game;
        this.viewport = viewport;
        this.camera = camera;
        this.messageLabel = messageLabel;
    }

    @Override
    public void response(String response) {
        messageLabel.setText(response);
        System.out.println(response);

        try {
            saveAuthInfo(username, password);
            CoinUtils.saveCoinToFile(Integer.parseInt(response), FILE_COIN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Chạy trên luồng chính
        Gdx.app.postRunnable(() -> game.setScreen(new MainMenuScreen(game, viewport, camera)));
    }

    @Override
    public void error(String error) {
        messageLabel.setText(error);
    }
}
