
import com.dinogame.Config;
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
    public void checkDinoJump() {
        model.update(0, true);
        assertEquals(Config.JUMP_POWER - Config.GRAVITY, model.getHero().getVelocity().y);
    }

    @Test
    public void testStartGame() {
        assertEquals(GameModel.GameState.START, model.getGameState());
        model.update(0, true);
        assertEquals(GameModel.GameState.RUN, model.getGameState());
    }



}
