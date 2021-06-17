package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.playerObjects.Shooter;
import domain.playerObjects.atoms.AlphaAtom;
import domain.playerObjects.atoms.Atom;

class AtomMoveTest {
	
	static Shooter shooter;
	
    @BeforeAll
	static void setUpBeforeClass() throws Exception {
    	shooter = new Shooter(60);
    	shooter.gameSettings.setUnitLength("60");
    	
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
	//20 degrees
	void test_angle_1() {
		Atom atom1 = new AlphaAtom();
		assertEquals(0,shooter.getAngle());
		shooter.rotate("right");
		shooter.rotate("right");
		assertEquals(20,shooter.getAngle());
		shooter.place(atom1);
		assertEquals(32.521208599540124,atom1.getLocation().xLoc);
		assertEquals(537.9802870281301,atom1.getLocation().yLoc);
		atom1.setAngle(shooter.getAngle());
		atom1.move();
		assertEquals(33.54726902951713,atom1.getLocation().xLoc);
		assertEquals(535.1612091657723,atom1.getLocation().yLoc);
	}
	
	//angle -30 degrees
	@Test
	void test_angle_2() {
		Atom atom2 = new AlphaAtom();
		for(int i=0; i<5; i++) {
			shooter.rotate("left");
		}
		assertEquals(-30,shooter.getAngle());
		shooter.place(atom2);
		assertEquals(-17.999999999999996,atom2.getLocation().xLoc);
		assertEquals(542.842323350227,atom2.getLocation().yLoc);
		atom2.setAngle(shooter.getAngle());
		atom2.move();
		assertEquals(-15.40192378864668,atom2.getLocation().xLoc);
		assertEquals(541.342323350227,atom2.getLocation().yLoc);
		
	}
	//-90 degrees, turned left
	@Test
	void test_n90() {
		Atom atom3 = new AlphaAtom();
		for(int j=0; j<100; j++) {
			shooter.move("right");
		}
		for(int i=0; i<18; i++) {
			shooter.rotate("left");
		}
		shooter.place(atom3);
		assertEquals(300,shooter.getLocation().xLoc);
		assertEquals(-90,shooter.getAngle());
		assertEquals(252,atom3.getLocation().xLoc);
		assertEquals(600,atom3.getLocation().yLoc);
		atom3.setAngle(shooter.getAngle());
		atom3.move();
		assertEquals(249,atom3.getLocation().xLoc);
		assertEquals(600,atom3.getLocation().yLoc);
		
	}
	//90 degree angle
	@Test
	void test_p90() {
		Atom atom4 = new AlphaAtom();
		for(int i=0; i<18; i++) {
			shooter.rotate("right");
		}
		assertEquals(90,shooter.getAngle());
		shooter.place(atom4);
		assertEquals(372,atom4.getLocation().xLoc);
		assertEquals(600,atom4.getLocation().yLoc);
		atom4.setAngle(shooter.getAngle());
		atom4.move();
		assertEquals(375,atom4.getLocation().xLoc);
		assertEquals(600,atom4.getLocation().yLoc);
		
	}
	//perpendicular, 0 degreess
	@Test
	void test_0() {
		Atom atom5 = new AlphaAtom();
		for(int i=0; i<9; i++) {
			shooter.rotate("left");
		}
		assertEquals(0,shooter.getAngle());
		shooter.place(atom5);
		assertEquals(312,atom5.getLocation().xLoc);
		assertEquals(534,atom5.getLocation().yLoc);
		atom5.setAngle(shooter.getAngle());
		atom5.move();
		assertEquals(312,atom5.getLocation().xLoc);
		assertEquals(531,atom5.getLocation().yLoc);
	}
}
