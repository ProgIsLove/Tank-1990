package pj1_2020_kp_par0104;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {

	private Random rnd = new Random();
	private Handler handler;
	private GameConstant gameCon;
	private SpriteSheet sheet;
	private BufferedImage[] enemy_image = new BufferedImage[4];
	private Spawner spawner;
	private Hud hud;
	private int timerShoot;

	public Enemy(int x, int y, ID id, int direction, Handler handler, GameConstant gameCon, 
			SpriteSheet sheet, Spawner spawner, Hud hud) {
		super(x, y, id, direction);
		this.handler = handler;
		this.gameCon = gameCon;
		this.sheet = sheet;
		this.spawner = spawner;
		this.hud = hud;

		timerShoot = rnd.nextInt(gameCon.getTimerShoot());

		enemy_image[0] = sheet.grabImage(2, 4, gameCon.getTankSize(), gameCon.getTankSize());
		enemy_image[1] = sheet.grabImage(2, 5, gameCon.getTankSize(), gameCon.getTankSize());
		enemy_image[2] = sheet.grabImage(2, 6, gameCon.getTankSize(), gameCon.getTankSize());
		enemy_image[3] = sheet.grabImage(3, 1, gameCon.getTankSize(), gameCon.getTankSize());
	}
	
	@Override
	public void tick() {
		setSpeedX(gameCon.getSpeed());
		setSpeedY(gameCon.getSpeed());

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
			handler.addObject(new EnemyBullet(getX() + gameCon.getBulletSize() / 2,
					getY() + gameCon.getBulletSize() / 2, ID.EnemyBullet, getDirection(), 
					handler, gameCon, sheet, hud, spawner));
			timerShoot = rnd.nextInt(gameCon.getTimerShoot());
		} else
			timerShoot--;
		
		collision();
	}
	
	@Override
	public void render(Graphics g) {
		if (getDirection() == 1)
			g.drawImage(enemy_image[0], getX(), getY(), null);
		else if (getDirection() == 2)
			g.drawImage(enemy_image[3], getX(), getY(), null);
		else if (getDirection() == 3)
			g.drawImage(enemy_image[1], getX(), getY(), null);
		else if (getDirection() == 4)
			g.drawImage(enemy_image[2], getX(), getY(), null);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), gameCon.getTankSize(), gameCon.getTankSize());
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Block_sea_wall || tempObject.getId() == ID.Block_steel_wall
					|| tempObject.getId() == ID.Block_brick_wall) {
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
