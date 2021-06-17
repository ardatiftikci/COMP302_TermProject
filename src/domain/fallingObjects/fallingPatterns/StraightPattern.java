package domain.fallingObjects.fallingPatterns;

import domain.game.KUVidGame;

public class StraightPattern implements IFallingPattern {

	KUVidGame kuvidGame = KUVidGame.getInstance();
	int angle = 0;
	double startX;
	
	public void setStartLocation(double xLoc) {
		startX = xLoc;
	}
	
	@Override
	public int getDirection(double xLoc, double yLoc) {
		return angle;
	}
}