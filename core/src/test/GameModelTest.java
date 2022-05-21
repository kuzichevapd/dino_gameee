
import com.dinogame.Config;
import com.dinogame.model.GameModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;


public class GameModelTest {
    GameModel model;

    public void setUp() {
        model = new GameModel();
    }

    void checkDinoJump() {
        model.update(0, true);
//        assertEquals(Config.JUMP_POWER - Config.GRAVITY, model.getHero().getVelocity().y);
    }

    void testStartGame() {
//        assertEquals(GameModel.GameState.START, model.getGameState());
        model.update(0, true);
//        assertEquals(GameModel.GameState.RUN, model.getGameState());
    }

}
