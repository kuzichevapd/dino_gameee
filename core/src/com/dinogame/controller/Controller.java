package com.dinogame.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.model.*;
import com.dinogame.view.*;

public class Controller extends ApplicationAdapter{
    private SpriteBatch batch;
    private GameModel model;
    private HeroView heroView;
    private GroundView groundView;
    private CactusView cactusView;
    private Scores scores;
    private RestartButton button;
    private FinalScore finalScore;

    // перед запуском графики
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
    }

    // проверка на нажатие пробела
    public static boolean isSpacePressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }

    // выполняется каждый кадр, основная
    // отрисовка в соответствии с действиями пользователя
    @Override
    public void render() {
        // меняем состояние модели в соответствии с нажатием пробела
        if (isSpacePressed()) {
            model.doAction();
        }
        //обновление состояния игры
        model.update(Gdx.graphics.getDeltaTime());
        // зарисовка фона
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        // если динозавр бежит, отрисовываем его бегающее состояние
        // в противном случае спокойное состояние
        if (model.getHero().getHeroState() == Hero.HeroState.RUNNING) {
            heroView.drawAnimated(batch, model.getHeroPositionX(), model.getHeroPositionY(), model.getGameTime());
        } else {
            heroView.drawStatic(batch, model.getHeroPositionX(), model.getHeroPositionY());
        }
        //отрисовка земли
        groundView.draw(batch, model.getGround().getX(), model.getGround().getY(), model.getGround().getWidth());
        // отрисовка кактуса в рандомное время с проверкой, что он еще на экране
        for (Cactus cactus : model.getCactusSpawner().getCacti()) {
            cactusView.drawCactus(batch, cactus.getX(), cactus.getY(),
                    model.getCactusSpawner().getSpriteIndexByCactusId(cactus.getId()));
        }
        scores.draw(batch, model.getGameTime());
        batch.end();
        // отричовка финального окна
        if (model.getGameState() == GameModel.GameState.STOP) {
            Gdx.graphics.getGL20().glClearColor(1, 1, 1, 1);
            Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            batch.begin();
            button.draw(batch);
            finalScore.draw(batch, model.getGameTime());
            batch.end();
        }
    }

    //после выключения удаляет все текстуры.
    @Override
    public void dispose() {
        batch.dispose();
        heroView.dispose();
        groundView.dispose();
        cactusView.dispose();
        scores.dispose();
    }





}
