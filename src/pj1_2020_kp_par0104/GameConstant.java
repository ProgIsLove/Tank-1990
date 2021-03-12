package pj1_2020_kp_par0104;


public class GameConstant {
	
	private final int HEIGHT = 480;
	private final int WIDTH = 640;
	private final int SPEED = 2;
	private final int BULLET_SPEED = 3;
	private final int BLOCK_SIZE = 25;
	private final int TANK_SIZE = 16;
	private final int BULLET_SIZE = 6;
	private final int TIMER_SHOOT = 70;
	private final int RESPAWN_TIME = 100;
	private final int SCORE = 200;
	
	public int getHeight() {
		return HEIGHT;
	}
	public int getWidth() {
		return WIDTH;
	}
	public int getSpeed() {
		return SPEED;
	}
	
	public int getTankSize() {
		return TANK_SIZE;
	}

	public int getBulletSpeed() {
		return BULLET_SPEED;
	}
	
	public int getBlockSize() {
		return BLOCK_SIZE;
	}
	
	public int getBulletSize() {
		return BULLET_SIZE;
	}
	
	public int getTimerShoot() {
		return TIMER_SHOOT;
	}
	
	public int getRespawnTime() {
		return RESPAWN_TIME;
	}
	
	public int getScore() {
		return SCORE;
	}
}
