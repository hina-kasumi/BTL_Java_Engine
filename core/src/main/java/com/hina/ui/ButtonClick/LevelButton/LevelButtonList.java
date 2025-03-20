package com.hina.ui.ButtonClick.LevelButton;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hina.ui.MyText;

import java.util.ArrayList;
import java.util.List;

import static com.hina.constant.LevelSelectionScreenConst.LEVEL_SELECTION_MAX_COL;
import static com.hina.constant.LevelSelectionScreenConst.LEVEL_SELECTION_MAX_ROW;

public class LevelButtonList {
    private final List<LevelButton> levelButtons;
    private float x = 1;
    private float y = 1;
    private final int maxCol = LEVEL_SELECTION_MAX_COL;
    private final int maxRow = LEVEL_SELECTION_MAX_ROW;
    private final float gap = 2;
    private float width;
    private float height;
    private int currentPage = 1;

    public LevelButtonList() {
        levelButtons = new ArrayList<>();
    }

    public void add(LevelButton levelButton, float scale) {
        levelButton.setScale(scale);
        levelButtons.add(levelButton);
        width = levelButton.getWidth() * maxCol + (maxCol - 1) * gap;
        height = levelButton.getHeight() * maxRow + (maxRow - 1) * gap;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void stageAddActor(Stage stage) {
        for (int i = 0; i < levelButtons.size(); i++) {
            levelButtons.get(i).setVisible(false);
            if (i >= (currentPage - 1) * (maxCol * maxRow) && i < currentPage * (maxRow * maxCol)) {
                levelButtons.get(i).setVisible(true);
            }
            levelButtons.get(i).setLevel(i + 1);
            levelButtons.get(i).setPosition(
                x + (i % maxCol) * (levelButtons.get(i).getWidth() + gap),
                y - (float) ((i / maxCol) % maxRow + 1) * (levelButtons.get(i).getHeight() + gap));
            stage.addActor(levelButtons.get(i).getImageButton());
        }
    }

    public void increaseCurrentPage() {
        currentPage++;
        if (currentPage > levelButtons.size() / (maxRow * maxCol) + 1)
            currentPage--;
        for (int i = 0; i < levelButtons.size(); i++) {
            levelButtons.get(i).setVisible(false);
            if (inPage(i)) {
                levelButtons.get(i).setVisible(true);
            }
        }
    }

    public void decreaseCurrentPage() {
        currentPage--;
        if (currentPage < 1) {
            currentPage = 1;
        }
        for (int i = 0; i < levelButtons.size(); i++) {
            levelButtons.get(i).setVisible(false);
            if (inPage(i)) {
                levelButtons.get(i).setVisible(true);
            }
        }
    }

    public void draw(MyText myText, SpriteBatch batch) {
        for (int i = 0; i < levelButtons.size(); i++) {
            if (inPage(i))
                levelButtons.get(i).draw(myText, batch);
        }
    }

    private boolean inPage(int i) {
        return i >= (currentPage - 1) * (maxCol * maxRow) && i < currentPage * (maxRow * maxCol);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void dispose() {
        levelButtons.forEach(LevelButton::dispose);
    }
}
