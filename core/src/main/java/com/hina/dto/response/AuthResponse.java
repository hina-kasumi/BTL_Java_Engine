package com.hina.dto.response;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.screens.MainMenuScreen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

import static com.hina.constant.GameConst.FILE_AUTH_INFO;

public class AuthResponse implements ResponseInterface {
    private final String username;
    private final String password;
    private final Game game;
    private final FitViewport viewport;
    private final OrthographicCamera camera;
    private final Label messageLabel;

    public AuthResponse(String username, String password, Game game, FitViewport viewport, OrthographicCamera camera, Label messageLabel) {
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

        try {
            File file = new File(FILE_AUTH_INFO);
            String basicAuth = Base64.getEncoder().encodeToString((username + ":" +password).getBytes());
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Could not create file");
                }
            }
            if (file.exists() && file.canWrite()) {
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                fw.write(basicAuth);
                fw.close();
            }
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
