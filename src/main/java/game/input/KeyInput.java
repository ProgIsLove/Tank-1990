package game.input;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class KeyInput {

    private final List<InputObserver> observers = new ArrayList<>();

    public void addObserver(InputObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(InputEventType event) {
        for (InputObserver obs : observers) {
            obs.onInput(event);
        }
    }

    public void keyPressed(KeyEvent e) {
        KeyCode key = e.getCode();
        switch (key) {
            case W -> notifyObservers(InputEventType.MOVE_UP_PRESSED);
            case S -> notifyObservers(InputEventType.MOVE_DOWN_PRESSED);
            case A -> notifyObservers(InputEventType.MOVE_LEFT_PRESSED);
            case D -> notifyObservers(InputEventType.MOVE_RIGHT_PRESSED);
            case SPACE -> notifyObservers(InputEventType.FIRE_PRESSED);
            case ESCAPE -> notifyObservers(InputEventType.ESCAPE);
        }
    }

    public void keyReleased(KeyEvent e) {
        KeyCode key = e.getCode();
        switch (key) {
            case W -> notifyObservers(InputEventType.MOVE_UP_RELEASED);
            case S -> notifyObservers(InputEventType.MOVE_DOWN_RELEASED);
            case A -> notifyObservers(InputEventType.MOVE_LEFT_RELEASED);
            case D -> notifyObservers(InputEventType.MOVE_RIGHT_RELEASED);
            case SPACE -> notifyObservers(InputEventType.FIRE_RELEASED);
        }
    }
}
