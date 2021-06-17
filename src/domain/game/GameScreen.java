package domain.game;

import java.util.ArrayList;

import domain.fallingObjects.molecules.Molecule;
import domain.fallingObjects.powerups.Powerup;
import domain.fallingObjects.reactionBlockers.ReactionBlocker;
import domain.playerObjects.Player;
import domain.playerObjects.Shooter;
import domain.playerObjects.atoms.Atom;

public class GameScreen {
	public ArrayList<Atom> atomList = new ArrayList<>();
	public ArrayList<Powerup> shotPowerupList = new ArrayList<>();	
	public ArrayList<Powerup> fallingPowerupList = new ArrayList<>();	
	public ArrayList<Molecule> moleculeList = new ArrayList<>();
	public ArrayList<ReactionBlocker> reactionBlockerList = new ArrayList<>();
	public ArrayList<Molecule> toRotate = new ArrayList<>();
	private static GameScreen instance;

	private GameScreen() {}  

	public static GameScreen getInstance() {  
		if (instance == null) {  
			instance = new GameScreen();  
		}  
		return instance;  
	}

	public void addAtom(Atom atom) {
		atomList.add(atom);
	}

	public void addShotPowerup(Powerup powerup) {
		shotPowerupList.add(powerup);
	}

	public void addFallingPowerup(Powerup powerup) {
		fallingPowerupList.add(powerup);
	}

	public void removePowerup(Powerup powerup) {
		fallingPowerupList.remove(powerup);
	}

	public void addMolecule(Molecule molecule) {
		moleculeList.add(molecule);
	}

	public void addReactionBlocker(ReactionBlocker reactionBlocker) {
		reactionBlockerList.add(reactionBlocker);
	}

	public void addRotatingObject(Molecule m) {
		toRotate.add(m);
	}


	//catch powerup done
	//formStableCompound (with RB effect)
	//destroyRB done
	//rb explode done

	public void checkStableCompound() {
		ArrayList<Atom> toRemoveAtom = new ArrayList<>();
		ArrayList<Molecule> toRemoveMolecule = new ArrayList<>();
		int unitLength = KUVidGame.getInstance().getGameSettings().unitLength;
		for(Atom a: atomList) {
			for(Molecule m: moleculeList) {
				if(distance(a.getLocation(),m.getLocation())<unitLength/2&&m.type.contains(a.getType())) {
					boolean blocked = false;
					for(ReactionBlocker rb: reactionBlockerList) {
						if(distance(a.getLocation(), rb.getLocation())<unitLength/2&&rb.type.equals(a.type)) {
							blocked=true;
						}
					}
					if(!blocked) {
						KUVidGame.getInstance().incrementScore(a.getEfficiency());
						toRemoveAtom.add(a);
						toRemoveMolecule.add(m);
					}
				}
			}
		}

		moleculeList.removeAll(toRemoveMolecule);
		atomList.removeAll(toRemoveAtom);
	}

	public void explodeReactionBlocker() {
		int height = KUVidGame.getInstance().getGameSettings().height;
		int width = KUVidGame.getInstance().getGameSettings().width;
		Shooter shooter = KUVidGame.getInstance().getPlayer().getShooter();
		Player player = KUVidGame.getInstance().getPlayer();
		int unitLength = KUVidGame.getInstance().getGameSettings().unitLength;
		ReactionBlocker explodedRB = null;
		for(ReactionBlocker rb: reactionBlockerList) {
			if(rb.getLocation().yLoc>height-rb.getSpeed()&&rb.getLocation().yLoc<=height) {
				explodedRB = rb;
				if(Math.abs(rb.getLocation().xLoc-shooter.getLocation().xLoc)<=2*unitLength) {
					player.setHealth(player.getHealth()-width/(Math.abs(rb.getLocation().xLoc-shooter.getLocation().xLoc)));
				}
			}
			else if(rb.getLocation().yLoc>=shooter.getLocation().yLoc&&rb.getLocation().yLoc<=height) {
				if(rb.getLocation().xLoc>=shooter.getLocation().xLoc&&rb.getLocation().xLoc<=(shooter.getLocation().xLoc+shooter.getWidth())) {
					player.setHealth(0);
					explodedRB = rb;
				}
			}
		}
		if(explodedRB!=null) {
			ArrayList<Atom> toRemoveAtom = new ArrayList<>();
			ArrayList<Molecule> toRemoveMolecule = new ArrayList<>();
			for(Atom atom : atomList) {
				if(distance(explodedRB.location,atom.location)<=2*unitLength)toRemoveAtom.add(atom);
			}
			for(Molecule molecule : moleculeList) {
				if(distance(explodedRB.location,molecule.location)<=2*unitLength)toRemoveMolecule.add(molecule);
			}

			atomList.removeAll(toRemoveAtom);
			moleculeList.removeAll(toRemoveMolecule);
			reactionBlockerList.remove(explodedRB);
		}
	}

	public void destroyReactionBlocker() {
		ArrayList<Powerup> toRemovePowerup = new ArrayList<>();
		ArrayList<ReactionBlocker> toRemoveReactionBlocker = new ArrayList<>();
		int unitLength = KUVidGame.getInstance().getGameSettings().unitLength;
		for(Powerup p: shotPowerupList) {
			for(ReactionBlocker rb: reactionBlockerList) {
				if(distance(rb.getLocation(),p.getLocation())<unitLength/2&&p.type.equals(rb.type)) {
					toRemovePowerup.add(p);
					toRemoveReactionBlocker.add(rb);
				}
			}
		}

		reactionBlockerList.removeAll(toRemoveReactionBlocker);
		shotPowerupList.removeAll(toRemovePowerup);
	}

	private double distance(Location location1, Location location2) {
		return Math.sqrt(Math.pow(location1.xLoc-location2.xLoc,2)+Math.pow(location1.yLoc-location2.yLoc,2));
	}

	public void controlBoundaries() {
		ArrayList<Atom> toRemoveAtom = new ArrayList<>();
		ArrayList<Powerup> toRemoveShotPowerup = new ArrayList<>();
		ArrayList<Powerup> toRemoveFallingPowerup = new ArrayList<>();
		ArrayList<ReactionBlocker> toRemoveReactionBlocker = new ArrayList<>();		
		ArrayList<Molecule> toRemoveMolecule = new ArrayList<>();

		for(Atom atom: atomList) {
			if(atom.getLocation().yLoc<-20)toRemoveAtom.add(atom);
		}

		for(Powerup powerup: shotPowerupList) {
			if(powerup.getLocation().yLoc<-20)toRemoveShotPowerup.add(powerup);
		}

		for(Powerup powerup: fallingPowerupList) {
			if(powerup.getLocation().yLoc>KUVidGame.getInstance().gameSettings.height)toRemoveFallingPowerup.add(powerup);
		}

		for(ReactionBlocker reactionBlocker: reactionBlockerList) {
			if(reactionBlocker.getLocation().yLoc>KUVidGame.getInstance().gameSettings.height)toRemoveReactionBlocker.add(reactionBlocker);
		}

		for(Molecule molecule: moleculeList) {
			if(molecule.getLocation().yLoc>KUVidGame.getInstance().gameSettings.height)toRemoveMolecule.add(molecule);
		}
		atomList.removeAll(toRemoveAtom);
		shotPowerupList.removeAll(toRemoveShotPowerup);
		fallingPowerupList.removeAll(toRemoveFallingPowerup);
		reactionBlockerList.removeAll(toRemoveReactionBlocker);
		moleculeList.removeAll(toRemoveMolecule);
	}
}
