package test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.game.GameSettings;
import domain.playerObjects.Inventory;
import domain.playerObjects.Player;

public class CheckInventoryTest {

	static Player player;	
	static Inventory inventory; 

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		GameSettings.getInstance().setSavePlace("file");
		player = new Player();
		inventory = player.inventory;

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {}

	@BeforeEach
	void setUp() throws Exception {
		//setting initial number of objects to random values 
		//atoms 
		inventory.numberOfAtoms.replace("alpha", 100);
		inventory.numberOfAtoms.replace("beta", 100);
		inventory.numberOfAtoms.replace("gamma", 0);
		inventory.numberOfAtoms.replace("sigma", 100);
		
		//powerups 
		inventory.numberOfPowerups.replace("alpha", 1);
		inventory.numberOfPowerups.replace("beta", 0);
		inventory.numberOfPowerups.replace("sigma", 0);
		inventory.numberOfPowerups.replace("gamma", 2);
		
		//shields
		inventory.numberOfShields.replace("eta",12);
		inventory.numberOfShields.replace("lota",7);
		inventory.numberOfShields.replace("theta",35);
		inventory.numberOfShields.replace("zeta",23);
	}

	@AfterEach
	void tearDown() throws Exception {}

	@Test
	void atomNumberCheck() {
		//testing whether checkInventory works for atoms numerically 
		String toBeChecked = "atom";
		Random rand = new Random();
		int random = rand.nextInt(4)+1;
		String atomType = inventory.typeMap.get(random);
		boolean found = inventory.checkInventory(atomType,toBeChecked);
		
		//checking whether the random atom exists in the inventory
		assertEquals(found, inventory.numberOfAtoms.get(atomType) != 0);
		
		//testing for atoms that are not available in the inventory 
		//set to 0 at setUp : this file 
		atomType = "gamma";
		found = inventory.checkInventory(atomType,toBeChecked);
		assertEquals(found, inventory.numberOfAtoms.get(atomType) != 0);

	}
	
	@Test
	void powerupNumberCheck() {
		//testing whether checkInventory works for powerups 
		String toBeChecked = "powerup";
		String powerupType = "gamma";//or any other powerupType 
		boolean found = inventory.checkInventory(powerupType, toBeChecked);
		
		assertEquals(found,inventory.numberOfPowerups.get(powerupType) != 0);
		
		//testing for 0 amount of powerups 
		inventory.numberOfPowerups.replace(powerupType, 0);
		found = inventory.checkInventory(powerupType, toBeChecked);
		assertEquals(found,inventory.numberOfPowerups.get(powerupType) != 0);

	}
	
	
	@Test
	void shieldNumberCheck() {
		//testing whether checkInventory works for shields 
		String toBeChecked = "shield";
		String shieldType = "lota"; //or any other shield type
		boolean found = inventory.checkInventory(shieldType, toBeChecked);
		
		assertEquals(found,inventory.numberOfShields.get(shieldType) != 0);
		
		//testing for 0 amount of shields 
		inventory.numberOfShields.replace(shieldType, 0);
		found = inventory.checkInventory(shieldType,toBeChecked);
		assertEquals(found,inventory.numberOfShields.get(shieldType) != 0);
	}
	
	
	@Test 
	void removalTest() {
		//testing whether checkInventory works properly after a removal opearation
		//in cases which results in 0 atoms for a given object
		String objectType = "powerup";
		String powerupType = "gamma";
		String objectType2 = "atom";
		String atomType = "sigma";
		
		//setting up the amount of items so they reach zero 
		inventory.numberOfPowerups.replace(powerupType, 2);
		inventory.decreaseNumberOfPowerups(powerupType, 2);
		inventory.numberOfAtoms.replace(atomType, 13);
		inventory.decreaseNumberOfAtoms(atomType, 13);
		
		boolean foundP = inventory.checkInventory(powerupType,objectType); //should be false 
		boolean foundA = inventory.checkInventory(atomType, objectType2);
		
		assertEquals(foundP, inventory.numberOfPowerups.get(powerupType) != 0);
		assertEquals(foundA, inventory.numberOfAtoms.get(atomType) != 0);

	}
	
	@Test
	void atomTypeCheck() {
		//testing whether non-existing atom types can disrupt the work process
		String toBeChecked = "atom";
		//non-existing atomType
		String exploitAtom = "omega";
		boolean found = inventory.checkInventory(exploitAtom,toBeChecked); //false 
		
		//should be false - false 
		assertEquals(found,inventory.numberOfAtoms.containsKey(exploitAtom));		
	}
			
}


