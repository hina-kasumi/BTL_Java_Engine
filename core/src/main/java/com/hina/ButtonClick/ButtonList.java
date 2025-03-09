package com.hina.ButtonClick;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonList {
    private final List<ButtonAbstract> list;

    public ButtonList() {
        list = new ArrayList<>();
    }

    public void add(ButtonAbstract ...buttonAbstract) {
        list.addAll(Arrays.asList(buttonAbstract));
    }

    public void dispose() {
        list.forEach(ButtonAbstract::dispose);
    }

    public void stageAddActor(Stage stage) {
        list.forEach(i -> stage.addActor(i.getImageButton()));
    }

    public void setVisible(boolean visible) {
        list.forEach(i -> i.setVisible(visible));
    }

    public List<ButtonAbstract> getList() {
        return list;
    }
}
