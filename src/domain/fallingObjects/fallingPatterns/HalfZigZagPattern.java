package domain.fallingObjects.fallingPatterns;

import domain.game.KUVidGame;

public class HalfZigZagPattern implements IFallingPattern {
	KUVidGame kuvidGame = KUVidGame.getInstance();
	int angle = 45;
	double startX;

	public void setStartLocation(double xLoc) {
		startX = xLoc;
	}

	@Override
	public int getDirection(double xLoc, double yLoc) {
		if (yLoc<kuvidGame.getGameSettings().height/2) {
			if (xLoc-startX<0) {
				angle = 45;
			}
			else if (xLoc-startX>kuvidGame.getGameSettings().unitLength/Math.sqrt(2)) {
				angle = -45;
			}
		}
		else {
			angle = 0;
		}

		return angle;
	}
}
