package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

public class Block extends GameObject {

	private final WritableImage[] blocks = new WritableImage[4];

	public Block(int x, int y, GameObjectType gameObjectType, SpriteSheet sheet) {
		super(x, y, gameObjectType);
		blocks[0] = sheet.grabImage(1, 1, GameConstant.BLOCK_SIZE, GameConstant.BLOCK_SIZE);
		blocks[1] = sheet.grabImage(1, 2, GameConstant.BLOCK_SIZE, GameConstant.BLOCK_SIZE);
		blocks[2] = sheet.grabImage(1, 3, GameConstant.BLOCK_SIZE, GameConstant.BLOCK_SIZE);
		blocks[3] = sheet.grabImage(3, 2, GameConstant.BLOCK_SIZE, GameConstant.BLOCK_SIZE);
	}

	@Override
	public void tick() {
	}
	
	@Override
	public void render(GraphicsContext g) {
		if (getId() == GameObjectType.BLOCK_BRICK_WALL)
			g.drawImage(blocks[0], getX(), getY());
		else if (getId() == GameObjectType.BLOCK_STEEL_WALL)
			g.drawImage(blocks[1], getX(), getY());
		else if (getId() == GameObjectType.BLOCK_SEA_WALL)
			g.drawImage(blocks[2], getX(), getY());
		else if (getId() == GameObjectType.GOLDEN_CROWN)
			g.drawImage(blocks[3], getX(), getY());
	}
	
	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(getX(), getY(), GameConstant.BLOCK_SIZE, GameConstant.BLOCK_SIZE);
	}
}
