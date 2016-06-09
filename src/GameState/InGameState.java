package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Backgrounds.Background;
import Entity.*;


public class InGameState extends GameState {
	
	private Background bg;
	//private ArrayList<Planet> planets;
	private int amoutOfPlanets = 50;
	private boolean foundPlanetCollision;
	private ArrayList<Planet> planets = new ArrayList<Planet>();
	private PlayerPlanet playerPlanet;
	boolean isCollidingGrayzone;
	boolean isCollidingPlayer;
	boolean iscoolidingEnemy;
	
	public InGameState(GameStateManager gsm){
		this.gsm=gsm;
		init();
	}
	
	public void init(){
		bg = new Background("/Backgrounds/space.jpg", 1);
		
		while(planets.size() < amoutOfPlanets){
			playerPlanet = new PlayerPlanet(10, 10, 90, 400);
			Planet planet = new Planet(0, 0, 0, 0);
			foundPlanetCollision = false;

			for (int i = 0; i < planets.size(); i++){
				Planet currentPlanet = (Planet) planets.get(i);
				isCollidingGrayzone = planets.get(i).checkPlanetCollision(currentPlanet.getX(), currentPlanet.getY(), currentPlanet.getPlanetDiameter(), planet.getX(), planet.getY(), planet.getPlanetDiameter());
				isCollidingPlayer = planets.get(i).checkPlanetCollision(currentPlanet.getX(), currentPlanet.getY(), currentPlanet.getPlanetDiameter(), playerPlanet.getX(), playerPlanet.getY(), playerPlanet.getPlanetDiameter());
				if(isCollidingGrayzone || isCollidingPlayer){
					foundPlanetCollision = true;
				}
			}
			
			if(!foundPlanetCollision){
			      planets.add(planet);
			}
		}		
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
		
		// draw playerPlanet
		
		playerPlanet.draw(g2d);

	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}
}
