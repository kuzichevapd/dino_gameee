package com.dinogame.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.Config;
import java.util.ArrayList;


public class CactusView {

    final private ArrayList<Texture> sprites;

    public CactusView() {
        sprites = new ArrayList<>();
        sprites.add(new Texture(Config.CACTUS_1_SPRITE_NAME));
        sprites.add(new Texture(Config.CACTUS_2_SPRITE_NAME));
    }

    public void drawCactus(SpriteBatch batch, float x, float y, int spriteIndex) {
        batch.draw(sprites.get(spriteIndex), x, y);
    }

    public void dispose() {
        sprites.get(0).dispose();
        sprites.get(1).dispose();
    }


}
