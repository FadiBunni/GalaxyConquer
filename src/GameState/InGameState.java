package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Backgrounds.Background;
import Entity.*;


public class InGameState extends GameState {
	
	private Background bg;
	//private ArrayList<Planet> planets;
	private int amoutOfPlanets = 20;
	private boolean foundPlanetCollision;
	private ArrayList<Planet> planets = new ArrayList<Planet>();
	boolean isColliding;
	
	public InGameState(GameStateManager gsm){
		this.gsm=gsm;
		init();
	}
	
	public void init(){
		bg = new Background("/Backgrounds/space.jpg", 1);
		

			while(planets.size() < amoutOfPlanets){
				Planet planet = new Planet(0, 0, 0, 0);
				foundPlanetCollision = false;
	
				for (int i = 0; i < planets.size(); i++){
					Planet currentPlanet = (Planet) planets.get(i);
					isColliding = planets.get(i).checkPlanetCollision(currentPlanet.getX(), currentPlanet.getY(), currentPlanet.getPlanetDiameter(), planet.getX(), planet.getY(), planet.getPlanetDiameter());
					if(isColliding){
						foundPlanetCollision = true;
					}
				}
				
				if(!foundPlanetCollision){
				      planets.add(planet);
				}
			}
		
		
		
		
//		planets = new ArrayList<Planet>();
//		for(int i = 1; i <= amoutOfPlanets; i++){
//			planets.add(new Planet(0, 0, 0, 0));
//		}
		
		for (int i = 0; i< planets.size(); i++){
			planets.get(i).init();
		}

	}
	

	public void update() {
		
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
