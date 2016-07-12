package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class EnemyPlanet extends GameObject {
	
	public EnemyPlanet(int xLoc, int yLoc, int planetSize, int planetScoreNumber) {
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
	public void draw(Graphics2D g2d) {
		
		FontMetrics fm = g2d.getFontMetrics();
		float x = (((planetSize - fm.stringWidth(Integer.toString((int)planetScoreNumber))) / 2) + xLoc);
		float y = (planetSize - (planetSize / 2) + yLoc) + fm.getDescent();

		g2d.setColor(Color.RED);
		g2d.fillOval((int)xLoc, (int)yLoc, planetSize, planetSize);
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g2d.setColor(Color.BLACK);
		g2d.drawString(Integer.toString((int)planetScoreNumber), x, y);
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
