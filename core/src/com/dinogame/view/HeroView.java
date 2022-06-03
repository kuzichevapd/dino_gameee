package com.dinogame.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dinogame.config.Config;

public class HeroView {
    final private TextureRegion heroSprite;
    private Texture animationFrames;
    private Animation<TextureRegion> runAnimation;

    public HeroView() {
        heroSprite = new TextureRegion(new Texture(Config.HERO_SPRITE_NAME));
        setRunAnimation();
    }

    public void drawStatic(SpriteBatch batch, float x, float y) {
        batch.draw(heroSprite, x, y);
    }

    public void drawAnimated(SpriteBatch batch, float x, float y, float gameTime) {
        batch.draw(runAnimation.getKeyFrame(gameTime, true), x, y);
    }


    private void setRunAnimation() {
        animationFrames = new Texture(Config.HERO_ANIMATION_NAME);
        //split - двумерный массив кадров из текстуры
        TextureRegion[][] running = TextureRegion.split(animationFrames,
                animationFrames.getWidth() / Config.HERO_ANIMATION_FRAMES, animationFrames.getHeight());
        TextureRegion[] frames = new TextureRegion[Config.HERO_ANIMATION_FRAMES];
        for (int i = 0; i < Config.HERO_ANIMATION_FRAMES; i++) {
            frames[i] = running[0][i];
        }
        // первый параметр - время кадра, второй - массив из кадров, представляющих анимацию
        runAnimation = new Animation<>(1f / Config.RUN_SPEED, frames);
    }

    public void dispose() {
        heroSprite.getTexture().dispose();
        animationFrames.dispose();
    }

}
