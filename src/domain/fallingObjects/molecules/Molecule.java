 package domain.fallingObjects.molecules;

import java.util.Random;

import domain.fallingObjects.IFallingObject;
import domain.fallingObjects.fallingPatterns.FallingPatternFactory;
import domain.fallingObjects.fallingPatterns.IFallingPattern;
import domain.game.KUVidGame;
import domain.game.Location;

public abstract class Molecule implements IFallingObject {
	
	public String type;
	public String imageAddress;
	public Location location;
	private int width;
	private int height;
	private double speed;
	public int rotationAngle;
	public boolean isStationary;
	private IFallingPattern fp;
	
	KUVidGame kuvidGame= KUVidGame.getInstance();
	Random rand = new Random();

	
	public Molecule(String type, int unitLength) {
		this.type=type;
		
		width = unitLength/4;
		height = unitLength/4;
		location = new Location((int) (rand.nextDouble()*kuvidGame.getGameSettings().width), 0); //x loc is random
		speed = unitLength*kuvidGame.getGameSettings().getClockTime()/1000;

		fp = FallingPatternFactory.getInstance().getFallingPattern(type);
		fp.setStartLocation(location.xLoc);
		
		rotationAngle=0;
		isStationary=true;
	}

	@Override
	public void fall() {
		int angle = fp.getDirection(getLocation().xLoc, getLocation().yLoc);
		setLocation(getLocation().xLoc+speed*Math.sin(Math.toRadians(angle)), getLocation().yLoc+speed*Math.cos(Math.toRadians(angle)));
	}
	
	public void rotate() {
		
	}
	
	
	public int getAngle() {
		return rotationAngle;
	}

	public String getImageAddress() {
		return imageAddress;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(double xLoc, double yLoc) {
		location.updateLocation(xLoc, yLoc);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
