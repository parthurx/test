package Projeto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject{
	private Manipulador manipulador;
	Random r = new Random();
	public EnemyBossBullet(int x, int y, ID id, Manipulador manipulador) {
		super(x, y, id);
		
		this.manipulador = manipulador;
		velX = r.nextInt(5 - -5)+ - 5;
		
		velY =5;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		//if(y <= 0 || y >= Game.HEIGTH - 32) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		if(y > Game.HEIGTH) manipulador.removeObject(this);
	    manipulador.addObject(new Rastro(x, y, ID.Rastro, Color.white, 16, 16, 0.02f, manipulador));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 16, 16);
		
	}

}