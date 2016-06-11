package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import Main.GamePanel;

public class EnemyPlanet extends GameObject {
	
	public EnemyPlanet(float xLoc, float yLoc, int planetSize, int planetScoreNumber) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.planetSize = planetSize;
		this.planetScoreNumber = planetScoreNumber;
		//init();
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

		g2d.setColor(Color.RED);
		g2d.fillOval((int)xLoc, (int)yLoc, planetSize, planetSize);
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g2d.setColor(Color.BLACK);
		g2d.drawString(Integer.toString(planetScoreNumber), x, y);
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
		return planetScoreNumber;
	}

	@Override
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
	
	public boolean checkPGTEPCollision(PlanetGrayzone planetGrayzone, EnemyPlanet enemyPlanet) {
		double dx = planetGrayzone.getX() - enemyPlanet.getX();
		double dy = planetGrayzone.getY() - enemyPlanet.getY();
		double distance = dx * dx + dy * dy;
		float radiusSum = planetGrayzone.getPlanetDiameter() + enemyPlanet.getPlanetDiameter();
		return distance < Math.pow(radiusSum, 2);
	}


}
