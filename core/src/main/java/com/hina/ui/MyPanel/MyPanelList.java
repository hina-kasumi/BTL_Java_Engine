package com.hina.ui.MyPanel;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class MyPanelList {
    private final List<MyPanel> list;

    public MyPanelList() {
        list = new ArrayList<>();
    }

    public void add(MyPanel myPanel) {
        list.add(myPanel);
    }

    public void stageAddActor(Stage stage) {
        list.forEach(i -> stage.addActor(i.getImage()));
    }

    public void dispose() {
        list.forEach(MyPanel::dispose);
    }

}
