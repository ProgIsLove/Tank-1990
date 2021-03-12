package pj1_2020_kp_par0104;


public class Spawner {
	
	private Handler handler;
	private GameConstant gameCon;
	private SpriteSheet sheet;
	private Hud hud;
	private int counter = 0;
	private int respawnTime;
	
	public Spawner(Handler handler, GameConstant gameCon, SpriteSheet sheet, Hud hud) {
		this.handler = handler;
		this.gameCon = gameCon;
		this.sheet = sheet;
		this.hud = hud;
		
		respawnTime = gameCon.getRespawnTime();
	}
	
	public void nextEnemy(int blockSpaceX, int blockSpaceY) {
		if(respawnTime <= 0 && hud.getEnemyCount() <= 5) {
			handler.addObject(new Enemy(0 + blockSpaceX, 0 + blockSpaceY, 
					ID.Enemy, 1,handler, gameCon, sheet, this, hud));
			counter = hud.getEnemyCount();
			counter += 1;
			hud.setEnemyCount(counter);
			respawnTime = gameCon.getRespawnTime();
		} else 
			respawnTime--;
	}
	
	public void nextLive() {
			handler.addObject(new Player(175, 325, 
					ID.Player, 1, handler, gameCon, sheet));
	}
}
