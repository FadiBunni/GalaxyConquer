package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Ship {
	 private double x;
	 private double y;
	 private double xEnd;
	 private double yEnd;
	 private double direction;
	 private int amountOfAttack;
	 private double speed;
	 private GameObject target;
	
	public Ship(PlayerPlanet p, GameObject o) {
		target = o;
		this.x = p.getX();
		this.y = p.getY();
		this.xEnd = o.getX() + o.getPlanetDiameter()/2;
		this.yEnd = o.getY() + o.getPlanetDiameter()/2;
		amountOfAttack = 10;
		speed = 2;
	}
	
	public void init(){
		
	}
	
	public void update(){
		getDirectionToCoords(xEnd, yEnd);
		moveShip();
	}

	public void draw(Graphics2D g){
				
		Path2D.Double triangle = new Path2D.Double();
		triangle.moveTo(x + (15*Math.cos(direction)), y + (15*Math.sin(direction)));
		triangle.lineTo(x - (5*Math.sin(direction)), y + (5*Math.cos(direction)));
		triangle.lineTo(x + (5*Math.sin(direction)), y - (5*Math.cos(direction)));
		triangle.closePath();
		g.setColor(Color.GREEN);
		g.fill(triangle);
		g.setColor(Color.BLACK);
		g.draw(triangle);
		
		
	}
	
	public int getAmountOfAttack() {
		return amountOfAttack;
	}
	
	private void getDirectionToCoords(double xEnd, double yEnd) {
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		float deltaX = (float) (this.xEnd - this.x);
		float deltaY = (float) (this.yEnd - this.y);
		direction = Math.atan2(deltaY, deltaX);
	}
	
	private void moveShip() {
		x+= speed * Math.cos(direction);
		y+= speed * Math.sin(direction);
	}
	
	public double getx() {
		return x;
	}
	
	public double gety() {
		return y;
	}
	
	public boolean intersects(GameObject o) {
		return o.getBounds().contains(x + (15*Math.cos(direction)), y + (15*Math.sin(direction)));
	}
	
	public void setTarget(GameObject o) {target = o; }
	public GameObject getTarget() {return target; }
}
