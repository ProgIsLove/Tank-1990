package game;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInput {

	private final Handler handler;
	private final SpriteSheet sheet;
	private final Hud hud;
    private final boolean[] keysDown = new boolean[4];
	private boolean isShooting = false;

	public KeyInput(Handler handler, SpriteSheet sheet, Hud hud) {

		this.handler = handler;
		this.sheet = sheet;
		this.hud = hud;

		keysDown[0] = false;
		keysDown[1] = false;
		keysDown[2] = false;
		keysDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		KeyCode key = e.getCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.PLAYER) {
				if (key == KeyCode.W) {
					tempObject.setSpeedY(-GameConstant.SPEED);
					tempObject.setDirection(1);
					keysDown[0] = true;
				}
				if (key == KeyCode.S) {
					tempObject.setSpeedY(GameConstant.SPEED);
					tempObject.setDirection(2);
					keysDown[1] = true;
				}
				if (key == KeyCode.A) {
					tempObject.setSpeedX(-GameConstant.SPEED);
					tempObject.setDirection(3);
					keysDown[2] = true;
				}
				if (key == KeyCode.D) {
					tempObject.setSpeedX(GameConstant.SPEED);
					tempObject.setDirection(4);
					keysDown[3] = true;
				}
				if (key == KeyCode.SPACE && !isShooting) {
					isShooting = true;
					handler.addObject(new Bullet(tempObject.getX() + GameConstant.BULLET_SIZE / 2,
							tempObject.getY() + GameConstant.BULLET_SIZE / 2, ID.BULLET, tempObject.getDirection(),
							handler, sheet, hud));
				}
			}
			if (key == KeyCode.ESCAPE) System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
        KeyCode key = e.getCode();

        for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.PLAYER) {
				if (key == KeyCode.W) {
					keysDown[0] = false;
					tempObject.setSpeedY(0);
				}
				if (key == KeyCode.S) {
					keysDown[1] = false;
					tempObject.setSpeedY(0);
				}
				if (key == KeyCode.A) {
					keysDown[2] = false;
					tempObject.setSpeedX(0);
				}
				if (key == KeyCode.D) {
					keysDown[3] = false;
					tempObject.setSpeedX(0);
				}
				if (key == KeyCode.SPACE) isShooting = false;
			}
		}
	}
}
