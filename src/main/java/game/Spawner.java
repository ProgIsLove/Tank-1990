package game;


public class Spawner {

    private final GameContext gameContext;


    private int respawnTime;

	public Spawner(GameContext gameContext) {
        this.gameContext = gameContext;
		
		respawnTime = GameConstant.RESPAWN_TIME;
	}
	
	public void nextEnemy(int blockSpaceX, int blockSpaceY) {
        int counter;
        Hud hud = gameContext.hud;
        if(hud.getEnemyCount() <= GameConstant.MAX_ENEMIES) {
            if (respawnTime <= 0) {
                gameContext.handler.addObject(
                        gameContext.factory.createGameObject(GameObjectType.ENEMY, blockSpaceX, blockSpaceY, 1, this)
                );
                counter = hud.getEnemyCount();
                counter += 1;
                hud.setEnemyCount(counter);
                respawnTime = GameConstant.RESPAWN_TIME;
            } else {
                respawnTime--;
            }
        }
	}
	
	public void nextLive() {
        gameContext.handler.addObject(
                gameContext.factory.createGameObject(GameObjectType.PLAYER, 175, 325, 1)
        );
	}
}
