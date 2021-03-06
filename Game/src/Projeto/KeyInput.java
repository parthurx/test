package Projeto;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private Manipulador manipulador;
	private boolean[] keyDown = new boolean[4];

	public KeyInput(Manipulador manipulador) {
		this.manipulador = manipulador;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < manipulador.object.size(); i++) {
			GameObject tempObject = manipulador.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				// KeyEvents para jogador 1
				if (key == KeyEvent.VK_W) { tempObject.setVelY(-5); keyDown[0]=true; }
				if (key == KeyEvent.VK_S) { tempObject.setVelY(5);	keyDown[1]=true; }
				if (key == KeyEvent.VK_D) { tempObject.setVelX(5);	keyDown[2]=true; }
				if (key == KeyEvent.VK_A) { tempObject.setVelX(-5);	keyDown[3]=true; }
				
			}
			
		}
		if(key == KeyEvent.VK_ENTER) System.exit(1);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < manipulador.object.size(); i++) {
			GameObject tempObject = manipulador.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				// KeyEvents para jogador 1
				if (key == KeyEvent.VK_W) keyDown[0] = false;    //tempObject.setVelY(0);
				if (key == KeyEvent.VK_S) keyDown[1] = false;	//tempObject.setVelY(0);
				if (key == KeyEvent.VK_D) keyDown[2] = false;   //tempObject.setVelX(0);
				if (key == KeyEvent.VK_A) keyDown[3] = false;  //tempObject.setVelX(0);
						//movimentos verticais
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
						//movimentos horizontais
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
						
			}
	
		}
	}

}
