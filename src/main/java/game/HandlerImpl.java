package game;

import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class HandlerImpl implements Handler, Tickable {
	
	LinkedList<GameObject> object = new LinkedList<>();
	
	@Override
	public void tick() {
		for(int i=0; i<object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	@Override
	public void render(GraphicsContext g) {
		for(int i=0; i<object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	@Override
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	@Override
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
