package com.dinogame.model;

import com.dinogame.Config;

public class Ground extends GameObject {
    public Ground() {
        super(0, Config.GROUND_LEVEL, Config.GROUND_WIDTH, Config.GROUND_HEIGHT);
        setVelocity(-Config.RUN_SPEED, 0);
        setCollider(0, 0, collider.width * 2, collider.height);
    }

    //двигаем землю. Если первая земля ушла за экран, перемещаем земли на изначальные координаты. Вызывается каждый кадр.
    @Override
    public void move() {
        super.move();
        if (spriteRectangle.x <= -spriteRectangle.width) {
            changePosition(0, spriteRectangle.y);
        }
    }
}
