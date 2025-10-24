package game.ui;

import game.core.GameConstant;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Hud {

	private int enemyCount = 0;
	private int live = 5;
	private int crownLive = 1;
	private int score = 0;

    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", 12));

        gc.fillText("Lives ", 30, GameConstant.HEIGHT - 80);
        gc.fillText("Score ", GameConstant.WIDTH / 2.0 - 80, GameConstant.HEIGHT - 80);
        gc.fillText(String.valueOf(score), GameConstant.WIDTH / 2.0 - 80, GameConstant.HEIGHT - 50);
        gc.fillText("Level ", GameConstant.WIDTH - 80, 50);
        int level = 1;
        gc.fillText(String.valueOf(level), GameConstant.WIDTH - 70, 75);
        gc.fillText("Enemy ", GameConstant.WIDTH - 85, 150);

        int space = 20;
        for (int i = 0; i < enemyCount; i++) {
            gc.setFill(Color.RED);
            gc.fillOval(GameConstant.WIDTH - 70, 170 + i * space, 10, 10);
        }

        for (int i = 0; i < live; i++) {
            gc.setFill(Color.GREEN);
            gc.fillOval(10 + i * space, GameConstant.HEIGHT - 60, 10, 10);
        }
    }

	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int live) {
		this.live = live;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getCrownLive() {
		return crownLive;
	}
	
	public void setCrownLive(int crownLive) {
		this.crownLive = crownLive;
	}
}
