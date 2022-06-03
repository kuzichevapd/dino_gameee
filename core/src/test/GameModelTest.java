
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
        assertEquals(0, model.getHero().getVelocity().y);
        model.startGame();
        assertEquals(GameModel.GameState.RUN, model.getGameState());
        model.doAction();
        assertEquals(0, model.getHero().getVelocity().x);
        assertEquals(Config.JUMP_POWER, model.getHero().getVelocity().y);


    }

    @Test
    public void startGameTest() {
        assertEquals(GameModel.GameState.START, model.getGameState());
        model.startGame();
        assertEquals(GameModel.GameState.RUN, model.getGameState());
    }

    @Test
    public void stopGameTest() {
        model.stopGame();
        assertEquals(GameModel.GameState.STOP, model.getGameState());
    }

    @Test
    public void restartGameTest() {
        model.restartGame();
        assertEquals(GameModel.GameState.START, model.getGameState());
        assertEquals(0, model.getGameTime());
        assertEquals(Config.START_X, model.getHeroPositionX());
        assertEquals(Config.GROUND_LEVEL, model.getHeroPositionY());
    }

}
