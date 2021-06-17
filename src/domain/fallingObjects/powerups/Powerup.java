package domain.fallingObjects.powerups;

import java.util.Random;

import domain.fallingObjects.IFallingObject;
import domain.game.GameSettings;
import domain.game.KUVidGame;
import domain.game.Location;
import domain.playerObjects.atoms.IThrowableObject;

public abstract class Powerup implements IFallingObject, IThrowableObject {
	
	public String type;
	public String imageAddress;
	public Location location;
	private int width;
	private int height;
	private double speed;
	public int angle;
	public KUVidGame kuvidGame= KUVidGame.getInstance();
	Random rand = new Random();
	public GameSettings gameSettings = GameSettings.getInstance();

	public Powerup(int unitLength) {
		width = unitLength/4;
		height = unitLength/4;
		location = new Location((int) (rand.nextDouble()*gameSettings.width), 0); //x loc is random
		speed = unitLength*gameSettings.getClockTime()/1000;
	}
	
	@Override
	public void fall() {
		setLocation(getLocation().xLoc, getLocation().yLoc+speed);
	}

	@Override
	public void move() {
		if(location.xLoc<=0) {
			if(angle<0) {
				angle=angle+90;
			}
		}else if(location.xLoc>=gameSettings.width-this.width) {
			if(angle>0) {
				angle=angle-90;
			}
		}
		location.updateLocation((location.xLoc+speed*Math.sin(Math.toRadians(angle))),
								(location.yLoc-speed*Math.cos(Math.toRadians(angle))));
	}
	
	public int getAngle() {
		return this.angle;
	}
	
	@Override
	public void setLocation(double x, double y, int angle, int width, int height) {
		double newX = x+width/2-this.width/2 + height*Math.sin(Math.toRadians(angle));
		double newY = gameSettings.height-(this.height+height)*Math.cos(Math.toRadians(angle));
		location.updateLocation(newX, newY);
	}
		
	@Override
	public void setAngle(int angle) {
		this.angle=angle;
	}

	public void setLocation(double xLoc, double yLoc) {
		location.updateLocation(xLoc, yLoc);
	}

	@Override
	public String getImageAddress() {
		return imageAddress;
	}

	public String getType() {
		return type;
	}

	public String getImageAdress() {
		return imageAddress;
	}

	public Location getLocation() {
		return location;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	
}
