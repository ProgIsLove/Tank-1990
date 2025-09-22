package game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class ButtonBuilder {

    private String text = "";
    private String fontFamily = "Arial";
    private int fontSize = 16;
    private FontWeight fontWeight = FontWeight.NORMAL;
    private FontPosture fontPosture = FontPosture.REGULAR;
    private Color textColor = Color.BLACK;
    private double prefWidth = -1;
    private Pos alignment = Pos.CENTER;

    private Button builtButton; // cache

    public ButtonBuilder text(String text) {
        this.text = text;
        return this;
    }

    public ButtonBuilder fontFamily(String family) {
        this.fontFamily = family;
        return this;
    }

    public ButtonBuilder fontSize(int size) {
        this.fontSize = size;
        return this;
    }

    public ButtonBuilder bold() {
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public ButtonBuilder italic() {
        this.fontPosture = FontPosture.ITALIC;
        return this;
    }

    public ButtonBuilder color(Color color) {
        this.textColor = color;
        return this;
    }

    public ButtonBuilder prefWidth(double width) {
        this.prefWidth = width;
        return this;
    }

    public ButtonBuilder align(Pos pos) {
        this.alignment = pos;
        return this;
    }

    public Button build() {
        if (builtButton == null) {
            builtButton = new Button(text);
            builtButton.setFont(Font.font(fontFamily, fontWeight, fontPosture, fontSize));
            builtButton.setTextFill(textColor);
            if (prefWidth > 0) {
                builtButton.setPrefWidth(prefWidth);
            }
            builtButton.setAlignment(alignment);
        }
        return builtButton;
    }
}
