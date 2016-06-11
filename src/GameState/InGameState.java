package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Backgrounds.Background;
import Entity.*;
import Main.GamePanel;


public class InGameState extends GameState {
	
	private Background bg;
	//private ArrayList<Planet> planets;
	private int amoutOfPlanets = 50;
	private int counter = 0;
	private boolean foundPlanetCollision;
	private ArrayList<PlanetGrayzone> planetGrayzones = new ArrayList<PlanetGrayzone>(50);
	private ArrayList<PlayerPlanet> playerPlanets = new ArrayList<PlayerPlanet>(52);
	private ArrayList<EnemyPlanet> enemyPlanets = new ArrayList<EnemyPlanet>(52);
	private boolean isCollidingGrayzone;
	private boolean isCollidingPlayer;
	private boolean isCoolidingEnemy;
	
	public InGameState(GameStateManager gsm){
		this.gsm=gsm;
		init();
	}
	
	public void init(){
		bg = new Background("/Backgrounds/space.jpg", 1);	
	}
	
	

	public void update() {
		PlayerPlanet playerPlanet = new PlayerPlanet(10, 10, 90, 400);
		EnemyPlanet enemyPlanet = new EnemyPlanet(GamePanel.WIDTH - 100 , GamePanel.HEIGHT - 100,90,400);
		playerPlanets.add(playerPlanet);
		enemyPlanets.add(enemyPlanet);
		while(planetGrayzones.size() < amoutOfPlanets){
			PlanetGrayzone planetGrayzone = new PlanetGrayzone();
			
			foundPlanetCollision = false;
			
			System.out.println("planetGrayzone: " + planetGrayzones.size());
			System.out.println("playerPlanet: " + playerPlanets.size());
			
			for (int i = 0; i < planetGrayzones.size(); i++){
				PlanetGrayzone currentPlanet = (PlanetGrayzone) planetGrayzones.get(i);
				isCollidingGrayzone = planetGrayzones.get(i).checkPGTPGCollision(currentPlanet, planetGrayzone);
				isCollidingPlayer = playerPlanets.get(0).checkPGTPPCollision(planetGrayzone, playerPlanet);
				isCoolidingEnemy = enemyPlanets.get(0).checkPGTEPCollision(planetGrayzone, enemyPlanet);
				if(isCollidingGrayzone || isCollidingPlayer || isCoolidingEnemy){
					foundPlanetCollision = true;
					counter++;
					if(counter == 5){
						planetGrayzones.remove(planetGrayzones.size()-1);
					}
				}
			}
			
			if(!foundPlanetCollision){
				planetGrayzones.add(planetGrayzone);
				counter = 0; 
			}
		}	
	}

	public void draw(Graphics2D g2d) {
		// draw bg
		bg.draw(g2d);
		
		// draw planet
		for (int i = 0; i< planetGrayzones.size(); i++){
			planetGrayzones.get(i).draw(g2d);
		}
		
		// draw playerPlanet
		for (int i = 0; i< playerPlanets.size(); i++){
			playerPlanets.get(i).draw(g2d);
		}
		
		// draw enemyPlanet
		for (int i = 0; i< enemyPlanets.size(); i++){
			enemyPlanets.get(i).draw(g2d);
		}

	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}
}
