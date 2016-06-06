package GameState;

import java.awt.Graphics2D;

import Backgrounds.Background;


public class InGameState extends GameState {
	
	private Background bg;
	
	public InGameState(GameStateManager gsm){
		this.gsm=gsm;
		init();
	}
	
	public void init(){
		bg = new Background("/Backgrounds/space.jpg", 1);
	}

	public void update() {}

	public void draw(Graphics2D g) {
		bg.draw(g);
	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}
}
