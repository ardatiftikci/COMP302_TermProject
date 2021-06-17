package domain.playerObjects;

import domain.fallingObjects.powerups.Powerup;
import domain.game.GameScreen;
import domain.game.GameSettings;
import domain.game.Location;
import domain.playerObjects.atoms.Atom;
import domain.playerObjects.atoms.IThrowableObject;
import domain.playerObjects.shields.EtaShield;
import domain.playerObjects.shields.LotaShield;
import domain.playerObjects.shields.ThetaShield;
import domain.playerObjects.shields.ZetaShield;

public class Shooter {


	public int width;
	public int height;
	int angle;
	double speed;
	Location location;
	IThrowableObject projectile = null;
	public GameSettings gameSettings;
	public Shooter(int unitLength) {
		width = unitLength/2;
		height = unitLength;
		angle = 0;
		gameSettings = GameSettings.getInstance();
		speed=unitLength*gameSettings.getClockTime()/1000;
		location= new Location(0, unitLength*10-height);
	}
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getAngle() {
		return angle;
	}

	public Location getLocation() {
		return location;
	}

	public void setXLocation(double xLoc) {
		location.xLoc=xLoc;
	}

	public void setAngle(int angle) {
		this.angle=angle;
	}

	public void move(String direction) {
		if(direction.equals("right")) {
			moveRight();
		}else {
			moveLeft();
		}
		if(projectile!=null) projectile.setLocation(location.xLoc,location.yLoc,angle,width,height);
	}
	private void moveLeft() {
		if(location.xLoc>0) {
			location.updateLocation(location.xLoc-speed, location.yLoc);
		}else {
			location.updateLocation(0, location.yLoc);
		}

	}
	private void moveRight() {
		if(location.xLoc<gameSettings.width-width) {
			location.updateLocation(location.xLoc+speed, location.yLoc);
		}else {
			location.updateLocation(gameSettings.width-width, location.yLoc);
		}
	}

	public void rotate(String direction) {
		if(direction.equals("right")) {
			rotateRight();
		}else {
			rotateLeft();
		}
		if(projectile!=null)projectile.setLocation(location.xLoc,location.yLoc,angle,width,height);
	}

	private void rotateLeft() {
		if(angle>=-80) {
			angle-=10;
		}else {
			angle=-90;
		}
	}

	private void rotateRight() {
		if(angle<=80) {
			angle+=10;
		}else {
			angle=90;
		}	
	}

	public void place(IThrowableObject projectile) {
		this.projectile=projectile;
		if(projectile!=null) projectile.setLocation(location.xLoc, location.yLoc, angle,width,height);
	}

	public IThrowableObject getProjectile() {
		return projectile;
	}

	public IThrowableObject triggerShooter() {
		if(projectile!=null) {
			projectile.setAngle(angle);
			if(projectile instanceof Powerup) {
				GameScreen.getInstance().addShotPowerup((Powerup) projectile);
			}else {
				GameScreen.getInstance().addAtom((Atom) projectile);

			}
		}
		return projectile;
	}

	public void addShield(String type) {
		//REQUIRES: Shooter must have a projectile placed on.
		//MODIFIES: projectile (efficiency, speed, location) 
		//EFFECTS: shield is added to projectile if it is an atom, location is reassigned according
		//to position and angle of shooter (should be same with the old one)
		//efficiency must be between 0 and 1
		if(type.equals("eta")) {
			projectile = new EtaShield((Atom) projectile);
		}else if(type.equals("lota")) {
			projectile = new LotaShield((Atom) projectile);
		}else if(type.equals("theta")) {
			projectile = new ThetaShield((Atom) projectile);
		}else {
			projectile = new ZetaShield((Atom) projectile);
		}
		projectile.setLocation(location.xLoc, location.yLoc, angle,width,height);
	}

	public Powerup catchPowerup() {
		Powerup caughtPowerup = null;
		for(Powerup p: GameScreen.getInstance().fallingPowerupList) {
			if(p.getLocation().yLoc>=location.yLoc&&p.getLocation().yLoc<=gameSettings.height){
				if(p.getLocation().xLoc>=location.xLoc&&p.getLocation().xLoc<=(location.xLoc+width)){
					caughtPowerup=p;
				}
			}
		}
		GameScreen.getInstance().removePowerup(caughtPowerup);
		return caughtPowerup;
	}

}
