package com.dinogame.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.model.*;
import com.dinogame.model.Record;
import com.dinogame.view.*;

public class Controller extends ApplicationAdapter {
    private SpriteBatch batch;
    private GameModel model;
    private HeroView heroView;
    private GroundView groundView;
    private CactusView cactusView;
    private Scores scores;
    private RestartButton button;
    private FinalScore finalScore;
    private RecordView recordView;
    private float additiveScores;

    @Override
    public void create() {
        batch = new SpriteBatch();
        model = new GameModel();
        heroView = new HeroView();
        groundView = new GroundView();
        cactusView = new CactusView();
        scores = new Scores();
        button = new RestartButton();
        finalScore = new FinalScore();
        recordView = new RecordView();
        additiveScores = 0;
    }

    public static boolean isSpacePressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }

    @Override
    public void render() {
        if (isSpacePressed()) {
            model.doAction();
        }
        model.update(Gdx.graphics.getDeltaTime());
        if (model.getGameState() == GameModel.GameState.START) {
            additiveScores = 0;
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (model.getHero().getHeroState() == Hero.HeroState.RUNNING) {
            heroView.drawAnimated(batch, model.getHeroPositionX(), model.getHeroPositionY(), model.getGameTime());
        } else {
            heroView.drawStatic(batch, model.getHeroPositionX(), model.getHeroPositionY());
        }
        groundView.draw(batch, model.getGround().getX(), model.getGround().getY(), model.getGround().getWidth());
        for (Cactus cactus : model.getCactusSpawner().getCacti()) {
            cactusView.drawCactus(batch, cactus.getX(), cactus.getY(),
                    model.getCactusSpawner().getSpriteIndexByCactusId(cactus.getId()));
        }
        for (Record record : model.getRecordSpawner().getRecordsList()) {
            recordView.drawRecord(batch, record.getX(), record.getY(), model.getRecordSpawner().
                    getSpriteIndexByRecordId(record.getId()));
            if (model.getRecordSpawner().checkHeroCollisionWithRecord(model.getHero()) != -1) {
                if (model.getRecordSpawner().getSpriteIndexByRecordId(record.getId()) == 0) {
                    additiveScores += 5;
                } else if (model.getRecordSpawner().getSpriteIndexByRecordId(record.getId()) == 1) {
                    additiveScores += 10;
                }
            }

        }
        scores.draw(batch, model.getGameTime(), additiveScores);
        batch.end();
        if (model.getGameState() == GameModel.GameState.STOP) {
            Gdx.graphics.getGL20().glClearColor(1, 1, 1, 1);
            Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            batch.begin();
            button.draw(batch);
            finalScore.draw(batch, model.getGameTime(), additiveScores);
            batch.end();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        heroView.dispose();
        groundView.dispose();
        cactusView.dispose();
        scores.dispose();
        finalScore.dispose();
        additiveScores = 0;
        recordView.dispose();
    }


}
