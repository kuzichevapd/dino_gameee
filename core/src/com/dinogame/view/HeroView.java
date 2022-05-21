package com.dinogame.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dinogame.Config;
import com.dinogame.model.Hero;

public class HeroView {
    private Hero hero;
    private TextureRegion heroSprite;
    private Texture animationFrames;
    private Animation<TextureRegion> runAnimation;

    public HeroView(Hero hero) {
        this.hero = hero;
        heroSprite = new TextureRegion(new Texture(Config.HERO_SPRITE_NAME));
        setRunAnimation();
    }

    // класс TextureRegion описывает прямоугольник внутри текстуры и полезен при рисовании только части текстуры
    private TextureRegion getSprite(float gameTime) {
        // если динозаврик бежит - анимация зацикливается
        if (hero.getHeroState() == Hero.HeroState.RUNNING) {
            return runAnimation.getKeyFrame(gameTime, true);
        } else {
            return heroSprite;
        }
    }

    public void draw(SpriteBatch batch, float gameTime) {
        batch.draw(getSprite(gameTime), hero.getSpriteRectangle().x, hero.getSpriteRectangle().y);
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
