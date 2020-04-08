package Projeto;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 2985996870857747019L;

	public static final int WIDTH = 640, HEIGTH = WIDTH / 12 * 9;

	private Thread thread;
	private boolean running = false;
	private Random r;
	private Manipulador manipulador;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	public enum STATE{
		Menu,
		Cadastrar,
		Login,
		Game,
		End
	};
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		
		manipulador = new Manipulador();
		hud = new HUD();
		menu = new Menu(this, manipulador, hud);
		this.addKeyListener(new KeyInput(manipulador));
		this.addMouseListener(menu);
		
		new Window(WIDTH, HEIGTH, "Criando um jogo", this);
		
		
		spawner = new Spawn(manipulador, hud);
		
		r= new Random();
		
		if(gameState == STATE.Game) {
			manipulador.addObject(new Player(WIDTH/2-32, HEIGTH/2-32, ID.Player, manipulador));
			
			manipulador.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGTH), ID.BasicEnemy, manipulador));
		}else {
			for(int i = 0; i < 20; i ++) {
				manipulador.addObject(new MenuParticulas(r.nextInt(WIDTH), r.nextInt(HEIGTH), ID.MenuParticulas, manipulador));
			}
		}
		
		
		
		
		
		
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus ();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	private void tick() {
		manipulador.tick();
		
		if(gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
			if(HUD.HEALTH <= 0) {
				HUD.HEALTH = 100;
				gameState = STATE.End;
				manipulador.clearEnemys();
				for(int i = 0; i < 20; i ++) {
					manipulador.addObject(new MenuParticulas(r.nextInt(WIDTH), r.nextInt(HEIGTH), ID.MenuParticulas, manipulador));
				}
			}
		}else if(gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
			
		}
	
	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0, WIDTH, HEIGTH);
		
		
		manipulador.render(g);
		if(gameState == STATE.Game) {
			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Cadastrar || gameState == STATE.End) {
			menu.render(g);
			
		}
		
		
		
		g.dispose();
		bs.show();
	}
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	public static void main(String args[]) {
		new Game();
	}
}
