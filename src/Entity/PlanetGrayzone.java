package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import Main.GamePanel;

public class PlanetGrayzone extends GameObject {
	
	public PlanetGrayzone() {
		planetSize = randomPlanetIntervalSize(30,90);
		planetScoreNumber = planetSize*3;
		init();
		xLoc = ((int) (Math.random()*GamePanel.WIDTH)) + planetSize / 2;
		yLoc = ((int) (Math.random()*GamePanel.HEIGHT)) + planetSize / 2;
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics2D g) {

		FontMetrics fm = g.getFontMetrics();
		float x = (((planetSize - fm.stringWidth(Integer.toString((int)planetScoreNumber))) / 2) + xLoc);
		float y = (planetSize - (planetSize / 2) + yLoc) + fm.getDescent();
		g.setColor(Color.GRAY);
		g.fillOval((int)xLoc, (int)yLoc, planetSize, planetSize);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.setColor(Color.BLACK);
		g.drawString(Integer.toString((int)planetScoreNumber), x, y);
		
	}

	public int randomPlanetIntervalSize(int min, int max) {
		int range = (max-min) + 1;
		return (int)(Math.random() * range) + min;
	} 
	
	public boolean collidesWithWindow() {
		int left = xLoc;
		int right = xLoc+planetSize;
		int up = yLoc;
		int down = yLoc+planetSize;
		
		if(left < 0 || right > GamePanel.WIDTH || up < 0 || down > GamePanel.HEIGHT) {
			return true;
		}
		else return false;
		
	}
	
	public float getX(){
		return this.xLoc;
	}
	
	public float getY(){
		return this.yLoc;
	}
	
	public int getPlanetDiameter() {
		return this.planetSize;
	}
	
	public int planetScoreNumber() {
		return (int)planetScoreNumber;
	}
	
}
