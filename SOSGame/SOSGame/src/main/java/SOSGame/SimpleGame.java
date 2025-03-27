package SOSGame;

public class SimpleGame extends GameState {
    private boolean gameOver;
    private String winner; // "Blue"、"Red" 或留空表示平局

    public SimpleGame(BoardSize boardSize) {
        super(boardSize);
        gameOver = false;
        winner = "";
    }

    @Override
    protected void updateGameState(int row, int col, String letter, String currentPlayer, int sosCount) {
        // 若本次落子产生至少一条 SOS，则游戏结束，当前玩家获胜
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
