package com.dinogame.model;

import com.dinogame.config.Config;

public class Record extends GameObject{
    private final int id;

    public Record(float x, float y, int id) {
        super(x, y, Config.CIRCLE_WIDTH, Config.CIRCLE_HEIGHT);
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
