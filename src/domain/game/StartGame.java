package domain.game;

import domain.playerObjects.Player;
import ui.RunningModeFrame;

public class StartGame {

	GameSettings gameSettings;
	KUVidGame kuvidGame;
	public StartGame(String [] atom, String [] molecule, String [] reactionBlocker,String [] powerup, String [] shield,
			boolean alphaStationary, boolean betaStationary, int level, String unitLength, String savePlace) {
		
		GameSettings gameSettings = GameSettings.getInstance();
		gameSettings.setAtomNumbers(atom);
		gameSettings.setMoleculeNumbers(molecule);
		gameSettings.setReactionBlockers(reactionBlocker);
		gameSettings.setPowerups(powerup);
		gameSettings.setLinearMoleculeNumbers(alphaStationary, betaStationary);
		gameSettings.setLevel(level);
		gameSettings.setUnitLength(unitLength);
		gameSettings.setSavePlace(savePlace);
		gameSettings.setShieldNumbers(shield);
		KUVidGame.getInstance().gameSettings=gameSettings;
		KUVidGame.getInstance().player = new Player();
		KUVidGame.getInstance().timeLeft= 600*1000;
		KUVidGame.getInstance().setSums();
		new RunningModeFrame();		
	}

}
