package game;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInput {

    private final GameContext gameContext;
    private final boolean[] keysDown = new boolean[4];
	private boolean isShooting = false;

	public KeyInput(GameContext gameContext) {
        this.gameContext = gameContext;

		keysDown[0] = false;
		keysDown[1] = false;
		keysDown[2] = false;
		keysDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		KeyCode key = e.getCode();

		for (GameObject player : gameContext.handler.getGameObjectsByType(GameObjectType.PLAYER)) {
				if (key == KeyCode.W) {
                    player.setSpeedY(-GameConstant.SPEED);
                    player.setDirection(1);
					keysDown[0] = true;
				}
				if (key == KeyCode.S) {
                    player.setSpeedY(GameConstant.SPEED);
                    player.setDirection(2);
					keysDown[1] = true;
				}
				if (key == KeyCode.A) {
                    player.setSpeedX(-GameConstant.SPEED);
                    player.setDirection(3);
					keysDown[2] = true;
				}
				if (key == KeyCode.D) {
                    player.setSpeedX(GameConstant.SPEED);
                    player.setDirection(4);
					keysDown[3] = true;
				}
				if (key == KeyCode.SPACE && !isShooting) {
					isShooting = true;
					gameContext.handler.addObject(new Bullet(player.getX() + GameConstant.BULLET_SIZE / 2,
                            player.getY() + GameConstant.BULLET_SIZE / 2, GameObjectType.BULLET, player.getDirection(), gameContext));
				}

			if (key == KeyCode.ESCAPE) System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
        KeyCode key = e.getCode();

        for (GameObject player : gameContext.handler.getGameObjectsByType(GameObjectType.PLAYER)) {
				if (key == KeyCode.W) {
					keysDown[0] = false;
                    player.setSpeedY(0);
				}
				if (key == KeyCode.S) {
					keysDown[1] = false;
                    player.setSpeedY(0);
				}
				if (key == KeyCode.A) {
					keysDown[2] = false;
                    player.setSpeedX(0);
				}
				if (key == KeyCode.D) {
					keysDown[3] = false;
                    player.setSpeedX(0);
				}
				if (key == KeyCode.SPACE) isShooting = false;
		}
	}
}
