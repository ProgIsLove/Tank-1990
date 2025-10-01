package game;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HandlerImpl implements Handler, Tickable {

    private final LinkedList<GameObject> gameObjects = new LinkedList<>();
    private final List<GameObject> objectsToAdd = new ArrayList<>();
    private final List<GameObject> objectsToRemove = new ArrayList<>();

    public List<GameObject> getGameObjectsByType(GameObjectType type) {
        List<GameObject> result = new ArrayList<>();
        for (GameObject obj : gameObjects) {
            if (obj.getId() == type) result.add(obj);
        }
        return result;
    }

    public List<GameObject> getGameObjectsByTypes(GameObjectType... types) {
        List<GameObject> result = new ArrayList<>();
        for (GameObject obj : gameObjects) {
            for (GameObjectType type : types) {
                if (obj.getId() == type) {
                    result.add(obj);
                    break;
                }
            }
        }
        return result;
    }

	@Override
	public void tick() {

        for (GameObject obj : gameObjects) {
            obj.tick();
        }

        // Apply queued additions
        if (!objectsToAdd.isEmpty()) {
            gameObjects.addAll(objectsToAdd);
            objectsToAdd.clear();
        }

        // Apply queued removals
        if (!objectsToRemove.isEmpty()) {
            gameObjects.removeAll(objectsToRemove);
            objectsToRemove.clear();
        }
	}
	
	@Override
	public void render(GraphicsContext g) {
        for (GameObject obj : gameObjects) {
            obj.render(g);
        }
	}

    @Override
    public void addObject(GameObject object) {
        objectsToAdd.add(object); // queue addition
    }

    @Override
    public void removeObject(GameObject object) {
        objectsToRemove.add(object); // queue removal
    }

    @Override
    public void clear() {
        gameObjects.clear();
    }
}
