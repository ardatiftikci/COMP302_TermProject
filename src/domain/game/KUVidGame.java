package domain.game;

import java.util.Random;

import domain.fallingObjects.molecules.Molecule;
import domain.fallingObjects.powerups.Powerup;
import domain.fallingObjects.reactionBlockers.ReactionBlocker;
import domain.playerObjects.Player;
import domain.playerObjects.atoms.Atom;

public class KUVidGame {

	private static KUVidGame instance;

	GameSettings gameSettings;
	Player player;
	boolean isPaused = false;
	boolean inBlender = false;
	boolean isOver = false;
	public int timeLeft;

	public int sumFall, sumMol,sumPow,sumRB;

	private KUVidGame() {}

	public static KUVidGame getInstance() {
		if (instance == null)
			instance = new KUVidGame();
		return instance;
	}

	public void setSums() {
		sumMol = gameSettings.alphaMolecules+gameSettings.betaMolecules+gameSettings.gammaMolecules+gameSettings.sigmaMolecules+
				gameSettings.alphaLinearMolecules+gameSettings.betaLinearMolecules;
		sumPow =gameSettings.alphaPowerups+gameSettings.betaPowerups+gameSettings.gammaPowerups+gameSettings.sigmaPowerups; 
		sumRB =gameSettings.alphaReactionBlockers+gameSettings.betaReactionBlockers+gameSettings.gammaReactionBlockers+gameSettings.sigmaReactionBlockers; 
		sumFall = sumMol + sumPow + sumRB;
	}

	public void setGameSettings(GameSettings gameSettings) {
		this.gameSettings=gameSettings;
	}

	public void setPlayer(Player player) {
		this.player=player;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public void setInBlender(boolean inBlender) {
		this.inBlender = inBlender;
	}

	public void doClockTickActions(int timeLeft) {
		this.timeLeft=timeLeft;
		isGameOver();
		moveProjectiles();
		fallFalling();
		createFalling();
		rotateRotating();
		controlBoundaries();
		GameScreen.getInstance().destroyReactionBlocker();
		GameScreen.getInstance().explodeReactionBlocker();
		GameScreen.getInstance().checkStableCompound();
		getPlayer().catchPowerup();
	}

	public void isGameOver() {
		GameScreen gameScreen = GameScreen.getInstance();
		boolean isObjectFinished = gameScreen.moleculeList.isEmpty()&&gameScreen.fallingPowerupList.isEmpty()
				&&gameScreen.reactionBlockerList.isEmpty()&&sumFall==0;

		boolean die = player.getHealth()<=0;
		boolean time = timeLeft<=0;
		boolean isAtomFinished = player.isAtomFinished()&&player.isPowerupFinished()&&
				gameScreen.atomList.isEmpty()&&gameScreen.shotPowerupList.isEmpty();
		
		isOver = isObjectFinished||die||time||isAtomFinished;
		
		if(isOver) {
			double collectionTime = (600000-timeLeft)/1000;
			player.incrementScore(1/collectionTime);
		}
	}
	
	public boolean isOver() {
		return isOver;
	}

	private void controlBoundaries() {
		if(timeLeft%2000==0) {
			GameScreen.getInstance().controlBoundaries();
		}
	}

	private void createFalling() {
		if(sumFall>0) {
			int period = (int) (1000/Math.pow(2, gameSettings.level));
			Random random = new Random();
			if (timeLeft%period == 0) {
				int randomInt = random.nextInt(sumFall);
				if (randomInt<sumMol) {
					createMolecule();
				}
				else if(randomInt<sumMol+sumPow)createPowerup();
				else createReactionBlocker();
				sumFall--;
			}
		}
	}

	private void createReactionBlocker() {
		FallingObjectCreator.getInstance().createReactionBlocker();
		sumRB--;
	}

	private void createPowerup() {
		FallingObjectCreator.getInstance().createPowerup();
		sumPow--;
	}

	private void createMolecule() {
		FallingObjectCreator.getInstance().createMolecule();
		sumMol--;
	}

	private void fallFalling() {
		for(Powerup p: GameScreen.getInstance().fallingPowerupList) {
			p.fall();
		}
		for(Molecule m: GameScreen.getInstance().moleculeList) {
			m.fall();
		}
		for(ReactionBlocker rb : GameScreen.getInstance().reactionBlockerList) {
			rb.fall();
		}
	}

	private void rotateRotating() {
		for(Molecule o: GameScreen.getInstance().toRotate) {
			o.rotate();
		}	
	}

	private void moveProjectiles() {
		for(Powerup p: GameScreen.getInstance().shotPowerupList) {
			p.move();
		}
		for(Atom a: GameScreen.getInstance().atomList) {
			a.move();
		}
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public boolean inBlender() {
		return inBlender;
	}

	public GameSettings getGameSettings() {
		return gameSettings;
	}

	public Player getPlayer() {
		return player;
	}

	public void moveShooter(String direction) {
		player.moveShooter(direction);
	}

	public void rotateShooter(String direction) {
		player.rotateShooter(direction);
	}

	public void pickAtom() {
		player.pickAtom();
	}

	public void pickPowerup(String type) {
		player.pickPowerup(type);
	}

	public void incrementScore(double increment) {
		player.incrementScore(increment);
	}

	public void shoot() {
		player.shoot();
	}

	public void breakOrBlend(int atom1, int atom2) {
		player.breakOrBlend(atom1, atom2);
	}

	public void addShield(String type) {
		player.addShield(type);
	}

	public void saveGame(String username) {
		player.saveGame(username);
	}

	public void loadGame(String username) {
		player.loadGame(username);
	}
}
