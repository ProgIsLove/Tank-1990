package game;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TextFieldBuilder {

    private String promptText = "";
    private String fontFamily = "Arial";
    private int fontSize = 16;
    private FontWeight fontWeight = FontWeight.NORMAL;
    private FontPosture fontPosture = FontPosture.REGULAR;
    private Color textColor = Color.BLUE;
    private double maxWidth = -1;
    private int maxLength = -1;

    private TextField builtTextField;

    public TextFieldBuilder prompt(String prompt) {
        this.promptText = prompt;
        return this;
    }

    public TextFieldBuilder fontFamily(String family) {
        this.fontFamily = family;
        return this;
    }

    public TextFieldBuilder fontSize(int size) {
        this.fontSize = size;
        return this;
    }

    public TextFieldBuilder bold() {
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public TextFieldBuilder italic() {
        this.fontPosture = FontPosture.ITALIC;
        return this;
    }

    public TextFieldBuilder color(Color color) {
        this.textColor = color;
        return this;
    }

    public TextFieldBuilder maxWidth(double width) {
        this.maxWidth = width;
        return this;
    }

    public TextFieldBuilder maxLength(int maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public TextField build() {
        if (builtTextField == null) {
            builtTextField = new TextField();
            builtTextField.setPromptText(promptText);
            builtTextField.setFont(Font.font(fontFamily, fontWeight, fontPosture, fontSize));
            if (maxWidth > 0) {
                builtTextField.setMaxWidth(maxWidth);
            }
            if (maxLength > 0) {
                builtTextField.setTextFormatter(new TextFormatter<String>(change ->
                        change.getControlNewText().length() <= maxLength ? change : null
                ));
            }
        }
        return builtTextField;
    }
}
