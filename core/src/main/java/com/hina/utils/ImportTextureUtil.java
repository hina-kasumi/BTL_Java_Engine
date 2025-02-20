package com.hina.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ImportTextureUtil {
    public static Animation<TextureRegion> newImportAnimation(String url, float frameDuration) {
        FileHandle fileHandle = Gdx.files.internal(url);

        List<FileHandle> list = null;
        if (fileHandle.exists() && fileHandle.isDirectory()) {
            list = Arrays.stream(fileHandle.list()).sorted((f1, f2) -> {
                String a = f1.name().replaceAll("[^0-9]", "");
                String b = f2.name().replaceAll("[^0-9]", "");
                return Integer.parseInt(a) - Integer.parseInt(b);
            }).toList();
        }

        if (list == null || list.isEmpty()) {
            throw new RuntimeException("Không tìm thấy file trong thư mục: " + url);
        }

        Array<TextureRegion> frames = new Array<>();
        for (FileHandle file : list) {
            Texture texture = new Texture(file);
            frames.add(new TextureRegion(texture));
        }

        return new Animation<>(frameDuration, frames, Animation.PlayMode.NORMAL);
    }

    public static Animation<TextureRegion> newImportAnimation(String url) {
        return newImportAnimation(url, 0.1f);
    }
}
