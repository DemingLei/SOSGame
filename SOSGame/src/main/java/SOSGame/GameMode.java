package SOSGame;

public class GameMode {
    // Using boolean to define: true for simple mode, false for synthesized mode
    private boolean simpleGame;

    public GameMode(boolean simpleGame) {
        this.simpleGame = simpleGame;
    }

    public boolean isSimpleGame() {
        return simpleGame;
    }
}

