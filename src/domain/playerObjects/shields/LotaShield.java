package domain.playerObjects.shields;

import domain.playerObjects.atoms.Atom;

public class LotaShield extends ShieldDecorator {

	Atom atom;
	
	public LotaShield(Atom atom) {
		this.atom=atom;
	}
	
	@Override
	public double getSpeed() {
		return atom.getSpeed()*0.93;
	}

	@Override
	public double getEfficiency() {
		// TODO Auto-generated method stub
		return atom.getEfficiency()*(1+(1-atom.getEfficiency())*0.1);
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
