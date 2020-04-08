package Projeto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JTextField;

import Projeto.Game.STATE;
 



public class Menu extends MouseAdapter{
	
	JTextField jt = new JTextField();
	
	private Game game;
	private HUD hud;
	private Manipulador manipulador;
	private Random r = new Random();
	public Menu(Game game, Manipulador manipulador, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.manipulador = manipulador;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			//Botão de jogar.
			if(mouseOver (mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				manipulador.addObject(new Player(Game.WIDTH/2-32, Game.HEIGTH/2-32, ID.Player, manipulador));
				manipulador.clearEnemys();
				manipulador.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGTH), ID.BasicEnemy, manipulador));
				
			}
			//botão cadastrar
			if(mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Cadastrar;
			}
			
			
			//Botão de sair.
			if(mouseOver (mx, my,210, 350, 200, 64)) {
				System.exit(1);
			}		
		}
		
	
		//Botão voltar de cadastro.
				if(game.gameState == STATE.Cadastrar) {
				if(mouseOver(mx, my, 210, 350, 200, 64)) {
					game.gameState = STATE.Menu;
					return;
					}
				}
				if(game.gameState == STATE.End) {
					if(mouseOver(mx, my, 210, 350, 200, 64)) {
						game.gameState = STATE.Game;
						hud.setLevel(1);
						hud.setScore(0);
						manipulador.addObject(new Player(Game.WIDTH/2-32, Game.HEIGTH/2-32, ID.Player, manipulador));
						manipulador.clearEnemys();
						manipulador.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGTH), ID.BasicEnemy, manipulador));
						}
					}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int heigth) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + heigth) {
					return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Menu", 240, 70);
			
			g.setFont(fnt2);
			g.drawString("Jogar", 270, 190);		
			g.drawRect(210, 150, 200, 64);
			
		
			g.drawRect(210, 250, 200, 64);
			g.drawString("Cadastrar", 240, 290);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Sair", 270, 390);
		}else if (game.gameState == STATE.Cadastrar) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 50);
			
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Tela de Cadastro", 110, 70);
			
			g.setColor(Color.white);	
			g.drawRect(320, 150, 200, 64);
			g.setColor(Color.white);	
			g.drawRect(320, 240, 200, 64);
			g.setFont(fnt);
			g.drawString("Usuário: ",110,200);
			g.setFont(fnt2);
			g.drawString("Senha: ",110,280);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Voltar", 240, 400);
		}else if (game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("GAME OVER", 170, 70);
			
			g.setFont(fnt3);
			g.drawString("Você perdeu com a pontuação de: "+hud.getScore(), 155, 200);
			
			g.setFont(fnt2);
			g.drawString("Tente novamente", 200, 390);

		}
		
		
	}

}
