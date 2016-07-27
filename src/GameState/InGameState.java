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
	private int amountOfPlanets = 40;
	private int planetDistance = 1;
	private int mouseX;
	private int mouseY;
	private int rectStartX;
	private int rectStartY;

	// TODO - Read about how to make arrays off objects.
	private ArrayList<GameObject> planets = new ArrayList<GameObject>();
	// TODO - BAD PRACTICE! This boolean should not be public and static, but
	// private instead (Or even removed if better alternative is found)

	public InGameState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	public void init() {
		bg = new Background("/Backgrounds/space.jpg", 1);

		EnemyPlanet enemyPlanet = new EnemyPlanet(-30, -30, 90, 400);
		PlayerPlanet playerPlanet = new PlayerPlanet(GamePanel.WIDTH - 150, GamePanel.HEIGHT - 150, 90, 400);
		planets.add(playerPlanet);
		planets.add(enemyPlanet);
		spawnPlanets();
	}

	public void update() {
		// check keys
		handleInput();

		// update planets
		for (GameObject obj : planets) {
			obj.update();
			if (obj.getClass() == PlayerPlanet.class) {
				PlayerPlanet p = (PlayerPlanet) obj;
				if (p.getHighlighted()) {
					for (GameObject obj1 : planets) {
						// TODO - Fix the dragging while holding right button to send ship
						// TODO - should not be able to send ships to own planet
						if (MouseEvents.mouseHovered(obj1.getBounds())) {
							if (MouseEvents.isPressed(MouseEvents.RIGHTCLICK)) {
								p.spawnShips(obj1);
								p.setHighlighted(false);
							}
						}
					}
				}
				if(MouseEvents.isPressed(MouseEvents.LEFTCLICK)) {
					if (p.getBounds().intersects(RectToMouse(rectStartX, rectStartY))
							|| p.getBounds().contains(rectStartX, rectStartY)) {
						p.setHighlighted(true);
					} else {
						p.setHighlighted(false);
					}
					
				}
			}

		}
	}

	public void draw(Graphics2D g) {
		// set rendering to be beautiful :)
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// draw bg
		bg.draw(g);

		// draw planets
		for (GameObject obj : planets) {
			obj.draw(g);
		}

		if (MouseEvents.isPressed(MouseEvents.LEFTCLICK)) {
			g.draw(RectToMouse(rectStartX, rectStartY));
		}
	}

	// spawns planets and checks for collision.
	public void spawnPlanets() {

		while (planets.size() < amountOfPlanets) {

			PlanetGrayzone currentPlanet = new PlanetGrayzone();

			System.out.println("planetGrayzone: " + planets.size());

			if (!checkPlanetsCollision(currentPlanet) && !currentPlanet.collidesWithWindow()) {
				planets.add(currentPlanet);
			}
		}
	}

	public boolean checkPlanetsCollision(PlanetGrayzone currentPlanet) {
		double dx, dy, distance;
		float radiusSum;
		boolean isColliding;

		// checking for planet collision when spawning;
		for (GameObject obj : planets) {
			dx = currentPlanet.getX() - obj.getX();
			dy = currentPlanet.getY() - obj.getY();
			distance = dx * dx + dy * dy;
			radiusSum = currentPlanet.getPlanetDiameter() + obj.getPlanetDiameter();
			isColliding = distance < Math.pow(radiusSum + planetDistance, 2);
			if (isColliding) {
				return true;
			}
		}
		return false;
	}

	// Returns the rectangle from the clicked point to the mouse location
	private Rectangle RectToMouse(int x, int y) {
		mouseX = MouseEvents.mouseX;
		mouseY = MouseEvents.mouseY;
		// right-bottom
		if (mouseX > x && mouseY > y)
			return new Rectangle(x, y, mouseX - x, mouseY - y);
		// left-bottom
		else if (mouseX < x && mouseY > y)
			return new Rectangle(mouseX, y, x - mouseX, mouseY - y);
		// top right
		else if (mouseX > x && mouseY < y)
			return new Rectangle(x, mouseY, mouseX - x, y - mouseY);
		// top left
		else
			return new Rectangle(mouseX, mouseY, x - mouseX, y - mouseY);
	}

	@Override
	public void handleInput() {
		if (Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(true);
		}
		
		if (!MouseEvents.isPressed(MouseEvents.LEFTCLICK)) {
			rectStartX = MouseEvents.mouseX;
			rectStartY = MouseEvents.mouseY;
		}

	}
}
