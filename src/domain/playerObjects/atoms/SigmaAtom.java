package domain.playerObjects.atoms;

import java.util.Random;

public class SigmaAtom extends Atom {

	public SigmaAtom() {
		super();
		type = "sigma";
		imageAddress = "src/images/sigmaAtom.png";
		protonNumber = 64;
		double random = (new Random()).nextDouble();

		if(random<1/3) {
			neutronNumber=63;
		}else if(random<2/3) {
			neutronNumber=64;
		}else {
			neutronNumber=67;
		}
	}

	@Override
	public double getEfficiency() {
		return (1+0.7)/2 + ((double) Math.abs(neutronNumber-protonNumber))/protonNumber;
	}


}
