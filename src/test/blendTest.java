package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.game.GameSettings;
import domain.playerObjects.Blender;
import domain.playerObjects.Inventory;
import domain.playerObjects.Player;

public class blendTest {
	
	static Inventory inventory;
	static Player player;
	static Blender blender;
	static GameSettings gameSettings;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		GameSettings.getInstance().setSavePlace("file");
		player = new Player();
		inventory = player.inventory;
		blender = player.blender;

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		//Number of atoms are reset
		inventory.numberOfAtoms.replace("alpha", 100);
		inventory.numberOfAtoms.replace("beta", 100);
		inventory.numberOfAtoms.replace("gamma", 100);
		inventory.numberOfAtoms.replace("sigma", 100);

	}

	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	@Test
	void testAlphaBlend() {
		
		//Previous values of atoms are stored before blending
		int prev1 = inventory.numberOfAtoms.get("alpha");
		int prev2 = inventory.numberOfAtoms.get("beta");
		//blendAtoms is called
		blender.blendAtoms("alpha", "beta");
		
		//If blendAtoms was successful, alpha atom # must be equal to previous value - converting ratio which is 2
		assertEquals((prev1-blender.alphaToBeta), inventory.numberOfAtoms.get("alpha"));
		//If blendAtoms was successful, beta atom # must be equal to previous value + 1 
		assertEquals((prev2+1), inventory.numberOfAtoms.get("beta"));
		
		
		//alphaToBeta test completed
		
		
		//Previous values of atoms are stored before blending
		prev1 = inventory.numberOfAtoms.get("alpha");
		prev2 = inventory.numberOfAtoms.get("gamma");
		//blendAtoms is called
		blender.blendAtoms("alpha", "gamma");
		
		//If blendAtoms was successful, alpha atom # must be equal to previous value - converting ratio which is 3
		assertEquals((prev1-blender.alphaToGamma), inventory.numberOfAtoms.get("alpha"));
		//If blendAtoms was successful, gamma atom # must be equal to previous value + 1 
		assertEquals((prev2+1), inventory.numberOfAtoms.get("gamma"));
		
		
		//alphaToGamma test completed
		
		
		//Previous values of atoms are stored before blending
		prev1 = inventory.numberOfAtoms.get("alpha");
		prev2 = inventory.numberOfAtoms.get("sigma");
		//blendAtoms is called
		blender.blendAtoms("alpha", "sigma");
		
		//If blendAtoms was successful, alpha atom # must be equal to previous value - converting ratio which is 4
		assertEquals((prev1-blender.alphaToSigma), inventory.numberOfAtoms.get("alpha"));
		//If blendAtoms was successful, sigma atom # must be equal to previous value + 1 
		assertEquals((prev2+1), inventory.numberOfAtoms.get("sigma"));
		
		
		//alphaToSigma test completed
		
	}
	
	@Test
	void testBetaBlend() {
		
		//Previous values of atoms are stored before blending
		int prev1 = inventory.numberOfAtoms.get("beta");
		int prev2 = inventory.numberOfAtoms.get("gamma");
		//blendAtoms is called
		blender.blendAtoms("beta", "gamma");
		
		//If blendAtoms was successful, beta atom # must be equal to previous value - converting ratio which is 2
		assertEquals((prev1-blender.betaToGamma), inventory.numberOfAtoms.get("beta"));
		//If blendAtoms was successful, gamma atom # must be equal to previous value + 1 
		assertEquals((prev2+1), inventory.numberOfAtoms.get("gamma"));
		
		
		//betaToGamma test completed
		
		
		//Previous values of atoms are stored before blending
		prev1 = inventory.numberOfAtoms.get("beta");
		prev2 = inventory.numberOfAtoms.get("sigma");
		//blendAtoms is called
		blender.blendAtoms("beta", "sigma");
		
		//If blendAtoms was successful, beta atom # must be equal to previous value - converting ratio which is 3
		assertEquals((prev1-blender.betaToSigma), inventory.numberOfAtoms.get("beta"));
		//If blendAtoms was successful, sigma atom # must be equal to previous value + 1 
		assertEquals((prev2+1), inventory.numberOfAtoms.get("sigma"));
		
		
		//betaToSigma test completed
		
	}
	
	@Test
	void testGammaBlend() {
		
		//Previous values of atoms are stored before blending
		int prev1 = inventory.numberOfAtoms.get("gamma");
		int prev2 = inventory.numberOfAtoms.get("sigma");
		//blendAtoms is called
		blender.blendAtoms("gamma", "sigma");
		
		//If blendAtoms was successful, gamma atom # must be equal to previous value - converting ratio which is 2
		assertEquals((prev1-blender.gammaToSigma), inventory.numberOfAtoms.get("gamma"));
		//If blendAtoms was successful, sigma atom # must be equal to previous value + 1 
		assertEquals((prev2+1), inventory.numberOfAtoms.get("sigma"));
		
		
		//gammaToSigma test completed
		
	}
	
	@Test
	void testReverseOrderBlend() {
		
		//Previous values of atoms are stored before blending
		int prev1 = inventory.numberOfAtoms.get("beta");
		int prev2 = inventory.numberOfAtoms.get("alpha");
		//blendAtoms is called in reverse rank order
		blender.blendAtoms("beta", "alpha");
		
		//If blendAtoms was successful, beta atom # must be equal to previous value since the method
		//wouldn't perform any changes in reverse order
		assertEquals(prev1, inventory.numberOfAtoms.get("beta"));
		//If blendAtoms was successful, alpha atom # must be equal to previous value
		assertEquals(prev2, inventory.numberOfAtoms.get("alpha"));
		
		
		//betaToAlpha test completed
		
		
		//Previous values of atoms are stored before blending
		prev1 = inventory.numberOfAtoms.get("gamma");
		prev2 = inventory.numberOfAtoms.get("alpha");
		//blendAtoms is called in reverse rank order
		blender.blendAtoms("gamma", "alpha");
		
		//If blendAtoms was successful, gamma atom # must be equal to previous value since the method
		//wouldn't perform any changes in reverse order
		assertEquals(prev1, inventory.numberOfAtoms.get("gamma"));
		//If blendAtoms was successful, alpha atom # must be equal to previous value
		assertEquals(prev2, inventory.numberOfAtoms.get("alpha"));
		
		
		//gammaToAlpha test completed
		
		
		//Previous values of atoms are stored before blending
		prev1 = inventory.numberOfAtoms.get("sigma");
		prev2 = inventory.numberOfAtoms.get("beta");
		//blendAtoms is called in reverse rank order
		blender.blendAtoms("sigma", "beta");
		
		//If blendAtoms was successful, sigma atom # must be equal to previous value since the method
		//wouldn't perform any changes in reverse order
		assertEquals(prev1, inventory.numberOfAtoms.get("sigma"));
		//If blendAtoms was successful, beta atom # must be equal to previous value
		assertEquals(prev2, inventory.numberOfAtoms.get("beta"));
		
		
		//sigmaToBeta test completed
		
	}
	
	@Test
	void testZeroAlphaBlend() {
		
		// # of alpha atoms are set to 0
		inventory.numberOfAtoms.replace("alpha", 0);
		//Previous values of atoms are stored before blending
		int prev1 = inventory.numberOfAtoms.get("alpha");
		int prev2 = inventory.numberOfAtoms.get("beta");
		//blendAtoms is called while the source atom number is not enough
		blender.blendAtoms("alpha", "beta");
		
		//If blendAtoms was successful, alpha atom # should be equal to previous #  since blendAtom wouldn't perform
		//if the number of source atoms are not enough
		assertEquals(prev1, inventory.numberOfAtoms.get("alpha"));
		//If blendAtoms was successful, beta atom # should be equal to previous #
		assertEquals(prev2, inventory.numberOfAtoms.get("beta"));
		
		
		//zeroAlphaToBeta test completed
		
		
		// # of alpha atoms are set to 1
		inventory.numberOfAtoms.replace("alpha", 1);
		//Previous values of atoms are stored before blending
		prev1 = inventory.numberOfAtoms.get("alpha");
		prev2 = inventory.numberOfAtoms.get("gamma");
		//blendAtoms is called while the source atom number is not enough
		blender.blendAtoms("alpha", "gamma");
		
		//If blendAtoms was successful, alpha atom # should be equal to previous #  since blendAtom wouldn't perform
		//if the number of source atoms are not enough
		assertEquals(prev1, inventory.numberOfAtoms.get("alpha"));
		//If blendAtoms was successful, gamma atom # should be equal to previous #
		assertEquals(prev2, inventory.numberOfAtoms.get("gamma"));
		
		
		//zeroAlphaToGamma test completed
		
		
		// # of alpha atoms are set to 2
		inventory.numberOfAtoms.replace("alpha", 2);
		//Previous values of atoms are stored before blending
		prev1 = inventory.numberOfAtoms.get("alpha");
		prev2 = inventory.numberOfAtoms.get("sigma");
		//blendAtoms is called while the source atom number is not enough
		blender.blendAtoms("alpha", "sigma");
		
		//If blendAtoms was successful, alpha atom # should be equal to previous # since blendAtom wouldn't perform
		//if the number of source atoms are not enough
		assertEquals(prev1, inventory.numberOfAtoms.get("alpha"));
		//If blendAtoms was successful, sigma atom # should be equal to previous #
		assertEquals(prev2, inventory.numberOfAtoms.get("sigma"));
		
		
		//zeroAlphaToSigma test completed
		
	}



}
