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
import javafx.stage.Stage;

public class Window extends Application {

	private final Game game;
    private final UIFactory factory;

    public Window() {
        this.game = new Game();
        this.factory = new DefaultUIFactory();
    }

    @Override
    public void start(Stage stage) {
        Label titleLabel = factory.createTitleLabel("Tank 1990");
        Label instructions = factory.createInstructionsLabel("""
                Move:
                 W - up, S - down, A - left, D - right
                Shoot:
                 Spacebar
                """);

        Button playButton = factory.createButton("Play");
        Button quitButton = factory.createButton("Quit");

        playButton.setOnAction(e -> {
            BorderPane gameRoot = new BorderPane();
            gameRoot.setCenter(game);
            Scene gameScene = new Scene(gameRoot, GameConstant.WIDTH, GameConstant.HEIGHT);

            gameScene.setOnKeyPressed(ev -> game.getKeyInput().keyPressed(ev));
            gameScene.setOnKeyReleased(ev -> game.getKeyInput().keyReleased(ev));

            stage.setScene(gameScene);
            game.start();
        });

        quitButton.setOnAction(e -> stage.close());

        HBox buttons = new HBox(20, playButton, quitButton);
        buttons.setAlignment(Pos.CENTER);

        VBox menuLayout = new VBox(30, titleLabel, instructions, buttons);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setPadding(new Insets(40));

        Scene scene = new Scene(menuLayout, GameConstant.WIDTH, GameConstant.HEIGHT);

        stage.setTitle("Tank 1990");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
