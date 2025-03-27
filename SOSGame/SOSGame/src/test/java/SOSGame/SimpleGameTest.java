package SOSGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SimpleGameTest {

    @Test
    public void testWinningMove() {
        BoardSize boardSize = new BoardSize(3);
        SimpleGame game = new SimpleGame(boardSize);

        // (0, 0), (0, 2), (0, 1) blue player
        assertEquals(0, game.makeMove(0, 0, "S", "Blue"));
        assertEquals(0, game.makeMove(0, 2, "S", "Blue"));
        int sosCount = game.makeMove(0, 1, "O", "Blue");
        assertEquals(2, sosCount, "should constitute an SOS sequence");
        assertTrue(game.isGameOver(), "The game should end when an SOS is formed");
        assertEquals("Blue", game.getWinner(), "Blue player should win.");
    }

    @Test
    public void testDraw() {
        BoardSize boardSize = new BoardSize(3);
        SimpleGame game = new SimpleGame(boardSize);

        // Fill the board with the same letter “S” to avoid forming an SOS sequence
        String currentPlayer = "Blue";
        int size = boardSize.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int result = game.makeMove(i, j, "S", currentPlayer);

                assertEquals(0, result, "location(" + i + "," + j + ") should not form SOS");
                currentPlayer = currentPlayer.equals("Blue") ? "Red" : "Blue";
            }
        }
        assertTrue(game.isGameOver(), "The game shall end when the board is full");
        assertEquals("Draw", game.getWinner(), "Draw should be returned when there is no winner");
    }
}
