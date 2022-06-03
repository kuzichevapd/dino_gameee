
import com.dinogame.config.Config;
import com.dinogame.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameModelTest {
    GameModel model;

    @BeforeEach
    public void setUp() {
        model = new GameModel();
    }

    @Test
    public void dinoJumpTest() {
        model.update(0);
        assertEquals(0, model.getHero().getVelocity().x);
        assertEquals(Config.JUMP_POWER - Config.GRAVITY, model.getHero().getVelocity().y);
    }

    @Test
    public void startGameTest() {
        assertEquals(GameModel.GameState.START, model.getGameState());
        model.update(0);
        assertEquals(GameModel.GameState.RUN, model.getGameState());
    }



}
