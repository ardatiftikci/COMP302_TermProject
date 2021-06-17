package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import domain.fallingObjects.IFallingObject;
import domain.fallingObjects.molecules.Molecule;
import domain.fallingObjects.molecules.NormalAlphaMolecule;
import domain.fallingObjects.powerups.AlphaPowerup;
import domain.fallingObjects.powerups.Powerup;
import domain.fallingObjects.reactionBlockers.AlphaReactionBlocker;
import domain.fallingObjects.reactionBlockers.ReactionBlocker;
import domain.game.GameScreen;
import domain.game.KUVidGame;
import domain.playerObjects.Shooter;
import domain.playerObjects.atoms.Atom;
import domain.playerObjects.atoms.BetaAtom;
import domain.playerObjects.atoms.IThrowableObject;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public int gameStatus=0;

	KUVidGame kuvidGame;
	private String[] atomImageAddresses = {"src/images/alphaAtom.png","src/images/betaAtom.png","src/images/gammaAtom.png","src/images/sigmaAtom.png"};
	private String[] moleculeImageAddresses = {"src/images/alphaMolecule.png","src/images/betaMolecule.png","src/images/gammaMolecule.png","src/images/sigmaMolecule.png",
			"src/images/linearAlphaMolecule.png","src/images/linearBetaMolecule.png"};
	private String[] powerupImageAddresses = {"src/images/alphaPowerup.png","src/images/betaPowerup.png","src/images/gammaPowerup.png","src/images/sigmaPowerup.png"};
	private String[] reactionBlockerImageAddresses = {"src/images/alphaRB.png","src/images/betaRB.png","src/images/gammaRB.png","src/images/sigmaRB.png"};

	private HashMap<String, Image> images = new HashMap<String, Image>();

	public GamePanel() {
		this.kuvidGame = KUVidGame.getInstance();
		loadImages();
		setPreferredSize(new Dimension(kuvidGame.getGameSettings().width, kuvidGame.getGameSettings().height));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawShooter(g);
		drawFallingObjects(g);
		drawProjectile(g);
		drawProjectiles(g);
		drawBlender(g);
	}


	public void drawShooter(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Shooter shooter = kuvidGame.getPlayer().getShooter();
		AffineTransform oldTransform = g2d.getTransform();
		AffineTransform rotateTransform = AffineTransform.getRotateInstance(Math.toRadians(shooter.getAngle()),(shooter.getLocation().xLoc+shooter.getWidth()/2), (shooter.getLocation().yLoc+shooter.getHeight()));
		rotateTransform.translate(shooter.getLocation().xLoc, shooter.getLocation().yLoc);
		g2d.drawImage(images.get("shooter"), rotateTransform , null);
		g2d.setTransform(oldTransform);
	}

	/*
	 * All falling objects are drawn in this method: molecules, powerups, reaction blockers.
	 */
	public void drawFallingObjects(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		ArrayList<IFallingObject> toFall = new ArrayList<>();
		toFall.addAll(GameScreen.getInstance().moleculeList);
		toFall.addAll(GameScreen.getInstance().fallingPowerupList);
		toFall.addAll(GameScreen.getInstance().reactionBlockerList);
		for(IFallingObject o: toFall) {
			AffineTransform oldTransform = g2.getTransform();
			AffineTransform rotateTransform = AffineTransform.getRotateInstance(Math.toRadians(o.getAngle()), o.getLocation().xLoc+o.getWidth()/2,o.getLocation().yLoc+o.getHeight()/2);
			rotateTransform.translate(o.getLocation().xLoc, o.getLocation().yLoc);
			g2.drawImage(images.get(o.getImageAddress()), rotateTransform , null);
			g2.setTransform(oldTransform);
		}
	}

	public void drawProjectile(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Shooter shooter = kuvidGame.getPlayer().getShooter();
		if(shooter.getProjectile()!=null) {
			IThrowableObject projectile = shooter.getProjectile();
			//System.out.println(projectile);
			AffineTransform at = new AffineTransform(); 
			AffineTransform oldTransform = g2.getTransform();
			at.translate(projectile.getLocation().xLoc, projectile.getLocation().yLoc);
			g2.drawImage(images.get(projectile.getImageAddress()), at , null);
			g2.setTransform(oldTransform);
		}

	}

	public void drawProjectiles(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		ArrayList<IThrowableObject> toMove = new ArrayList<>();
		toMove.addAll(GameScreen.getInstance().atomList);
		toMove.addAll(GameScreen.getInstance().shotPowerupList);
		
		for(IThrowableObject projectile: toMove) {
			AffineTransform at = new AffineTransform(); 
			AffineTransform old = g2.getTransform();
			at.translate(projectile.getLocation().xLoc, projectile.getLocation().yLoc);
			g2.drawImage(images.get(projectile.getImageAddress()), at , null);
			g2.setTransform(old);
		}
	}

	public void drawBlender(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(kuvidGame.inBlender()) {
			g2d.drawImage(images.get("blender"),(kuvidGame.getGameSettings().width-kuvidGame.getGameSettings().height/3)/2,
					(kuvidGame.getGameSettings().height-kuvidGame.getGameSettings().height/3)/2 , null);
		}
	}

	private void loadImages() {
		Shooter shooter = kuvidGame.getPlayer().getShooter();
		Image shooterImage = new ImageIcon("src/images/shooter.png").getImage().getScaledInstance(shooter.width, shooter.height, Image.SCALE_DEFAULT);
		Image blenderImage = new ImageIcon("src/images/blender.png").getImage().getScaledInstance(kuvidGame.getGameSettings().height/3,kuvidGame.getGameSettings().height/3, Image.SCALE_DEFAULT);
		images.put("shooter", shooterImage);
		images.put("blender", blenderImage);

		Atom atom = new BetaAtom();
		for(int i=0;i<4;i++) {
			images.put(atomImageAddresses[i], new ImageIcon(atomImageAddresses[i]).getImage().getScaledInstance(atom.getWidth(), atom.getHeight(), Image.SCALE_DEFAULT));
		}
		Molecule molecule = new NormalAlphaMolecule(kuvidGame.getGameSettings().unitLength);
		for(int i=0;i<6;i++) {
			images.put(moleculeImageAddresses[i], new ImageIcon(moleculeImageAddresses[i]).getImage().getScaledInstance(molecule.getWidth(), molecule.getHeight(), Image.SCALE_DEFAULT));
		}

		Powerup powerup = new AlphaPowerup(kuvidGame.getGameSettings().unitLength);
		for(int i=0;i<4;i++) {
			images.put(powerupImageAddresses[i], new ImageIcon(powerupImageAddresses[i]).getImage().getScaledInstance(powerup.getWidth(), powerup.getHeight(), Image.SCALE_DEFAULT));
		}

		ReactionBlocker reactionBlocker = new AlphaReactionBlocker(kuvidGame.getGameSettings().unitLength);
		for(int i=0;i<4;i++) {
			images.put(reactionBlockerImageAddresses[i], new ImageIcon(reactionBlockerImageAddresses[i]).getImage().getScaledInstance(reactionBlocker.getWidth(), reactionBlocker.getWidth(), Image.SCALE_DEFAULT));
		}

	}
	
}