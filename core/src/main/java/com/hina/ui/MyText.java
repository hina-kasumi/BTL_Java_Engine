package com.hina.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.hina.constant.GameConst.PPM;


public class MyText {
    private final Texture[] texture;

    public MyText() {
        texture = new Texture[10];
        for (int i = 0; i < texture.length; i++) {
            texture[i] = new Texture("ui/number/sprite_0" + i + ".png");
        }
    }

    public void drawText(SpriteBatch batch, float x, float y, int number, float scale) {
        int[] numbers = parse(number);
        for (int i = 0; i < numbers.length; i++) {
            Texture t = texture[numbers[i]];
            batch.draw(t, x + (t.getWidth() + 1) * i / PPM * scale, y,
                t.getWidth() / PPM * scale,
                t.getHeight() / PPM * scale);
        }
    }

    private int[] parse(int number) {
        String numberStr = String.valueOf(number);
        int[] result = new int[numberStr.length()];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(numberStr.charAt(i) + "");
        }

        return result;
    }

    public float getWidth(int number, float scale) {
        int[] numbers = parse(number);

        return (numbers.length * texture[numbers[0]].getWidth() + 1) / PPM * scale;
    }

    public void dispose() {
        for (Texture value : texture) {
            value.dispose();
        }
    }
}
