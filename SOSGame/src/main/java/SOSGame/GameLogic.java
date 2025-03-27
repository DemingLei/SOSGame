package SOSGame;

public class GameLogic {
    private GameState sosGame;
    private GameMode gameMode;

    public GameLogic(BoardSize boardSize, GameMode gameMode) {
        this.gameMode = gameMode;
        if (gameMode.isSimpleGame()) {
            sosGame = new SimpleGame(boardSize);
        } else {
            sosGame = new GeneralGame(boardSize);
        }
    }

    public int makeMove(int row, int col, String letter, String currentPlayer) {
        return sosGame.makeMove(row, col, letter, currentPlayer);
    }

    public String getCell(int row, int col) {
        return sosGame.getCell(row, col);
    }

    public int getBoardSize() {
        return sosGame.boardSize.getSize();
    }

    public boolean isSimpleGame() {
        return gameMode.isSimpleGame();
    }

    public boolean isGameOver() {
        return sosGame.isGameOver();
    }

    public String getWinner() {
        return sosGame.getWinner();
    }

    public int getBlueScore() {
        if (sosGame instanceof GeneralGame) {
            return ((GeneralGame) sosGame).getBlueScore();
        }
        return 0;
    }

    public int getRedScore() {
        if (sosGame instanceof GeneralGame) {
            return ((GeneralGame) sosGame).getRedScore();
        }
        return 0;
    }
}
