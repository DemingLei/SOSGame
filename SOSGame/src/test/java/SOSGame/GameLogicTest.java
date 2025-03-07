package SOSGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GameLogicTest {

    @Test
    // Test Effective Movement
    void testValidMoveSample() {
        // Use 3x3 board, simple mode
        BoardSize boardSize = new BoardSize(3);
        GameMode gameMode = new GameMode(true);
        GameLogic gameLogic = new GameLogic(boardSize, gameMode);

        // put S on (0,0)
        boolean moveResult = gameLogic.makeMove(0, 0, "S");
        assertTrue(moveResult, "Fill in the letters in the valid positions successfully");
        assertEquals("S", gameLogic.getCell(0, 0), "The cell after the drop should show 'S'");
    }

    @Test
        // Test Effective Movement
    void testValidMoveGeneral() {
        // Use 3x3 board, false mode
        BoardSize boardSize = new BoardSize(3);
        GameMode gameMode = new GameMode(false);
        GameLogic gameLogic = new GameLogic(boardSize, gameMode);

        // put S on (0,0)
        boolean moveResult = gameLogic.makeMove(0, 0, "S");
        assertTrue(moveResult, "Fill in the letters in the valid positions successfully");
        assertEquals("S", gameLogic.getCell(0, 0), "The cell after the drop should show 'S'");
    }


    @Test
    // Verify that the drop operation fails for coordinates that are outside the range of the board
    void testInvalidMoveOutOfBoundsSample() {
        BoardSize boardSize = new BoardSize(3);
        GameMode gameMode = new GameMode(true);
        GameLogic gameLogic = new GameLogic(boardSize, gameMode);


        assertFalse(gameLogic.makeMove(3, 0, "O"), "Failure to fill in letters beyond the range of the board");
        assertFalse(gameLogic.makeMove(-1, 0, "S"), "Negative coordinate fill in letters should fail");
    }

    @Test
        // Verify that the drop operation fails for coordinates that are outside the range of the board
    void testInvalidMoveOutOfBoundsGeneral() {
        BoardSize boardSize = new BoardSize(3);
        GameMode gameMode = new GameMode(false);
        GameLogic gameLogic = new GameLogic(boardSize, gameMode);


        assertFalse(gameLogic.makeMove(3, 0, "O"), "Failure to fill in letters beyond the range of the board");
        assertFalse(gameLogic.makeMove(-1, 0, "S"), "Negative coordinate fill in letters should fail");
    }

    @Test
    // Verify that letters cannot be filled in repeatedly in the same cell
    void testMoveOnOccupiedCellSample() {
        BoardSize boardSize = new BoardSize(3);
        GameMode gameMode = new GameMode(true);
        GameLogic gameLogic = new GameLogic(boardSize, gameMode);


        assertTrue(gameLogic.makeMove(1, 1, "O"), "First letter entered at (1,1) succeeds");

        assertFalse(gameLogic.makeMove(1, 1, "S"), "Falling on occupied cells should fail");
        assertEquals("O", gameLogic.getCell(1, 1), "Cell content should still be 'O'");
    }

    @Test
        // Verify that letters cannot be filled in repeatedly in the same cell
    void testMoveOnOccupiedCellGeneral() {
        BoardSize boardSize = new BoardSize(3);
        GameMode gameMode = new GameMode(false);
        GameLogic gameLogic = new GameLogic(boardSize, gameMode);


        assertTrue(gameLogic.makeMove(1, 1, "O"), "First letter entered at (1,1) succeeds");

        assertFalse(gameLogic.makeMove(1, 1, "S"), "Falling on occupied cells should fail");
        assertEquals("O", gameLogic.getCell(1, 1), "Cell content should still be 'O'");
    }

    @Test
    // Ensure that GameLogic correctly communicates and reflects game mode settings
    void testGameModeInGameLogicSample() {
        BoardSize boardSize = new BoardSize(3);
        GameMode gameMode = new GameMode(false);
        GameLogic gameLogic = new GameLogic(boardSize, gameMode);
        assertFalse(gameLogic.isSimpleGame(), "GameLogic should return the game mode passed in (synthesized mode is false)");
    }
}

