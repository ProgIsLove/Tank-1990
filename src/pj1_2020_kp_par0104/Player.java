package pj1_2020_kp_par0104;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
	
	private Handler handler;
	private GameConstant gameCon;
	private BufferedImage []player_image = new BufferedImage[4];
	
	public Player(int x, int y, ID id, int direction, Handler handler, GameConstant gameCon, 
			SpriteSheet sheet) {
		super(x, y, id, direction);
		this.handler = handler;
		this.gameCon = gameCon;
		
		player_image[0] = sheet.grabImage(1, 6, gameCon.getTankSize(), gameCon.getTankSize());
		player_image[1] = sheet.grabImage(2, 1, gameCon.getTankSize(), gameCon.getBlockSize());
		player_image[2] = sheet.grabImage(2, 2, gameCon.getTankSize(), gameCon.getBlockSize());
		player_image[3] = sheet.grabImage(2, 3, gameCon.getTankSize(), gameCon.getTankSize());
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
	public void render(Graphics g) {
		if(getDirection() == 1)
			g.drawImage(player_image[0], getX(), getY(), null);
		else if(getDirection() == 2)
			g.drawImage(player_image[3], getX(), getY(), null);
		else if(getDirection() == 3)
			g.drawImage(player_image[1], getX(), getY(), null);
		else if(getDirection() == 4)
			g.drawImage(player_image[2], getX(), getY(), null);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), gameCon.getTankSize(), gameCon.getTankSize());
	}
	
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Block_sea_wall || tempObject.getId() == ID.Block_steel_wall || tempObject.getId() == ID.Block_brick_wall) {
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
