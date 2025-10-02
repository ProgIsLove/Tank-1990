package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class DefaultUIFactory implements UIFactory {

    @Override
    public Button createButton(String text) {
        return new ButtonBuilder()
                .text(text)
                .bold()
                .fontSize(16)
                .fontFamily("Arial")
                .build();
    }

    @Override
    public Label createTitleLabel(String text) {
        return new LabelBuilder()
                .text(text)
                .bold()
                .fontSize(48)
                .fontFamily("Arial")
                .color(Color.DARKCYAN)
                .build();
    }

    @Override
    public Label createBoldLabel(String text) {
        return new LabelBuilder()
                .text(text)
                .bold()
                .fontSize(20)
                .fontFamily("Arial")
                .build();
    }

    @Override
    public Label createNormalLabel(String text) {
        return new LabelBuilder()
                .text(text)
                .fontSize(16)
                .fontFamily("Arial")
                .setMinHeight(0)
                .build();
    }

    @Override
    public Label createInstructionsLabel(String text) {
        return new LabelBuilder()
                .text(text)
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
    }

    @Override
    public TextField createNameInput() {
        return new TextFieldBuilder()
                .prompt("ABC")
                .fontFamily("Arial")
                .bold()
                .maxLength(3)
                .fontSize(20)
                .maxWidth(80)
                .build();
    }
}
