package SOSGame;

public abstract class Player {
    protected String playerName;
    protected String playerColor;

    public Player(String playerName, String playerColor) {
        this.playerName = playerName;
        this.playerColor = playerColor;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public abstract void makeMove(GameLogic gameLogic, GUI gui);
}
