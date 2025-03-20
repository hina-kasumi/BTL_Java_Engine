package com.hina.ui.ButtonClick;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class LinkToWebButton extends ButtonAbstract {
    private final String webAddress;

    public LinkToWebButton(String src, String webAddress) {
        super(src);
        this.webAddress = webAddress;
    }

    public LinkToWebButton(String normal, String hover, String webAddress) {
        super(normal, hover);
        this.webAddress = webAddress;
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        try {
            Desktop.getDesktop().browse(new URI(webAddress));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
