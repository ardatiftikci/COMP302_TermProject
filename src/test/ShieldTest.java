package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.playerObjects.Player;
import domain.playerObjects.Shooter;
import domain.playerObjects.atoms.AlphaAtom;
import domain.playerObjects.atoms.Atom;
import domain.playerObjects.atoms.BetaAtom;
import domain.playerObjects.atoms.GammaAtom;
import domain.playerObjects.atoms.SigmaAtom;

class AlphaTestAtom extends AlphaAtom{
	
	@Override
	public double getEfficiency() {
		return 0.5;
	}
}

class BetaTestAtom extends BetaAtom{
	
	@Override
	public double getEfficiency() {
		return 0.5;
	}
}

class SigmaTestAtom extends SigmaAtom{
	
	@Override
	public double getEfficiency() {
		return 0.5;
	}
}

class GammaTestAtom extends GammaAtom{
	
	@Override
	public double getEfficiency() {
		return 0.5;
	}
}
class ShieldTest {
	static Shooter shooter;
	static Player player;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		shooter = new Shooter(60);
		shooter.gameSettings.setUnitLength("60");
		shooter.gameSettings.savePlace="file";
		player = new Player();
		player.shooter=shooter;
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	@Test
	void testShieldsBlackBox() {
		//BlackBoxTest, just looking specifications - interval [0,1]
		ArrayList<String> shieldList = new ArrayList<>();
		shieldList.add("eta");
		shieldList.add("theta");
		shieldList.add("lota");
		shieldList.add("zeta");
		for(int i=0;i<20;i++) {
			player.pickAtom();
			shooter.addShield(shieldList.get(0));
			Atom shieldedAtom = (Atom) shooter.getProjectile();
			assertTrue(shieldedAtom.getEfficiency()>=0&&shieldedAtom.getEfficiency()<=1);
		}

	}
	
	@Test
	void testEtaShieldAlphaTestAtom() {
		//Alpha Atom with oneEtaShield GlassBox
		Atom atom = new AlphaTestAtom();
		//fixed efficiency for verifying, in this unit test I do not test atom's prior efficiency, I test efficiency after shiellds.
		shooter.place(atom);
		shooter.addShield("eta");
		Atom atomShielded = (Atom) shooter.getProjectile();
		if(atom.protonNumber==atom.neutronNumber) {
			assertEquals(atomShielded.getEfficiency(), 0.50125);
			//different calculation for proton=neutron
		}else {
			assertEquals(atomShielded.getEfficiency(), 0.53125);
			//neutron number is random but it can be 7,8,9 proton is deterministically 8
			//If neutron number is 7 or 9 coefficient does not change accordingly. It is 1/8. abs(n-p) is 1
		}
		assertEquals(atomShielded.getSpeed(),2.85,0.00001);//speed was 3 (unit length 60), 10^-5 fluctation is OK, because of double precision.
		//one EtaShieldTest completed
		//two EtaShieldTest started
		shooter.addShield("eta");
		atomShielded = (Atom) shooter.getProjectile();

		if(atom.protonNumber==atom.neutronNumber) {
			assertEquals(atomShielded.getEfficiency(), 0.513749922,0.00001);
		}else {
			assertEquals(atomShielded.getEfficiency(), 0.56237793,0.00001);
		}
		assertEquals(atomShielded.getSpeed(),2.7075,0.00001);
		//two EtaShieldTest completed
		//three EtaShieldTest started
		shooter.addShield("eta");
		atomShielded = (Atom) shooter.getProjectile();

		if(atom.protonNumber==atom.neutronNumber) {
			assertEquals(atomShielded.getEfficiency(), 0.526240469,0.00001);
		}else {
			assertEquals(atomShielded.getEfficiency(), 0.593141554,0.00001);
		}
		assertEquals(atomShielded.getSpeed(),2.572125,0.00001);
		//three EtaShield completed
	}

	@Test
	void testLotaShieldBetaAtom() {
		//Beta Atom with oneLotaShield GlassBox
		Atom atom = new BetaTestAtom();
		shooter.place(atom);
		shooter.addShield("lota");
		Atom atomShielded = (Atom) shooter.getProjectile();
		assertEquals(atomShielded.getEfficiency(), 0.525);
		assertEquals(atomShielded.getSpeed(),2.79,0.00001);
		//one LotaShieldTest completed
		//two LotaShieldTest started
		shooter.addShield("lota");
		atomShielded = (Atom) shooter.getProjectile();
		assertEquals(atomShielded.getEfficiency(), 0.5499375,0.00001);
		assertEquals(atomShielded.getSpeed(),2.5947,0.00001);
		//two LotaShieldTest completed
		//three LotaShieldTest started
		shooter.addShield("lota");
		atomShielded = (Atom) shooter.getProjectile();
		assertEquals(atomShielded.getEfficiency(), 0.574688125,0.00001);
		assertEquals(atomShielded.getSpeed(),2.413071,0.00001);
		//three LotaShield completed
	}

	@Test
	void testThetaShieldGammaAtom() {
		//Gamma Atom with oneThetaShield GlassBox
		Atom atom = new GammaTestAtom();
		shooter.place(atom);
		shooter.addShield("theta");
		Atom atomShielded = (Atom) shooter.getProjectile();
		assertTrue(atomShielded.getEfficiency()>=0.5125&&atomShielded.getEfficiency()<=0.5375);
		assertEquals(atomShielded.getSpeed(),2.73,0.00001);
		//one ThetaShieldTest completed
		//two ThetaShieldTest started
		shooter.addShield("theta");
		atomShielded = (Atom) shooter.getProjectile();
		assertTrue(atomShielded.getEfficiency()>=0.513749219&&atomShielded.getEfficiency()<=0.574789062);
		assertEquals(atomShielded.getSpeed(),2.4843,0.00001);
		//two ThetaShieldTest completed
		//three ThetaShieldTest started
		shooter.addShield("theta");
		atomShielded = (Atom) shooter.getProjectile();
		assertTrue(atomShielded.getEfficiency()>=0.526239767&&atomShielded.getEfficiency()<=0.611450051);
		assertEquals(atomShielded.getSpeed(),2.260713,0.00001);
		//three ThetaShield completed
	}

	@Test
	void testZetaShieldSigmaAtom() {
		//Gamma Atom with oneZetaShield GlassBox
		Atom atom = new SigmaTestAtom();
		shooter.place(atom);
		shooter.addShield("zeta");
		Atom atomShielded = (Atom) shooter.getProjectile();
		if(atom.protonNumber==atom.neutronNumber) {
			assertEquals(atomShielded.getEfficiency(), 0.50125);
		}else {
			assertEquals(atomShielded.getEfficiency(), 0.5);
		}
		assertEquals(atomShielded.getSpeed(),2.67,0.00001);
		//one ZetaShieldTest completed
		//two ZetaShieldTest started
		shooter.addShield("zeta");
		atomShielded = (Atom) shooter.getProjectile();
		if(atom.protonNumber==atom.neutronNumber) {
			assertEquals(atomShielded.getEfficiency(), 0.562375);
		}else {
			assertEquals(atomShielded.getEfficiency(), 0.5);
		}
		assertEquals(atomShielded.getSpeed(),2.3763,0.00001);
		//two ZetaShieldTest completed
		//three ZetaShieldTest started
		shooter.addShield("zeta");
		atomShielded = (Atom) shooter.getProjectile();
		if(atom.protonNumber==atom.neutronNumber) {
			assertEquals(atomShielded.getEfficiency(), 0.574680468);
		}else {
			assertEquals(atomShielded.getEfficiency(), 0.5);
		}
		assertEquals(atomShielded.getSpeed(),2.114907,0.00001);
		//three ZetaShield completed
	}
	
	@Test
	void testAllShields() {
		//Adding All Shields Combined
		Atom atom = new AlphaTestAtom();
		shooter.place(atom);
		shooter.addShield("eta");
		Atom atomShielded = (Atom) shooter.getProjectile();
		if(atom.protonNumber==atom.neutronNumber) {
			assertEquals(atomShielded.getEfficiency(), 0.50125);
		}else {
			assertEquals(atomShielded.getEfficiency(), 0.53125);
		}
		assertEquals(atomShielded.getSpeed(),2.85,0.00001);

		shooter.addShield("lota");
		atomShielded = (Atom) shooter.getProjectile();
		if(atom.protonNumber==atom.neutronNumber) {
			assertEquals(atomShielded.getEfficiency(), 0.526249844);
		}else {
			assertEquals(atomShielded.getEfficiency(), 0.556152344,0.00001);
		}
		assertEquals(atomShielded.getSpeed(),2.6505,0.00001);
		
		shooter.addShield("zeta");
		atomShielded = (Atom) shooter.getProjectile();
		if(atom.protonNumber==atom.neutronNumber) {
			assertEquals(atomShielded.getEfficiency(), 0.623565034);
		}else {
			assertEquals(atomShielded.getEfficiency(), 0.556152344,0.00001);
		}
		assertEquals(atomShielded.getSpeed(),2.358945,0.00001);
		
		shooter.addShield("theta");
		atomShielded = (Atom) shooter.getProjectile();
		if(atom.protonNumber==atom.neutronNumber) {
			assertTrue(atomShielded.getEfficiency()>=0.635301618&&atomShielded.getEfficiency()<=0.65877478635);
		}else {
			assertTrue(atomShielded.getEfficiency()>=0.56849469&&atomShielded.getEfficiency()<=0.593179381);
		}
		assertEquals(atomShielded.getSpeed(),2.14663995,0.00001);
		
		//All shields added to one atom. Test completed.
		
		//Further tests can be done with combinations of atoms and shields, but my tests cover all possible scenarios, so I think it is enough.

	}

	@Test
	void testLocation() {
		Atom alphaAtom = new AlphaTestAtom();
		shooter.place(alphaAtom);
		shooter.addShield("zeta");
		Atom alphaAtomShielded = (Atom) shooter.getProjectile();
		assertEquals(alphaAtom.getLocation().xLoc,alphaAtomShielded.getLocation().xLoc);
		assertEquals(alphaAtom.getLocation().yLoc,alphaAtomShielded.getLocation().yLoc);

		Atom betaAtom = new BetaTestAtom();
		shooter.place(betaAtom);
		shooter.addShield("lota");
		Atom betaAtomShielded = (Atom) shooter.getProjectile();
		assertEquals(betaAtom.getLocation().xLoc,betaAtomShielded.getLocation().xLoc);
		assertEquals(betaAtom.getLocation().yLoc,betaAtomShielded.getLocation().yLoc);		

		Atom gammaAtom = new GammaTestAtom();
		shooter.place(gammaAtom);
		shooter.addShield("theta");
		Atom gammaAtomShielded = (Atom) shooter.getProjectile();
		assertEquals(gammaAtom.getLocation().xLoc,gammaAtomShielded.getLocation().xLoc);
		assertEquals(gammaAtom.getLocation().yLoc,gammaAtomShielded.getLocation().yLoc);

		Atom sigmaAtom = new SigmaTestAtom();
		shooter.place(sigmaAtom);
		shooter.addShield("eta");
		Atom sigmaAtomShielded = (Atom) shooter.getProjectile();
		assertEquals(sigmaAtom.getLocation().xLoc,sigmaAtomShielded.getLocation().xLoc);
		assertEquals(sigmaAtom.getLocation().yLoc,sigmaAtomShielded.getLocation().yLoc);
	}
}
