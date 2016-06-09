package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import Main.GamePanel;

public class Planet extends GameObject {
	
	public Planet(int xLoc, int yLoc, int planetSize, int planetScoreNumber) {
		super(xLoc, yLoc, planetSize, planetScoreNumber);
		this.xLoc = (int) (Math.random()*GamePanel.WIDTH);
		this.yLoc = (int) (Math.random()*GamePanel.HEIGHT);
		this.planetSize = randomPlanetIntervalSize(40,90);
		this.planetScoreNumber = this.planetSize*5;
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		checkWindowCollision(xLoc, yLoc);
	}

	@Override
	public void draw(Graphics2D g2d) {

		FontMetrics fm = g2d.getFontMetrics();
		int x = (((planetSize - fm.stringWidth(Integer.toString(planetScoreNumber))) / 2) + xLoc);
		int y = (planetSize - (planetSize / 2) + yLoc) + fm.getDescent();

		g2d.setColor(Color.GRAY);
		g2d.fillOval(xLoc, yLoc, planetSize, planetSize);
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g2d.setColor(Color.BLACK);
		g2d.drawString(Integer.toString(planetScoreNumber), x, y);
	}

	@Override
	public int randomPlanetIntervalSize(int min, int max) {
		int range = (max-min) + 1;
		return (int)(Math.random() * range) + min;
	} 
	
	public int[] checkWindowCollision(int xLoc, int yLoc) {
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
		return new int[] {xLoc, yLoc};
	}

}
