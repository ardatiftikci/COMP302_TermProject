package domain.playerObjects.atoms;

import domain.game.Location;

public interface IThrowableObject {
	public void move();
	public String getImageAddress();
	public int getWidth();
	public int getHeight();
	public void setLocation(double x , double y, int angle, int width, int height);
	public Location getLocation();
	public void setAngle(int angle);
	public int getAngle();
	public String getType();
}
