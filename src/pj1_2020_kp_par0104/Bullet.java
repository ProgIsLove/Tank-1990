package pj1_2020_kp_par0104;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {

	private Handler handler;
	private GameConstant gameCon;
	private Hud hud;
	private BufferedImage bullet_image;

	public Bullet(int x, int y, ID id, int direction, Handler handler, GameConstant gameCon, 
			SpriteSheet sheet, Hud hud) {
		super(x, y, id, direction);
		this.handler = handler;
		this.gameCon = gameCon;
		this.hud = hud;
		
		bullet_image = sheet.grabImage(1, 4, gameCon.getBulletSize(), gameCon.getBulletSize());
	}
	
	@Override
	public void tick() {
		setSpeedY(gameCon.getBulletSpeed());
		setSpeedX(gameCon.getBulletSpeed());
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
	public void render(Graphics g) {
		g.drawImage(bullet_image, getX(), getY(), null);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX(),getY(), gameCon.getBulletSize(), gameCon.getBulletSize());
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Block_brick_wall) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
					handler.removeObject(tempObject);
				}
			}
			if(tempObject.getId() == ID.Enemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					int tempCounter = hud.getEnemyCount();
					int tempScore = hud.getScore();
					handler.removeObject(this);
					handler.removeObject(tempObject);
					tempCounter -= 1;
					tempScore += gameCon.getScore();
					hud.setEnemyCount(tempCounter);
					hud.setScore(tempScore);
				}
			}
			
			if(tempObject.getId() == ID.Block_steel_wall || tempObject.getId() == ID.Block_sea_wall) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}	
		}
	}
}
