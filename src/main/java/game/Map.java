package game;

import java.util.Random;

public class Map implements Tickable {

    private final HandlerImpl handlerImpl;
    private final SpriteSheet sheet;
    private final Spawner spawner;
    private final Level level;
    private final Random rnd = new Random();

    private boolean isDraw = false;

    private int[][] gameField;

    public Map(HandlerImpl handlerImpl, Spawner spawner, SpriteSheet sheet, Level level) {
        this.handlerImpl = handlerImpl;
        this.spawner = spawner;
        this.sheet = sheet;
        this.level = level;
    }

    @Override
    public void tick() {
        fillGameLevel();
        spawnEnemy();
    }

    public void setIsDraw(boolean isDraw) { this.isDraw = isDraw; }

    private void fillGameLevel() {
        gameField = level.levelOne();

        if (isDraw) return;

        for (int row = 0; row < gameField.length; row++) {
            for (int col = 0; col < gameField[row].length; col++) {
                int blockValue = gameField[row][col];
                int x = col * GameConstant.BLOCK_SIZE;
                int y = row * GameConstant.BLOCK_SIZE;

                switch (blockValue) {
                    case 1 -> handlerImpl.addObject(new Block(x, y, ID.BLOCK_BRICK_WALL, sheet));
                    case 2 -> handlerImpl.addObject(new Block(x, y, ID.BLOCK_STEEL_WALL, sheet));
                    case 3 -> handlerImpl.addObject(new Block(x, y, ID.GOLDEN_CROWN, sheet));
                    case 4 -> handlerImpl.addObject(new Player(x, y, ID.PLAYER, 1, handlerImpl, sheet));
                    case 5 -> handlerImpl.addObject(new Block(x, y, ID.BLOCK_SEA_WALL, sheet));
                }
            }
        }

        isDraw = true;
    }

    private void spawnEnemy() {
        if (gameField == null) return;

        int key = rnd.nextInt(2);
        int blockValue = switch (key) {
            case 0 -> gameField[2][5];
            case 1 -> gameField[2][15];
            default -> throw new IllegalStateException("Unexpected key: " + key);
        };

        if (blockValue == 7) {
            spawner.nextEnemy(375, 50);
        } else {
            spawner.nextEnemy(125, 50);
        }
    }
}