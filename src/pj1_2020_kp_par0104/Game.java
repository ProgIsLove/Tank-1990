package pj1_2020_kp_par0104;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, Renderable, Tickable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameConstant gameCon;
	private Handler handler;
	private Window window;
	private Map map;
	private Spawner spawner;
	private BufferedImageLoader loader;
	private SpriteSheet sheet;
	private Level level;
	private Hud hud;
	
	private Thread thread;
	private boolean running = false;
	
	public BufferedImage sprite_sheet;
	
	public Game() {
		loader = new BufferedImageLoader();
		sprite_sheet = loader.loadImage("/SpriteSheetPic.png");
		sheet = new SpriteSheet(sprite_sheet);
		level = new Level();
		handler = new Handler();
		gameCon = new GameConstant();
		hud = new Hud(gameCon);
		spawner = new Spawner(handler,gameCon, sheet, hud);
		map = new Map(handler, gameCon, spawner, sheet, level);
		
		this.addKeyListener(new KeyInput(handler, gameCon, sheet, hud));
		
		window = new Window(gameCon, "Tank 1990", this);
		window.createWindow();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	
	@Override
	public void tick() {
		handler.tick();
		map.tick();
		if(hud.getLive() <= 0 || hud.getCrownLive() <= 0) {
			handler.object.clear();
			System.out.println("Game Over! " + "High score: " + hud.getScore());
			restartGame();
		}
	}

	public void restartGame() {
		hud.setLive(5);
		hud.setScore(0);
		hud.setEnemyCount(0);
		hud.setCrownLive(1);
		map.setIsDraw(false);
		map.setBlockX(0);
		map.setBlockY(0);
	}
	
	@Override
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, gameCon.getWidth(),gameCon.getHeight());
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
}
