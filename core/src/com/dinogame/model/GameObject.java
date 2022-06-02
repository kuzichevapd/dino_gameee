package com.dinogame.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
    // protected - поля и методы видны только в классах, лежащих в этом же пакете или в классах наследниках

    //прямоугольник, отображающий положение текстуры на экране
    protected Rectangle spriteRectangle;
    //у любого объекта есть "коллайдер" - прямоугольник для отслеживания столкновений.
    //можно отследить столкновение с другим объектом, и сделать что-то.
    protected Rectangle collider;
    //ускорение объекта
    protected Vector2 velocity;

    public GameObject(float positionX, float positionY, float width, float height) {
        spriteRectangle = new Rectangle(positionX, positionY, width, height);
        collider = new Rectangle(spriteRectangle);
        velocity = new Vector2(0, 0);
    }

    public Rectangle getCollider() {
        return collider;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    //установить коллайдер
    //x и y задаются относительно положения текстуры.
    public void setCollider(float x, float y, float width, float height) {
        collider.x = spriteRectangle.x + x;
        collider.y = spriteRectangle.y + y;
        collider.width = width;
        collider.height = height;
    }

    //функция для "телепортации" объекта на новое место в обход ускорения.
    public void changePosition(float newX, float newY) {
        float distanceX = newX - spriteRectangle.x;
        float distanceY = newY - spriteRectangle.y;
        collider.x += distanceX;
        collider.y += distanceY;
        spriteRectangle.x += distanceX;
        spriteRectangle.y += distanceY;

    }

    //функция для движения объекта в соответствии со скоростью. Вызывается каждый кадр.
    public void move() {
        spriteRectangle.x += velocity.x;
        spriteRectangle.y += velocity.y;
        collider.x += velocity.x;
        collider.y += velocity.y;
    }

    //придать объекту ускорение
    public void accelerate(float velocityX, float velocityY) {
        velocity.x += velocityX;
        velocity.y += velocityY;
    }

    //установить ускорение объекта
    public void setVelocity(float velocityX, float velocityY) {
        velocity.x = velocityX;
        velocity.y = velocityY;
    }

    //проверка на столкновение
    public boolean checkCollision(GameObject object) {
        return this.collider.overlaps(object.collider);
    }

    public Rectangle getSpriteRectangle() {
        return spriteRectangle;
    }

    public float getX() {
        return spriteRectangle.x;
    }

    public float getY() {
        return spriteRectangle.y;
    }

    public float getWidth() {
        return spriteRectangle.width;
    }


}
