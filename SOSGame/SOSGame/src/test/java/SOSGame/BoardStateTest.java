package SOSGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BoardStateTest {

    @Test
    // Verify that each cell is an empty string when the board is initialized
    void testInitialBoardIsEmpty() {
        int size = 3;
        BoardState boardState = new BoardState(size);

        //Create a 3×3 board, then traverse all the cells and check if each position is an empty string by getCell(i, j)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertEquals("", boardState.getCell(i, j), "初始时每个单元格都应为空");
            }
        }
    }

    @Test
    // Validation returns success when dropping a letter on an empty cell
    void testSetCellSuccess() {
        BoardState boardState = new BoardState(3);
        // Put “S” in the (1,1) position
        boolean result = boardState.setCell(1, 1, "S");
        assertTrue(result, "Putting letters in empty cells succeeds");
        assertEquals("S", boardState.getCell(1, 1), "The cell content should be 'S'");
    }

    @Test
    // Verify that if the cell already has a letter, dropping it again will fail
    void testSetCellFailureOnOccupiedCell() {
        BoardState boardState = new BoardState(3);

        boardState.setCell(1, 1, "O");

        boolean result = boardState.setCell(1, 1, "S");
        assertFalse(result, "Falling on occupied cells should fail.");
        assertEquals("O", boardState.getCell(1, 1), "The cell content should be 'O'");
    }
}
