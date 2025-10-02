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
    private final HighScoreDAO dao;
    private final UIFactory factory;

    public HighScoreScene(Stage stage, int score) {
        this.stage = stage;
        this.score = score;
        this.dao = new HighScoreDAOImpl();
        this.factory = new DefaultUIFactory();
        showScene();
    }

    private void showScene() {
        Label titleLabel = factory.createTitleLabel("High Score");
        Label scoreLabel = factory.createBoldLabel("Your Score: " + this.score);
        TextField nameInput = factory.createNameInput();

        Button submitScoreButton = factory.createButton("Submit Score");
        Button playAgainButton = factory.createButton("Play Again");
        Button quitButton = factory.createButton("Quit");

        VBox scoreListBox = new VBox(5);
        scoreListBox.setAlignment(Pos.CENTER);
        updateScoreList(scoreListBox);

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);

        buttons.getChildren().addAll(playAgainButton, quitButton);

        VBox topSection = new VBox(0, titleLabel, scoreLabel);
        topSection.setAlignment(Pos.CENTER);

        VBox layout = new VBox(8, topSection, nameInput, submitScoreButton, scoreListBox, buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5));

        Scene scene = new Scene(layout, GameConstant.WIDTH, GameConstant.HEIGHT);

        // Button actions
        submitScoreButton.setOnAction(e -> {
            String name = nameInput.getText().trim();
            if (!name.isEmpty()) {
                dao.saveScore(new HighScore(name, this.score));
                updateScoreList(scoreListBox);
                submitScoreButton.setDisable(true);
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
            Label label = factory.createNormalLabel(hs.name() + " - " + hs.score());
            scoreListBox.getChildren().add(label);
        }
    }
}
