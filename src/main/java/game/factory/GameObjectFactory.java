package game.factory;

import game.core.GameContext;
import game.objects.*;

public class GameObjectFactory {

    private final GameContext context;

    public GameObjectFactory(GameContext context) {
        this.context = context;
    }

    public GameObject createGameObject(GameObjectType type, int x, int y) {
        return switch (type) {
            case BLOCK_BRICK_WALL -> new Block(x, y, GameObjectType.BLOCK_BRICK_WALL, context.sheet);
            case BLOCK_SEA_WALL -> new Block(x, y, GameObjectType.BLOCK_SEA_WALL, context.sheet);
            case BLOCK_STEEL_WALL -> new Block(x, y, GameObjectType.BLOCK_STEEL_WALL, context.sheet);
            case GOLDEN_CROWN -> new Block(x, y, GameObjectType.GOLDEN_CROWN, context.sheet);
            default -> throw new IllegalArgumentException("Invalid GameObjectType");
        };
    }

    public GameObject createGameObject(GameObjectType type, int x, int y, int direction) {
        return switch (type) {
            case PLAYER -> new Player(x, y, GameObjectType.PLAYER, direction, context);
            case BULLET -> new Bullet(x, y, GameObjectType.BULLET, direction, context);
            default -> throw new IllegalArgumentException("Invalid GameObjectType");
        };
    }

    public GameObject createGameObject(GameObjectType type, int x, int y, int direction, Spawner spawner) {
        return switch (type) {
            case ENEMY -> new Enemy(x, y, GameObjectType.ENEMY, direction, spawner, context);
            case ENEMY_BULLET ->
                    new EnemyBullet(x, y, GameObjectType.ENEMY_BULLET, direction, spawner, context);
            default -> throw new IllegalArgumentException("Invalid GameObjectType");
        };
    }
}
