package com.dinogame.model;
import com.dinogame.Config;
public class GameModel {
    //игровые сущности
    private Hero hero;
    private Ground ground;
    private CactusSpawner cactusSpawner;
    //текущее время с начала игры.
    private float gameTime;
    private GameState gameState;


    // конструктор GameModel, отрисовывает большую часть игры
    public GameModel() {
        hero = new Hero();
        ground = new Ground();
        cactusSpawner = new CactusSpawner();
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

    public CactusSpawner getCactusSpawner() {
        return cactusSpawner;
    }

    public float getGameTime() {
        return gameTime;
    }

    public GameState getGameState() {
        return gameState;
    }

    //функция для управления. Т.к. игра управляется одним пробелом,
    //то нет смысла создавать отдельный класс под контроллер
    // проверяет, что в случае, если нажат пробел выбирает какое состояние героя задано из значений класса GameState
    // если старт - начинаем игру, gameState = run, то есть начинаем бежать, а потом прыгем
    private void checkController(boolean isKeyPressed) {
        if (isKeyPressed) {
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
    }

    //обновляет состояние персонажа
    //??
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
    //??
    private void cactusSpawnerUpdate() {
        cactusSpawner.spawn(gameTime);
        cactusSpawner.checkInvisible();
        if (cactusSpawner.checkHeroCollision(hero)) {
            stopGame();
        }
        cactusSpawner.updatePosition();
    }

    //обновляет состояние игры
    public void update(float time, boolean isKeyPressed) {
        checkController(isKeyPressed);
        if (gameState == GameState.RUN) {
            gameTime += time;
            heroUpdate();
            cactusSpawnerUpdate();
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
        hero.changePosition(Config.START_X, Config.GROUND_LEVEL);
    }

    // enum - класс с ограниченным количеством значений, этот класс оторбражает состояние игры
    public enum GameState {
        START, STOP, RUN
    }
}
