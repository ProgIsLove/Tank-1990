package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import java.util.Random;

public class Enemy extends GameObject {

    private final Spawner spawner;
    private final Hud hud;
    private final Handler handler;
    private final SpriteSheet sheet;
    private final WritableImage[] enemyImages = new WritableImage[4];
	private final Random rnd = new Random();

    private int timerShoot;

	public Enemy(int x, int y, ID id, int direction, Handler handler,
			SpriteSheet sheet, Spawner spawner, Hud hud) {
		super(x, y, id, direction);
		this.handler = handler;
		this.sheet = sheet;
		this.spawner = spawner;
		this.hud = hud;

		timerShoot = rnd.nextInt(GameConstant.TIMER_SHOOT);

		enemyImages[0] = sheet.grabImage(2, 4, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
		enemyImages[1] = sheet.grabImage(2, 5, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
		enemyImages[2] = sheet.grabImage(2, 6, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
		enemyImages[3] = sheet.grabImage(3, 1, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
	}
	
	@Override
	public void tick() {
		setSpeedX(GameConstant.SPEED);
		setSpeedY(GameConstant.SPEED);

		int tempY = getY();
		int tempX = getX();

		switch(getDirection()) {
		case 1:{
			tempY -= getSpeedY();
			setY(tempY);
			break;
		}
		case 2:{
			tempY += getSpeedY();
			setY(tempY);
			break;
		}
		case 3:{
			tempX -= getSpeedX();
			setX(tempX);
			break;
		}
		case 4:{
			tempX += getSpeedX();
			setX(tempX);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + getDirection());
		}
		if (timerShoot <= 0) {
			handler.addObject(new EnemyBullet(getX() + GameConstant.BULLET_SIZE / 2,
					getY() + GameConstant.BULLET_SIZE / 2, ID.ENEMY_BULLET, getDirection(),
					handler, sheet, hud, spawner));
			timerShoot = rnd.nextInt(GameConstant.TIMER_SHOOT);
		} else
			timerShoot--;
		
		collision();
	}
	
	@Override
	public void render(GraphicsContext g) {
		if (getDirection() == 1)
			g.drawImage(enemyImages[0], getX(), getY());
		else if (getDirection() == 2)
			g.drawImage(enemyImages[3], getX(), getY());
		else if (getDirection() == 3)
			g.drawImage(enemyImages[1], getX(), getY());
		else if (getDirection() == 4)
			g.drawImage(enemyImages[2], getX(), getY());
	}
	
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(getX(), getY(), GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.BLOCK_SEA_WALL || tempObject.getId() == ID.BLOCK_STEEL_WALL
					|| tempObject.getId() == ID.BLOCK_BRICK_WALL) {
				if (getBounds().intersects(tempObject.getBounds())) {
					
					int tempY = getY();
					int tempX = getX();
					int move;

					if(getDirection() == 1) {
						move = rnd.nextInt(4 + 1 - 1) + 1;
						tempY += getSpeedY();
						setY(tempY);
						setDirection(move);
						break;
					}
					
					if(getDirection() == 2) {
						move = rnd.nextInt(4 + 1 - 1) + 1;
						tempY -= getSpeedY();
						setY(tempY);
						setDirection(move);
						break;
					}
					
					if(getDirection() == 3) {
						move = rnd.nextInt(4 + 1 - 1) + 1;
						tempX += getSpeedX();
						setX(tempX);
						setDirection(move);
						break;
					}
					
					if(getDirection() == 4) {
						move = rnd.nextInt(4 + 1 - 1) + 1;
						tempX -= getSpeedX();
						setX(tempX);
						setDirection(move);
						break;
					}
				}
			}
		}
	}
}
