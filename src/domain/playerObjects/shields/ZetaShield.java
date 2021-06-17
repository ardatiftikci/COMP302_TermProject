package domain.playerObjects.shields;

import domain.playerObjects.atoms.Atom;

public class ZetaShield extends ShieldDecorator{
	Atom atom;
	
	public ZetaShield(Atom atom) {
		this.atom=atom;
	}
	
	public double getSpeed() {
		return atom.getSpeed()*0.89;
	}

	@Override
	public double getEfficiency() {
		// TODO Auto-generated method stub
		if(atom.getProtonNumber()==atom.getNeutronNumber()) {
			return atom.getEfficiency()*(1+(1-atom.getEfficiency())*0.2);
		}else {
			return atom.getEfficiency();
		}
	}
	
	@Override
	public String getImageAddress() {
		return atom.getImageAddress();
	}
	
	
	@Override
	public int getProtonNumber() {
		return atom.getProtonNumber();
	}
	
	@Override
	public int getNeutronNumber() {
		return atom.getNeutronNumber();
	}
	
	@Override
	public String getType() {
		return atom.getType();
	}
}
