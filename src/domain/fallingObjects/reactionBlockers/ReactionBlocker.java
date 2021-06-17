package domain.fallingObjects.reactionBlockers;

import java.util.Random;

import domain.fallingObjects.IFallingObject;
import domain.fallingObjects.fallingPatterns.FallingPatternFactory;
import domain.fallingObjects.fallingPatterns.IFallingPattern;
import domain.game.KUVidGame;
import domain.game.Location;

public abstract class ReactionBlocker implements IFallingObject {
	
	public String type;
	public String imageAddress;
	public Location location;
	private int width;
	private int height;
	private double speed;
	private IFallingPattern fp;
	public KUVidGame kuvidGame= KUVidGame.getInstance();
	Random rand = new Random();
	
	public ReactionBlocker(String type, int unitLength) {
		this.type = type;
		width = unitLength/4;
		height = unitLength/4;
		location = new Location((int) (rand.nextDouble()*kuvidGame.getGameSettings().width), 0); //x loc is random
		speed = unitLength*kuvidGame.getGameSettings().getClockTime()/1000;
		fp = FallingPatternFactory.getInstance().getFallingPattern(type);
		fp.setStartLocation(location.xLoc);
	}
	
	public void fall() {
		int angle = fp.getDirection(getLocation().xLoc, getLocation().yLoc);
		setLocation((getLocation().xLoc+speed*Math.sin(Math.toRadians(angle))), (getLocation().yLoc+speed*Math.cos(Math.toRadians(angle))));
	}

	public void blockReaction() {
		
	}
	
	public double getSpeed() {
		return speed;
	}

	public void explode() {
		
	}
	
	public int getAngle() {
		return 0;
	}
	
	public void setLocation(double xLoc, double yLoc) {
		location.updateLocation(xLoc, yLoc);
	}
	
	public String getImageAddress() {
		return imageAddress;
	}

	public String getType() {
		return type;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Location getLocation() {
		return location;
	}

}
