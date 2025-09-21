package game;

import javafx.scene.canvas.GraphicsContext;

import java.awt.Graphics;

public interface IHandler {
	
	void addObject(GameObject object);
	void removeObject(GameObject object);
	void render(GraphicsContext g);
}
