package domain.game;

import java.util.HashMap;
import java.util.Random;

import domain.fallingObjects.molecules.GammaMolecule;
import domain.fallingObjects.molecules.LinearAlphaMolecule;
import domain.fallingObjects.molecules.LinearBetaMolecule;
import domain.fallingObjects.molecules.Molecule;
import domain.fallingObjects.molecules.NormalAlphaMolecule;
import domain.fallingObjects.molecules.NormalBetaMolecule;
import domain.fallingObjects.molecules.SigmaMolecule;

public class MoleculeFactory {

	private static MoleculeFactory instance;
	public static HashMap<Integer, Integer> numberOfMoleculesMap = new HashMap<>();
	private MoleculeFactory() {
		GameSettings gameSettings = KUVidGame.getInstance().gameSettings;
		numberOfMoleculesMap.put(0, gameSettings.alphaMolecules);
		numberOfMoleculesMap.put(1, gameSettings.betaMolecules);
		numberOfMoleculesMap.put(2, gameSettings.gammaMolecules);
		numberOfMoleculesMap.put(3, gameSettings.sigmaMolecules);
		numberOfMoleculesMap.put(4, gameSettings.alphaLinearMolecules);
		numberOfMoleculesMap.put(5, gameSettings.betaLinearMolecules);
	}

	public static MoleculeFactory getInstance() {
		if (instance == null)
			instance = new MoleculeFactory();
		return instance;
	}

	public Molecule createMolecule() {
		Random rand = new Random();
		int type = rand.nextInt(6);

		if(!noMoreMolecules()) {
			while(numberOfMoleculesMap.get(type)==0) {
				type = rand.nextInt(6);
			}
			return createMolecule(type);
		}else {
			return null;
		}
	}

	private boolean noMoreMolecules() {
		for(Integer numberOfMolecules : numberOfMoleculesMap.values()) {
			if(numberOfMolecules!=0) return false;
		}
		return true;
	}

	public Molecule createMolecule(int type) {
		KUVidGame kuvidGame = KUVidGame.getInstance();
		int unitLength = kuvidGame.getGameSettings().unitLength;

		numberOfMoleculesMap.put(type, numberOfMoleculesMap.get(type)-1);
		switch (type) {
		case 0: return new NormalAlphaMolecule(unitLength);
		case 1: return new NormalBetaMolecule(unitLength);
		case 2: return new GammaMolecule(unitLength);
		case 3: return new SigmaMolecule(unitLength);
		case 4: return new LinearAlphaMolecule(kuvidGame.getGameSettings().isAlphaStationary,unitLength);
		case 5: return new LinearBetaMolecule(kuvidGame.getGameSettings().isBetaStationary,unitLength);
		default: return new SigmaMolecule(unitLength);
		}
	}
	
	public String moleculeList() {
		return numberOfMoleculesMap.get(0)+"-"+numberOfMoleculesMap.get(1)+"-"+numberOfMoleculesMap.get(2)+"-"+
				numberOfMoleculesMap.get(3)+"-"+numberOfMoleculesMap.get(4)+"-"+numberOfMoleculesMap.get(5)+"-";
	}

}
