package Projeto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{
	private Manipulador manipulador;
	Random r = new Random();
	private int timer = 80;
	private int timer2 = 50;
	public EnemyBoss(int x, int y, ID id, Manipulador manipulador) {
		super(x, y, id);
		
		this.manipulador = manipulador;
		
		velX = 0;
		velY =2;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 96, 96);
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		if(timer <= 0) velY = 0;
		else timer--;
		
		if(timer <= 0) timer2--;
		if(timer2 <= 0) 
		{
			if(velX == 0) velX = 2;
			velX += 0.01f*Math.signum(velX);
			
			

			velX = Game.clamp(velX, -10, 10);
			int spawn = r.nextInt(10);
			if(spawn == 0)manipulador.addObject(new EnemyBossBullet((int)x+48, (int)y+48, ID.BasicEnemy, manipulador));
		}
		//if(y <= 0 || y >= Game.HEIGTH - 32) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 96) velX *= -1;
	   // manipulador.addObject(new Rastro(x, y, ID.Rastro, Color.blue, 96, 96, 0.008f, manipulador));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 96, 96);
		
	}

}