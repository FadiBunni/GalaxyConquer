package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class EnemyPlanet extends GameObject {
	
	public EnemyPlanet(int xLoc, int yLoc, int planetSize, double planetScoreNumber) {
		this.xLoc = xLoc  + planetSize / 2;
		this.yLoc = yLoc  + planetSize / 2;
		this.planetSize = planetSize;
		this.planetScoreNumber = planetScoreNumber;
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		planetScoreNumber += 0.05 ;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillOval((int)xLoc, (int)yLoc, planetSize, planetSize);
		setPlanetText(g);
	}
	
	// Sets the planetScoreNumber in the middle of the planet
	private void setPlanetText(Graphics2D g) {
		FontMetrics fm = g.getFontMetrics();
		float x = (((planetSize - fm.stringWidth(Integer.toString((int)planetScoreNumber))) / 2) + xLoc);
		float y = (planetSize - (planetSize / 2) + yLoc) + fm.getDescent();
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.setColor(Color.BLACK);
		g.drawString(Integer.toString((int)planetScoreNumber), x, y);
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

	@Override
	public int planetScoreNumber() {
		return (int)planetScoreNumber;
	}
}
