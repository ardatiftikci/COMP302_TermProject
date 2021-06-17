package domain.game;

import domain.playerObjects.atoms.AlphaAtom;
import domain.playerObjects.atoms.Atom;
import domain.playerObjects.atoms.BetaAtom;
import domain.playerObjects.atoms.GammaAtom;
import domain.playerObjects.atoms.SigmaAtom;

public class AtomFactory {

	private static AtomFactory instance;  

	private AtomFactory() {}  

	public static AtomFactory getInstance() {  
		if (instance == null) {  
			instance = new AtomFactory();  
		}  
		return instance;  
	}

	public Atom createAtom(String type) {
		if(type.equals("alpha"))return new AlphaAtom();
		else if(type.equals("beta"))return new BetaAtom();
		else if(type.equals("gamma"))return new GammaAtom();
		else if(type.equals("sigma"))return new SigmaAtom();
		else return null;

	}


}
