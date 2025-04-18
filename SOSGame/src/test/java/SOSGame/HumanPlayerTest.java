package SOSGame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {

    @Test
    void testHumanPlayerInitialization() {
        HumanPlayer player = new HumanPlayer("Alice", "Red");
        assertEquals("Alice", player.getPlayerName());
        assertEquals("Red", player.getPlayerColor());
    }

    @Test
    void testMakeMoveDoesNothing() {
        HumanPlayer player = new HumanPlayer("Alice", "Red");
        player.makeMove(null, null); //As long as no exceptions are thrown, since this method is implemented as null
    }
}
