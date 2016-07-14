package Entity;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;



public abstract class GameObject{
	
	//planets
	protected int planetSize;
	protected double planetScoreNumber;
	protected int xLoc;
	protected int yLoc;
	
	
	public Ellipse2D.Double getBounds() {
		return new Ellipse2D.Double(xLoc,yLoc, planetSize, planetSize);
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g2d);
	public abstract float getX();
	public abstract float getY();
	public abstract int getPlanetDiameter();
	public abstract int planetScoreNumber();
}
