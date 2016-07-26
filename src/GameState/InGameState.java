package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;

import Handler.Keys;
import Handler.MouseEvents;
import Backgrounds.Background;
import Entity.*;
import Main.GamePanel;


public class InGameState extends GameState {

	private Background bg;
	private int amountOfPlanets = 30;
	private int planetDistance = 15;
	private int mouseX;
	private int mouseY;
	private boolean hasPressedOnce;
	private int rectStartX;
	private int rectStartY;
	
	// TODO - Read about how to make arrays off objects.
	private static ArrayList<PlanetGrayzone> planetGrayzones = new ArrayList<PlanetGrayzone>();
	private static ArrayList<PlayerPlanet> playerPlanets = new ArrayList<PlayerPlanet>();
	private static ArrayList<EnemyPlanet> enemyPlanets = new ArrayList<EnemyPlanet>();
	// TODO - BAD PRACTICE! This boolean should not be public and static, but private instead (Or even removed if better alternative is found)
	private static boolean hasPressed;

	public InGameState(GameStateManager gsm){
		super(gsm);
		init();
	}

	public void init(){
		bg = new Background("/Backgrounds/space.jpg", 1);	
		
		EnemyPlanet enemyPlanet = new EnemyPlanet(-30, -30 , 90, 400);
		PlayerPlanet playerPlanet = new PlayerPlanet(GamePanel.WIDTH - 150 , GamePanel.HEIGHT - 150, 90, 400);
		playerPlanets.add(playerPlanet);
		enemyPlanets.add(enemyPlanet);
		spawnPlanets();
	}

	public void update() {
		// check keys
		handleInput();

		beginDragged();

		// update playerPlanet
		for (int i = 0; i< playerPlanets.size(); i++){
			PlayerPlanet p = playerPlanets.get(i);
			p.update();
			if(p.getHighlighted()) {
				for(int j = 0; j < planetGrayzones.size(); j++) {
					if(MouseEvents.mouseHovered(planetGrayzones.get(j).getBounds()) && !getHasPressed()) {
						if(MouseEvents.isPressed(MouseEvents.RIGHTCLICK)) {
							p.spawnShips(planetGrayzones.get(j));
							setHasPressed(true);
						}
					}
				}
			}
			
			if(hasPressedOnce) {
				if(playerPlanets.get(i).getBounds().intersects(RectToMouse(rectStartX, rectStartY)) ||
						playerPlanets.get(i).getBounds().contains(rectStartX, rectStartY)) {
					playerPlanets.get(i).setHighlighted(true);
				}
				else {
					playerPlanets.get(i).setHighlighted(false);
				}

				if(!MouseEvents.isPressed(MouseEvents.LEFTCLICK)) {
					hasPressedOnce = false;
				}
			}
		}
		
		// update enemyPlanet
		for (int i = 0; i< enemyPlanets.size(); i++){
			enemyPlanets.get(i).update();
		}
	}

	public void draw(Graphics2D g) {
		// set rendering to be beautiful :)
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// draw bg
		bg.draw(g);
		
		// draw planetGrayzone
		for (int i = 0; i< planetGrayzones.size(); i++){
			planetGrayzones.get(i).draw(g);
		}

		// draw playerPlanet
		for (int i = 0; i< playerPlanets.size(); i++){
			playerPlanets.get(i).draw(g);
		}

		// draw enemyPlanet
		for (int i = 0; i< enemyPlanets.size(); i++){
			enemyPlanets.get(i).draw(g);
		}
		
		if(hasPressedOnce){
			g.draw(RectToMouse(rectStartX, rectStartY));
		}
	}
			
	// spawns planets and checks for collision.
	public void spawnPlanets() {

		while(planetGrayzones.size() < amountOfPlanets){

			PlanetGrayzone  currentPlanet = new PlanetGrayzone();

			System.out.println("planetGrayzone: " + planetGrayzones.size());

			if(!checkPlanetsCollision(currentPlanet) && !currentPlanet.collidesWithWindow()){
				planetGrayzones.add(currentPlanet);	
			}
		}	
	}
		
	public boolean checkPlanetsCollision(PlanetGrayzone currentPlanet) {
		double dx, dy, distance;
		float radiusSum;
		boolean isColliding;
		
		//checking for playerPlanet collision;
		PlayerPlanet playerPlanet = playerPlanets.get(0);
		dx = currentPlanet.getX() - playerPlanet.getX() ;
		dy = currentPlanet.getY() - playerPlanet.getY() ;
		distance = dx * dx + dy * dy;
		radiusSum = currentPlanet.getPlanetDiameter() + playerPlanet.getPlanetDiameter();
		isColliding = distance < Math.pow(radiusSum + planetDistance, 2);
		if(isColliding) {
			return true;
		}

		//checking for enemyPlanet collision;
		EnemyPlanet enemyPlanet = enemyPlanets.get(0);
		dx = currentPlanet.getX() - enemyPlanet.getX();
		dy = currentPlanet.getY() - enemyPlanet.getY();
		distance = dx * dx + dy * dy;
		radiusSum = currentPlanet.getPlanetDiameter() + enemyPlanet.getPlanetDiameter();
		isColliding = distance < Math.pow(radiusSum + planetDistance, 2);
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
			isColliding = distance < Math.pow(radiusSum + planetDistance, 2);
			if(isColliding) {
				return true;
			}
		}
		return false;
	}
	
	// Starts the mouse dragging procedure (Only runs once per click)
	public void beginDragged() {
		if(MouseEvents.isPressed(MouseEvents.LEFTCLICK) && !hasPressedOnce) {
			hasPressedOnce = true;
			rectStartX = MouseEvents.mouseX;
			rectStartY = MouseEvents.mouseY;
		} 
	}
	
	// Returns the rectangle from the clicked point to the mouse location
	private Rectangle RectToMouse(int x, int y) {
		mouseX = MouseEvents.mouseX;
		mouseY = MouseEvents.mouseY;
		// right-bottom
		if(mouseX > x && mouseY > y) return new Rectangle(x, y, mouseX-x, mouseY-y);
		// left-bottom
		else if(mouseX < x && mouseY > y) return new Rectangle(mouseX, y,x-mouseX, mouseY-y);
		// top right
		else if(mouseX > x && mouseY < y) return new Rectangle(x, mouseY, mouseX-x, y-mouseY);
		//top left
		else return new Rectangle(mouseX, mouseY, x-mouseX, y-mouseY);
	}

	@Override
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) gsm.setPaused(true);

	}

	public static void setHasPressed(boolean b) {
		hasPressed = b;
	}
	
	public static boolean getHasPressed(){
		return hasPressed;
	}
	
	public static ArrayList<PlanetGrayzone> getGrayzonePlanets() {
		return planetGrayzones;
	}
	
	public static ArrayList<PlayerPlanet> getPlayerPlanets() {
		return playerPlanets;
	}
	
	public static ArrayList<EnemyPlanet> getEnemyPlanets() {
		return enemyPlanets;
	}
}
