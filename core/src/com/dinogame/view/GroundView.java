package com.dinogame.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.Config;
import com.dinogame.model.Ground;


public class GroundView {
    final private Ground ground;
    final private Texture sprite;

    public GroundView(Ground ground) {
        this.ground = ground;
        sprite = new Texture(Config.GROUND_SPRITE_NAME);
    }

    //отрисовываем две земли друг за другом. Вызывается каждый кадр.
    public void draw(SpriteBatch batch) {
        float x = ground.getSpriteRectangle().x;
        float y = ground.getSpriteRectangle().y;
        float width = ground.getSpriteRectangle().width;
        batch.draw(sprite, x, y);
        batch.draw(sprite, x + width, y);
    }
}
