package Projeto;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Rastro extends GameObject{
	
	private float life;
	private float alpha = 1;
	private Manipulador manipulador;
	private Color color;
	private int width, heigth;
	
	public Rastro(int x, int y, ID id, Color color, int width, int heigth, float life, Manipulador manipulador) {
		super(x, y, id);
		this.manipulador = manipulador;
		this.color = color;
		this.width = width;
		this.heigth = heigth;
		this.life = life;
	}

	@Override
	public void tick() {
		if(alpha > life) {
			alpha -= life - 0.0001f;
		}else manipulador.removeObject(this);
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect(x, y, width, heigth);
		
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha ) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
	@Override
	public Rectangle getBounds() {
	
		return null;
	}

}
