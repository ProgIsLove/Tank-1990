package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

public class EnemyBullet extends GameObject {

	private final Handler handler;
	private final Hud hud;
	private final Spawner spawner;
	private final WritableImage bulletImage;

	public EnemyBullet(int x, int y, ID id, int direction, Handler handler, SpriteSheet sheet,
			Hud hud, Spawner spawner) {
		super(x, y, id, direction);
		this.handler = handler;
		this.hud = hud;
		this.spawner = spawner;

		bulletImage = sheet.grabImage(1, 5, GameConstant.BULLET_SIZE, GameConstant.BULLET_SIZE);
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
		g.drawImage(bulletImage, getX(), getY());
	}
	
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(getX(), getY(), GameConstant.BULLET_SIZE, GameConstant.BULLET_SIZE);
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			int tempLive = hud.getLive();
			int tempCrownLive = hud.getCrownLive();

			if (tempObject.getId() == ID.BULLET || tempObject.getId() == ID.BLOCK_BRICK_WALL) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
					handler.removeObject(tempObject);
				}
			}
			if (tempObject.getId() == ID.PLAYER) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
					handler.removeObject(tempObject);
					tempLive -= 1;
					hud.setLive(tempLive);
					if(hud.getLive() != 0 || hud.getCrownLive() != 0) {
						spawner.nextLive();
					}
				}
			}
			if (tempObject.getId() == ID.GOLDEN_CROWN) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
					handler.removeObject(tempObject);
					tempCrownLive -= 1;
					tempLive -= 1;
					hud.setCrownLive(tempCrownLive);
					hud.setLive(tempLive);
				}
			}
			if (tempObject.getId() == ID.BLOCK_STEEL_WALL || tempObject.getId() == ID.BLOCK_SEA_WALL) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}
		}
	}
}
