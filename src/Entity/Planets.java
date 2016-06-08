package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;

public class Planets extends GameObject {

	public Planets(int xLoc, int yLoc, int planetSize, int planetScoreNumber) {
		super(xLoc, yLoc, planetSize, planetScoreNumber);
		
		this.xLoc = (int) (Math.random()*GamePanel.WIDTH);
		this.yLoc = (int) (Math.random()*GamePanel.HEIGHT);
		this.planetSize = randomPlanetIntervalSize(1,50) + 15;
		this.planetScoreNumber = (int) planetSize*5;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.GRAY);
		g2d.fillOval(xLoc, yLoc, planetSize, planetSize);
	}

	@Override
	public int randomPlanetIntervalSize(int min, int max) {
		int range = (max-min) + 1;
		return (int)(Math.random() * range) + min;
	}
		
}
