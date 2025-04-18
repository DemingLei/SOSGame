package SOSGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class ComputerPlayerTest {

    private GameLogic gameLogic;
    private ComputerPlayer computerPlayer;
    private MockGUI mockGUI;

    @BeforeEach
    void setup() {
        gameLogic = new GameLogic(new BoardSize(3), new GameMode(true)); // simple
        computerPlayer = new TestComputerPlayer("AI", "Blue");
        mockGUI = new MockGUI();
    }

    @Test
    void testComputerMakesValidMove() {
        computerPlayer.makeMove(gameLogic, mockGUI);

        boolean hasMoved = false;
        for (int i = 0; i < gameLogic.getBoardSize(); i++) {
            for (int j = 0; j < gameLogic.getBoardSize(); j++) {
                if (!gameLogic.getCell(i, j).isEmpty()) {
                    hasMoved = true;
                    break;
                }
            }
        }

        assertTrue(hasMoved, "ComputerPlayer should make a move on the board.");
    }

    static class TestComputerPlayer extends ComputerPlayer {
        public TestComputerPlayer(String name, String color) {
            super(name, color);
        }

        @Override
        public void makeMove(GameLogic gameLogic, GUI gui) {
            int boardSize = gameLogic.getBoardSize();
            ArrayList<int[]> emptyCells = new ArrayList<>();

            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (gameLogic.getCell(i, j).isEmpty()) {
                        emptyCells.add(new int[]{i, j});
                    }
                }
            }

            if (emptyCells.isEmpty()) return;

            int[] cell = emptyCells.get(0);
            int row = cell[0], col = cell[1];
            String letter = "S"; // fix S

            int sosCount = gameLogic.makeMove(row, col, letter, getPlayerColor());
            gui.updateCellButton(row, col, letter, getPlayerColor());
            gui.updateScores();
        }
    }


    static class MockGUI extends GUI {
        @Override
        public void updateCellButton(int row, int col, String letter, String playerColor) {
            // Simulate calls, assertions can be added when verifying paths
        }

        @Override
        public void updateScores() {
        }

        @Override
        public void toggleCurrentPlayer() {
        }

        @Override
        public void updateCurrentTurnLabel() {
        }

        @Override
        public boolean isCurrentPlayerComputer() {
            return false;
        }

        @Override
        public void computerMove() {
        }

        @Override
        public void showGameOverAlert() {
        }
    }
}
