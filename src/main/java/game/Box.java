package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.function.Consumer;

public class Box implements UIComponent {

    private final Button playButton;
    private final Button quitButton;
    private final HBox hbox;

    public Box(Consumer<Button> playAction, Consumer<Button> quitAction) {
        // Build buttons using ButtonBuilder
        this.playButton = new ButtonBuilder()
                .text("▶ Play")
                .fontSize(18)
                .bold()
                .prefWidth(120)
                .build();

        this.quitButton = new ButtonBuilder()
                .text("✖ Quit")
                .fontSize(18)
                .bold()
                .prefWidth(120)
                .build();

        if (playAction != null) {
            playAction.accept(playButton);
        }
        if (quitAction != null) {
            quitAction.accept(quitButton);
        }

        this.hbox = new HBox(20, playButton, quitButton);
        this.hbox.setAlignment(Pos.CENTER);
        this.hbox.setPadding(new Insets(20));
    }

    @Override
    public Node getNode() {
        return hbox;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }
}
