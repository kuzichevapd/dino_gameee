import com.dinogame.config.Config;
import com.dinogame.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.dinogame.model.Hero.HeroState.*;
import static org.junit.jupiter.api.Assertions.*;


public class GameObjectTest {
    Cactus cactus;
    Hero hero;
    Ground ground;

    @BeforeEach
    public void setUp() {
        cactus = new Cactus(Config.WINDOW_WIDTH, Config.GROUND_LEVEL, 1);
        hero = new Hero();
        ground = new Ground();
    }

    @Test
    public void cactusIdTest() {
        assertEquals(1, cactus.getId());
    }


    @Test
    public void heroStateTest() {
        hero.run();
        assertEquals(RUNNING, hero.getHeroState());
        hero.jump();
        assertEquals(JUMPED, hero.getHeroState());
        assertEquals(0, hero.getVelocity().x);
        assertEquals(Config.JUMP_POWER, hero.getVelocity().y);
        hero.stop();
        assertEquals(STOPPED, hero.getHeroState());
    }

    @Test
    public void groundPositionTest() {
        assertEquals(0, ground.getSpriteRectangle().x);
        assertEquals(Config.GROUND_LEVEL, ground.getSpriteRectangle().y);
        assertEquals(Config.GROUND_WIDTH, ground.getSpriteRectangle().width);
        assertEquals(Config.GROUND_HEIGHT, ground.getSpriteRectangle().height);
    }

    @Test
    public void groundVelocityTest() {
        assertEquals(-Config.RUN_SPEED, ground.getVelocity().x);
        assertEquals(0, ground.getVelocity().y);
    }

    @Test
    public void setVelocityTest() {
        ground.setVelocity(1, 1);
        assertEquals(1, ground.getVelocity().x);
        assertEquals(1, ground.getVelocity().y);
    }

    @Test
    public void accelerateTest() {
        ground.accelerate(1, 1);
        assertEquals(-Config.RUN_SPEED + 1, ground.getVelocity().x);
        assertEquals(1, ground.getVelocity().y);
    }

    @Test
    public void changePositionTest() {
        ground.changePosition(1, 250);
        assertEquals(1, ground.getSpriteRectangle().x);
        assertEquals(250, ground.getSpriteRectangle().y);
    }

    @Test
    public void groundMoveTest() {
        ground.move();
        assertEquals(-Config.RUN_SPEED, ground.getSpriteRectangle().x);
        assertEquals(Config.GROUND_LEVEL, ground.getSpriteRectangle().y);
        assertEquals(-Config.RUN_SPEED, ground.getCollider().x);
        assertEquals(Config.GROUND_LEVEL, ground.getCollider().y);
    }


}
