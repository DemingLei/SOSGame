package SOSGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GeneralGameTest {

    @Test
    public void testScoreUpdate() {
        BoardSize boardSize = new BoardSize(3);
        GeneralGame game = new GeneralGame(boardSize);

        // Blue player (0, 0), (0, 2), (0, 1)
        assertEquals(0, game.makeMove(0, 0, "S", "Blue"));
        assertEquals(0, game.makeMove(0, 2, "S", "Blue"));
        int sosCount = game.makeMove(0, 1, "O", "Blue");
        assertEquals(2, sosCount);

        // Check Blue Player Score Updates
        assertEquals(2, game.getBlueScore(), "Blue player's score should be 2");
        assertEquals(0, game.getRedScore(), "Red player's score should be 0");
    }

    @Test
    public void testGameOverAndWinner() {
        BoardSize boardSize = new BoardSize(3);
        GeneralGame game = new GeneralGame(boardSize);

        // Fill the board without forming any SOS
        String currentPlayer = "Blue";
        for (int i = 0; i < boardSize.getSize(); i++) {
            for (int j = 0; j < boardSize.getSize(); j++) {
                if (game.getCell(i, j).isEmpty()) {
                    game.makeMove(i, j, "O", currentPlayer);
                    currentPlayer = currentPlayer.equals("Blue") ? "Red" : "Blue";
                }
            }
        }
        assertTrue(game.isGameOver(), "The game should end when the board is full");
        assertEquals("Draw", game.getWinner(), "If the scores are equal, the game shall be awarded as Draw");
    }
}
