package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;

public class HighScoreScene {

    private final Stage stage;
    private final int score;
    private final HighScoreDAO dao = new HighScoreDAOImpl();

    public HighScoreScene(Stage stage, int score) {
        this.stage = stage;
        this.score = score;
        showScene();
    }

    private void showScene() {
        Label titleLabel = new LabelBuilder()
                .text("High Score")
                .bold()
                .fontSize(48)
                .fontFamily("Arial")
                .color(Color.DARKCYAN)
                .build();

        Label scoreLabel = new LabelBuilder()
                .text("Your Score: " + this.score)
                .bold()
                .fontSize(20)
                .fontFamily("Arial")
                .build();

        TextField nameInput = new TextField();
        nameInput.setPromptText("ABC");
        nameInput.setMaxWidth(80);
        nameInput.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nameInput.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 3 ? change : null
        ));

        Button submitButton = new ButtonBuilder()
                .text("Submit Score")
                .fontFamily("Arial")
                .bold()
                .fontSize(16)
                .build();

        VBox scoreListBox = new VBox(5);
        scoreListBox.setAlignment(Pos.CENTER);
        updateScoreList(scoreListBox);

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);

        Button playAgainButton = new ButtonBuilder()
                .text("▶ Play Again")
                .fontFamily("Arial")
                .bold()
                .fontSize(16).build();

        Button quitButton = new ButtonBuilder()
                .text("✖ Quit")
                .fontFamily("Arial")
                .bold()
                .fontSize(16).build();

        buttons.getChildren().addAll(playAgainButton, quitButton);

        VBox topSection = new VBox(0, titleLabel, scoreLabel);
        topSection.setAlignment(Pos.CENTER);

        VBox layout = new VBox(8, topSection, nameInput, submitButton, scoreListBox, buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5));

        Scene scene = new Scene(layout, GameConstant.WIDTH, GameConstant.HEIGHT);

        // Button actions
        submitButton.setOnAction(e -> {
            String name = nameInput.getText().trim();
            if (!name.isEmpty()) {
                dao.saveScore(new HighScore(name, this.score));
                updateScoreList(scoreListBox);
                submitButton.setDisable(true);
                nameInput.setDisable(true);
            }
        });

        playAgainButton.setOnAction(e -> {
            Game game = new Game();
            BorderPane gameRoot = new BorderPane();
            gameRoot.setCenter(game);
            Scene gameScene = new Scene(gameRoot, GameConstant.WIDTH, GameConstant.HEIGHT);
            gameScene.setOnKeyPressed(ev -> game.getKeyInput().keyPressed(ev));
            gameScene.setOnKeyReleased(ev -> game.getKeyInput().keyReleased(ev));
            stage.setScene(gameScene);
            game.start();
        });

        quitButton.setOnAction(e -> stage.close());

        stage.setScene(scene);
    }

    private void updateScoreList(VBox scoreListBox) {
        scoreListBox.getChildren().clear();
        List<HighScore> topScores = dao.getTopScores(3);
        for (HighScore hs : topScores) {
            Label label = new Label(hs.name() + " - " + hs.score());
            label.setFont(Font.font("Arial", 18));
            scoreListBox.getChildren().add(label);
        }
    }
}
