package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Backgrounds.Background;
import Entity.*;


public class InGameState extends GameState {
	
	private Background bg;
	private ArrayList<Planet> planets;
	private int amoutOfPlanets = 20;
	
	public InGameState(GameStateManager gsm){
		this.gsm=gsm;
		init();
	}
	
	public void init(){
		bg = new Background("/Backgrounds/space.jpg", 1);
		
		planets = new ArrayList<Planet>();
		for(int i = 1; i <= amoutOfPlanets; i++){
			planets.add(new Planet(0, 0, 0, 0));
		}

	}
	

	public void update() {
		for (int i = 0; i< planets.size(); i++){
			planets.get(i).update();
		}
	}

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
