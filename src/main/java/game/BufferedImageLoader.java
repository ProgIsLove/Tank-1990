package game;

import javafx.scene.image.Image;

import java.util.Objects;

public class BufferedImageLoader {

    public Image loadImage(String path) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
    }
}
