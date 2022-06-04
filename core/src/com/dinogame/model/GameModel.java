package com.dinogame.model;

import com.dinogame.config.Config;

public class GameModel {
    //игровые сущности
    final private Hero hero;
    final private Ground ground;
    final private CactusSpawner cactusSpawner;
    //текущее время с начала игры.
    private float gameTime;
    private GameState gameState;
    final private RecordSpawner recordSpawner;


    // конструктор GameModel, отрисовывает большую часть игры
    public GameModel() {
        hero = new Hero();
        ground = new Ground();
        cactusSpawner = new CactusSpawner();
        recordSpawner = new RecordSpawner();
        gameState = GameState.START;
        gameTime = 0;
    }

    // геттеры для различных составляющих GameModel
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

    // проверяет, что в случае, если нажат пробел выбирает какое состояние героя задано из значений класса GameState
    // если старт - начинаем игру, gameState = run, то есть начинаем бежать, а потом прыгем
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

    //обновляет состояние персонажа
    // если он приземлился - то продолжаем бег
    // если состояние - JUMPED, придаем ускорение вверх
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

    //обоновляет состояние генератора кактусов
    // если столкнулся - останавливаем игру
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
        recordSpawner.updateRecordPosition();
    }

    //обновляет состояние игры
    public void update(float time) {
        if (gameState == GameState.RUN) {
            gameTime += time;
            heroUpdate();
            cactusSpawnerUpdate();
            recordSpawnerUpdate();
            ground.move();
        }
    }

    //остановить игру (проигрыш)
    public void stopGame() {
        gameState = GameState.STOP;
        hero.stop();
    }

    //начинаем бежать
    public void startGame() {
        gameState = GameState.RUN;
    }

    //заново начинаем игру
    public void restartGame() {
        gameState = GameState.START;
        gameTime = 0;
        cactusSpawner.restart();
        recordSpawner.restart();
        hero.changePosition(Config.START_X, Config.GROUND_LEVEL);
    }

    // enum - класс с ограниченным количеством значений, этот класс оторбражает состояние игры
    public enum GameState {
        START, STOP, RUN
    }
}
