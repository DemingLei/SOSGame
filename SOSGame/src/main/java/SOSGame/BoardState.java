package SOSGame;

public class BoardState {
    private String[][] board;

    public BoardState(int size) {
        board = new String[size][size];
        // Initialize the board with an empty string for each square
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "";
            }
        }
    }

    // If the cell is empty, place a letter S or o and return true
    public boolean setCell(int row, int col, String letter) {
        if (board[row][col].isEmpty()) {
            board[row][col] = letter;
            return true;
        }
        return false;
    }

    public String getCell(int row, int col) {
        return board[row][col];
    }
}

