package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;


public class Bullet extends GameObject {

	private final Handler handler;
	private final Hud hud;
	private final WritableImage bulletImages;

	public Bullet(int x, int y, ID id, int direction, Handler handler,
			SpriteSheet sheet, Hud hud) {
		super(x, y, id, direction);
		this.handler = handler;
		this.hud = hud;
		
		bulletImages = sheet.grabImage(1, 4, GameConstant.BULLET_SIZE, GameConstant.BULLET_SIZE);
	}
	
	@Override
	public void tick() {
		setSpeedY(GameConstant.BULLET_SPEED);
		setSpeedX(GameConstant.BULLET_SPEED);
		switch (getDirection()) {
		case 1: {
			int tempY = getY();
			tempY -= getSpeedY();
			setY(tempY);
			break;
		}
		case 2: {
			int tempY = getY();
			tempY += getSpeedY();
			setY(tempY);
			break;
		}
		case 3: {
			int tempX = getX();
			tempX -= getSpeedX();
			setX(tempX);
			break;
		}
		case 4: {
			int tempX = getX();
			tempX += getSpeedX();
			setX(tempX);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + getDirection());
		}
		
		collision();
	}
	
	@Override
	public void render(GraphicsContext g) {
		g.drawImage(bulletImages, getX(), getY());
	}
	
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D (getX(),getY(), GameConstant.BULLET_SIZE, GameConstant.BULLET_SIZE);
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.BLOCK_BRICK_WALL) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
					handler.removeObject(tempObject);
				}
			}
			if(tempObject.getId() == ID.ENEMY) {
				if (getBounds().intersects(tempObject.getBounds())) {
					int tempCounter = hud.getEnemyCount();
					int tempScore = hud.getScore();
					handler.removeObject(this);
					handler.removeObject(tempObject);
					tempCounter -= 1;
					tempScore += GameConstant.SCORE;
					hud.setEnemyCount(tempCounter);
					hud.setScore(tempScore);
				}
			}
			
			if(tempObject.getId() == ID.BLOCK_STEEL_WALL || tempObject.getId() == ID.BLOCK_SEA_WALL) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}	
		}
	}
}
