package domain.playerObjects.atoms;

import domain.game.GameSettings;
import domain.game.Location;

public abstract class Atom implements IThrowableObject{

	public String type;
	public String imageAddress;
	public Location location;
	private int width;
	private int height;
	private double speed;
	private int angle;
	
	public int neutronNumber;
	public int protonNumber;
	public GameSettings gameSettings;

	public Atom() {
		gameSettings = GameSettings.getInstance();
		int unitLength=gameSettings.unitLength;
		width = unitLength/10;
		height = unitLength/10;
		location = new Location(0, 0);
		speed = unitLength*gameSettings.getClockTime()/1000;
	}
	
	public void setLocation(double xLoc, double yLoc) {
		location.xLoc=xLoc;
		location.yLoc=yLoc;
	}
	
	public void setAngle(int angle) {
		this.angle=angle;
	}

	public int getAngle() {
		return this.angle;
	}
	
	public abstract double getEfficiency();
	
	public int getNeutronNumber() {
		return neutronNumber;
	}

	public void setNeutronNumber(int neutronNumber) {
		this.neutronNumber = neutronNumber;
	}

	public int getProtonNumber() {
		return protonNumber;
	}

	public void setProtonNumber(int protonNumber) {
		this.protonNumber = protonNumber;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}

	public double getSpeed() {
		return speed;
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
		location.updateLocation((location.xLoc+getSpeed()*Math.sin(Math.toRadians(angle))),
								(location.yLoc-getSpeed()*Math.cos(Math.toRadians(angle))));
	}

	@Override
	public void setLocation(double x, double y, int angle, int width, int height) {
		double newX = x+width/2-this.width/2 + height*Math.sin(Math.toRadians(angle));
		double newY = gameSettings.height-(this.height+height)*Math.cos(Math.toRadians(angle));
		location.updateLocation(newX, newY);
	}

	@Override
	public String getImageAddress() {
		return imageAddress;
	}

	public String getType() {
		return type;
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
