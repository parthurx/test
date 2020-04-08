package Projeto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticulas extends GameObject{
	private Manipulador manipulador;
	
	Random r = new Random();
	
	private Color col;
	
	
	
	public MenuParticulas(int x, int y, ID id, Manipulador manipulador) {
		super(x, y, id);
		
		this.manipulador = manipulador;
		
		velX = r.nextInt(7 - -7)+ - 7;
		velY = r.nextInt(7 - -7)+ - 7;
		if(velX == 0) velX = 1;
		if(velY == 0) velY = 1;
	
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
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
	    manipulador.addObject(new Rastro(x, y, ID.Rastro, col, 16, 16, 0.05f, manipulador));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect(x, y, 16, 16);
		
	}

}
