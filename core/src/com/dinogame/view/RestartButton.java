package com.dinogame.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.Config;

public class RestartButton {
    final private Texture sprite;
    final private int x;
    final private int y;

    public RestartButton() {
        sprite = new Texture(Config.BUTTON_SPRITE_NAME);
        x = (Config.WINDOW_WIDTH - sprite.getWidth()) / 2;
        y = (Config.WINDOW_HEIGHT - sprite.getHeight()) / 3 - 50;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(sprite, x, y);
    }
}
