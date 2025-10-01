package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.GameObjectType.*;

public class Enemy extends GameObject {

    private final Spawner spawner;
    private final WritableImage[] enemyImages = new WritableImage[4];
	private final Random rnd = new Random();
    private final GameContext gameContext;

    private int timerShoot;

	public Enemy(int x, int y, GameObjectType gameObjectType, int direction, Spawner spawner, GameContext gameContext) {
		super(x, y, gameObjectType, direction);
		this.spawner = spawner;
        this.gameContext = gameContext;

        timerShoot = rnd.nextInt(GameConstant.TIMER_SHOOT);

		enemyImages[0] = gameContext.sheet.grabImage(2, 4, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
		enemyImages[1] = gameContext.sheet.grabImage(2, 5, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
		enemyImages[2] = gameContext.sheet.grabImage(2, 6, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
		enemyImages[3] = gameContext.sheet.grabImage(3, 1, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);


	}
	
	@Override
	public void tick() {
        move();
        shoot();
        collision();
	}

    private void shoot() {
        if (timerShoot <= 0) {
            gameContext.handler.addObject(gameContext.factory.createGameObject(GameObjectType.ENEMY_BULLET, getX() + GameConstant.BULLET_SIZE / 2,
                    getY() + GameConstant.BULLET_SIZE / 2, getDirection(), spawner));
			timerShoot = rnd.nextInt(GameConstant.TIMER_SHOOT);
		} else
			timerShoot--;
    }

    private void move() {
        setSpeedX(GameConstant.SPEED);
        setSpeedY(GameConstant.SPEED);

        int tempY = getY();
        int tempX = getX();

        switch(getDirection()) {
        case 1:{
            tempY -= getSpeedY();
            setY(tempY);
            break;
        }
        case 2:{
            tempY += getSpeedY();
            setY(tempY);
            break;
        }
        case 3:{
            tempX -= getSpeedX();
            setX(tempX);
            break;
        }
        case 4:{
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
		if (getDirection() == 1)
			g.drawImage(enemyImages[0], getX(), getY());
		else if (getDirection() == 2)
			g.drawImage(enemyImages[3], getX(), getY());
		else if (getDirection() == 3)
			g.drawImage(enemyImages[1], getX(), getY());
		else if (getDirection() == 4)
			g.drawImage(enemyImages[2], getX(), getY());
	}
	
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(getX(), getY(), GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
	}
	
	public void collision() {
        List<GameObject> gameObjects = gameContext.handler.getGameObjectsByTypes(
                BLOCK_SEA_WALL,
                BLOCK_BRICK_WALL,
                BLOCK_STEEL_WALL);

        for (GameObject obj : gameObjects) {
            if (obj == this) continue;

            if (getBounds().intersects(obj.getBounds())) {
                switch (obj.getId()) {
                    case BLOCK_SEA_WALL, BLOCK_STEEL_WALL, BLOCK_BRICK_WALL -> {
                        int tempY = getY();
                        int tempX = getX();
                        int move;

                        if(getDirection() == 1) {
                            move = rnd.nextInt(4 + 1 - 1) + 1;
                            tempY += getSpeedY();
                            setY(tempY);
                            setDirection(move);
                            break;
                        }

                        if(getDirection() == 2) {
                            move = rnd.nextInt(4 + 1 - 1) + 1;
                            tempY -= getSpeedY();
                            setY(tempY);
                            setDirection(move);
                            break;
                        }

                        if(getDirection() == 3) {
                            move = rnd.nextInt(4 + 1 - 1) + 1;
                            tempX += getSpeedX();
                            setX(tempX);
                            setDirection(move);
                            break;
                        }

                        if(getDirection() == 4) {
                            move = rnd.nextInt(4 + 1 - 1) + 1;
                            tempX -= getSpeedX();
                            setX(tempX);
                            setDirection(move);
                        }
                    }
                }
            }
        }
	}
}
