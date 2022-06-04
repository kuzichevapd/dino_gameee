package com.dinogame.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.config.Config;
import java.util.ArrayList;

public class RecordView {

    final private ArrayList<Texture> sprites;

    public RecordView() {
        sprites = new ArrayList<>();
        sprites.add(new Texture(Config.CIRCLE_RECORD));
        sprites.add(new Texture(Config.STAR_RECORD));
    }

    public void drawRecord(SpriteBatch batch, float x, float y, int spriteIndex) {
        batch.draw(sprites.get(spriteIndex), x, y);
    }

    public void dispose() {
        sprites.get(0).dispose();
        sprites.get(1).dispose();
    }

}
