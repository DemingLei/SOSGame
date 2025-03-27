package SOSGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameLogicTest {

    @Test
    public void testValidMoveSimpleGame() {
        BoardSize boardSize = new BoardSize(3);
        GameMode gameMode = new GameMode(true); // Simple Game Mode
        GameLogic logic = new GameLogic(boardSize, gameMode);

        // Place “S” at (0,0)
        int result = logic.makeMove(0, 0, "S", "Blue");
        assertEquals(0, result, "A single-step drop should not create an SOS");
        assertEquals("S", logic.getCell(0, 0), "Location (0,0) should be S");
    }

    @Test
    public void testInvalidMoveOutOfBounds() {
        BoardSize boardSize = new BoardSize(3);
        GameMode gameMode = new GameMode(true);
        GameLogic logic = new GameLogic(boardSize, gameMode);

        // Subscript crossing test
        int result = logic.makeMove(-1, 0, "S", "Blue");
        assertEquals(-1, result, "Out of range drops should return -1");
    }

    @Test
    public void testInvalidMoveOccupiedCell() {
        BoardSize boardSize = new BoardSize(3);
        GameMode gameMode = new GameMode(true);
        GameLogic logic = new GameLogic(boardSize, gameMode);

        // First land at (0,0)
        int firstMove = logic.makeMove(0, 0, "S", "Blue");
        assertEquals(0, firstMove);
        // A further drop at the same position should return -1
        int secondMove = logic.makeMove(0, 0, "O", "Red");
        assertEquals(-1, secondMove, "Repeated drops in the same position should return -1");
    }
}
