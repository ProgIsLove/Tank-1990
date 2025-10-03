package game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public interface UIFactory {
    Button createButton(String text);

    Button createPlayButton(String text, Stage stage, Game game);
    Button createQuitButton(Stage stage);

    Label createTitleLabel(String text);
    Label createBoldLabel(String text);
    Label createNormalLabel(String text);
    Label createInstructionsLabel(String text);

    TextField createNameInput();
}
