package domain.game;

public class GameSettings {
	private static GameSettings instance;
	private GameSettings() {
		
	}
	
	public static GameSettings getInstance() {
		if (instance == null)
			instance = new GameSettings();
		return instance;
	}
	
	public int alphaAtoms=100, betaAtoms=100, sigmaAtoms=100, gammaAtoms=100;
	public int alphaMolecules=100, betaMolecules=100, sigmaMolecules=100, gammaMolecules=100;
	public int alphaReactionBlockers=10, betaReactionBlockers=10, sigmaReactionBlockers=10, gammaReactionBlockers=10;
	public int alphaPowerups=20, betaPowerups=20, sigmaPowerups=20, gammaPowerups=20;
	public int alphaLinearMolecules=100, betaLinearMolecules=100;
	public int etaShields=10, lotaShields=10, thetaShields=10, zetaShields=10;
	public boolean isAlphaStationary, isBetaStationary;
	public int unitLength;
	public int level;
	public int width,height;
	public String savePlace;
	
	public int getClockTime() {
		return 50;
	}
	public void setAtomNumbers(String[] atom) {
		try {
			alphaAtoms = Integer.parseInt(atom[0]);
		}catch(NumberFormatException exception) {
		}
		try {
			betaAtoms = Integer.parseInt(atom[1]);
		}catch(NumberFormatException exception) {
		}
		try {
			sigmaAtoms = Integer.parseInt(atom[2]);
		}catch(NumberFormatException exception) {
		}
		try {
			gammaAtoms = Integer.parseInt(atom[3]);
		}catch(NumberFormatException exception) {
		}
	}
	
	public void setMoleculeNumbers(String[] molecule) {
		try {
			alphaMolecules = Integer.parseInt(molecule[0]);
		}catch(NumberFormatException exception) {
		}
		try {
			betaMolecules = Integer.parseInt(molecule[1]);
		}catch(NumberFormatException exception) {
		}
		try {
			sigmaMolecules = Integer.parseInt(molecule[2]);
		}catch(NumberFormatException exception) {
		}
		try {
			gammaMolecules = Integer.parseInt(molecule[3]);
		}catch(NumberFormatException exception) {
		}
		try {
			alphaLinearMolecules = Integer.parseInt(molecule[4]);
		}catch(NumberFormatException exception) {
		}
		try {
			betaLinearMolecules = Integer.parseInt(molecule[5]);
		}catch(NumberFormatException exception) {
		}
	}
	
	public void setReactionBlockers(String[] reactionBlocker) {
		try {
			alphaReactionBlockers = Integer.parseInt(reactionBlocker[0]);
		}catch(NumberFormatException exception) {
		}
		try {
			betaReactionBlockers = Integer.parseInt(reactionBlocker[1]);
		}catch(NumberFormatException exception) {
		}
		try {
			sigmaReactionBlockers = Integer.parseInt(reactionBlocker[2]);
		}catch(NumberFormatException exception) {
		}
		try {
			gammaReactionBlockers = Integer.parseInt(reactionBlocker[3]);
		}catch(NumberFormatException exception) {
		}
	}
	
	public void setPowerups(String[] powerup) {
		try {
			alphaPowerups = Integer.parseInt(powerup[0]);
		}catch(NumberFormatException exception) {
		}
		try {
			betaPowerups = Integer.parseInt(powerup[1]);
		}catch(NumberFormatException exception) {
		}
		try {
			sigmaPowerups = Integer.parseInt(powerup[2]);
		}catch(NumberFormatException exception) {
		}
		try {
			gammaPowerups = Integer.parseInt(powerup[3]);
		}catch(NumberFormatException exception) {
		}
	}
	
	public void setLinearMoleculeNumbers(boolean isAlpha, boolean isBeta) {
		isAlphaStationary=isAlpha;
		isBetaStationary=isBeta;
	}

	public void setLevel(int selectedIndex) {
		level=selectedIndex;
	}
	
	public void setUnitLength(String unitLength) {
		try {
			this.unitLength=Integer.parseInt(unitLength);
		}catch(NumberFormatException exception) {
			this.unitLength=60;
		}
		width=this.unitLength*15;
		height=this.unitLength*10;
	}
	
	public void setSavePlace(String savePlace) {
		this.savePlace=savePlace;
	}
	
	public void setShieldNumbers(String[] shield) {
		try {
			etaShields = Integer.parseInt(shield[0]);
		}catch(NumberFormatException exception) {
		}
		try {
			lotaShields = Integer.parseInt(shield[1]);
		}catch(NumberFormatException exception) {
		}
		try {
			thetaShields = Integer.parseInt(shield[2]);
		}catch(NumberFormatException exception) {
		}
		try {
			zetaShields = Integer.parseInt(shield[3]);
		}catch(NumberFormatException exception) {
		}
	}

	
}
