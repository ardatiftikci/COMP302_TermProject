package domain.playerObjects;

import java.util.HashMap;

public class Blender {

	public int alphaToBeta = 2;
	public int alphaToGamma = 3;
	public int alphaToSigma = 4;
	public int betaToGamma = 2;
	public int betaToSigma = 3;
	public int gammaToSigma = 2;
	public String firstType;
	public String secondType;
	HashMap <Integer, String> typeMap = new HashMap<>();
	Inventory inventory;

	public Blender(Inventory inventory) {
		typeMap.put(1, "alpha");
		typeMap.put(2, "beta");
		typeMap.put(3, "gamma");
		typeMap.put(4, "sigma");
		this.inventory=inventory;
	}

	public void breakOrBlend(int first, int second) {
		firstType = typeMap.get(first);
		secondType = typeMap.get(second);
		if(first>second) {
			breakAtom(firstType, secondType);
		}else if(first<second){
			blendAtoms(firstType, secondType);
		}
	}

	public void breakAtom(String type1, String type2) {
		
		
		int previousNumberOfType1;
		
		if(type1=="beta" && type2=="alpha") {
			previousNumberOfType1 = inventory.numberOfAtoms.get(type1);
			inventory.decreaseNumberOfAtoms(type1, 1);
			if( (previousNumberOfType1 - 1) == inventory.numberOfAtoms.get(type1)) {
				inventory.increaseNumberOfAtoms(type2, alphaToBeta);
			}

		}else if(type1=="gamma" && type2=="alpha") {
			previousNumberOfType1 = inventory.numberOfAtoms.get(type1);
			inventory.decreaseNumberOfAtoms(type1, 1);
			if( (previousNumberOfType1 - 1) == inventory.numberOfAtoms.get(type1)) {
				inventory.increaseNumberOfAtoms(type2, alphaToGamma);
			}

		}else if(type1=="gamma" && type2=="beta") {
			previousNumberOfType1 = inventory.numberOfAtoms.get(type1);
			inventory.decreaseNumberOfAtoms(type1, 1);
			if( (previousNumberOfType1 - 1) == inventory.numberOfAtoms.get(type1)) {
				inventory.increaseNumberOfAtoms(type2, betaToGamma);
			}

		}else if(type1=="sigma" && type2=="alpha") {
			previousNumberOfType1 = inventory.numberOfAtoms.get(type1);
			inventory.decreaseNumberOfAtoms(type1, 1);
			if( (previousNumberOfType1 - 1) == inventory.numberOfAtoms.get(type1)) {
				inventory.increaseNumberOfAtoms(type2, alphaToSigma);
			}

		}else if(type1=="sigma" && type2=="beta") {
			previousNumberOfType1 = inventory.numberOfAtoms.get(type1);
			inventory.decreaseNumberOfAtoms(type1, 1);
			if( (previousNumberOfType1 - 1) == inventory.numberOfAtoms.get(type1)) {
				inventory.increaseNumberOfAtoms(type2, betaToSigma);
			}

		}else if(type1=="sigma" && type2=="gamma") {
			previousNumberOfType1 = inventory.numberOfAtoms.get(type1);
			inventory.decreaseNumberOfAtoms(type1, 1);
			if( (previousNumberOfType1 - 1) == inventory.numberOfAtoms.get(type1)) {
				inventory.increaseNumberOfAtoms(type2, gammaToSigma);
			}
			
		}	

	}

	public void blendAtoms(String type1, String type2) {	
		
		//REQUIRES: There must be at least 2 alpha or 2 beta or 2 gamma atoms.
		//MODIFIES: inventory (number of atoms)
		//EFFECTS: 2 or more atoms are blended to a higher ranked atom if source atoms are enough
		//to create destination atom. To blend 1 rank up, there must be at least 2 source atoms;
		//and for 2 ranks up, the source atom number must be at least 3; and for 3 ranks up, the
		//source atom number must be at least 4.
		
		int previousNumberOfType1;
		if(type1=="alpha" && type2=="beta") {
			previousNumberOfType1 = inventory.numberOfAtoms.get(type1);
			inventory.decreaseNumberOfAtoms(type1, alphaToBeta);
			if( (previousNumberOfType1 - alphaToBeta) == inventory.numberOfAtoms.get(type1)) {
				inventory.increaseNumberOfAtoms(type2, 1);	
			}
			
		}else if(type1=="alpha" && type2=="gamma") {
			previousNumberOfType1 = inventory.numberOfAtoms.get(type1);
			inventory.decreaseNumberOfAtoms(type1, alphaToGamma);
			if( (previousNumberOfType1 - alphaToGamma) == inventory.numberOfAtoms.get(type1)) {
				inventory.increaseNumberOfAtoms(type2, 1);
			}

		}else if(type1=="alpha" && type2=="sigma") {
			previousNumberOfType1 = inventory.numberOfAtoms.get(type1);
			inventory.decreaseNumberOfAtoms(type1, alphaToSigma);
			if( (previousNumberOfType1 - alphaToSigma) == inventory.numberOfAtoms.get(type1)) {
				inventory.increaseNumberOfAtoms(type2, 1);
			}
			

		}else if(type1=="beta" && type2=="gamma") {
			previousNumberOfType1 = inventory.numberOfAtoms.get(type1);
			inventory.decreaseNumberOfAtoms(type1, betaToGamma);
			if( (previousNumberOfType1 - betaToGamma) == inventory.numberOfAtoms.get(type1)) {
				inventory.increaseNumberOfAtoms(type2, 1);
			}
			

		}else if(type1=="beta" && type2=="sigma") {
			previousNumberOfType1 = inventory.numberOfAtoms.get(type1);
			inventory.decreaseNumberOfAtoms(type1, betaToSigma);
			if( (previousNumberOfType1 - betaToSigma) == inventory.numberOfAtoms.get(type1)) {
				inventory.increaseNumberOfAtoms(type2, 1);
			}

		}else if(type1=="gamma" && type2=="sigma") {
			previousNumberOfType1 = inventory.numberOfAtoms.get(type1);
			inventory.decreaseNumberOfAtoms(type1, gammaToSigma);
			if( (previousNumberOfType1 - gammaToSigma) == inventory.numberOfAtoms.get(type1)) {
				inventory.increaseNumberOfAtoms(type2, 1);
			}
			
		}
		
	}
	
}
