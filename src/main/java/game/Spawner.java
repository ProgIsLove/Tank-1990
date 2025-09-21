package game;


public class Spawner {
	
	private final Handler handler;
	private final SpriteSheet sheet;
	private final Hud hud;
	private int counter = 0;
	private int respawnTime;
	
	public Spawner(Handler handler, SpriteSheet sheet, Hud hud) {
		this.handler = handler;
		this.sheet = sheet;
		this.hud = hud;
		
		respawnTime = GameConstant.RESPAWN_TIME;
	}
	
	public void nextEnemy(int blockSpaceX, int blockSpaceY) {
		if(respawnTime <= 0 && hud.getEnemyCount() <= 5) {
			handler.addObject(new Enemy(blockSpaceX, blockSpaceY,
					ID.ENEMY, 1,handler, sheet, this, hud));
			counter = hud.getEnemyCount();
			counter += 1;
			hud.setEnemyCount(counter);
			respawnTime = GameConstant.RESPAWN_TIME;
		} else 
			respawnTime--;
	}
	
	public void nextLive() {
			handler.addObject(new Player(175, 325, 
					ID.PLAYER, 1, handler, sheet));
	}
}
