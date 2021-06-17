package domain.game;

import java.util.HashMap;
import java.util.Random;

import domain.fallingObjects.powerups.AlphaPowerup;
import domain.fallingObjects.powerups.BetaPowerup;
import domain.fallingObjects.powerups.GammaPowerup;
import domain.fallingObjects.powerups.Powerup;
import domain.fallingObjects.powerups.SigmaPowerup;

public class PowerupFactory {
	
	private static PowerupFactory instance;
	public static HashMap<Integer, Integer> numberOfPowerupsMap = new HashMap<>();
	
	private PowerupFactory() {
		GameSettings gameSettings = KUVidGame.getInstance().gameSettings;
		numberOfPowerupsMap.put(0, gameSettings.alphaPowerups);
		numberOfPowerupsMap.put(1, gameSettings.betaPowerups);
		numberOfPowerupsMap.put(2, gameSettings.gammaPowerups);
		numberOfPowerupsMap.put(3, gameSettings.sigmaPowerups);
	}
	
	public static PowerupFactory getInstance() {
		if (instance == null)
			instance = new PowerupFactory();
		return instance;
	}

	public Powerup createPowerup() {
		Random rand = new Random();
		int type = rand.nextInt(4);

		if (!noMorePowerups()) {
			while (numberOfPowerupsMap.get(type)==0) {
				type = rand.nextInt(4);
			}
			return createPowerup(type);
		}else {
			return null;
		}
	}
	public Powerup createPowerup(int type) {
		KUVidGame kuvidGame = KUVidGame.getInstance();
		int unitLength = kuvidGame.getGameSettings().unitLength;
		numberOfPowerupsMap.put(type, numberOfPowerupsMap.get(type)-1);
		switch (type) {
			case 0: return new AlphaPowerup(unitLength);
			case 1: return new BetaPowerup(unitLength);
			case 2: return new GammaPowerup(unitLength);
			case 3: return new SigmaPowerup(unitLength);
			default: return new SigmaPowerup(unitLength);
		}
	}
	private boolean noMorePowerups() {
		for(Integer numberOfPowerups : numberOfPowerupsMap.values()) {
			if(numberOfPowerups!=0) return false;
		}
		return true;
	}
	
	public Powerup createPowerup(String type) {
		KUVidGame kuvidGame = KUVidGame.getInstance();
		int unitLength = kuvidGame.getGameSettings().unitLength;
		if (type.equals("alpha")) return new AlphaPowerup(unitLength);
		else if (type.equals("beta")) return new BetaPowerup(unitLength);
		else if (type.equals("gamma")) return new GammaPowerup(unitLength);
		else if (type.equals("sigma")) return new SigmaPowerup(unitLength);
		else return null;
	}
	
	public String powerupList() {
		return numberOfPowerupsMap.get(0)+"-"+numberOfPowerupsMap.get(1)+"-"+numberOfPowerupsMap.get(2)+"-"+numberOfPowerupsMap.get(3);
	}
	
}
