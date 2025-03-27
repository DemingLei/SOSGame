package SOSGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GameModeTest {

    @Test
    // Verify that isSimpleGame() returns true when true is passed
    void testSimpleGameMode() {
        GameMode gameMode = new GameMode(true);
        assertTrue(gameMode.isSimpleGame(), "Under sample game mode isSimpleGame() should return true");
    }

    @Test
    // Verify that isSimpleGame() returns false when false is passed
    void testGeneralGameMode() {
        GameMode gameMode = new GameMode(false);
        assertFalse(gameMode.isSimpleGame(), "Under general game mode isSimpleGame() should return false");
    }
}
