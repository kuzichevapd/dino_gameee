package com.dinogame.model;

import com.badlogic.gdx.math.Vector2;
import com.dinogame.Config;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CactusSpawner {
    //место создания кактусов
    final private Vector2 spawnPosition;
    //список с отображаемыми на экране кактусами.
    final private ArrayList<Cactus> cacti;
    //промежуток времени между кактусами. Задается рандомно после каждого создания.
    private double spawnTime;
    private int counter;

    public CactusSpawner() {
        spawnPosition = new Vector2(Config.WINDOW_WIDTH, Config.GROUND_LEVEL);
        cacti = new ArrayList<>();
        spawnTime = 0;
        counter = 0;
    }

    public ArrayList<Cactus> getCacti() {
        return cacti;
    }

    //создает кактусы. Вызывается каждый кадр.
    public void spawn(float gameTime) {
        if (spawnTime < gameTime) {
            //класс ThreadLocalRandom - это объеденение классов ThreadLocal и Random,
            // но последний плохо работает в многопоточной среде
            // проверка - настало ли время для спавна нового кактуса
            cacti.add(createCactus());
            spawnTime = ThreadLocalRandom.current().nextDouble(gameTime + 0.8, gameTime + 2);
        }
    }

    //создаем объект кактуса
    public Cactus createCactus() {
        Cactus cactus = new Cactus(spawnPosition.x, spawnPosition.y, counter);
        // айди последующего увеличивается на один
        counter++;
        //все кактусы двигаются в направлении игрока
        cactus.setVelocity(-Config.RUN_SPEED, 0);
        //делаем поменьше коллайдер кактуса, чтобы удобнее играть было
        cactus.setCollider(0.2f * cactus.spriteRectangle.width, 0,
                0.8f * cactus.spriteRectangle.width, cactus.spriteRectangle.height);
        return cactus;
    }

    //двигает все кактусы. Вызывается каждый кадр.
    public void updatePosition() {
        for (GameObject cactus : cacti) {
            cactus.move();
        }
    }

    //удаляет кактус, если он ушел за пределы экрана
    public void checkInvisible() {
        if (!cacti.isEmpty() && cacti.get(0).spriteRectangle.x < -Config.CACTUS_WIDTH) {
            cacti.remove(0);
        }
    }

    //проверяет столкновение кактуса с персонажем
    public boolean checkHeroCollision(Hero hero) {
        for (GameObject cactus : cacti) {
            if (cactus.checkCollision(hero)) return true;
        }
        return false;
    }

    //удаляет все кактусы. Вызывается при рестарте игры
    private void removeAll() {
        if (!cacti.isEmpty()) {
            cacti.clear();
        }
    }

    public void restart() {
        removeAll();
        spawnTime = 0;
    }
}
