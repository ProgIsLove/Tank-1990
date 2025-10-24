package game.objects;

import game.core.GameConstant;
import game.core.GameContext;
import game.core.Level;
import game.engine.Tickable;

import java.util.Random;

public class Map implements Tickable {

    private final Level level;
    private final Random rnd = new Random();
    private final GameContext gameContext;
    private final Spawner spawner;

    private boolean isDraw = false;
    private int[][] gameField;

    public Map(Level level, GameContext gameContext, Spawner spawner) {
        this.level = level;
        this.gameContext = gameContext;
        this.spawner = spawner;
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

                GameObject obj = switch (blockValue) {
                    case 1 -> gameContext.factory.createGameObject(GameObjectType.BLOCK_BRICK_WALL, x, y);
                    case 2 -> gameContext.factory.createGameObject(GameObjectType.BLOCK_STEEL_WALL, x, y);
                    case 3 -> gameContext.factory.createGameObject(GameObjectType.GOLDEN_CROWN, x, y);
                    case 4 -> gameContext.factory.createGameObject(GameObjectType.PLAYER, x, y, 1);
                    case 5 -> gameContext.factory.createGameObject(GameObjectType.BLOCK_SEA_WALL, x, y);
                    default -> null;
                };

                if (obj != null) {
                    gameContext.handler.addObject(obj);
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