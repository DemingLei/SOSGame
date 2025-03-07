package SOSGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI extends Application {
    private GameLogic gameLogic;
    private Button[][] boardButtons;
    private GridPane gameTable;
    private String currentPlayer; // BluePlayer and RedPlayer

    // Controls
    private RadioButton blueS, blueO;
    private RadioButton redS, redO;
    private RadioButton blueHuman, blueComputer;
    private RadioButton redHuman, redComputer;
    private TextField boardSizeNumber;
    private RadioButton simpleGame, generalGame;
    private Label currentTurn;

    // Highlight the blue and red player panels
    private VBox bluePlayerSheet;
    private VBox redPlayerSheet;

    @Override
    public void start(Stage primaryStage) {
        // Top: Game options area
        HBox topBoard = new HBox(20);
        topBoard.setPadding(new Insets(20));
        topBoard.setAlignment(Pos.CENTER);

        // Game mode
        ToggleGroup gameTypes = new ToggleGroup();
        simpleGame = new RadioButton("Simple game");
        generalGame = new RadioButton("General game");
        simpleGame.setToggleGroup(gameTypes);
        generalGame.setToggleGroup(gameTypes);
        simpleGame.setSelected(true);

        // Board size
        Label boardSizeLabel = new Label("Board size:");
        boardSizeNumber = new TextField("9");
        boardSizeNumber.setPrefWidth(40);

        topBoard.getChildren().addAll(simpleGame, generalGame, boardSizeLabel, boardSizeNumber);

        // Left: Blue player
        bluePlayerSheet = new VBox(10);
        bluePlayerSheet.setPadding(new Insets(10));
        bluePlayerSheet.setAlignment(Pos.CENTER);

        // Control options: Human or Computer
        Label bluePlayerLabel = new Label("Blue player");
        ToggleGroup bluePlayerType = new ToggleGroup();
        blueHuman = new RadioButton("Human");
        blueComputer = new RadioButton("Computer");
        blueHuman.setToggleGroup(bluePlayerType);
        blueComputer.setToggleGroup(bluePlayerType);
        blueHuman.setSelected(true);

        // S and O button
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

        // Right: Red Player
        redPlayerSheet = new VBox(10);
        redPlayerSheet.setPadding(new Insets(10));
        redPlayerSheet.setAlignment(Pos.CENTER);

        // Control options: Human or Computer
        Label redPlayerLabel = new Label("Red player");
        ToggleGroup redPlayerType = new ToggleGroup();
        redHuman = new RadioButton("Human");
        redComputer = new RadioButton("Computer");
        redHuman.setToggleGroup(redPlayerType);
        redComputer.setToggleGroup(redPlayerType);
        redHuman.setSelected(true);

        //S and O button
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

        // Game Board
        gameTable = new GridPane();
        gameTable.setPadding(new Insets(20));
        gameTable.setAlignment(Pos.CENTER);
        gameTable.setGridLinesVisible(true);

        // Bottom control Buttons
        HBox bottomWindow = new HBox(20);
        bottomWindow.setPadding(new Insets(10));
        bottomWindow.setAlignment(Pos.CENTER);

        CheckBox recordGame = new CheckBox("Record game");
        currentTurn = new Label("Current turn: ");
        Button replayButton = new Button("Replay");
        replayButton.setDisable(true);
        Button newGameButton = new Button("New Game");

        bottomWindow.getChildren().addAll(recordGame, currentTurn, replayButton, newGameButton);

        // Master Layout
        BorderPane root = new BorderPane();
        root.setTop(topBoard);
        root.setLeft(bluePlayerSheet);
        root.setRight(redPlayerSheet);
        root.setCenter(gameTable);
        root.setBottom(bottomWindow);

        // New Game Button
        newGameButton.setOnAction(e -> startNewGame());

        Scene scene = new Scene(root, 700, 700);
        primaryStage.setTitle("SOS GAME");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start a New Game
        startNewGame();
    }

    // A judgment that the size of the board must be greater than 2
    private void startNewGame() {
        int size = 9;
        try {
            size = Integer.parseInt(boardSizeNumber.getText());
        } catch (NumberFormatException ex) {
            boardSizeNumber.setText("9");
            size = 9;
        }

        // Check the board size
        if (size <= 2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Board Size");
            alert.setHeaderText("Illegal Board Size");
            alert.setContentText("Please Re-type");
            alert.showAndWait();
            return;
        }

        boolean isSimple = simpleGame.isSelected();
        // Passing in the BoardSize and GameMode class
        gameLogic = new GameLogic(new BoardSize(size), new GameMode(isSimple));
        currentPlayer = "Blue";
        updateCurrentTurnLabel();

        // delete the old board and creat the new one
        gameTable.getChildren().clear();
        boardButtons = new Button[size][size];

        // A new game board
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
    }

    // Dealing with board unit click events
    private void handleCellClick(int row, int col, Button cellButton) {
        if (!gameLogic.getCell(row, col).isEmpty()) {
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

        boolean moveMade = gameLogic.makeMove(row, col, letter);
        if (moveMade) {
            cellButton.setText(letter);
            toggleCurrentPlayer();
            updateCurrentTurnLabel();
        }
    }

    private void toggleCurrentPlayer() {
        currentPlayer = currentPlayer.equals("Blue") ? "Red" : "Blue";
    }

    // Updated current tur and highlight panels for players
    private void updateCurrentTurnLabel() {
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

    public static void main(String[] args) {
        launch(args);
    }
}
