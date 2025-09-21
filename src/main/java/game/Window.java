package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Window extends Application {

	private final Game game;

    public Window() {
        this.game = new Game();
    }

    @Override
    public void start(Stage stage) {
        // Title
        Label titleLabel = new Label("Tank 1990");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 64));
        titleLabel.setTextFill(Color.DARKCYAN);

        // Instructions
        Label instructions = new Label("""
            Move:
              W - up, S - down, A - left, D - right
            Shoot:
              Spacebar
            """);
        instructions.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        instructions.setTextFill(Color.WHITE);
        instructions.setTextAlignment(TextAlignment.CENTER);
        instructions.setAlignment(Pos.CENTER);
        instructions.setWrapText(true);
        instructions.setPadding(new Insets(7));
        instructions.setStyle(
                "-fx-background-color: rgb(12,105,128); " +
                        "-fx-background-radius: 12; " +
                        "-fx-border-radius: 12;"
        );
        instructions.setMinHeight(120);

        // Buttons
        Button playButton = new Button("▶ Play");
        playButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        playButton.setPrefWidth(120);

        Button quitButton = new Button("✖ Quit");
        quitButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        quitButton.setPrefWidth(120);

        HBox buttonBox = new HBox(20, playButton, quitButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20));

        // Layout
        VBox menuLayout = new VBox(30, titleLabel, instructions, buttonBox);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setPadding(new Insets(40));

        Scene scene = new Scene(menuLayout, GameConstant.WIDTH, GameConstant.HEIGHT);

        // Input delegation to Game
        scene.setOnKeyPressed(e -> game.getKeyInput().keyPressed(e));
        scene.setOnKeyReleased(e -> game.getKeyInput().keyReleased(e));

        // Button actions
        playButton.setOnAction(e -> {
            BorderPane gameRoot = new BorderPane();
            gameRoot.setCenter(game);
            Scene gameScene = new Scene(gameRoot, GameConstant.WIDTH, GameConstant.HEIGHT);

            // Pass input handling to game scene
            gameScene.setOnKeyPressed(ev -> game.getKeyInput().keyPressed(ev));
            gameScene.setOnKeyReleased(ev -> game.getKeyInput().keyReleased(ev));

            stage.setScene(gameScene);
            game.start();
        });

        quitButton.setOnAction(e -> stage.close());

        stage.setTitle("Tank 1990");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
