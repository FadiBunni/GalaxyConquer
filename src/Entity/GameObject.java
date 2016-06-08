package Entity;

public abstract class GameObject implements IEntity {
	// planets
	protected int planetSize;
	protected int planetDiameter;
	protected int planetScoreNumber;
	protected int xLoc;
	protected int yLoc;
	protected String planetColor;
	
	
	public GameObject() {
	}
	
	public abstract int randomPlanetIntervalSize(int min, int max);	
}
