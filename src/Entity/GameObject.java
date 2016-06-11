package Entity;

import java.awt.Graphics2D;



public abstract class GameObject{
	// planets
	protected int planetSize;
	protected int planetScoreNumber;
	protected float xLoc;
	protected float yLoc;
	
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g2d);
	public abstract float getX();
	public abstract float getY();
	public abstract int getPlanetDiameter();
	public abstract int planetScoreNumber();
	public abstract float[] checkWindowCollision(float xLoc, float yLoc);
}
