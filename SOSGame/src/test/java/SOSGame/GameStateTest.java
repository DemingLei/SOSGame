package SOSGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameStateTest {

    // 用于测试的简单子类
    class DummyGameState extends GameState {
        public DummyGameState(BoardSize boardSize) {
            super(boardSize);
        }
        @Override
        protected void updateGameState(int row, int col, String letter, String currentPlayer, int sosCount) {

        }
        @Override
        public boolean isGameOver() {
            return false;
        }
        @Override
        public String getWinner() {
            return "";
        }
    }

    private BoardSize boardSize;
    private DummyGameState gameState;

    @BeforeEach
    public void setUp() {
        boardSize = new BoardSize(3);
        gameState = new DummyGameState(boardSize);
    }

    @Test
    public void testMakeMoveOutOfBounds() {
        int result = gameState.makeMove(-1, 0, "S", "Blue");
        assertEquals(-1, result, "Out of range lines should return -1");

        result = gameState.makeMove(0, 3, "O", "Red");
        assertEquals(-1, result, "Out of range columns should return -1");
    }

    @Test
    public void testMakeMoveOccupiedCell() {
        // (0,0)
        int result = gameState.makeMove(0, 0, "S", "Blue");
        assertEquals(0, result, "First drop should return 0");

        // (0, 0) again
        result = gameState.makeMove(0, 0, "O", "Red");
        assertEquals(-1, result, "Occupied grid drop should return -1");
    }

    @Test
    public void testCountSOSForO() {
        // (0, 0), (0, 2)
        assertEquals(0, gameState.makeMove(0, 0, "S", "Blue"));
        assertEquals(0, gameState.makeMove(0, 2, "S", "Blue"));
        // (0, 1)
        int sosCount = gameState.makeMove(0, 1, "O", "Blue");
        assertEquals(2, sosCount);
    }

    @Test
    public void testCountSOSForS() {
        // (0, 2), (0, 1)
        assertEquals(0, gameState.makeMove(0, 2, "S", "Blue"));
        assertEquals(0, gameState.makeMove(0, 1, "O", "Blue"));
        // (0, 0)
        int sosCount = gameState.makeMove(0, 0, "S", "Blue");
        assertEquals(2, sosCount);
    }

    @Test
    public void testGetCell() {
        // Checking the initial state
        assertTrue(gameState.getCell(1, 1).isEmpty(), "Initially (1,1) should be empty");
        // After the drop (1,1) should be updated to the corresponding letter
        gameState.makeMove(1, 1, "O", "Red");
        assertEquals("O", gameState.getCell(1, 1), "After the drop (1,1) should be O");
    }
}
