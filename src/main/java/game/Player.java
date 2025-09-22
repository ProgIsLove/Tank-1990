package game;


import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

public class Player extends GameObject {
	
	private final HandlerImpl handlerImpl;
	private final WritableImage[] playerImages = new WritableImage[4];
	
	public Player(int x, int y, ID id, int direction, HandlerImpl handlerImpl, SpriteSheet sheet) {
		super(x, y, id, direction);
		this.handlerImpl = handlerImpl;
		
		playerImages[0] = sheet.grabImage(1, 6, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
		playerImages[1] = sheet.grabImage(2, 1, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
		playerImages[2] = sheet.grabImage(2, 2, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
		playerImages[3] = sheet.grabImage(2, 3, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
	}
	
	@Override
	public void tick() {
		int tempY = getY();
		tempY += getSpeedY();
		setY(tempY);
		
		int tempX = getX();
		tempX += getSpeedX();
		setX(tempX);
		
		collision();
	}
	
	@Override
	public void render(GraphicsContext g) {
		if(getDirection() == 1)
			g.drawImage(playerImages[0], getX(), getY());
		else if(getDirection() == 2)
			g.drawImage(playerImages[3], getX(), getY());
		else if(getDirection() == 3)
			g.drawImage(playerImages[1], getX(), getY());
		else if(getDirection() == 4)
			g.drawImage(playerImages[2], getX(), getY());
	}
	
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(getX(), getY(), GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
	}
	
	public void collision() {
		for(int i = 0; i < handlerImpl.object.size(); i++) {
			GameObject tempObject = handlerImpl.object.get(i);
			
			if(tempObject.getId() == ID.BLOCK_SEA_WALL || tempObject.getId() == ID.BLOCK_STEEL_WALL || tempObject.getId() == ID.BLOCK_BRICK_WALL) {
				if(getBounds().intersects(tempObject.getBounds())) {
					
					int tempX = getX();
					tempX -= getSpeedX();
					setX(tempX);
					
					int tempY = getY();
					tempY -= getSpeedY();
					setY(tempY);
				}
			}
		}
	}	
}
