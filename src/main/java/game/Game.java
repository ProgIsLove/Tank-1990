package game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Game extends Canvas implements Renderable, Tickable {

	private final Handler handler;
	private final Map map;
	private final Spawner spawner;
	private final BufferedImageLoader loader;
	private final SpriteSheet sheet;
	private final Level level;
	private final Hud hud;
    private final KeyInput keyInput;
    private AnimationTimer gameLoop;

	public Game() {
        super(GameConstant.WIDTH, GameConstant.HEIGHT);

		loader = new BufferedImageLoader();

        sheet = new SpriteSheet(loader.loadImage("/img/SpriteSheetPic.png"));
		level = new Level();
		handler = new Handler();
		hud = new Hud();
		spawner = new Spawner(handler, sheet, hud);
		map = new Map(handler, spawner, sheet, level);

        keyInput = new KeyInput(handler, sheet, hud);

        setupGameLoop();
	}

    public KeyInput getKeyInput() {
        return keyInput;
    }

    private void setupGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                tick();
                render();
            }
        };
    }

    public void start() {
        gameLoop.start();
    }

    @Override
    public void tick() {
        handler.tick();
        map.tick();
        if (hud.getLive() <= 0 || hud.getCrownLive() <= 0) {
            handler.object.clear();
            System.out.println("Game Over! High score: " + hud.getScore());
            restartGame();
        }
    }

	public void restartGame() {
		hud.setLive(5);
		hud.setScore(0);
		hud.setEnemyCount(0);
		hud.setCrownLive(1);
		map.setIsDraw(false);
	}

    @Override
    public void render() {
        GraphicsContext gc = getGraphicsContext2D();

        // clear screen
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameConstant.WIDTH, GameConstant.HEIGHT);

        // let other components render
        handler.render(gc);
        hud.render(gc);
    }
}
