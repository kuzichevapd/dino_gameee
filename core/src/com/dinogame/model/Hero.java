package com.dinogame.model;

import com.dinogame.config.Config;

public class Hero extends GameObject {
    private HeroState heroState;

    public Hero() {
        super(Config.START_X, Config.GROUND_LEVEL, Config.HERO_WIDTH, Config.HERO_HEIGHT);
        heroState = HeroState.STOPPED;
        setCollider(16, 16, 41, 71);
    }

    public HeroState getHeroState() {
        return heroState;
    }

    public void jump() {
        if (heroState != HeroState.JUMPED) {
            setVelocity(0, Config.JUMP_POWER);
            heroState = HeroState.JUMPED;
        }
    }

    public void run() {
        heroState = HeroState.RUNNING;
    }

    public void stop() {
        heroState = HeroState.STOPPED;
    }

    public enum HeroState {
        JUMPED, RUNNING, STOPPED
    }
}
