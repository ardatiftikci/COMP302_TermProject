package domain.playerObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import domain.fallingObjects.molecules.Molecule;
import domain.fallingObjects.powerups.Powerup;
import domain.fallingObjects.reactionBlockers.ReactionBlocker;
import domain.game.AtomFactory;
import domain.game.GameScreen;
import domain.game.GameSettings;
import domain.game.KUVidGame;
import domain.game.MoleculeFactory;
import domain.game.PowerupFactory;
import domain.game.ReactionBlockerFactory;
import domain.playerObjects.atoms.Atom;
import domain.playerObjects.atoms.IThrowableObject;
import domain.saveLoad.DatabaseSaveLoadAdapter;
import domain.saveLoad.FileSaveLoadAdapter;
import domain.saveLoad.ISaveLoadAdapter;

public class Player {
	public Blender blender;
	public Shooter shooter;
	public Inventory inventory;
	double score=0;
	double health=100;
	KUVidGame kuvidGame;	
	GameSettings gameSettings;
	private ISaveLoadAdapter saveLoadAdapter;

	public Player() {
		inventory = new Inventory();
		shooter = new Shooter(GameSettings.getInstance().unitLength);
		blender = new Blender(inventory);
		pickAtom();
		kuvidGame = KUVidGame.getInstance();
		gameSettings = GameSettings.getInstance();

		if(gameSettings.savePlace.equals("file")) {
			saveLoadAdapter = new FileSaveLoadAdapter();
		}else {
			saveLoadAdapter = new DatabaseSaveLoadAdapter();
		}
		saveLoadAdapter.prepare();
	}


	public Shooter getShooter() {
		return shooter;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public void moveShooter(String direction) {
		shooter.move(direction);
	}

	public void rotateShooter(String direction) {
		shooter.rotate(direction);
	}

	public void catchPowerup() {
		//REQUIRES: none (called in every clock tick)
		//MODIFIES: map of the number of powerups in the inventory
		//EFFECTS: number of powerups of the caught powerup type is incremented
		//         by 1 in the inventory if there is a caught powerup.
		Powerup caughtPowerup = shooter.catchPowerup();
		if(caughtPowerup!=null) {
			inventory.increaseNumberOfPowerups(caughtPowerup.type, 1);
		}
	}
	public void pickAtom() {
		Atom random = inventory.getAtom(inventory.randomAtom());	
		shooter.place(random);
	}

	public void pickPowerup(String type) {
		Powerup powerup = PowerupFactory.getInstance().createPowerup(inventory.getPowerup(type));
		shooter.place(powerup);
	}

	public void shoot() {
		IThrowableObject type = shooter.triggerShooter();
		inventory.decreaseNumberOf(type);
		if (type instanceof Atom) {
			inventory.atomUsage.replace(type.getType(), "used");	
		}
		pickAtom();
	}

	public void breakOrBlend(int atom1, int atom2) {
		blender.breakOrBlend(atom1, atom2);
	}


	public void addShield(String type) {
		if(inventory.checkShield(type)&&shooter.getProjectile() instanceof Atom) {
			shooter.addShield(type);
			IThrowableObject atom = shooter.projectile;
			inventory.lastAtoms.replace(atom.getType(), (Atom) atom);
			inventory.decreaseNumberOfShields(type);
		}

	}

	public void incrementScore(double increment) {
		score+=increment;
	}

	public void saveGame(String username) {
		HashMap<String, Integer> typeMap = new HashMap<>();
		typeMap.put("alpha",0);
		typeMap.put("beta",1);
		typeMap.put("gamma",2);
		typeMap.put("sigma",3);
		typeMap.put("linear_alpha",4);
		typeMap.put("linear_beta",5);

		ArrayList<String> saveList = new ArrayList<>();
		saveList.add(Integer.toString(kuvidGame.timeLeft));
		saveList.add(Double.toString(getHealth()));
		saveList.add(Double.toString(getScore()));
		saveList.add(inventory.atomList());
		saveList.add(inventory.powerupList());
		saveList.add(inventory.shieldList());
		saveList.add(MoleculeFactory.getInstance().moleculeList());
		saveList.add(ReactionBlockerFactory.getInstance().reactionBlockerList());
		saveList.add(PowerupFactory.getInstance().powerupList());
		if(shooter.getProjectile() instanceof Atom) {
			saveList.add(Double.toString(shooter.getLocation().xLoc)+"-"+shooter.getAngle()+"-"+0+"-"+typeMap.get(shooter.getProjectile().getType()));
		}else {
			saveList.add(Double.toString(shooter.getLocation().xLoc)+"-"+shooter.getAngle()+"-"+1+"-"+typeMap.get(shooter.getProjectile().getType()));
		}
		saveList.add(Integer.toString(gameSettings.level));
		saveLocations(saveList);
		try {
			saveLoadAdapter.save(saveList, username);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveLocations(ArrayList<String> saveList) {
		GameScreen gameScreen = GameScreen.getInstance();

		HashMap<String, Integer> typeMap = new HashMap<>();
		typeMap.put("alpha",0);
		typeMap.put("beta",1);
		typeMap.put("gamma",2);
		typeMap.put("sigma",3);
		typeMap.put("linear_alpha",4);
		typeMap.put("linear_beta",5);
		for(Atom atom : gameScreen.atomList) {
			saveList.add("0-"+typeMap.get(atom.getType())+"-"+atom.getLocation().xLoc+"-"+atom.getLocation().yLoc);
		}
		for(Molecule molecule : gameScreen.moleculeList) {
			if(molecule.isStationary)saveList.add("1-"+typeMap.get(molecule.type)+"-"+molecule.getLocation().xLoc+"-"+molecule.getLocation().yLoc);
			else saveList.add("1-"+typeMap.get(molecule.type)+"-"+molecule.getLocation().xLoc+"-"+molecule.getLocation().yLoc+"-"+molecule.rotationAngle);
		}
		for(Powerup shotPowerup : gameScreen.shotPowerupList) {
			saveList.add("2-"+typeMap.get(shotPowerup.type)+"-"+shotPowerup.getLocation().xLoc+"-"+shotPowerup.getLocation().yLoc);
		}
		for(Powerup fallPowerup : gameScreen.fallingPowerupList) {
			saveList.add("3-"+typeMap.get(fallPowerup.type)+"-"+fallPowerup.getLocation().xLoc+"-"+fallPowerup.getLocation().yLoc);
		}
		for(ReactionBlocker reactionBlocker : gameScreen.reactionBlockerList) {
			saveList.add("4-"+typeMap.get(reactionBlocker.type)+"-"+reactionBlocker.getLocation().xLoc+"-"+reactionBlocker.getLocation().yLoc);
		}

	}

	public void loadGame(String username) {
		HashMap<Integer, String> typeMap = new HashMap<>();
		typeMap.put(0, "alpha");
		typeMap.put(1, "beta");
		typeMap.put(2, "gamma");
		typeMap.put(3, "sigma");
		ArrayList<String> loadList = new ArrayList<>();
		try {
			loadList = saveLoadAdapter.load(username);
			if(loadList.size()==0)System.out.println("Username does not exist!");
			else {
				kuvidGame.timeLeft = Integer.parseInt(loadList.get(0));
				setHealth(Double.parseDouble(loadList.get(1)));
				setScore(Double.parseDouble(loadList.get(2)));

				String atomNumbers = loadList.get(3);
				StringTokenizer st = new StringTokenizer(atomNumbers,"-");
				inventory.numberOfAtoms.put("alpha", Integer.parseInt(st.nextToken()));
				inventory.numberOfAtoms.put("beta", Integer.parseInt(st.nextToken()));
				inventory.numberOfAtoms.put("sigma", Integer.parseInt(st.nextToken()));
				inventory.numberOfAtoms.put("gamma", Integer.parseInt(st.nextToken()));

				String powerupNumbers = loadList.get(4);
				StringTokenizer st2 = new StringTokenizer(powerupNumbers,"-");
				inventory.numberOfPowerups.put("alpha", Integer.parseInt(st2.nextToken()));
				inventory.numberOfPowerups.put("beta", Integer.parseInt(st2.nextToken()));
				inventory.numberOfPowerups.put("sigma", Integer.parseInt(st2.nextToken()));
				inventory.numberOfPowerups.put("gamma", Integer.parseInt(st2.nextToken()));

				String shieldNumbers = loadList.get(5);
				StringTokenizer st3 = new StringTokenizer(shieldNumbers,"-");
				inventory.numberOfShields.put("eta", Integer.parseInt(st3.nextToken()));
				inventory.numberOfShields.put("lota", Integer.parseInt(st3.nextToken()));
				inventory.numberOfShields.put("zeta", Integer.parseInt(st3.nextToken()));
				inventory.numberOfShields.put("theta", Integer.parseInt(st3.nextToken()));

				String moleculeNumbers = loadList.get(6);
				StringTokenizer st4 = new StringTokenizer(moleculeNumbers,"-");
				MoleculeFactory mf = MoleculeFactory.getInstance();
				for(int i=0;i<6;i++) {
					mf.numberOfMoleculesMap.put(i, Integer.parseInt(st4.nextToken()));
				}

				String reactionBlockerNumbers = loadList.get(7);
				StringTokenizer st5 = new StringTokenizer(reactionBlockerNumbers,"-");
				ReactionBlockerFactory rbf = ReactionBlockerFactory.getInstance();
				for(int i=0;i<4;i++) {
					rbf.numberOfReactionBlockersMap.put(i, Integer.parseInt(st5.nextToken()));
				}

				String powerup2Numbers = loadList.get(8);
				StringTokenizer st6 = new StringTokenizer(powerup2Numbers,"-");
				PowerupFactory pf = PowerupFactory.getInstance();
				for(int i=0;i<4;i++) {
					pf.numberOfPowerupsMap.put(i, Integer.parseInt(st6.nextToken()));
				}

				String shooterInfo = loadList.get(9);
				StringTokenizer st7 = new StringTokenizer(shooterInfo,"-");
				shooter.setXLocation(Double.parseDouble(st7.nextToken()));
				shooter.setAngle(Integer.parseInt(st7.nextToken()));
				if(st7.nextToken().equals("0")) {
					shooter.place(AtomFactory.getInstance().createAtom(typeMap.get(Integer.parseInt(st7.nextToken()))));
				}else {
					shooter.place(PowerupFactory.getInstance().createPowerup(typeMap.get(Integer.parseInt(st7.nextToken()))));

				}
				gameSettings.level=Integer.parseInt(loadList.get(10));
				loadLocations(loadList);	
			}
		} catch (IOException e) {
			System.out.println("Username does not exist!");
		}
		setLoadSums();
	}


	private void loadLocations(ArrayList<String> loadList) {
		GameScreen gameScreen = GameScreen.getInstance();
		gameScreen.atomList.clear();
		gameScreen.moleculeList.clear();
		gameScreen.fallingPowerupList.clear();
		gameScreen.shotPowerupList.clear();
		gameScreen.reactionBlockerList.clear();

		HashMap<Integer, String> typeMap = new HashMap<>();
		typeMap.put(0, "alpha");
		typeMap.put(1, "beta");
		typeMap.put(2, "gamma");
		typeMap.put(3, "sigma");
		typeMap.put(4,"linear_alpha");
		typeMap.put(5, "linear_beta");

		for(int i=11;i<loadList.size();i++) {
			StringTokenizer st = new StringTokenizer(loadList.get(i),"-");
			int kind = Integer.parseInt(st.nextToken());
			Integer type = Integer.parseInt(st.nextToken());
			double xLoc = Double.parseDouble(st.nextToken());
			double yLoc = Double.parseDouble(st.nextToken());

			if(kind==0) {
				Atom atom = AtomFactory.getInstance().createAtom(typeMap.get(type));
				atom.setLocation(xLoc,yLoc);
				gameScreen.addAtom(atom);
			}else if(kind==1) {
				Molecule molecule = MoleculeFactory.getInstance().createMolecule(type);
				molecule.setLocation(xLoc,yLoc);
				gameScreen.addMolecule(molecule);
				if(!molecule.isStationary) {
					molecule.rotationAngle=Integer.parseInt(st.nextToken());
					gameScreen.addRotatingObject(molecule);
				}
			}else if(kind==2) {
				Powerup powerup = PowerupFactory.getInstance().createPowerup(type);
				powerup.setLocation(xLoc, yLoc);
				gameScreen.addShotPowerup(powerup);
			}else if(kind==3) {
				Powerup powerup = PowerupFactory.getInstance().createPowerup(type);
				powerup.setLocation(xLoc, yLoc);
				gameScreen.addFallingPowerup(powerup);
			}else if(kind==4) {
				ReactionBlocker reactionBlocker = ReactionBlockerFactory.getInstance().createReactionBlocker(type);
				reactionBlocker.setLocation(xLoc, yLoc);
				gameScreen.addReactionBlocker(reactionBlocker);
			}

		}
	}

	public void setLoadSums() {
		kuvidGame.sumMol=0;
		kuvidGame.sumPow=0;
		kuvidGame.sumRB=0;
		MoleculeFactory mf = MoleculeFactory.getInstance();
		ReactionBlockerFactory rbf = ReactionBlockerFactory.getInstance();
		PowerupFactory pf = PowerupFactory.getInstance();
		for(int i=0;i<6;i++) {
			kuvidGame.sumMol+=mf.numberOfMoleculesMap.get(i);
		}
		for(int i=0;i<4;i++) {
			kuvidGame.sumRB+=rbf.numberOfReactionBlockersMap.get(i);
		}
		for(int i=0;i<4;i++) {
			kuvidGame.sumPow+=pf.numberOfPowerupsMap.get(i);
		}
		kuvidGame.sumFall = kuvidGame.sumMol + kuvidGame.sumPow + kuvidGame.sumRB;
	}


	public boolean isAtomFinished() {
		for(Integer value : inventory.numberOfAtoms.values()) {
			if(value!=0) return false;
		}
		return true;
	}

	public boolean isPowerupFinished() {
		for(Integer value : inventory.numberOfPowerups.values()) {
			if(value!=0) return false;
		}
		return true;
	}

}

