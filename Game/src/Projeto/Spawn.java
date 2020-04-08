package Projeto;

import java.util.Random;

public class Spawn {
	private Manipulador manipulador;
	private HUD hud;
	private Random r = new Random();
	private int scoreKeep = 0;

	public Spawn(Manipulador manipulador, HUD hud) {
		this.manipulador = manipulador;
		this.hud = hud;
	}

	public void tick() {
		scoreKeep++;
		if (scoreKeep >= 100) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);

			if (hud.getLevel() == 2) {
				manipulador.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGTH), ID.BasicEnemy, manipulador));
			}else if(hud.getLevel() == 3){
				manipulador.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGTH), ID.BasicEnemy, manipulador));
			}else if(hud.getLevel() == 4){
				manipulador.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGTH), ID.FastEnemy, manipulador));
			}else if(hud.getLevel() == 5) {
				manipulador.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGTH), ID.SmartEnemy, manipulador));
			}else if(hud.getLevel() == 6) {
				manipulador.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGTH), ID.FastEnemy, manipulador));
			}else if(hud.getLevel() == 7) {
				manipulador.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGTH), ID.FastEnemy, manipulador));
			}else if(hud.getLevel() == 10) {
				manipulador.clearEnemys();
				manipulador.addObject(new EnemyBoss(Game.WIDTH /2 - 48, -120, ID.EnemyBoss, manipulador));
			}
			}
	}
}
