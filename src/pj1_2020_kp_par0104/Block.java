package pj1_2020_kp_par0104;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject {

	private GameConstant gameCon;
	private BufferedImage [] block = new BufferedImage[4];

	public Block(int x, int y, ID id, Handler handler, GameConstant gameCon, SpriteSheet sheet) {
		super(x, y, id);
		this.gameCon = gameCon;
		block[0] = sheet.grabImage(1, 1, gameCon.getBlockSize(), gameCon.getBlockSize());
		block[1] = sheet.grabImage(1, 2, gameCon.getBlockSize(), gameCon.getBlockSize());
		block[2] = sheet.grabImage(1, 3, gameCon.getBlockSize(), gameCon.getBlockSize());
		block[3] = sheet.grabImage(3, 2, gameCon.getBlockSize(), gameCon.getBlockSize());
	}

	@Override
	public void tick() {
	}
	
	@Override
	public void render(Graphics g) {
		if (getId() == ID.Block_brick_wall)
			g.drawImage(block[0], getX(), getY(), null);
		else if (getId() == ID.Block_steel_wall)
			g.drawImage(block[1], getX(), getY(), null);
		else if (getId() == ID.Block_sea_wall)
			g.drawImage(block[2], getX(), getY(), null);
		else if (getId() == ID.Golden_crown)
			g.drawImage(block[3], getX(), getY(), null);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), gameCon.getBlockSize(), gameCon.getBlockSize());
	}
}
