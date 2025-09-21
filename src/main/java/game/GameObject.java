package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;


public abstract class GameObject {
	
	private int x;
	private int y;
	private ID id;
	private int speedX;
	private int speedY;
	private int direction;

    protected GameObject(int x, int y, ID id, int direction) {
		this(x,y,id);
		this.direction = direction;
	}
	
	protected GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();
	public abstract void render(GraphicsContext g);
	public abstract Rectangle2D getBounds();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}
