package game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public interface UIFactory {
    Button createButton(String text);

    Label createTitleLabel(String text);
    Label createBoldLabel(String text);
    Label createNormalLabel(String text);
    Label createInstructionsLabel(String text);

    TextField createNameInput();
}
