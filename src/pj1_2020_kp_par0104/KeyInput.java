package pj1_2020_kp_par0104;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private GameConstant gameCon;
	private SpriteSheet sheet;
	private Hud hud;
	private boolean isShooting = false;
	private boolean[] keyDown = new boolean[4];

	public KeyInput(Handler handler, GameConstant gameCon, SpriteSheet sheet, Hud hud) {

		this.handler = handler;
		this.gameCon = gameCon;
		this.sheet = sheet;
		this.hud = hud;

		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					tempObject.setSpeedY(-gameCon.getSpeed());
					tempObject.setDirection(1);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setSpeedY(gameCon.getSpeed());
					tempObject.setDirection(2);
					keyDown[1] = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setSpeedX(-gameCon.getSpeed());
					tempObject.setDirection(3);
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setSpeedX(gameCon.getSpeed());
					tempObject.setDirection(4);
					keyDown[3] = true;
				}
				if (key == KeyEvent.VK_SPACE && !isShooting) {
					isShooting = true;
					handler.addObject(new Bullet(tempObject.getX() + gameCon.getBulletSize() / 2,
							tempObject.getY() + gameCon.getBulletSize() / 2, ID.Bullet, tempObject.getDirection(),
							handler, gameCon, sheet, hud));
				}
			}
			if (key == KeyEvent.VK_ESCAPE) System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					keyDown[0] = false;
					tempObject.setSpeedY(0);
				}
				if (key == KeyEvent.VK_S) {
					keyDown[1] = false;
					tempObject.setSpeedY(0);
				}
				if (key == KeyEvent.VK_A) {
					keyDown[2] = false;
					tempObject.setSpeedX(0);
				}
				if (key == KeyEvent.VK_D) {
					keyDown[3] = false;
					tempObject.setSpeedX(0);
				}
				if (key == KeyEvent.VK_SPACE) isShooting = false;
			}
		}
	}
}
