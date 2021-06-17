package domain.fallingObjects;

import domain.game.Location;

public interface IFallingObject {
	public void fall();
	public int getWidth();
	public int getHeight();
	public String getImageAddress();
	public Location getLocation();
	public void setLocation(double x, double y);
	public int getAngle();
}
