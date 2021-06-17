package domain.game;

import java.util.HashMap;
import java.util.Random;

import domain.fallingObjects.reactionBlockers.AlphaReactionBlocker;
import domain.fallingObjects.reactionBlockers.BetaReactionBlocker;
import domain.fallingObjects.reactionBlockers.GammaReactionBlocker;
import domain.fallingObjects.reactionBlockers.ReactionBlocker;
import domain.fallingObjects.reactionBlockers.SigmaReactionBlocker;

public class ReactionBlockerFactory {
	
	private static ReactionBlockerFactory instance;
	public static HashMap<Integer, Integer> numberOfReactionBlockersMap = new HashMap<>();
	
	private ReactionBlockerFactory() {
		GameSettings gameSettings = KUVidGame.getInstance().gameSettings;
		numberOfReactionBlockersMap.put(0, gameSettings.alphaReactionBlockers);
		numberOfReactionBlockersMap.put(1, gameSettings.betaReactionBlockers);
		numberOfReactionBlockersMap.put(2, gameSettings.gammaReactionBlockers);
		numberOfReactionBlockersMap.put(3, gameSettings.sigmaReactionBlockers);
	}
	
	public static ReactionBlockerFactory getInstance() {
		if (instance == null)
			instance = new ReactionBlockerFactory();
		return instance;
	}
	
	public ReactionBlocker createReactionBlocker() {
		Random rand = new Random();
		int type = rand.nextInt(4);
		
		if (!noMorePowerups()) {
			while (numberOfReactionBlockersMap.get(type)==0) {
				type = rand.nextInt(4);
			}
			return createReactionBlocker(type);
		}else {
			return null;
		}
	}
	
	private boolean noMorePowerups() {
		for(Integer numberOfPowerups : numberOfReactionBlockersMap.values()) {
			if(numberOfPowerups!=0) return false;
		}
		return true;
	}
	
	public ReactionBlocker createReactionBlocker(int type) {
		KUVidGame kuvidGame = KUVidGame.getInstance();
		numberOfReactionBlockersMap.put(type, numberOfReactionBlockersMap.get(type)-1);
		int unitLength = kuvidGame.getGameSettings().unitLength;
		switch (type) {
			case 0: return new AlphaReactionBlocker(unitLength);
			case 1: return new BetaReactionBlocker(unitLength);
			case 2: return new GammaReactionBlocker(unitLength);
			case 3: return new SigmaReactionBlocker(unitLength);
			default: return new SigmaReactionBlocker(unitLength);
		}
	}
	
	public String reactionBlockerList() {
		return numberOfReactionBlockersMap.get(0)+"-"+numberOfReactionBlockersMap.get(1)+"-"+numberOfReactionBlockersMap.get(2)+"-"+numberOfReactionBlockersMap.get(3);
	}
	
}
