package com.hina.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.hina.ui.MyPanel.MainMenuBackground;
import com.hina.ui.MyPanel.MyPanelList;
import com.hina.utils.AuthUtils;
import com.hina.utils.ResponseInterface;

import java.io.FileWriter;
import java.io.IOException;

import static com.hina.constant.GameConst.FILE_AUTH_INFO;


public class LoginScreen extends ScreenAbstract {
    private Stage stage;
    private Stage stageWithOutViewPort;
    private TextField usernameField, passwordField;
    private TextButton loginButton, registerButton;
    private Label messageLabel;
    private MyPanelList myPanelList;
    private final Color textColor = Color.WHITE;
    private final float padding = 10;
    private final float width = 300;
    private final float height = 50;
    private Texture texture;


    public LoginScreen(ScreenAbstract screen) {
        super(screen);
    }

    @Override
    public void show() {
        Pixmap pixmap = new Pixmap(5, 5, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.valueOf("#A0C878")); // Màu viền
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();

        NinePatch ninePatch = new NinePatch(texture, 2, 2, 2, 2);
        messageLabel = new Label("Welcome!", new Label.LabelStyle(new BitmapFont(), textColor));

        initStage();
        initBackground();
        initTextField(ninePatch);
        initButton(ninePatch);

        initTable(ninePatch);
    }


    private void initStage() {
        stage = new Stage(viewport);
        stageWithOutViewPort = new Stage();
        Gdx.input.setInputProcessor(stageWithOutViewPort);
    }

    private void initBackground() {
        myPanelList = new MyPanelList();
        MainMenuBackground background = new MainMenuBackground(viewport);
        myPanelList.add(background);
        myPanelList.stageAddActor(stage);
    }

    private void initTextField(NinePatch ninePatch) {
        NinePatchDrawable textFieldBackground = new NinePatchDrawable(ninePatch);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = textColor;
        style.background = textFieldBackground;

        usernameField = new TextField("", style);
        passwordField = new TextField("", style);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
    }

    private void initButton(NinePatch ninePatch) {
        // style button
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        NinePatchDrawable buttonBackground = new NinePatchDrawable(ninePatch);
        buttonStyle.font = new BitmapFont();
        buttonStyle.fontColor = textColor;
        buttonStyle.up = buttonBackground;
        buttonStyle.down = buttonBackground;


        loginButton = new TextButton("Login", buttonStyle);
        loginButton.pad(padding);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (username.isEmpty() || password.isEmpty()) {
                    messageLabel.setText("Username or password cannot be empty!");
                    return;
                }

                AuthUtils.login(username, password, new AuthResponse(username, password));
            }
        });

        registerButton = new TextButton("Register", buttonStyle);
        registerButton.pad(padding);
        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (username.isEmpty() || password.isEmpty()) {
                    messageLabel.setText("Username or password cannot be empty!");
                    return;
                }

                AuthUtils.register(username, password, new AuthResponse(username, password));
            }
        });
    }

    private void initTable(NinePatch ninePatch) {
        Table table = new Table();

        // color
        table.setBackground(new NinePatchDrawable(ninePatch));
        table.setColor(Color.valueOf("#80CBC4"));

        //position and size
//        table.setFillParent(true);
        int elementNum = 4;
        table.setWidth(width + padding * 2);
        table.setHeight(height * elementNum + padding * 2 * elementNum);

        table.setPosition(
            viewport.getScreenWidth() / 2f - width / 2,
            viewport.getScreenHeight() / 2f - height
        );

        // add element
        table.add(messageLabel).colspan(2).height(height).pad(padding).center();
        table.row();
        table.add(usernameField).colspan(2).width(width).height(height).pad(padding).left();
        table.row();
        table.add(passwordField).colspan(2).width(width).height(height).pad(padding).left();
        table.row();
        table.add(registerButton).height(height).width(width / 2 - padding).pad(padding).center();
        table.add(loginButton).height(height).width(width / 2 - padding).pad(padding).center();

        stageWithOutViewPort.addActor(table);
//        table.debug();
    }


    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stageWithOutViewPort.act(v);
        stageWithOutViewPort.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        stageWithOutViewPort.dispose();
        myPanelList.dispose();
        texture.dispose();
    }

    private class AuthResponse implements ResponseInterface {
        private final String username;
        private final String password;

        public AuthResponse(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public void response(String response) {
            messageLabel.setText(response);

            try (FileWriter fileWriter = new FileWriter(FILE_AUTH_INFO)) {
                fileWriter.write(username + "\n" + password);
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
}
