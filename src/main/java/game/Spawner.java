package game;


public class Spawner {

	private int counter = 0;
	private int respawnTime;
    private final GameContext gameContext;
	
	public Spawner(GameContext gameContext) {
        this.gameContext = gameContext;
		
		respawnTime = GameConstant.RESPAWN_TIME;
	}
	
	public void nextEnemy(int blockSpaceX, int blockSpaceY) {
        Hud hud = gameContext.hud;
        if(respawnTime <= 0 && hud.getEnemyCount() <= 5) {
            gameContext.handler.addObject(
                    gameContext.factory.createGameObject(GameObjectType.ENEMY, blockSpaceX, blockSpaceY, 1, this)
            );

			counter = hud.getEnemyCount();
			counter += 1;
			hud.setEnemyCount(counter);
			respawnTime = GameConstant.RESPAWN_TIME;
		} else 
			respawnTime--;
	}
	
	public void nextLive() {
        gameContext.handler.addObject(
                gameContext.factory.createGameObject(GameObjectType.PLAYER, 175, 325, 1)
        );
	}
}
