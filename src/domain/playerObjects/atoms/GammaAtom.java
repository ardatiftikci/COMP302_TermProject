package domain.playerObjects.atoms;

import java.util.Random;

public class GammaAtom extends Atom {

	public GammaAtom() {
		super();
		type = "gamma";
		imageAddress = "src/images/gammaAtom.png";
		protonNumber = 32;
		double random = (new Random()).nextDouble();
		
		if(random<1/3) {
			neutronNumber=29;
		}else if(random<2/3) {
			neutronNumber=32;
		}else {
			neutronNumber=33;
		}
	}

	@Override
	public double getEfficiency() {
		return 0.8 + ((double) Math.abs(neutronNumber-protonNumber))/(2*protonNumber);
	}

}
