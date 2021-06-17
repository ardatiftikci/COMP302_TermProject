package domain.playerObjects.atoms;

import java.util.Random;

public class BetaAtom extends Atom {

	public BetaAtom() {
		super();
		type = "beta";
		imageAddress = "src/images/betaAtom.png";
		protonNumber = 16;
		double random = (new Random()).nextDouble();
		
		if(random<1/5) {
			neutronNumber=15;
		}else if(random<2/5) {
			neutronNumber=16;
		}else if(random<3/5) {
			neutronNumber=17;
		}else if(random<4/5) {
			neutronNumber=18;
		}else {
			neutronNumber=21;
		}
		
	}

	@Override
	public double getEfficiency() {
		return 0.9 - (0.5*Math.abs(neutronNumber-protonNumber))/protonNumber;
	}

	
}
