package game.core;

import game.engine.HandlerImpl;
import game.factory.GameObjectFactory;
import game.graphics.SpriteSheet;
import game.ui.Hud;

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
