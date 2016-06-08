package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Backgrounds.Background;
import Entity.*;


public class InGameState extends GameState {
	
	private Background bg;
	private ArrayList<Planets> planets =  new ArrayList<Planets>();
	
	public InGameState(GameStateManager gsm){
		this.gsm=gsm;
		init();
	}
	
	public void init(){
		bg = new Background("/Backgrounds/space.jpg", 1);
		
		
		//planet[0] = new Planets(0, 0, 0, 0);
		
		for(int i = 0; i< 10; i++)
			planets.add(new Planets(0, 0, 0, 0));

	}
	

	public void update() {}

	public void draw(Graphics2D g2d) {
		// draw bg
		bg.draw(g2d);
		
		// draw planet
		for (int i = 0; i< planets.size(); i++){
			planets.get(i).draw(g2d);
		}
	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}
}
