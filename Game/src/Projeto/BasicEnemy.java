package Projeto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
	private Manipulador manipulador;
	public BasicEnemy(int x, int y, ID id, Manipulador manipulador) {
		super(x, y, id);
		
		this.manipulador = manipulador;
		
		velX = 5;
		velY =5;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGTH - 32) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
	    manipulador.addObject(new Rastro(x, y, ID.Rastro, Color.red, 16, 16, 0.02f, manipulador));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
		
	}

}
