package com.dinogame.model;

import com.dinogame.config.Config;

public class GameModel {
    final private Hero hero;
    final private Ground ground;
    final private CactusSpawner cactusSpawner;
    private float gameTime;
    private GameState gameState;
    final private RecordSpawner recordSpawner;

    public GameModel() {
        hero = new Hero();
        ground = new Ground();
        cactusSpawner = new CactusSpawner();
        recordSpawner = new RecordSpawner();
        gameState = GameState.START;
        gameTime = 0;
    }

    public Hero getHero() {
        return hero;
    }

    public Ground getGround() {
        return ground;
    }

    public float getHeroPositionX() {
        return hero.getSpriteRectangle().x;
    }

    public float getHeroPositionY() {
        return hero.getSpriteRectangle().y;
    }

    public CactusSpawner getCactusSpawner() {
        return cactusSpawner;
    }

    public RecordSpawner getRecordSpawner() {return recordSpawner;}

    public float getGameTime() {
        return gameTime;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void doAction() {
            switch (gameState) {
                case START:
                    startGame();
                    hero.jump();
                    break;
                case RUN:
                    hero.jump();
                    break;
                case STOP:
                    restartGame();
                    break;
            }
    }

    private void heroUpdate() {
        if (hero.checkCollision(ground) && hero.velocity.y < 0) {
            hero.run();
            hero.setVelocity(0, 0);
        }
        if (hero.getHeroState() == Hero.HeroState.JUMPED) {
            hero.accelerate(0, -Config.GRAVITY);
        }
        hero.move();
    }

    private void cactusSpawnerUpdate() {
        cactusSpawner.spawn(gameTime);
        cactusSpawner.checkInvisible();
        if (cactusSpawner.checkHeroCollision(hero)) {
            stopGame();
        }
        cactusSpawner.updatePosition();
    }

    private void recordSpawnerUpdate() {
        recordSpawner.spawnRecord(gameTime);
        recordSpawner.checkRecordOutcome();
        if (recordSpawner.checkHeroCollisionWithRecord(hero) != -1){
            recordSpawner.remove(recordSpawner.checkHeroCollisionWithRecord(hero));
        }
        recordSpawner.updateRecordPosition();
    }

    public void update(float time) {
        if (gameState == GameState.RUN) {
            gameTime += time;
            heroUpdate();
            cactusSpawnerUpdate();
            recordSpawnerUpdate();
            ground.move();
        }
    }

    public void stopGame() {
        gameState = GameState.STOP;
        hero.stop();
    }

    public void startGame() {
        gameState = GameState.RUN;
    }

    public void restartGame() {
        gameState = GameState.START;
        gameTime = 0;
        cactusSpawner.restart();
        recordSpawner.restart();
        hero.changePosition(Config.START_X, Config.GROUND_LEVEL);
    }

    public enum GameState {
        START, STOP, RUN
    }
}
