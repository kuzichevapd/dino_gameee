package com.dinogame.model;

import com.badlogic.gdx.math.Vector2;
import com.dinogame.config.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class CactusSpawner {
    final private Vector2 spawnPosition;
    final private List<Cactus> cacti;
    private double spawnTime;
    private final Map<Integer, Integer> cactusIdToSpriteIndex;
    private int currentCactusId;

    public CactusSpawner() {
        spawnPosition = new Vector2(Config.WINDOW_WIDTH, Config.GROUND_LEVEL);
        cacti = new ArrayList<>();
        spawnTime = 0;
        cactusIdToSpriteIndex = new HashMap<>();
        currentCactusId = 0;
    }

    public List<Cactus> getCacti() {
        return cacti;
    }

    public void spawn(float gameTime) {
        if (spawnTime < gameTime) {
            Cactus cactus = createCactus();
            cacti.add(cactus);
            cactusIdToSpriteIndex.put(cactus.getId(),
                    ThreadLocalRandom.current().nextInt(0, 2));
            spawnTime = ThreadLocalRandom.current().nextDouble(gameTime + 0.8, gameTime + 2);
        }
    }

    public int getSpriteIndexByCactusId(int cactusId) {
        return cactusIdToSpriteIndex.get(cactusId);
    }

    public Cactus createCactus() {
        Cactus cactus = new Cactus(spawnPosition.x, spawnPosition.y, currentCactusId);
        currentCactusId++;
        cactus.setVelocity(-Config.RUN_SPEED, 0);
        cactus.setCollider(0.2f * cactus.spriteRectangle.width, 0,
                0.8f * cactus.spriteRectangle.width, cactus.spriteRectangle.height);
        return cactus;
    }

    public void updatePosition() {
        for (GameObject cactus : cacti) {
            cactus.move();
        }
    }

    public void checkInvisible() {
        if (!cacti.isEmpty() && cacti.get(0).spriteRectangle.x < -Config.CACTUS_WIDTH) {
            cacti.remove(0);
        }
    }

    public boolean checkHeroCollision(Hero hero) {
        for (GameObject cactus : cacti) {
            if (cactus.checkCollision(hero)) return true;
        }
        return false;
    }

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
