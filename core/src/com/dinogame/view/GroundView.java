package com.dinogame.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.config.Config;


public class GroundView {

    final private Texture sprite;

    public GroundView() {
        sprite = new Texture(Config.GROUND_SPRITE_NAME);
    }

    public void draw(SpriteBatch batch, float x, float y, float width) {
        batch.draw(sprite, x, y);
        batch.draw(sprite, x + width, y);
    }

    public void dispose() {
        sprite.dispose();
    }
}
