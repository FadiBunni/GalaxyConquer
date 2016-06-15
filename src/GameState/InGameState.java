package GameState;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import Handler.Keys;

import Backgrounds.Background;
import Entity.*;
import Main.GamePanel;


public class InGameState extends GameState {
	
	private Background bg;
	//private ArrayList<Planet> planets;
	private int amountOfPlanets = 40;
	private ArrayList<PlanetGrayzone> planetGrayzones = new ArrayList<PlanetGrayzone>(50);
	private ArrayList<PlayerPlanet> playerPlanets = new ArrayList<PlayerPlanet>(52);
	private ArrayList<EnemyPlanet> enemyPlanets = new ArrayList<EnemyPlanet>(52);

	
	public InGameState(GameStateManager gsm){
		super(gsm);
		init();
	}
	
	public void init(){
		bg = new Background("/Backgrounds/space.jpg", 1);	
		
		PlayerPlanet playerPlanet = new PlayerPlanet(10, 10, 90, 400);
		EnemyPlanet enemyPlanet = new EnemyPlanet(GamePanel.WIDTH - 100 , GamePanel.HEIGHT - 100,90,400);
		playerPlanets.add(playerPlanet);
		enemyPlanets.add(enemyPlanet);
		
	}
	
	public void update() {
		
		// check keys
		handleInput();
		
		
		spawnPlanets();
			
		// update playerPlanet
		for (int i = 0; i< playerPlanets.size(); i++){
			playerPlanets.get(i).update();
		}
		
		// update enemyPlanet
		for (int i = 0; i< enemyPlanets.size(); i++){
			enemyPlanets.get(i).update();
		}
	}
		
	public void spawnPlanets() {
		int counter = 0;
		boolean foundPlanetCollision;
		
		while(planetGrayzones.size() < amountOfPlanets){
			
			PlanetGrayzone  currentPlanet = new PlanetGrayzone();
			foundPlanetCollision = false;
			
			System.out.println("planetGrayzone: " + planetGrayzones.size());
			
			for (int i = 0; i < planetGrayzones.size(); i++){
				
				if(checkPlanetsCollision(currentPlanet)){
					
					foundPlanetCollision = true;
					counter++;
					
					if(counter == 5) {
						planetGrayzones.remove(planetGrayzones.size() - 1);
					}
				}
			}
			
			if(!foundPlanetCollision){
				planetGrayzones.add(currentPlanet);
				counter=0;
			}
		}	
	}

	public boolean checkPlanetsCollision(PlanetGrayzone currentPlanet) {
		double dx, dy, distance;
		float radiusSum;
		boolean isColliding;
		
		//checking for playerPlanet collision;
		PlayerPlanet playerPlanet = playerPlanets.get(0);
		dx = currentPlanet.getX() - playerPlanet.getX();
		dy = currentPlanet.getY() - playerPlanet.getY();
		distance = dx * dx + dy * dy;
		radiusSum = currentPlanet.getPlanetDiameter() + playerPlanet.getPlanetDiameter();
		isColliding = distance < Math.pow(radiusSum, 2);
		if(isColliding) {
			return true;
		}
		
		//checking for enemyPlanet collision;
		EnemyPlanet enemyPlanet = enemyPlanets.get(0);
		dx = currentPlanet.getX() - enemyPlanet.getX();
		dy = currentPlanet.getY() - enemyPlanet.getY();
		distance = dx * dx + dy * dy;
		radiusSum = currentPlanet.getPlanetDiameter() + enemyPlanet.getPlanetDiameter();
		isColliding = distance < Math.pow(radiusSum, 2);
		if(isColliding) {
			return true;
		}
		
		//checking for all planetGrayzone collision;
		for(int i = 0; i < planetGrayzones.size(); i++) {
			PlanetGrayzone planetGrayzone = planetGrayzones.get(i); 
			dx = currentPlanet.getX() - planetGrayzone.getX();
			dy = currentPlanet.getY() - planetGrayzone.getY();
			distance = dx * dx + dy * dy;
			radiusSum = currentPlanet.getPlanetDiameter() + planetGrayzone.getPlanetDiameter();
			isColliding = distance < Math.pow(radiusSum, 2);
			if(isColliding) {
				return true;
			}
		}
		return false;
	}
		
	public void draw(Graphics2D g2d) {
		// set rendering to be beautiful :)
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// draw bg
		bg.draw(g2d);
		
		// draw planetGrayzone
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

	@Override
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) gsm.setPaused(true);
		
	}
}
