package domain.playerObjects.shields;

import domain.playerObjects.atoms.Atom;

public class EtaShield extends ShieldDecorator {
	Atom atom;

	public EtaShield(Atom atom) {
		this.atom=atom;
	}

	@Override
	public double getSpeed() {
		return atom.getSpeed()*0.95;
	}
	
	@Override
	public double getEfficiency() {
		if(atom.getNeutronNumber()!=atom.getProtonNumber()) {
			return atom.getEfficiency()*(1+(1-atom.getEfficiency()) * ((double)Math.abs(atom.getProtonNumber()-atom.getNeutronNumber()))/atom.getProtonNumber());
		}else {
			return atom.getEfficiency()*(1+(1-atom.getEfficiency())*0.05);
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
