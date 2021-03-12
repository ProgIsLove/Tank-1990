package pj1_2020_kp_par0104;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject {

	private Handler handler;
	private GameConstant gameCon;
	private Hud hud;
	private Spawner spawner;
	private BufferedImage bullet_image;

	public EnemyBullet(int x, int y, ID id, int direction, Handler handler, GameConstant gameCon, SpriteSheet sheet,
			Hud hud, Spawner spawner) {
		super(x, y, id, direction);
		this.handler = handler;
		this.gameCon = gameCon;
		this.hud = hud;
		this.spawner = spawner;

		bullet_image = sheet.grabImage(1, 5, gameCon.getBulletSize(), gameCon.getBulletSize());
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
		return new Rectangle(getX(), getY(), gameCon.getBulletSize(), gameCon.getBulletSize());
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			int tempLive = hud.getLive();
			int tempCrownLive = hud.getCrownLive();

			if (tempObject.getId() == ID.Bullet || tempObject.getId() == ID.Block_brick_wall) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
					handler.removeObject(tempObject);
				}
			}
			if (tempObject.getId() == ID.Player) {
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
			if (tempObject.getId() == ID.Golden_crown) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
					handler.removeObject(tempObject);
					tempCrownLive -= 1;
					tempLive -= 1;
					hud.setCrownLive(tempCrownLive);
					hud.setLive(tempLive);
				}
			}
			if (tempObject.getId() == ID.Block_steel_wall || tempObject.getId() == ID.Block_sea_wall) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}
		}
	}
}
