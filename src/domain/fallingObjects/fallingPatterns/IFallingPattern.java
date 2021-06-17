package domain.fallingObjects.fallingPatterns;

public interface IFallingPattern {
	
	int getDirection(double x, double y);
	void setStartLocation(double xLoc);
}
