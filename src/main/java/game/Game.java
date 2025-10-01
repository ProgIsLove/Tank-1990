package game;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Canvas implements Renderable, Tickable {

    private final Map map;
	private final Spawner spawner;
    private final Level level;
	private final Hud hud;
    private final KeyInput keyInput;
    private final GameContext gameContext;
    private AnimationTimer gameLoop;

	public Game() {
        super(GameConstant.WIDTH, GameConstant.HEIGHT);

        SpriteSheet sheet = new SpriteSheet();
		level = new Level();
        HandlerImpl handlerImpl = new HandlerImpl();
		hud = new Hud();

        gameContext = new GameContext(handlerImpl, sheet, hud);
        spawner = new Spawner(gameContext);
		map = new Map(level, gameContext, spawner);

        keyInput = new KeyInput();
        PlayerController playerController = new PlayerController(gameContext);
        keyInput.addObserver(playerController);

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
        gameContext.handler.tick();
        map.tick();
        if (hud.getLive() <= 0 || hud.getCrownLive() <= 0) {
            gameContext.handler.clear();
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
        gameContext.handler.render(gc);
        hud.render(gc);
    }
}
