package domain.fallingObjects.molecules;

public class LinearAlphaMolecule extends AlphaMolecule {
	public LinearAlphaMolecule(boolean isStationary, int unitLength) {
		super("linear_alpha",unitLength);
		this.isStationary=isStationary;
		imageAddress = "src/images/linearAlphaMolecule.png";
	}
	
	public void rotate() {
		rotationAngle+=10;
		if(rotationAngle==360)rotationAngle=0;
	}
}
