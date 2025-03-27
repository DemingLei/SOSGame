package SOSGame;

public class GeneralGame extends GameState {
    private boolean gameOver;
    private int blueScore;
    private int redScore;

    public GeneralGame(BoardSize boardSize) {
        super(boardSize);
        this.gameOver = false;
        this.blueScore = 0;
        this.redScore = 0;
    }

    @Override
    // Score statistics
    protected void updateGameState(int row, int col, String letter, String currentPlayer, int sosCount) {
        if (currentPlayer.equals("Blue")) {
            blueScore += sosCount;
        } else if (currentPlayer.equals("Red")) {
            redScore += sosCount;
        }
        // Game over if full
        if (isBoardFull()) {
            gameOver = true;
        }
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public String getWinner() {
        if (!gameOver) return "";
        if (blueScore > redScore) {
            return "Blue";
        } else if (redScore > blueScore) {
            return "Red";
        } else {
            return "Draw";
        }
    }

    // Get blue score
    public int getBlueScore() {
        return blueScore;
    }

    // Get red score
    public int getRedScore() {
        return redScore;
    }
}
