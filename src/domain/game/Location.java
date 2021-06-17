package domain.game;

public class Location {

	public double xLoc;
	public double yLoc;
	
	public Location(double xLoc, double yLoc) {
		this.xLoc=xLoc;
		this.yLoc=yLoc;
	}
	
	//Location object is updated. Shooter receives information from the updated location obj.
	public void updateLocation(double newX, double newY) {
		xLoc=newX;
		yLoc=newY;
	}
}
