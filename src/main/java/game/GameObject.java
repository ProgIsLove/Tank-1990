package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;


public abstract class GameObject {

    private final GameObjectType gameObjectType;

	private int x;
	private int y;
	private int speedX;
	private int speedY;
	private int direction;

    protected GameObject(int x, int y, GameObjectType gameObjectType, int direction) {
		this(x,y, gameObjectType);
		this.direction = direction;
	}
	
	protected GameObject(int x, int y, GameObjectType gameObjectType) {
		this.x = x;
		this.y = y;
		this.gameObjectType = gameObjectType;
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

	public GameObjectType getGameObjectType() {
		return gameObjectType;
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
