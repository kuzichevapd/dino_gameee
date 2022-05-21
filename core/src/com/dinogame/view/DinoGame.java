package com.dinogame.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.Controller;
import com.dinogame.model.GameModel;

//основное представление view. Просто делается отрисовка того, что выдает model.
public class DinoGame extends ApplicationAdapter{
    SpriteBatch batch;
    GameModel model;
    HeroView heroView;
    GroundView groundView;
    CactiView cactiView;
    Scores scores;
    RestartButton button;
    ScoresCount finalScore;

    //делается перед запуском графики
    @Override
    public void create() {
        batch = new SpriteBatch();
        model = new GameModel();
        heroView = new HeroView(model.getHero());
        groundView = new GroundView(model.getGround());
        cactiView = new CactiView(model.getCactusSpawner());
        scores = new Scores();
        button = new RestartButton();
        finalScore = new ScoresCount();

    }

    //выполянется каждый кадр
    @Override
    public void render() {
        model.update(Gdx.graphics.getDeltaTime(), Controller.isSpacePressed());
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        heroView.draw(batch, model.getGameTime());
        groundView.draw(batch);
        cactiView.drawAll(batch);
        scores.draw(batch, model.getGameTime());
        batch.end();
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
    }
}
