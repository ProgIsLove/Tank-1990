package pj1_2020_kp_par0104;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler implements IHandler, Tickable {
	
	LinkedList<GameObject> object = new LinkedList<>();
	
	@Override
	public void tick() {
		for(int i=0; i<object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	@Override
	public void render(Graphics g) {
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
