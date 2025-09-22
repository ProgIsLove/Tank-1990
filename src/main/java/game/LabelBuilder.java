package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class LabelBuilder implements UIComponent {

    private String text = "";
    private String fontFamily = "Arial";
    private int fontSize = 16;
    private FontWeight fontWeight = FontWeight.NORMAL;
    private FontPosture fontPosture = FontPosture.REGULAR;
    private Color textColor = Color.BLACK;
    private Pos alignment = Pos.CENTER;
    private TextAlignment textAlignment = TextAlignment.CENTER;
    private int minHeight = 120;
    private Insets padding = new  Insets(0);
    private String setStyle = "";

    private Label builtLabel;

    public LabelBuilder text(String text) {
        this.text = text;
        return this;
    }

    public LabelBuilder fontFamily(String family) {
        this.fontFamily = family;
        return this;
    }

    public LabelBuilder fontSize(int size) {
        this.fontSize = size;
        return this;
    }

    public LabelBuilder bold() {
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public LabelBuilder italic() {
        this.fontPosture = FontPosture.ITALIC;
        return this;
    }

    public LabelBuilder color(Color color) {
        this.textColor = color;
        return this;
    }

    public LabelBuilder align(Pos pos) {
        this.alignment = pos;
        return this;
    }

    public LabelBuilder setStyle(String style) {
        this.setStyle = style;
        return this;
    }

    public LabelBuilder setTextAlignment(TextAlignment textAlignment) {
        this.textAlignment = textAlignment;
        return this;
    }

    public LabelBuilder setMinHeight(int minHeight) {
        this.minHeight = minHeight;
        return this;
    }

    public LabelBuilder setPadding(Insets padding) {
        this.padding = padding;
        return this;
    }

    public Label build() {
        if (builtLabel == null) {
            builtLabel = new Label(text);
            builtLabel.setFont(Font.font(fontFamily, fontWeight, fontPosture, fontSize));
            builtLabel.setTextFill(textColor);
            builtLabel.setAlignment(alignment);
            builtLabel.setTextAlignment(textAlignment);
            builtLabel.setMinHeight(minHeight);
            builtLabel.setPadding(padding);
            builtLabel.setStyle(setStyle);
        }
        return builtLabel;
    }

    @Override
    public Node getNode() {
        return build();
    }



}
