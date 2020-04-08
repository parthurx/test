package Projeto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
	Random r = new Random();
	Manipulador manipulador;
	public Player(int x, int y, ID id, Manipulador manipulador) {
		super(x, y, id);
		this.manipulador = manipulador;
		
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	@Override
	public void tick() {
	x += velX;
	y += velY;
		
	x = Game.clamp(x, 0, Game.WIDTH - 37 );
	y = Game.clamp(y, 0, Game.HEIGTH - 60 );
	manipulador.addObject(new Rastro(x, y, ID.Rastro, Color.white, 32, 32, 0.05f, manipulador));
	
	collision();
	}
	public void collision() {
		for(int i = 0; i < manipulador.object.size(); i++) {
			GameObject tempObject = manipulador.object.get(i);
			if(tempObject.getId() == ID.BasicEnemy ||tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					//codigo de colisão
					HUD.HEALTH -= 2;
				}
			}
		}
	}
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		
		g.fillRect(x, y, 32, 32);
	}
	
	
}
