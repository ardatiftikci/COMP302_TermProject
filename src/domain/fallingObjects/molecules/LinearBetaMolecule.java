package domain.fallingObjects.molecules;

public class LinearBetaMolecule extends BetaMolecule {
	public LinearBetaMolecule(boolean isStationary, int unitLength) {
		super("linear_beta",unitLength);
		this.isStationary=isStationary;
		imageAddress = "src/images/linearBetaMolecule.png";
	}
	
	public void rotate() {
		rotationAngle+=10;
		if(rotationAngle==360)rotationAngle=0;
	}

}
