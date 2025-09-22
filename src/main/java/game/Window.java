package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Window extends Application {

	private final Game game;

    public Window() {
        this.game = new Game();
    }

    @Override
    public void start(Stage stage) {
        Label titleBuilder = new LabelBuilder()
                .text("Tank 1990")
                .fontSize(48)
                .fontFamily("Arial")
                .bold()
                .color(Color.DARKCYAN).build();

        Label instructions = new LabelBuilder()
                .text("""
            Move:
              W - up, S - down, A - left, D - right
            Shoot:
              Spacebar
            """)
                .fontSize(20)
                .bold()
                .fontFamily("Arial")
                .color(Color.WHITE)
                .align(Pos.CENTER)
                .setTextAlignment(TextAlignment.CENTER)
                .setStyle("-fx-background-color: rgb(12,105,128); " +
                        "-fx-background-radius: 12; " +
                        "-fx-border-radius: 12;")
                .setMinHeight(120)
                .setPadding(new Insets(7))
                .build();

        VBox menuLayout = getVBox(stage, titleBuilder, instructions);

        Scene scene = new Scene(menuLayout, GameConstant.WIDTH, GameConstant.HEIGHT);

        stage.setTitle("Tank 1990");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private VBox getVBox(Stage stage, Label titleLabel, Label instructions) {
        UIComponent hbox = new Box((play -> play.setOnAction(e -> {
            BorderPane gameRoot = new BorderPane();
            gameRoot.setCenter(game);
            Scene gameScene = new Scene(gameRoot, GameConstant.WIDTH, GameConstant.HEIGHT);

            gameScene.setOnKeyPressed(ev -> game.getKeyInput().keyPressed(ev));
            gameScene.setOnKeyReleased(ev -> game.getKeyInput().keyReleased(ev));

            stage.setScene(gameScene);
            game.start();
        })), quit -> quit.setOnAction(e -> stage.close()));

        VBox menuLayout = new VBox(30, titleLabel, instructions, hbox.getNode());
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setPadding(new Insets(40));
        return menuLayout;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
