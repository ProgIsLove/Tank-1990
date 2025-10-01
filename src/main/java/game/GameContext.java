package game;

public class GameContext {
    public final HandlerImpl handler;
    public final SpriteSheet sheet;
    public final Hud hud;
    public final GameObjectFactory factory;

    public GameContext(HandlerImpl handler, SpriteSheet sheet, Hud hud) {
        this.handler = handler;
        this.sheet = sheet;
        this.hud = hud;
        this.factory = new GameObjectFactory(this);
    }
}
