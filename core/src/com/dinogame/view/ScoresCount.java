package com.dinogame.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.dinogame.Config;

public class ScoresCount {
    final private Texture spriteTextGameIsOver;
    final private Texture spriteTextYourScoreIs;
    final private Texture spriteTextRestart;
    final private Texture spriteScores;
    final private Vector2 textPositionGameIsOver;
    final private Vector2 textPositionYourScoreIs;
    final private Vector2 textPositionRestart;
    final private Vector2 scoresPosition;
    final private int numberWidth;
    final private TextureRegion[] numbers;

    public ScoresCount() {
        spriteTextGameIsOver = new Texture(Config.GAME_iS_OVER);
        spriteTextYourScoreIs = new Texture(Config.YOUR_SCORE_IS);
        spriteTextRestart = new Texture(Config.DO_YOU_WANT_TO_RESTART);
        spriteScores = new Texture(Config.NUMBERS_FOR_SCORE);
        textPositionGameIsOver = new Vector2(Config.WINDOW_WIDTH - 734, Config.WINDOW_HEIGHT - 100);
        textPositionYourScoreIs = new Vector2(Config.WINDOW_WIDTH - 780, Config.WINDOW_HEIGHT - 160);
        textPositionRestart = new Vector2(Config.WINDOW_WIDTH - 725, Config.WINDOW_HEIGHT - 270);
        scoresPosition = new Vector2(Config.WINDOW_WIDTH - 70, Config.WINDOW_HEIGHT - 150);
        numberWidth = spriteScores.getWidth() / 10;
        TextureRegion[][] split = TextureRegion.split(spriteScores, numberWidth, 38);
        numbers = new TextureRegion[10];
        System.arraycopy(split[0], 0, numbers, 0, split[0].length);
    }

    public void draw(SpriteBatch batch, float gameTime) {
        int time = (int) gameTime;
        int k = 0;
        while (time > 0) {
            int num = time % 10;
            batch.draw(numbers[num], scoresPosition.x - numberWidth * k, scoresPosition.y);
            k++;
            time /= 10;
        }
        batch.draw(spriteTextGameIsOver, textPositionGameIsOver.x, textPositionGameIsOver.y);
        batch.draw(spriteTextYourScoreIs, textPositionYourScoreIs.x, textPositionYourScoreIs.y);
        batch.draw(spriteTextRestart, textPositionRestart.x, textPositionRestart.y);
    }

}
