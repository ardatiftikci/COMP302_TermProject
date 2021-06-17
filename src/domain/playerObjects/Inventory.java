package domain.playerObjects;

import java.util.HashMap;
import java.util.Random;

import domain.game.AtomFactory;
import domain.game.GameSettings;
import domain.game.KUVidGame;
import domain.playerObjects.atoms.Atom;
import domain.playerObjects.atoms.IThrowableObject;

public class Inventory {

	/*
	 * numberOfAtoms: 
	 * 		Key -> Atom type, Value -> Corresponding amount 
	 * 
	 * numberOfPowerups:
	 * 		Key -> Powerup type, Value -> Corresponding amount 
	 * 
	 */

	KUVidGame kuvidGame = KUVidGame.getInstance();
	public HashMap<String,Integer> numberOfAtoms = new HashMap<String,Integer>(); 
	public HashMap<String,Integer> numberOfPowerups = new HashMap<String,Integer>();
	public HashMap<String,Integer> numberOfShields = new HashMap<String,Integer>(); 
	public HashMap <Integer,String> typeMap = new HashMap<>();

	public HashMap<String,String> atomUsage = new HashMap<String,String>();
	public HashMap<String,Atom> lastAtoms = new HashMap<String,Atom>();

	/*	 
	 * Game settings -> KUVidgame initialize number of corresponding elements by default
	 */

	public Inventory() {

		typeMap.put(1, "alpha");
		typeMap.put(2, "beta");
		typeMap.put(3, "gamma");
		typeMap.put(4, "sigma");

		GameSettings gameSettings = GameSettings.getInstance();
		numberOfAtoms.put("alpha", gameSettings.alphaAtoms);
		numberOfAtoms.put("beta", gameSettings.betaAtoms);
		numberOfAtoms.put("sigma", gameSettings.sigmaAtoms);
		numberOfAtoms.put("gamma", gameSettings.gammaAtoms);

		numberOfPowerups.put("alpha", 0);
		numberOfPowerups.put("beta", 0);
		numberOfPowerups.put("sigma", 0);
		numberOfPowerups.put("gamma", 0);

		numberOfShields.put("eta", gameSettings.etaShields);
		numberOfShields.put("lota", gameSettings.lotaShields);
		numberOfShields.put("theta", gameSettings.thetaShields);
		numberOfShields.put("zeta", gameSettings.zetaShields);

		lastAtoms.put("alpha", AtomFactory.getInstance().createAtom("alpha"));
		lastAtoms.put("beta", AtomFactory.getInstance().createAtom("beta"));
		lastAtoms.put("gamma", AtomFactory.getInstance().createAtom("gamma"));
		lastAtoms.put("sigma", AtomFactory.getInstance().createAtom("sigma"));

		atomUsage.put("alpha","notUsed");
		atomUsage.put("beta","notUsed");
		atomUsage.put("gamma","notUsed");
		atomUsage.put("sigma","notUsed");

	}

	public String randomAtom() {
		Random rand = new Random();
		int random = rand.nextInt(4)+1;
		boolean atomFound=false;
		/*
		 * checkInventory to see whether elements exist  
		 */
		if(!noMoreAtoms()) {
			while(!atomFound) {
				atomFound = checkInventory(typeMap.get(random), "atom");
				if(atomFound) return typeMap.get(random);
				random = rand.nextInt(4)+1;
			}
		}
		return "";
	}

	public Atom getAtom(String atomType) {

		if(atomType.contentEquals("alpha")) {
			if(atomUsage.get(atomType).contentEquals("notUsed")) {
				return lastAtoms.get(atomType);
			}else {
				lastAtoms.replace(atomType, AtomFactory.getInstance().createAtom(atomType));
				atomUsage.replace(atomType, "notUsed");
				return lastAtoms.get(atomType);
			}
		}else if(atomType.contentEquals("beta")) {
			if(atomUsage.get(atomType).contentEquals("notUsed")) {
				return lastAtoms.get(atomType);
			}else {
				lastAtoms.replace(atomType, AtomFactory.getInstance().createAtom(atomType));
				atomUsage.replace(atomType, "notUsed");
				return lastAtoms.get(atomType);
			}
		} else if(atomType.contentEquals("gamma")) {
			if(atomUsage.get(atomType).contentEquals("notUsed")) {
				return lastAtoms.get(atomType);
			}else {
				lastAtoms.replace(atomType, AtomFactory.getInstance().createAtom(atomType));
				atomUsage.replace(atomType, "notUsed");
				return lastAtoms.get(atomType);
			}
		} else if(atomType.contentEquals("sigma")) {
			if(atomUsage.get(atomType).contentEquals("notUsed")) {
				return lastAtoms.get(atomType);
			}else {
				lastAtoms.replace(atomType, AtomFactory.getInstance().createAtom(atomType));
				atomUsage.replace(atomType, "notUsed");
				return lastAtoms.get(atomType);
			}
		} else {
			return null;
		}
	}
	
	
	//Checks whether given atom is shielded  
	public boolean checkShield(String shieldType){
		return numberOfShields.get(shieldType)>0;
	}
	
	public void decreaseNumberOfShields(String type) {
		numberOfShields.put(type, numberOfShields.get(type)-1);
	}
	

	public String getPowerup(String type) {

		/*
		 * checkInventory to see whether elements exist  
		 */
		boolean powerupFound = checkInventory(type, "powerup");
		if(powerupFound) return type;
		else return "";

	}

	public boolean noMoreAtoms() {
		for(Integer number : numberOfAtoms.values()) {
			if(number!=0)return false;
		}
		return true;
	}

	public boolean noMorePowerups() {
		for(Integer number : numberOfPowerups.values()) {
			if(number!=0)return false;
		}
		return true;
	}
	//Increases the number of specified powerup by the specified amount  
	public void increaseNumberOfPowerups(String powerupType, int amount) {
		if(!numberOfPowerups.containsKey(powerupType))
			throw new java.lang.Error("Powerup type does not exist.");

		int currentAmountOfPowerup = numberOfPowerups.get(powerupType);
		numberOfPowerups.put(powerupType, currentAmountOfPowerup+amount);
	}

	//Decreases the number of specified powerup by the specified amount
	public void decreaseNumberOfPowerups(String powerupType, int amount) {
		if(!numberOfPowerups.containsKey(powerupType))
			throw new java.lang.Error("Powerup type does not exist.");

		int currentAmountOfPowerup = numberOfPowerups.get(powerupType);
		numberOfPowerups.put(powerupType, currentAmountOfPowerup-amount);
	}

	//Increases the number of specified atom by the specified amount   
	public void increaseNumberOfAtoms(String atomType, int amount) {
		//Checking whether atomType provided is valid 
		if(!numberOfAtoms.containsKey(atomType)) 
			throw new java.lang.Error("Atom type does not exist.");
		int currentAmountOfAtom = numberOfAtoms.get(atomType);
		numberOfAtoms.put(atomType, currentAmountOfAtom+amount);

	}

	//Decreases the number of specified atom by the specified amount 
	public void decreaseNumberOfAtoms(String atomType, int amount) {
		//Checking whether atomType provided is valid 
		if(!numberOfAtoms.containsKey(atomType)) 
			throw new java.lang.Error("Atom type does not exist.");
		//Checking if there enough atoms to decrease
		if( (numberOfAtoms.get(atomType) - amount) >= 0) {
			int currentAmountOfAtom = numberOfAtoms.get(atomType);
			numberOfAtoms.put(atomType, currentAmountOfAtom-amount);
		}

	}

	//Checks whether the item exists in the inventory
	public boolean checkInventory(String item, String type) {

		if (type.equals("atom")) {
			if(!numberOfAtoms.containsKey(item))return false;
			else if(numberOfAtoms.get(item)==0)return false;
			else return true;
		} else if (type.equals("powerup")) {
			if(!numberOfPowerups.containsKey(item))return false;
			else if(numberOfPowerups.get(item)==0)return false;
			else return true;
		} else if(type.contentEquals("shield")) {
			if(!numberOfShields.containsKey(item)) return false;
			else if(numberOfShields.get(item)==0)return false;
			else return true;
		} else {
			throw new java.lang.Error("Non existent item type for invetory"); 
		}

	}

	public void decreaseNumberOf(IThrowableObject projectile) {
		if(projectile!=null) {
			if(projectile instanceof Atom) {
				decreaseNumberOfAtoms(projectile.getType(), 1);
			}else {
				decreaseNumberOfPowerups(projectile.getType(), 1);

			}
		}
	} 

	public String atomList() {
		return numberOfAtoms.get("alpha")+"-"+numberOfAtoms.get("beta")+"-"+numberOfAtoms.get("sigma")+"-"+numberOfAtoms.get("gamma");
	}

	public String powerupList() {
		return numberOfPowerups.get("alpha")+"-"+numberOfPowerups.get("beta")+"-"+numberOfPowerups.get("sigma")+"-"+numberOfPowerups.get("gamma");
	}

	public String shieldList() {
		return numberOfShields.get("eta")+"-"+numberOfShields.get("lota")+"-"+numberOfShields.get("theta")+"-"+numberOfShields.get("zeta");
	}
}
