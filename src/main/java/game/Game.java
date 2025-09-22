package game;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Canvas implements Renderable, Tickable {

	private final HandlerImpl handlerImpl;
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
		handlerImpl = new HandlerImpl();
		hud = new Hud();
		spawner = new Spawner(handlerImpl, sheet, hud);
		map = new Map(handlerImpl, spawner, sheet, level);

        keyInput = new KeyInput(handlerImpl, sheet, hud);

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

    public void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }

    @Override
    public void tick() {
        handlerImpl.tick();
        map.tick();
        if (hud.getLive() <= 0 || hud.getCrownLive() <= 0) {
            handlerImpl.object.clear();
            this.stopGameLoop();
            Platform.runLater(() -> {
                Stage stage = (Stage) this.getScene().getWindow();
                new HighScoreScene(stage, hud.getScore());
            });
        }
    }

    @Override
    public void render() {
        GraphicsContext gc = getGraphicsContext2D();

        // clear screen
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameConstant.WIDTH, GameConstant.HEIGHT);

        // let other components render
        handlerImpl.render(gc);
        hud.render(gc);
    }
}
