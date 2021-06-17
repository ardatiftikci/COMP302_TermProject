package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.fallingObjects.powerups.AlphaPowerup;
import domain.fallingObjects.powerups.BetaPowerup;
import domain.fallingObjects.powerups.GammaPowerup;
import domain.fallingObjects.powerups.Powerup;
import domain.fallingObjects.powerups.SigmaPowerup;
import domain.game.GameScreen;
import domain.game.GameSettings;
import domain.game.Location;
import domain.playerObjects.Inventory;
import domain.playerObjects.Player;
import domain.playerObjects.Shooter;

class CollisionTest {

	static Shooter shooter;
	static Inventory inventory;
	static Player player;
	static Location shooterLoc;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		GameSettings.getInstance().setSavePlace("file");
		player = new Player();
		inventory = player.inventory;
		shooter = player.getShooter();
		shooterLoc = shooter.getLocation();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		for (int i=0; i<10; i++) {
			shooter.move("right");
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}
	@Test
	void testAlphaPowerup() {
		//Alpha powerup, placed on the shooter.
		Powerup p = new AlphaPowerup(GameSettings.getInstance().unitLength);
		p.setLocation(shooterLoc.xLoc+shooter.getWidth()/2, shooterLoc.yLoc+shooter.getHeight()/2);
		GameScreen.getInstance().fallingPowerupList.add(p);
		//store previous # of Alpha Powerups in the inventory to compare later
		int prev = inventory.numberOfPowerups.get("alpha");
		player.catchPowerup();
		//if the powerup was caught successfully, current # of Alpha powerups should be previous + 1
		assertEquals(prev+1, (int) inventory.numberOfPowerups.get("alpha"));
	}
	
	@Test
	void testBetaPowerup() {
		//Beta powerup, placed on the shooter.
		Powerup p = new BetaPowerup(GameSettings.getInstance().unitLength);
		p.setLocation(shooterLoc.xLoc+shooter.getWidth()/2, shooterLoc.yLoc+shooter.getHeight()/2);
		GameScreen.getInstance().fallingPowerupList.add(p);
		//store previous # of Beta Powerups in the inventory to compare later
		int prev = inventory.numberOfPowerups.get("beta");
		player.catchPowerup();
		//if the powerup was caught successfully, current # of Beta powerups should be previous + 1
		assertEquals(prev+1, (int) inventory.numberOfPowerups.get("beta"));
	}
	
	@Test
	void testGammaPowerup() {
		//Gamma powerup, placed on the shooter.
		Powerup p = new GammaPowerup(GameSettings.getInstance().unitLength);
		p.setLocation(shooterLoc.xLoc+shooter.getWidth()/2, shooterLoc.yLoc+shooter.getHeight()/2);
		GameScreen.getInstance().fallingPowerupList.add(p);
		//store previous # of Gamma Powerups in the inventory to compare later
		int prev = inventory.numberOfPowerups.get("gamma");
		player.catchPowerup();
		//if the powerup was caught successfully, current # of Gamma powerups should be previous + 1
		assertEquals(prev+1, (int) inventory.numberOfPowerups.get("gamma"));
	}
	
	@Test
	void testSigmaPowerup() {
		//Sigma powerup, placed on the shooter.
		Powerup p = new SigmaPowerup(GameSettings.getInstance().unitLength);
		p.setLocation(shooterLoc.xLoc+shooter.getWidth()/2, shooterLoc.yLoc+shooter.getHeight()/2);
		GameScreen.getInstance().fallingPowerupList.add(p);
		//store previous # of Sigma Powerups in the inventory to compare later
		int prev = inventory.numberOfPowerups.get("sigma");
		player.catchPowerup();
		//if the powerup was caught successfully, current # of Sigma powerups should be previous + 1
		assertEquals(prev+1, (int) inventory.numberOfPowerups.get("sigma"));
	}
	
	@Test
	void testOneOfEach() {
		//creating one powerup of each type at the same location
		Powerup alpha = new AlphaPowerup(GameSettings.getInstance().unitLength);
		Powerup beta = new BetaPowerup(GameSettings.getInstance().unitLength);
		Powerup gamma = new GammaPowerup(GameSettings.getInstance().unitLength);
		Powerup sigma = new SigmaPowerup(GameSettings.getInstance().unitLength);
		GameScreen.getInstance().fallingPowerupList.add(alpha);
		GameScreen.getInstance().fallingPowerupList.add(beta);
		GameScreen.getInstance().fallingPowerupList.add(gamma);
		GameScreen.getInstance().fallingPowerupList.add(sigma);
		int prevAlpha = (int) inventory.numberOfPowerups.get("alpha");
		int prevBeta = (int) inventory.numberOfPowerups.get("beta");
		int prevGamma = (int) inventory.numberOfPowerups.get("gamma");
		int prevSigma = (int) inventory.numberOfPowerups.get("sigma");
		alpha.setLocation(shooterLoc.xLoc+shooter.getWidth()/2, shooterLoc.yLoc+shooter.getHeight()/2);
		beta.setLocation(shooterLoc.xLoc+shooter.getWidth()/2, shooterLoc.yLoc+shooter.getHeight()/2);
		gamma.setLocation(shooterLoc.xLoc+shooter.getWidth()/2, shooterLoc.yLoc+shooter.getHeight()/2);
		sigma.setLocation(shooterLoc.xLoc+shooter.getWidth()/2, shooterLoc.yLoc+shooter.getHeight()/2);
		//calling catch powerup 4 times, one fore each in order to test whether
		//the shooter or the powerups present any unintended behavior when powerups overlap with each other.
		player.catchPowerup();
		player.catchPowerup();
		player.catchPowerup();
		player.catchPowerup();
		//finally check the # of powerups in the inventory to see if all types were incremented by 1.
		assertEquals(prevAlpha+1, (int) inventory.numberOfPowerups.get("alpha"));
		assertEquals(prevBeta+1, (int) inventory.numberOfPowerups.get("beta"));
		assertEquals(prevGamma+1, (int) inventory.numberOfPowerups.get("gamma"));
		assertEquals(prevSigma+1, (int) inventory.numberOfPowerups.get("sigma"));
	}

}

