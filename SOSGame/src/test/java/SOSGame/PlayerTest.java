package SOSGame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    static class DummyPlayer extends Player {
        public DummyPlayer(String name, String color) {
            super(name, color);
        }

        @Override
        public void makeMove(GameLogic gameLogic, GUI gui) {
            // do nothing
        }
    }

    @Test
    void testPlayerProperties() {
        Player player = new DummyPlayer("Test", "Blue");
        assertEquals("Test", player.getPlayerName());
        assertEquals("Blue", player.getPlayerColor());
    }
}
