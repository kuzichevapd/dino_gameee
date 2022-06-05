package com.dinogame.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.dinogame.config.Config;

public class Scores {
    final private TextureRegion[] numbers;
    final private int numberWidth;
    final private Vector2 position;
    final private Texture sprite;

    public Scores() {
        sprite = new Texture(Config.NUMBERS_SPRITE_NAME);
        position = new Vector2(Config.WINDOW_WIDTH - 50, Config.WINDOW_HEIGHT - 50);
        numberWidth = sprite.getWidth() / 10;
        TextureRegion[][] split = TextureRegion.split(sprite, numberWidth, sprite.getHeight());
        numbers = new TextureRegion[10];
        System.arraycopy(split[0], 0, numbers, 0, split[0].length);
    }

    public void draw(SpriteBatch batch, float gameTime, float additiveScores) {
        int time = (int) gameTime + (int) additiveScores;
        int k = 0;
        if (time == 0) batch.draw(numbers[0], position.x, position.y);
        while (time > 0) {
            int num = time % 10;
            batch.draw(numbers[num], position.x - numberWidth * k, position.y);
            time /= 10;
            k++;
        }
    }

    public void dispose() {
        sprite.dispose();
    }
}
