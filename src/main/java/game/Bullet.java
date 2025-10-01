package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;


public class Bullet extends GameObject {

	private final Image bulletImages;
    private final GameContext gameContext;

	public Bullet(int x, int y, GameObjectType gameObjectType, int direction, GameContext gameContext) {
		super(x, y, gameObjectType, direction);

        this.gameContext = gameContext;
		
		bulletImages = gameContext.sheet.grabImage(1, 4, GameConstant.BULLET_SIZE, GameConstant.BULLET_SIZE);
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
		g.drawImage(bulletImages, getX(), getY());
	}
	
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D (getX(),getY(), GameConstant.BULLET_SIZE, GameConstant.BULLET_SIZE);
	}
	
	public void collision() {
        List<GameObject> gameObjects = gameContext.handler.getGameObjectsByTypes(
                GameObjectType.BLOCK_STEEL_WALL,
                GameObjectType.ENEMY,
                GameObjectType.BLOCK_BRICK_WALL,
                GameObjectType.BLOCK_SEA_WALL
        );

        for (GameObject obj : gameObjects) {
            if (obj == this) continue;

            if (getBounds().intersects(obj.getBounds())) {
                switch (obj.getId()) {
                    case GameObjectType.BLOCK_BRICK_WALL -> {
                        gameContext.handler.removeObject(this);
                        gameContext.handler.removeObject(obj);
                    }
                    case GameObjectType.ENEMY -> {
                        int tempCounter = gameContext.hud.getEnemyCount();
                        int tempScore = gameContext.hud.getScore();
                        gameContext.handler.removeObject(this);
                        gameContext.handler.removeObject(obj);
                        tempCounter -= 1;
                        tempScore += GameConstant.SCORE;
                        gameContext.hud.setEnemyCount(tempCounter);
                        gameContext.hud.setScore(tempScore);
                    }
                    case GameObjectType.BLOCK_STEEL_WALL, GameObjectType.BLOCK_SEA_WALL -> gameContext.handler.removeObject(this);
                }
            }
        }
	}
}
