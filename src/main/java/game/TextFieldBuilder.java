package game;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TextFieldBuilder implements UIComponent {

    private String promptText = "";
    private String fontFamily = "Arial";
    private int fontSize = 16;
    private FontWeight fontWeight = FontWeight.NORMAL;
    private FontPosture fontPosture = FontPosture.REGULAR;
    private Color textColor = Color.BLACK;
    private double maxWidth = -1;

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

    public TextField build() {
        if (builtTextField == null) {
            builtTextField = new TextField();
            builtTextField.setPromptText(promptText);
            builtTextField.setFont(Font.font(fontFamily, fontWeight, fontPosture, fontSize));
            builtTextField.setStyle("-fx-text-fill: " + toRgbString(textColor) + ";");
            if (maxWidth > 0) {
                builtTextField.setMaxWidth(maxWidth);
            }
        }
        return builtTextField;
    }

    @Override
    public Node getNode() {
        return build();
    }

    // helper: convert Color -> CSS rgb string
    private String toRgbString(Color c) {
        return String.format("rgb(%d,%d,%d)",
                (int) (c.getRed() * 255),
                (int) (c.getGreen() * 255),
                (int) (c.getBlue() * 255));
    }
}
