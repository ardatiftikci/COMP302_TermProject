package domain.game;

import java.util.Random;

import domain.fallingObjects.molecules.Molecule;
import domain.fallingObjects.powerups.Powerup;
import domain.fallingObjects.reactionBlockers.ReactionBlocker;

public class FallingObjectCreator {

	private static FallingObjectCreator instance;
	Random rand = new Random();
	private FallingObjectCreator() {}

	public static FallingObjectCreator getInstance() {
		if (instance == null) {
			instance = new FallingObjectCreator();
		}
		return instance;
	}

	public void createMolecule() {
		Molecule m = MoleculeFactory.getInstance().createMolecule();
		if(m!=null) GameScreen.getInstance().addMolecule(m);
		if(m!=null&&!m.isStationary) GameScreen.getInstance().addRotatingObject(m);
	}
	
	public void createPowerup() {
		Powerup p = PowerupFactory.getInstance().createPowerup();
		GameScreen.getInstance().addFallingPowerup(p);
	}
	public void createReactionBlocker() {
		ReactionBlocker rb = ReactionBlockerFactory.getInstance().createReactionBlocker();
		GameScreen.getInstance().addReactionBlocker(rb);
	}
}
