package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import Main.GamePanel;

public class Ship {
	 private double x;
	 private double y;
	 private double xEnd;
	 private double yEnd;
	 private double direction;
	 private int amountOfAttack;
	 private double angle;
	 private double speed;
	 private boolean shipIntersect = false;
	 private GameObject target;
	
	public Ship(PlayerPlanet p, GameObject o) {
		target = o;
		spawnShipsAroundPlayerPlanets(p);
		this.xEnd = o.getX() + o.getPlanetDiameter() / 2;
		this.yEnd = o.getY() + o.getPlanetDiameter() / 2;
		amountOfAttack = 1;
		speed = 2;
		p.setPlanetScoreNumber(p.getPlanetScoreNumber()-1);
		init();
	}
	
	public void init(){
		getDirectionToCoords(xEnd, yEnd);
	}
	
	public void update(){
		moveShip();
		if(!shipIntersect)
			getDirectionToCoords(xEnd, yEnd);
	}

	public void draw(Graphics2D g){
		Path2D.Double triangle = new Path2D.Double();
		triangle.moveTo(x + (15*Math.cos(getDirection())), y + (15*Math.sin(getDirection())));
		triangle.lineTo(x - (5*Math.sin(getDirection())), y + (5*Math.cos(getDirection())));
		triangle.lineTo(x + (5*Math.sin(getDirection())), y - (5*Math.cos(getDirection())));
		triangle.closePath();
		g.setColor(Color.GREEN);
		g.fill(triangle);
		g.setColor(Color.BLACK);
		g.draw(triangle);
	}
			
	public void spawnShipsAroundPlayerPlanets(PlayerPlanet p) {
		double m = Math.random();
		double centerX;
		double centerY;
		double radiusX;
		double radiusY;
		centerX = p.getX() + p.getPlanetDiameter() / 2;
		centerY = p.getY() + p.getPlanetDiameter() / 2;
		radiusX = (p.getPlanetDiameter() / 2);
		radiusY = (p.getPlanetDiameter() / 2);
		this.x = (radiusX + 10) * (Math.cos(Math.toRadians(Math.floor(m * 361)))) + centerX;
		this.y = (radiusY + 10) * (Math.sin(Math.toRadians(Math.floor(m * 361)))) + centerY;
	}
		
	public void getDirectionToCoords(double xEnd, double yEnd) {
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		float deltaX = (float) (this.xEnd - this.x);
		float deltaY = (float) (this.yEnd - this.y);
		direction = Math.atan2(deltaY, deltaX);
		System.out.println("1.: "+direction);
	}
	
	private void moveShip() {
		x+= speed * Math.cos(getDirection());
		y+= speed * Math.sin(getDirection());
		angle = 90;
	}
	
	public int getAmountOfAttack() {
		return amountOfAttack;
	}
	
	public double makeShipGoAroundPlanet(ArrayList<GameObject> planets, double angle) {
		double centerX;
		double centerY;
		double radiusX;
		double radiusY;
		double newDirection = 0;
		for(GameObject planet: planets) {
			centerX = planet.getX() + planet.getPlanetDiameter() / 2;
			centerY = planet.getY() + planet.getPlanetDiameter() / 2;
			radiusX = (planet.getPlanetDiameter() / 2);
			radiusY = (planet.getPlanetDiameter() / 2);
			newDirection = Math.atan2((radiusX + 10) * (Math.cos(Math.toRadians(Math.floor(angle*361)))) + centerX, (radiusY + 10) * (Math.sin(Math.toRadians(Math.floor(angle*361)))) + centerY);
		}
		return newDirection;
	}
	
	public void checkCollision(ArrayList<GameObject> planets) {
		for(GameObject obj: planets) {
			shipIntersect = false;
			if(this.intersects(obj)) {
				setDirection(makeShipGoAroundPlanet(planets,angle));
				shipIntersect = true;
				System.out.println("2.: "+getDirection());
			}
			shipIntersect = false;

		}
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double arg) {
		x = arg;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double arg) {
		y = arg;
	}
	
	public double getXEnd(){
		return xEnd;
	}
	
	public double getYEnd(){
		return yEnd;
	}
	
	public double getDirection(){
		return direction;
	}
	
	public void setDirection(double arg){
		direction = arg;
	}
	
	public boolean intersects(GameObject o) {
		return o.getBounds().contains(x + (15*Math.cos(direction)), y + (15*Math.sin(direction)));
	}
	
	public void setTarget(GameObject o) {target = o; }
	public GameObject getTarget() {return target; }
}
