package game.objects;


import game.core.GameConstant;
import game.core.GameContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import java.util.List;

public class Player extends GameObject {
	
	private final GameContext gameContext;
	private final WritableImage[] playerImages = new WritableImage[4];

	public Player(int x, int y, GameObjectType gameObjectType, int direction, GameContext gameContext) {
		super(x, y, gameObjectType, direction);
        this.gameContext = gameContext;

        playerImages[0] = gameContext.sheet.grabImage(1, 6, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
		playerImages[1] = gameContext.sheet.grabImage(2, 1, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
		playerImages[2] = gameContext.sheet.grabImage(2, 2, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
		playerImages[3] = gameContext.sheet.grabImage(2, 3, GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
	}
	
	@Override
	public void tick() {
        move();
        collision();
	}

    private void move() {
        int tempY = getY();
        tempY += getSpeedY();
        setY(tempY);

        int tempX = getX();
        tempX += getSpeedX();
        setX(tempX);
    }

    @Override
	public void render(GraphicsContext g) {
		if(getDirection() == 1)
			g.drawImage(playerImages[0], getX(), getY());
		else if(getDirection() == 2)
			g.drawImage(playerImages[3], getX(), getY());
		else if(getDirection() == 3)
			g.drawImage(playerImages[1], getX(), getY());
		else if(getDirection() == 4)
			g.drawImage(playerImages[2], getX(), getY());
	}
	
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(getX(), getY(), GameConstant.TANK_SIZE, GameConstant.TANK_SIZE);
	}
	
	public void collision() {
        List<GameObject> gameObject = gameContext.handler.getGameObjectsByTypes(
                GameObjectType.BLOCK_BRICK_WALL,
                GameObjectType.BLOCK_STEEL_WALL,
                GameObjectType.BLOCK_SEA_WALL
        );

        for (GameObject obj : gameObject) {
            if (obj == this) continue;

            if (getBounds().intersects(obj.getBounds())) {
                switch (obj.getGameObjectType()) {
                    case BLOCK_SEA_WALL, BLOCK_STEEL_WALL, BLOCK_BRICK_WALL -> {
                        int tempX = getX();
                        tempX -= getSpeedX();
                        setX(tempX);

                        int tempY = getY();
                        tempY -= getSpeedY();
                        setY(tempY);
                    }
                }
            }
        }
	}	
}
