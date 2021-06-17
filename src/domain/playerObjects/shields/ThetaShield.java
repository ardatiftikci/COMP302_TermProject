package domain.playerObjects.shields;

import java.util.Random;

import domain.playerObjects.atoms.Atom;

public class ThetaShield extends ShieldDecorator {
	Atom atom;
	
	public ThetaShield(Atom atom) {
		this.atom=atom;
	}
	
	public double getSpeed() {
		return atom.getSpeed()*0.91;
	}

	@Override
	public double getEfficiency() {
		// TODO Auto-generated method stub
		double random = (new Random()).nextDouble()*0.1+0.05;
		return atom.getEfficiency()*(1+(1-atom.getEfficiency())*random);
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
