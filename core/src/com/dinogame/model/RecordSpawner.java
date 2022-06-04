package com.dinogame.model;

import com.badlogic.gdx.math.Vector2;
import com.dinogame.config.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RecordSpawner {
    final private Vector2 spawnPosition;
    private double spawnTime;
    final private Map<Integer, Integer> recordToId;
    private int currentRecordId;
    private final List<Record> recordsList;

    public RecordSpawner() {
        spawnPosition = new Vector2(Config.WINDOW_WIDTH, Config.GROUND_LEVEL - 70);
        spawnTime = 0;
        recordToId = new HashMap<>();
        currentRecordId = 0;
        recordsList = new ArrayList<>();
    }

    public Record createRecord() {
        Record record  = new Record(spawnPosition.x, spawnPosition.y, currentRecordId);
        currentRecordId++;
        record.setVelocity(-Config.RUN_SPEED, 0);
        return record;
    }

    public void spawnRecord(float gameTime) {
        if (spawnTime < gameTime) {
            Record record = createRecord();
            recordsList.add(record);
            recordToId.put(record.getId(),
                    ThreadLocalRandom.current().nextInt(0, 2));
            spawnTime = ThreadLocalRandom.current().nextDouble(gameTime + 0.8, gameTime + 10);
        }
    }

    public void updateRecordPosition() {
        for (GameObject currentRecord : recordsList) {
            currentRecord.move();
        }
    }

    public void checkRecordOutcome() {
        if (!recordsList.isEmpty() && recordsList.get(0).spriteRectangle.x < -Config.CACTUS_WIDTH) {
            recordsList.remove(0);
        }
    }

    private void removeAll() {
        if (!recordsList.isEmpty()) {
            recordsList.clear();
        }
    }

    public void restart() {
        removeAll();
        spawnTime = 0;
    }

    public List<Record> getRecordsList() {
        return recordsList;
    }

    public int getSpriteIndexByRecordId(int recordId) {
        return recordToId.get(recordId);
    }



}
