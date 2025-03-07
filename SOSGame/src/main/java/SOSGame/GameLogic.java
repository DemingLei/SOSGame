package SOSGame;

public class GameLogic {
    private BoardSize boardSize;
    private BoardState boardState;
    private GameMode gameMode;

    // Board State
    public GameLogic(BoardSize boardSize, GameMode gameMode) {
        this.boardSize = boardSize;
        this.gameMode = gameMode;
        boardState = new BoardState(boardSize.getSize());
    }

    // Setting letters in legal locations
    public boolean makeMove(int row, int col, String letter) {
        if (row < 0 || row >= boardSize.getSize() || col < 0 || col >= boardSize.getSize()) {
            return false;
        }
        return boardState.setCell(row, col, letter);
    }

    public String getCell(int row, int col) {
        return boardState.getCell(row, col);
    }

    public int getBoardSize() {
        return boardSize.getSize();
    }

    public boolean isSimpleGame() {
        return gameMode.isSimpleGame();
    }
}
