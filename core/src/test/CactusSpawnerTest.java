import com.dinogame.config.Config;
import com.dinogame.model.Cactus;
import com.dinogame.model.CactusSpawner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class CactusSpawnerTest {
    CactusSpawner cactusSpawner;

    @BeforeEach
    public void setUp() {
        cactusSpawner = new CactusSpawner();
    }

    @Test
    public void createCactusTest() {
        Cactus cactus = cactusSpawner.createCactus();
        assertEquals(0, cactus.getId());
        assertEquals(-Config.RUN_SPEED, cactus.getVelocity().x);
        assertEquals(0, cactus.getVelocity().y);
    }

    @Test
    public void spawnTest() {
        cactusSpawner.spawn(10);
        assertEquals(1, cactusSpawner.getCacti().size());
        cactusSpawner.spawn(0);
        assertEquals(1, cactusSpawner.getCacti().size());
    }

    @Test
    public void updatePositionTest() {
        cactusSpawner.spawn(10);
        cactusSpawner.updatePosition();
        assertEquals(Config.WINDOW_WIDTH - Config.RUN_SPEED,
                cactusSpawner.getCacti().get(0).getSpriteRectangle().x);
        assertEquals(Config.GROUND_LEVEL,
                cactusSpawner.getCacti().get(0).getSpriteRectangle().y);
        assertEquals(Config.WINDOW_WIDTH - Config.RUN_SPEED + 10,
                cactusSpawner.getCacti().get(0).getCollider().x);
        assertEquals(Config.GROUND_LEVEL,
                cactusSpawner.getCacti().get(0).getCollider().y);
    }

    @Test
    public void restartTest() {
        cactusSpawner.spawn(10);
        cactusSpawner.restart();
        assertTrue(cactusSpawner.getCacti().isEmpty());
    }

}
