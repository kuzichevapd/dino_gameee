package com.dinogame.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dinogame.Config;
import com.dinogame.model.Cactus;
import com.dinogame.model.CactusSpawner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class CactusView {
    Map<Integer, Integer> spritesById;
    final private CactusSpawner spawner;
    //список со спрайтами кактусов
    final private ArrayList<Texture> sprites;
    private int firstId;

    public CactusView(CactusSpawner spawner) {
        this.spawner = spawner;
        sprites = new ArrayList<>();
        sprites.add(new Texture(Config.CACTUS_1_SPRITE_NAME));
        sprites.add(new Texture(Config.CACTUS_2_SPRITE_NAME));
        spritesById = new HashMap<>();
    }

    public void drawCactus(SpriteBatch batch, Cactus cactus) {
        int id = cactus.getId();
        if (spritesById.get(id) == null) {
            // привязка ранодомного спрайта кактуса к айди
            int randIndex = ThreadLocalRandom.current().nextInt(0, sprites.size());
            spritesById.put(id, randIndex);
        }
        // текстура - изображение, которое было декодировано из исходного формата (например PNG)
        // и загружено в видео память графического процессора
        Texture sprite = sprites.get(spritesById.get(id));
        // отрисовка текстуры спрайта этого кактуса с указанными координатами объекта GameObject
        batch.draw(sprite, cactus.getSpriteRectangle().x, cactus.getSpriteRectangle().y);
    }

    //отрисовываем все кактусы. Вызывается каждый кадр.
    public void drawAll(SpriteBatch batch) {
        updateFirstId();
        for (Cactus cactus : spawner.getCacti()) {
            drawCactus(batch, cactus);
        }
    }

    private void updateFirstId() {
        if (spawner.getCacti().size() > 0) {
            // индекс последнего созданного кактуса
            int lastIndex = spawner.getCacti().size() - 1;
            // айди последнего созданного кактуса
            int lastId = spawner.getCacti().get(lastIndex).getId();
            // если айди первого кактуса не равен ранее запомненному айди,
            // то это значит, что кактус удален из общего массива кактусов в CactusSpawner
            if (lastId - lastIndex != firstId) {
                spritesById.remove(firstId);
                firstId = lastId - lastIndex;
            }
        }
    }

    public void dispose() {
        sprites.get(0).dispose();
        sprites.get(1).dispose();
    }


}
