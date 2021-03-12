package pj1_2020_kp_par0104;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Hud {
	
	private GameConstant gameCon;
	private int enemyCount = 0;
	private int live = 5;
	private int crownLive = 1;
	private int score = 0;
	private int level = 1;
	private final int space = 20;
	
	public Hud(GameConstant gameCon) {
		this.gameCon = gameCon;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 3, 12));
		g.drawString("Lives ", 30, gameCon.getHeight()-80);
		g.drawString("Score ", gameCon.getWidth()/2-80, gameCon.getHeight()-80);
		g.drawString("" + score, gameCon.getWidth()/2-80, gameCon.getHeight()-50);
		g.drawString("Level ", gameCon.getWidth()-80, 50);
		g.drawString("" + level, gameCon.getWidth()-70, 75);
		g.drawString("Enemy ", gameCon.getWidth()-85, 150);
		
		for(int i = 0; i < enemyCount; i++) {
			g.setColor(Color.RED);
			g.fillOval(gameCon.getWidth()-70, 170 + i * space, 10, 10);
		}
		
		for(int i = 0; i < live; i++) {
			g.setColor(Color.green);
			g.fillOval(10 + i*space, gameCon.getHeight()-60, 10, 10);
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
