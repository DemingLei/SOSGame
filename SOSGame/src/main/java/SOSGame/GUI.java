package SOSGame;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;


public class GUI extends Application {
    private GameLogic gameLogic;
    private Button[][] boardButtons;
    private GridPane gameTable;
    private String currentPlayer;

    // Controls
    private RadioButton blueS, blueO;
    private RadioButton redS, redO;
    private RadioButton blueHuman, blueComputer;
    private RadioButton redHuman, redComputer;
    private TextField boardSizeNumber;
    private RadioButton simpleGame, generalGame;
    private Label currentTurn;

    // Score label
    private Label blueScoreLabel, redScoreLabel;

    // Player panels
    private VBox bluePlayerSheet;
    private VBox redPlayerSheet;

    // Player objects
    private Player bluePlayer;
    private Player redPlayer;

    // Marker: Whether the game over hint box has been displayed or not
    private boolean gameOverAlertShown = false;

    @Override
    public void start(Stage primaryStage) {

        // Game Options Area
        HBox topBoard = new HBox(20);
        topBoard.setPadding(new Insets(20));
        topBoard.setAlignment(Pos.CENTER);

        ToggleGroup gameTypes = new ToggleGroup();
        simpleGame = new RadioButton("Simple game");
        generalGame = new RadioButton("General game");
        simpleGame.setToggleGroup(gameTypes);
        generalGame.setToggleGroup(gameTypes);
        simpleGame.setSelected(true);

        Label boardSizeLabel = new Label("Board size:");
        boardSizeNumber = new TextField("9");
        boardSizeNumber.setPrefWidth(40);

        topBoard.getChildren().addAll(simpleGame, generalGame, boardSizeLabel, boardSizeNumber);

        // blue board
        bluePlayerSheet = new VBox(10);
        bluePlayerSheet.setPadding(new Insets(10));
        bluePlayerSheet.setAlignment(Pos.CENTER);

        Label bluePlayerLabel = new Label("Blue player");
        ToggleGroup bluePlayerType = new ToggleGroup();
        blueHuman = new RadioButton("Human");
        blueComputer = new RadioButton("Computer");
        blueHuman.setToggleGroup(bluePlayerType);
        blueComputer.setToggleGroup(bluePlayerType);
        blueHuman.setSelected(true);

        ToggleGroup blueSOLetter = new ToggleGroup();
        blueS = new RadioButton("S");
        blueO = new RadioButton("O");
        blueS.setToggleGroup(blueSOLetter);
        blueO.setToggleGroup(blueSOLetter);
        blueS.setSelected(true);

        blueHuman.setOnAction(e -> {
            blueS.setDisable(false);
            blueO.setDisable(false);
        });
        blueComputer.setOnAction(e -> {
            blueS.setDisable(true);
            blueO.setDisable(true);
        });

        bluePlayerSheet.getChildren().addAll(bluePlayerLabel, blueHuman, blueS, blueO, blueComputer);
        blueScoreLabel = new Label("Score: 0");
        bluePlayerSheet.getChildren().add(blueScoreLabel);

        // red board
        redPlayerSheet = new VBox(10);
        redPlayerSheet.setPadding(new Insets(10));
        redPlayerSheet.setAlignment(Pos.CENTER);

        Label redPlayerLabel = new Label("Red player");
        ToggleGroup redPlayerType = new ToggleGroup();
        redHuman = new RadioButton("Human");
        redComputer = new RadioButton("Computer");
        redHuman.setToggleGroup(redPlayerType);
        redComputer.setToggleGroup(redPlayerType);
        redHuman.setSelected(true);

        ToggleGroup redSOLetter = new ToggleGroup();
        redS = new RadioButton("S");
        redO = new RadioButton("O");
        redS.setToggleGroup(redSOLetter);
        redO.setToggleGroup(redSOLetter);
        redS.setSelected(true);

        redHuman.setOnAction(e -> {
            redS.setDisable(false);
            redO.setDisable(false);
        });
        redComputer.setOnAction(e -> {
            redS.setDisable(true);
            redO.setDisable(true);
        });

        redPlayerSheet.getChildren().addAll(redPlayerLabel, redHuman, redS, redO, redComputer);
        redScoreLabel = new Label("Score: 0");
        redPlayerSheet.getChildren().add(redScoreLabel);

        // board area
        gameTable = new GridPane();
        gameTable.setPadding(new Insets(20));
        gameTable.setAlignment(Pos.CENTER);
        gameTable.setGridLinesVisible(true);

        // bottom control area
        HBox bottomWindow = new HBox(20);
        bottomWindow.setPadding(new Insets(10));
        bottomWindow.setAlignment(Pos.CENTER);

        CheckBox recordGame = new CheckBox("Record game");
        currentTurn = new Label("Current turn: ");
        Button replayButton = new Button("Replay");
        replayButton.setDisable(true);
        Button newGameButton = new Button("New Game");

        bottomWindow.getChildren().addAll(recordGame, currentTurn, replayButton, newGameButton);

        BorderPane root = new BorderPane();
        root.setTop(topBoard);
        root.setLeft(bluePlayerSheet);
        root.setRight(redPlayerSheet);
        root.setCenter(gameTable);
        root.setBottom(bottomWindow);

        newGameButton.setOnAction(e -> startNewGame());

        Scene scene = new Scene(root, 700, 700);
        primaryStage.setTitle("SOS GAME");
        primaryStage.setScene(scene);
        primaryStage.show();

        startNewGame();
    }

    // Reset the board when starting a new game
    private void startNewGame() {
        gameOverAlertShown = false;
        int size = 9;
        try {
            size = Integer.parseInt(boardSizeNumber.getText());
        } catch (NumberFormatException ex) {
            boardSizeNumber.setText("9");
            size = 9;
        }
        if (size <= 2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Board Size");
            alert.setHeaderText("Illegal Board Size");
            alert.setContentText("Please re-type a valid size");
            alert.showAndWait();
            return;
        }

        boolean isSimple = simpleGame.isSelected();
        gameLogic = new GameLogic(new BoardSize(size), new GameMode(isSimple));
        currentPlayer = "Blue";
        updateCurrentTurnLabel();
        updateScores();

        // Determine player type based on radio buttons
        if (blueHuman.isSelected()) {
            bluePlayer = new HumanPlayer("Blue", "Blue");
        } else {
            bluePlayer = new ComputerPlayer("Blue", "Blue");
        }
        if (redHuman.isSelected()) {
            redPlayer = new HumanPlayer("Red", "Red");
        } else {
            redPlayer = new ComputerPlayer("Red", "Red");
        }

        // Rebuild Board Buttons
        gameTable.getChildren().clear();
        boardButtons = new Button[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Button cellButton = new Button();
                cellButton.setPrefSize(40, 40);
                cellButton.setStyle("-fx-border-color: purple; -fx-font-size: 16px;");
                final int r = row;
                final int c = col;
                cellButton.setOnAction(e -> handleCellClick(r, c, cellButton));
                boardButtons[row][col] = cellButton;
                gameTable.add(cellButton, col, row);
            }
        }


        if (isCurrentPlayerComputer()) {
            computerMove();
        }
    }

    // Cell clicking in human-machine matchmaking: only works for human players
    private void handleCellClick(int row, int col, Button cellButton) {
        if (!gameLogic.getCell(row, col).isEmpty() || gameLogic.isGameOver()) {
            return;
        }
        String letter = "";
        if (currentPlayer.equals("Blue")) {
            if (!blueHuman.isSelected()) {
                return;
            }
            letter = blueS.isSelected() ? "S" : "O";
            cellButton.setStyle(cellButton.getStyle() + " -fx-text-fill: blue;");
        } else {
            if (!redHuman.isSelected()) {
                return;
            }
            letter = redS.isSelected() ? "S" : "O";
            cellButton.setStyle(cellButton.getStyle() + " -fx-text-fill: red;");
        }
        int sosCount = gameLogic.makeMove(row, col, letter, currentPlayer);
        if (sosCount < 0) {
            return;
        }
        cellButton.setText(letter);
        if (sosCount == 0) {
            toggleCurrentPlayer();
        }
        updateCurrentTurnLabel();
        updateScores();
        if (gameLogic.isGameOver()) {
            showGameOverAlert();
        } else if (isCurrentPlayerComputer()) {
            computerMove();
        }
    }

    // Delay 0.5 seconds
    public void computerMove() {
        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
        delay.setOnFinished(e -> {
            if (currentPlayer.equals("Blue")) {
                bluePlayer.makeMove(gameLogic, this);
            } else {
                redPlayer.makeMove(gameLogic, this);
            }
        });
        delay.play();
    }

    // Determine if the current player is a computer
    public boolean isCurrentPlayerComputer() {
        if (currentPlayer.equals("Blue")) {
            return blueComputer.isSelected();
        } else {
            return redComputer.isSelected();
        }
    }

    // Updating the button display for a given cell
    public void updateCellButton(int row, int col, String letter, String playerColor) {
        boardButtons[row][col].setText(letter);
        if (playerColor.equals("Blue")) {
            boardButtons[row][col].setStyle(boardButtons[row][col].getStyle() + " -fx-text-fill: blue;");
        } else {
            boardButtons[row][col].setStyle(boardButtons[row][col].getStyle() + " -fx-text-fill: red;");
        }
    }

    // Switch current player
    public void toggleCurrentPlayer() {
        currentPlayer = currentPlayer.equals("Blue") ? "Red" : "Blue";
    }

    // Update current round display
    public void updateCurrentTurnLabel() {
        currentTurn.setText("Current turn: " + currentPlayer);
        if (currentPlayer.equals("Blue")) {
            currentTurn.setStyle("-fx-text-fill: blue; -fx-font-weight: bold;");
            bluePlayerSheet.setStyle("-fx-border-color: blue; -fx-border-width: 2px; -fx-background-color: #d0eaff;");
            redPlayerSheet.setStyle("");
        } else {
            currentTurn.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            redPlayerSheet.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-color: #ffd0d0;");
            bluePlayerSheet.setStyle("");
        }
    }

    // Updated score display (for general mode)
    public void updateScores() {
        if (generalGame.isSelected()) {
            blueScoreLabel.setText("Score: " + gameLogic.getBlueScore());
            redScoreLabel.setText("Score: " + gameLogic.getRedScore());
        } else {
            blueScoreLabel.setText("");
            redScoreLabel.setText("");
        }
    }


    public void showGameOverAlert() {
        if (!gameOverAlertShown) {
            gameOverAlertShown = true;
            Platform.runLater(() -> {
                String winner = gameLogic.getWinner();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Over");
                if (winner.equals("Draw") || winner.isEmpty()) {
                    alert.setHeaderText("Game Over --- Draw!");
                } else {
                    alert.setHeaderText("Game Over --- " + winner + " wins!");
                }
                alert.setContentText("Try a new game!");
                alert.showAndWait();
            });
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
