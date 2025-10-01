package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import java.util.List;

public class EnemyBullet extends GameObject {

    private final Spawner spawner;
	private final WritableImage bulletImage;
    private final GameContext gameContext;

	public EnemyBullet(int x, int y, GameObjectType gameObjectType, int direction, Spawner spawner, GameContext gameContext) {
		super(x, y, gameObjectType, direction);
        this.spawner = spawner;
        this.gameContext = gameContext;

		this.bulletImage = gameContext.sheet.grabImage(1, 5, GameConstant.BULLET_SIZE, GameConstant.BULLET_SIZE);
	}
	
	@Override
	public void tick() {
        move();
        collision();
	}

    private void move() {
        setSpeedY(GameConstant.BULLET_SPEED);
        setSpeedX(GameConstant.BULLET_SPEED);
        switch (getDirection()) {
        case 1: {
            int tempY = getY();
            tempY -= getSpeedY();
            setY(tempY);
            break;
        }
        case 2: {
            int tempY = getY();
            tempY += getSpeedY();
            setY(tempY);
            break;
        }
        case 3: {
            int tempX = getX();
            tempX -= getSpeedX();
            setX(tempX);
            break;
        }
        case 4: {
            int tempX = getX();
            tempX += getSpeedX();
            setX(tempX);
            break;
        }
        default:
            throw new IllegalArgumentException("Unexpected value: " + getDirection());
        }
    }

    @Override
	public void render(GraphicsContext g) {
		g.drawImage(bulletImage, getX(), getY());
	}
	
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(getX(), getY(), GameConstant.BULLET_SIZE, GameConstant.BULLET_SIZE);
	}
	
	public void collision() {
        List<GameObject> gameObjects = gameContext.handler.getGameObjectsByTypes(
                GameObjectType.BULLET,
                GameObjectType.PLAYER,
                GameObjectType.GOLDEN_CROWN,
                GameObjectType.BLOCK_BRICK_WALL,
                GameObjectType.BLOCK_STEEL_WALL,
                GameObjectType.BLOCK_SEA_WALL
        );

        for (GameObject obj : gameObjects) {
            if (obj == this) continue;

            if (getBounds().intersects(obj.getBounds())) {
                switch (obj.getGameObjectType()) {
                    case GameObjectType.BULLET, GameObjectType.BLOCK_BRICK_WALL -> {
                        gameContext.handler.removeObject(this);
                        gameContext.handler.removeObject(obj);
                    }
                    case GameObjectType.PLAYER -> {
                        gameContext.handler.removeObject(this);
                        gameContext.handler.removeObject(obj);
                        int tempLive = gameContext.hud.getLive() - 1;
                        gameContext.hud.setLive(tempLive);
                        if (gameContext.hud.getLive() != 0 || gameContext.hud.getCrownLive() != 0) {
                            spawner.nextLive();
                        }
                    }
                    case GameObjectType.GOLDEN_CROWN -> {
                        gameContext.handler.removeObject(this);
                        gameContext.handler.removeObject(obj);
                        gameContext.hud.setCrownLive(gameContext.hud.getCrownLive() - 1);
                        gameContext.hud.setLive(gameContext.hud.getLive() - 1);
                    }
                    case GameObjectType.BLOCK_STEEL_WALL, GameObjectType.BLOCK_SEA_WALL -> gameContext.handler.removeObject(this);
                }
            }
        }
	}
}
