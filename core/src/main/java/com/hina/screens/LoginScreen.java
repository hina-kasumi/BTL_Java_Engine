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


public class LoginScreen extends ScreenAbstract {
    private Stage stage;
    private Stage stageWithOutViewPort;
    private TextField usernameField, passwordField;
    private TextButton loginButton;
    private Label messageLabel;
    private MyPanelList myPanelList;
    private final Color textColor = Color.WHITE;


    public LoginScreen(ScreenAbstract screen) {
        super(screen);
    }

    @Override
    public void show() {
        Pixmap pixmap = new Pixmap(5, 5, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.valueOf("#A0C878")); // Màu viền
        pixmap.fill();
        Texture texture = new Texture(pixmap);
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
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        NinePatchDrawable buttonBackground = new NinePatchDrawable(ninePatch);
        buttonStyle.font = new BitmapFont();
        buttonStyle.fontColor = textColor;
        buttonStyle.up = buttonBackground;
        buttonStyle.down = buttonBackground;
        loginButton = new TextButton("Login", buttonStyle);
        loginButton.pad(10);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (username.isEmpty() || password.isEmpty()) {
                    messageLabel.setText("Username or password cannot be empty!");
                    return;
                }

                AuthUtils.login(username, password, messageLabel);
            }
        });
    }

    private void initTable(NinePatch ninePatch) {
        Table table = new Table();
        float padding = 10;
        float width = 300;
        float height = 50;

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
        table.add(usernameField).width(width).height(height).pad(padding).left();
        table.row();
        table.add(passwordField).width(width).height(height).pad(padding).left();
        table.row();
        table.add(loginButton).colspan(2).height(height).pad(padding).center();

        stageWithOutViewPort.addActor(table);
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
    }
}
