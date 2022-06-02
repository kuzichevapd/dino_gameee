package com.dinogame.model;

import com.dinogame.Config;

public class Cactus extends GameObject {
    private final int id;

    public Cactus(float x, float y, int id) {
        super(x, y, Config.CACTUS_WIDTH, Config.CACTUS_HEIGHT);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
