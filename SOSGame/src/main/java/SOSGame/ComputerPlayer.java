package SOSGame;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class ComputerPlayer extends Player {
    private Random random;

    public ComputerPlayer(String name, String color) {
        super(name, color);
        random = new Random();
    }

    @Override
    public void makeMove(GameLogic gameLogic, GUI gui) {
        int boardSize = gameLogic.getBoardSize();
        ArrayList<int[]> emptyCells = new ArrayList<>();

        // Collect all spaces
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (gameLogic.getCell(i, j).isEmpty()) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }

        if (emptyCells.isEmpty()) {
            return;
        }

        // Simple strategy: randomly select a space and randomly select “S” or “O”
        int[] cell = emptyCells.get(random.nextInt(emptyCells.size()));
        int row = cell[0];
        int col = cell[1];
        String letter = random.nextBoolean() ? "S" : "O";

        int sosCount = gameLogic.makeMove(row, col, letter, playerColor);
        gui.updateCellButton(row, col, letter, playerColor);
        // Updated score display
        gui.updateScores();

        // Delay 0.5 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        if (sosCount > 0 && !gameLogic.isGameOver()) {
            // If this constitutes an SOS, move consecutively.
            pause.setOnFinished(e -> {
                this.makeMove(gameLogic, gui);
            });
            pause.play();
        } else {
            pause.setOnFinished(e -> {
                gui.toggleCurrentPlayer();
                gui.updateCurrentTurnLabel();
                // If the next player is a computer, automatic move after a time delay
                if (!gameLogic.isGameOver() && gui.isCurrentPlayerComputer()) {
                    gui.computerMove();
                }
            });
            pause.play();
        }

        if (gameLogic.isGameOver()) {
            gui.showGameOverAlert();
        }
    }
}
