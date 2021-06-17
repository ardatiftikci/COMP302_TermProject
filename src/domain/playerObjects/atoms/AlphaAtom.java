package domain.playerObjects.atoms;

import java.util.Random;

public class AlphaAtom extends Atom {
	public AlphaAtom() {
		super();
		type = "alpha";
		protonNumber = 8;
		double random = (new Random()).nextDouble();
		
		if(random<1/3) {
			neutronNumber=7;
		}else if(random<2/3) {
			neutronNumber=8;
		}else {
			neutronNumber=9;
		}
		
		imageAddress = "src/images/alphaAtom.png";
	}

	@Override
	public double getEfficiency() {
		return 0.85 * (1- ((double) (Math.abs(neutronNumber-protonNumber)))/protonNumber);
	}
	
	
	
}