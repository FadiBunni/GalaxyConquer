package Entity;

import java.awt.Graphics2D;

import Main.GamePanel;

public abstract class GameObject{
	// planets
	protected int planetSize;
	protected int planetDiameter;
	protected int planetScoreNumber;
	protected int xLoc;
	protected int yLoc;
	protected String planetColor;
	
	public GameObject(int xLoc, int yLoc, int planetSize, int planetScoreNumber) {
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g2d);
	
	public abstract int randomPlanetIntervalSize(int min, int max);	
	public abstract int[] checkWindowCollision(int xLoc, int yLoc);
}
