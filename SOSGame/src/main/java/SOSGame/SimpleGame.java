package SOSGame;

public class SimpleGame extends GameState {
    private boolean gameOver;
    private String winner; 

    public SimpleGame(BoardSize boardSize) {
        super(boardSize);
        gameOver = false;
        winner = "";
    }

    @Override
    protected void updateGameState(int row, int col, String letter, String currentPlayer, int sosCount) {
        
        if (sosCount > 0) {
            gameOver = true;
            winner = currentPlayer;
        } else if (isBoardFull()) {
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
        return winner.isEmpty() ? "Draw" : winner;
    }
}
