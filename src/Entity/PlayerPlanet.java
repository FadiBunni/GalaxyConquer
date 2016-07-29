package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class PlayerPlanet extends GameObject {
	private ArrayList<Ship> ships;
	boolean highlighted;
	
	public PlayerPlanet(int xLoc, int yLoc, int planetSize, double planetScoreNumber) {
		this.xLoc = xLoc + planetSize / 2;
		this.yLoc = yLoc + planetSize / 2;
		this.planetSize = planetSize;
		this.planetScoreNumber = planetScoreNumber;
		init();
	}

	@Override
	public void init() {	
		ships = new ArrayList<Ship>();	
	}
	
	
	@Override
	public void update() {
		planetScoreNumber += 0.1 ;
	
		for (int i = 0; i < ships.size(); i++){
			Ship s = ships.get(i);
			s.update();
			if(s.intersects(s.getTarget())) {
				ships.remove(s);
				i--;
				s.getTarget().setPlanetScoreNumber(s.getTarget().getPlanetScoreNumber()-1);
				if(s.getTarget().getPlanetScoreNumber() <= 0) {
					// TODO - planetsScoreNumber decreases when the first ships has hit the target, fix this problem.
					s.getTarget().setPlanetScoreNumber(s.getTarget().getPlanetScoreNumber() + ships.size());
				}
			}
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillOval(xLoc,yLoc, planetSize, planetSize);
		setPlanetText(g);
		
		if(highlighted) {
			g.setColor(Color.WHITE);
			g.drawOval(xLoc, yLoc, planetSize, planetSize);
		}
		
		// draw ships
		for (int i = 0; i < ships.size(); i++){
			ships.get(i).draw(g);
		}
	}
	
	// Sets the number in the middle of the planet
	private void setPlanetText(Graphics2D g) {
		FontMetrics fm = g.getFontMetrics();
		float x = (((planetSize - fm.stringWidth(Integer.toString((int)planetScoreNumber))) / 2) + xLoc);
		float y = (planetSize - (planetSize / 2) + yLoc) + fm.getDescent();
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString(Integer.toString((int)planetScoreNumber), x, y);
	}
	
	public void checkShipCollision(ArrayList<GameObject> planets) {
		for(Ship s: ships) {
			s.checkCollision(planets);
		}
	}

	public void spawnShips(GameObject p) {
		for(int i = 0; i-planetScoreNumber / 2<planetScoreNumber / 2; i++){
			Ship ship = new Ship(this, p);
			ships.add(ship);
			if(planetScoreNumber <= 0) {
				planetScoreNumber = 0;
			}
			
		}
	}
	
	@Override
	public float getX() {
		return this.xLoc;
	}

	@Override
	public float getY() {
		return this.yLoc;
	}

	@Override
	public int getPlanetDiameter() {
		return this.planetSize;
	}
	
	public void setHighlighted(boolean b) {
		highlighted = b;
	}
	public boolean getHighlighted() {
		return highlighted;
	}
	
	@Override
	public int getPlanetScoreNumber() {
		return (int)planetScoreNumber;
	}

	@Override
	public void setPlanetScoreNumber(int planetScoreNumber) {
		this.planetScoreNumber = planetScoreNumber;
	}
}
