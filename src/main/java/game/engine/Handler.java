package game.engine;

import game.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public interface Handler {
	
	void addObject(GameObject object);
	void removeObject(GameObject object);
	void render(GraphicsContext g);
    void clear();
}
