package Projeto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
	private Manipulador manipulador;
	private GameObject player;
	public SmartEnemy(int x, int y, ID id, Manipulador manipulador) {
		super(x, y, id);
		
		this.manipulador = manipulador;
		for(int i = 0; i < manipulador.object.size(); i++) {
			if(manipulador.object.get(i).getId() == ID.Player) player = manipulador.object.get(i);
			
		}
			
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX =  x - player.getX() -8;
		float diffY = y -player.getY() -8;
		float distancia = (float) Math.sqrt((x-player.getX())*(x-player.getX())+(y-player.getY())*(y-player.getY()));
		velX = (int) ((-1.0/distancia) * diffX*3);
		 velY = (int) ((-1.0/distancia) * diffY*3);
		
		if(y <= 0 || y >= Game.HEIGTH - 32) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
	    manipulador.addObject(new Rastro(x, y, ID.Rastro, Color.green, 16, 16, 0.02f, manipulador));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 16, 16);
		
	}

}