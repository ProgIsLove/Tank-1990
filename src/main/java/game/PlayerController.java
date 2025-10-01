package game;

public class PlayerController implements InputObserver {

    private final GameContext context;
    private boolean isShooting = false;

    public PlayerController(GameContext context) {
        this.context = context;
    }

    @Override
    public void onInput(InputEventType event) {
        for (GameObject player : context.handler.getGameObjectsByType(GameObjectType.PLAYER)) {
            switch (event) {
                case MOVE_UP_PRESSED -> {
                    player.setSpeedX(0);
                    player.setSpeedY(-GameConstant.SPEED);
                    player.setDirection(1);
                }
                case MOVE_DOWN_PRESSED -> {
                    player.setSpeedX(0);
                    player.setSpeedY(GameConstant.SPEED);
                    player.setDirection(2);
                }
                case MOVE_LEFT_PRESSED -> {
                    player.setSpeedX(-GameConstant.SPEED);
                    player.setSpeedY(0);
                    player.setDirection(3);
                }
                case MOVE_RIGHT_PRESSED -> {
                    player.setSpeedX(GameConstant.SPEED);
                    player.setSpeedY(0);
                    player.setDirection(4);
                }
                case FIRE_PRESSED -> {
                    if (!isShooting) {
                        context.handler.addObject(new Bullet(
                                player.getX() + GameConstant.BULLET_SIZE / 2,
                                player.getY() + GameConstant.BULLET_SIZE / 2,
                                GameObjectType.BULLET,
                                player.getDirection(),
                                context
                        ));
                        isShooting = true;
                    }
                }
                case MOVE_UP_RELEASED, MOVE_DOWN_RELEASED,
                     MOVE_LEFT_RELEASED, MOVE_RIGHT_RELEASED -> {
                    player.setSpeedX(0);
                    player.setSpeedY(0);
                }
                case FIRE_RELEASED -> isShooting = false;
                case ESCAPE -> System.exit(0);
            }
        }
    }
}

