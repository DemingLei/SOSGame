package SOSGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Scene;


public class GUI extends Application {

    public void start(Stage primaryStage) {
        // Create game types (Simple and General) and board size
        HBox topBoard = new HBox(20);
        topBoard.setPadding(new Insets(20));
        topBoard.setAlignment(Pos.CENTER);

        ToggleGroup gameTypes = new ToggleGroup();
        RadioButton simpleGame = new RadioButton("Simple game");
        RadioButton generalGame = new RadioButton("General game");
        simpleGame.setToggleGroup(gameTypes);
        generalGame.setToggleGroup(gameTypes);
        simpleGame.setSelected(true);

        Label boardSize = new Label("Board size");
        TextField boardSizeNumber = new TextField("9");
        boardSizeNumber.setPrefWidth(40);

        topBoard.getChildren().addAll(simpleGame, generalGame, boardSize, boardSizeNumber);

        // Make the blue player data
        VBox bluePlayerSheet = new VBox(10);
        bluePlayerSheet.setPadding(new Insets(10));
        //bluePlayerPanel.setAlignment(Pos.TOP_LEFT);
        bluePlayerSheet.setAlignment(Pos.BOTTOM_CENTER);

        Label bluePlayerLabel = new Label("Blue player");
        ToggleGroup bluePlayerType = new ToggleGroup();
        RadioButton blueHuman = new RadioButton("Human");
        RadioButton blueComputer = new RadioButton("Computer");
        blueHuman.setToggleGroup(bluePlayerType);
        blueComputer.setToggleGroup(bluePlayerType);
        blueHuman.setSelected(true);

        ToggleGroup blueSOLetter = new ToggleGroup();
        RadioButton blueS = new RadioButton("S");
        RadioButton blueO = new RadioButton("O");
        blueS.setToggleGroup(blueSOLetter);
        blueO.setToggleGroup(blueSOLetter);
        blueS.setSelected(true);

        // Switch computer model and human model in the blue side
        blueHuman.setOnAction(event -> {
            blueS.setDisable(false);
            blueO.setDisable(false);
        });

        blueComputer.setOnAction(event -> {
            blueS.setDisable(true);
            blueO.setDisable(true);
        });

        bluePlayerSheet.getChildren().addAll(bluePlayerLabel, blueHuman, blueS, blueO, blueComputer);

        // Right panel for Red player options
        VBox redPlayerSheet = new VBox(10);
        redPlayerSheet.setPadding(new Insets(10));
        //bluePlayerPanel.setAlignment(Pos.TOP_Right);
        redPlayerSheet.setAlignment(Pos.BOTTOM_CENTER);
//
        Label redPlayerLabel = new Label("Red player");
        ToggleGroup redPlayerType = new ToggleGroup();
        RadioButton redHuman = new RadioButton("Human");
        RadioButton redComputer = new RadioButton("Computer");
        redHuman.setToggleGroup(redPlayerType);
        redComputer.setToggleGroup(redPlayerType);
        redHuman.setSelected(true);

        ToggleGroup redSOLetter = new ToggleGroup();
        RadioButton redS = new RadioButton("S");
        RadioButton redO = new RadioButton("O");
        redS.setToggleGroup(redSOLetter);
        redO.setToggleGroup(redSOLetter);
        redS.setSelected(true);

        // Switch computer model and human model in the red side
        redHuman.setOnAction(event -> {
            redS.setDisable(false);
            redO.setDisable(false);
        });

        redComputer.setOnAction(event -> {
            redS.setDisable(true);
            redO.setDisable(true);
        });

        redPlayerSheet.getChildren().addAll(redPlayerLabel, redHuman, redS, redO, redComputer);

        // Center table in the game
        GridPane gameTable = new GridPane();
        gameTable.setPadding(new Insets(20));
        gameTable.setAlignment(Pos.CENTER);
        gameTable.setGridLinesVisible(true);

        // The number of the table size (size = 9 now) will be changed to dynamic in the following work
        int TableSize = 9;
        for (int row = 0; row < TableSize; row++) {
            for (int col = 0; col < TableSize; col++) {
                Label cell = new Label();
                cell.setPrefSize(40, 40);
                cell.setStyle("-fx-border-color: purple; -fx-alignment: center;");
                gameTable.add(cell, col, row);
            }
        }

        // Bottom sheet with game controls
        HBox bottomWindow = new HBox(20);
        bottomWindow.setPadding(new Insets(10));
        bottomWindow.setAlignment(Pos.CENTER);

        CheckBox recordGame = new CheckBox("Record game");
        Label currentTurn = new Label("Current turn: red/blue)");
        // The color will be changed by each turn in the following work
        Button replayButton = new Button("Replay");
        replayButton.setDisable(true);
        Button newGameButton = new Button("New Game");

        bottomWindow.getChildren().addAll(recordGame, currentTurn, replayButton, newGameButton);

        // Main template
        BorderPane root = new BorderPane();
        root.setTop(topBoard);
        root.setLeft(bluePlayerSheet);
        root.setRight(redPlayerSheet);
        root.setCenter(gameTable);
        root.setBottom(bottomWindow);

        // Creat the scene window
        Scene sceneWindow = new Scene(root, 700, 700);
        primaryStage.setTitle("SOS GAME");
        primaryStage.setScene(sceneWindow);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



