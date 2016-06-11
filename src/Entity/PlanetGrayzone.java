package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import Main.GamePanel;

public class PlanetGrayzone extends GameObject {
	
	public PlanetGrayzone() {
		this.xLoc = (int) (Math.random()*GamePanel.WIDTH);
		this.yLoc = (int) (Math.random()*GamePanel.HEIGHT);
		this.planetSize = randomPlanetIntervalSize(30,90);
		this.planetScoreNumber = this.planetSize*5;
		init();
	}

	@Override
	public void init() {
		checkWindowCollision(xLoc, yLoc);
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics2D g2d) {

		FontMetrics fm = g2d.getFontMetrics();
		float x = (((planetSize - fm.stringWidth(Integer.toString(planetScoreNumber))) / 2) + xLoc);
		float y = (planetSize - (planetSize / 2) + yLoc) + fm.getDescent();

		g2d.setColor(Color.GRAY);
		g2d.fillOval((int)xLoc, (int)yLoc, planetSize, planetSize);
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g2d.setColor(Color.BLACK);
		g2d.drawString(Integer.toString(planetScoreNumber), x, y);
	}

	public int randomPlanetIntervalSize(int min, int max) {
		int range = (max-min) + 1;
		return (int)(Math.random() * range) + min;
	} 
	
	public float[] checkWindowCollision(float xLoc, float yLoc) {
		if(xLoc - (this.planetSize / 2)  < 0) {
			this.xLoc = 0;
		}else if(xLoc + (this.planetSize) > GamePanel.WIDTH){
			this.xLoc = GamePanel.WIDTH - (this.planetSize);
		}
		
		if(yLoc - (this.planetSize / 2) < 0) {
			this.yLoc = 0;
		}else if(yLoc + (this.planetSize) > GamePanel.HEIGHT){
			this.yLoc = GamePanel.HEIGHT - (this.planetSize);
		}
		return new float[] {xLoc, yLoc};
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
		return planetScoreNumber;
	}
	
	public boolean checkPGTPGCollision(PlanetGrayzone currentPlanet, PlanetGrayzone planetGrayzone) {
		double dx = currentPlanet.getX() - planetGrayzone.getX();
		double dy = currentPlanet.getY() - planetGrayzone.getY();
		double distance = dx * dx + dy * dy;
		float radiusSum = currentPlanet.getPlanetDiameter() + planetGrayzone.getPlanetDiameter();
		return distance < Math.pow(radiusSum, 2);
	}
}
