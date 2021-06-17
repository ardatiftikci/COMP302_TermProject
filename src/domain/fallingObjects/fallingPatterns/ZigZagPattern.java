package domain.fallingObjects.fallingPatterns;

import domain.game.KUVidGame;

public class ZigZagPattern implements IFallingPattern {

	KUVidGame kuvidGame = KUVidGame.getInstance();
	int angle = 45;
	double startX;
	
	public void setStartLocation(double xLoc) {
		startX = xLoc;
	}
	
	@Override
	public int getDirection(double xLoc, double yLoc) {
		if (xLoc-startX<0) {
			angle = 45;
		}
		else if (xLoc-startX>kuvidGame.getGameSettings().unitLength/Math.sqrt(2)) {
			angle = -45;
		}
		return angle;
	}
	
}
