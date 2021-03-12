package pj1_2020_kp_par0104;

import java.util.Random;

public class Map implements Tickable {

	private Handler handler;
	private GameConstant gameCon;
	private SpriteSheet sheet;
	private Spawner spawner;
	private Level level;
	private Random rnd = new Random();
	private int blockSpaceX = 0;
	private int blockSpaceY = 0;
	private int blockValue;
	private int[][] gameField;
	private boolean isDraw = false;

	public Map(Handler handler, GameConstant gameCon, Spawner spawner, SpriteSheet sheet, Level level) {
		this.handler = handler;
		this.gameCon = gameCon;
		this.spawner = spawner;
		this.sheet = sheet;
		this.level = level;
	}
	
	public int getBlockX() {
		return blockSpaceX;
	}
	
	public int getBlockY() {
		return blockSpaceY;
	}
	
	public void setBlockX(int blockSpaceX) {
		this.blockSpaceX = blockSpaceX; 
	}
	
	public void setBlockY(int blockSpaceY) {
		this.blockSpaceY = blockSpaceY; 
	}
	
	public void setIsDraw(boolean isDraw) {
		this.isDraw = isDraw;
	}

	@Override
	public void tick() {
		fillGameLvl(blockSpaceX, blockSpaceY, blockValue);
		spawnEnemy(blockValue);
	}
	
	public void fillGameLvl(int blockSpaceX, int blockSpaceY, int blockValue) {

		gameField = level.levelOne();

		if (!isDraw) {
			for (int row = 0; row < gameField.length; row++) {
				for (int column = 0; column < gameField[0].length; column++) {

					blockValue = gameField[row][column];

					switch (blockValue) {
					case 1: {
						handler.addObject(new Block(0 + blockSpaceX, 0 + blockSpaceY, ID.Block_brick_wall, handler, gameCon, sheet));
						break;
					}
					case 2: {
						handler.addObject(new Block(0 + blockSpaceX, 0 + blockSpaceY, ID.Block_steel_wall, handler, gameCon, sheet));
						break;
					}
					case 3: {
						handler.addObject(new Block(0 + blockSpaceX, 0 + blockSpaceY, ID.Golden_crown, handler, gameCon, sheet));
						break;
					}
					case 4: {
						handler.addObject(new Player(0 + blockSpaceX, 0 + blockSpaceY, ID.Player, 1, handler, gameCon, sheet));
						break;
					}
					case 5: {
						handler.addObject(new Block(0 + blockSpaceX, 0 + blockSpaceY, ID.Block_sea_wall, handler, gameCon, sheet));
						break;
					}
					}
					setBlockX(gameCon.getBlockSize());
					blockSpaceX += getBlockX();
				}
				setBlockY(gameCon.getBlockSize());
				blockSpaceY += getBlockY();
				blockSpaceX = 0;
			}
			isDraw = true;
		}
	}
	
	public void spawnEnemy(int blockValue) {
		gameField = level.levelOne();
		int key;
		
		key = rnd.nextInt(2);
		switch (key) {
		case 0: {
			blockValue = gameField[2][5];
			break;
		}
		case 1: {
			blockValue = gameField[2][15];
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}
		
		if(blockValue == 7) {
			spawner.nextEnemy(375, 50);
		}else
			spawner.nextEnemy(125, 50);
	}
}
