package game;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class SpriteSheet {

    private final Image sheet;

    public SpriteSheet(Image sheet) {
        this.sheet = sheet;
    }

    public WritableImage grabImage(int col, int row, int width, int height) {
        PixelReader reader = sheet.getPixelReader();
        if (reader == null) {
            throw new IllegalStateException("SpriteSheet image has no PixelReader");
        }
        return new WritableImage(reader, (row * 25) - 25, (col * 25) - 25, width, height);
    }
}
