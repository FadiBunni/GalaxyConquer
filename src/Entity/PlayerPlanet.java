package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class PlayerPlanet extends GameObject {
	boolean highlighted;
	
	public PlayerPlanet(int xLoc, int yLoc, int planetSize, double planetScoreNumber) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
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
		g.setColor(Color.GREEN);
		g.fillOval(xLoc,yLoc, planetSize, planetSize);
		setPlanetText(g);
		
		if(highlighted) {
			g.setColor(Color.WHITE);
			g.drawOval(xLoc, yLoc, planetSize, planetSize);
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
	@Override
	public int planetScoreNumber() {
		return (int)planetScoreNumber;
	}
	
}
