package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import domain.game.GameSettings;
import domain.game.KUVidGame;

public class StatsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	KUVidGame kuvidGame;
	JLabel scoreLabel;
	JLabel timeLabel;
	JLabel healthLabel;
	JLabel alphaPowerupLabel, betaPowerupLabel, sigmaPowerupLabel, gammaPowerupLabel;
	JLabel alphaAtomLabel, betaAtomLabel, sigmaAtomLabel, gammaAtomLabel;
	JLabel alphaIconLabel, betaIconLabel, sigmaIconLabel, gammaIconLabel;
	JLabel etaLabel, lotaLabel, thetaLabel, zetaLabel;

	Image blenderImage;
	public StatsPanel() {
		this.kuvidGame=KUVidGame.getInstance();
		setPreferredSize(new Dimension(kuvidGame.getGameSettings().width/3, kuvidGame.getGameSettings().height));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel topPanel = new JPanel(new GridBagLayout());
		topPanel.setPreferredSize(new Dimension(kuvidGame.getGameSettings().width/3, kuvidGame.getGameSettings().height/3));
		JPanel middlePanel = new JPanel(new GridBagLayout());
		topPanel.setPreferredSize(new Dimension(kuvidGame.getGameSettings().width/3, kuvidGame.getGameSettings().height/3));
		JPanel bottomPanel = new JPanel(new GridBagLayout());
		topPanel.setPreferredSize(new Dimension(kuvidGame.getGameSettings().width/3, kuvidGame.getGameSettings().height/3));

		try {
			initializeTopPanel(topPanel, gbc);
			initializeMiddlePanel(middlePanel,gbc);
			initializeBottomPanel(bottomPanel, gbc);
		} catch (IOException e) {
			e.printStackTrace();
		}

		add(topPanel);
		add(middlePanel);
		add(bottomPanel);
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int xLoc = e.getX();
				int yLoc = e.getY();
				int width = alphaIconLabel.getWidth();
				int height = alphaIconLabel.getHeight();
				int xLocPowerups = alphaIconLabel.getX();
				
				if(e.getY()>topPanel.getHeight()&&e.getY()<(topPanel.getHeight()+middlePanel.getHeight())) {
					yLoc = yLoc-topPanel.getHeight();
					if(xLoc>=xLocPowerups&&xLoc<=(xLocPowerups+width)) {
						if(yLoc>=alphaIconLabel.getY()&&yLoc<=(alphaIconLabel.getY()+height)) {
							kuvidGame.pickPowerup("alpha");
						}
						else if(yLoc>=betaIconLabel.getY()&&yLoc<=(betaIconLabel.getY()+height)) {
							kuvidGame.pickPowerup("beta");
						}
						else if(yLoc>=sigmaIconLabel.getY()&&yLoc<=(sigmaIconLabel.getY()+height)) {
							kuvidGame.pickPowerup("sigma");
						}
						else if(yLoc>=gammaIconLabel.getY()&&yLoc<=(gammaIconLabel.getY()+height)) {
							kuvidGame.pickPowerup("gamma");
						}
						
					}
				}
			}
		});
	}

	private void initializeTopPanel(JPanel topPanel, GridBagConstraints gbc) throws IOException {
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.VERTICAL;

		Image timeImage= ImageIO.read(new File("src/images/clock.png")).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		Image healthImage= ImageIO.read(new File("src/images/heart.png")).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		ImageIcon timeIcon = new ImageIcon(timeImage);
		ImageIcon healthIcon = new ImageIcon(healthImage);

		JLabel score = new JLabel("Score:");
		score.setForeground(Color.white);
		JLabel time = new JLabel(timeIcon);
		JLabel health = new JLabel(healthIcon);

		scoreLabel = new JLabel(Double.toString(kuvidGame.getPlayer().getScore()));
		scoreLabel.setForeground(Color.white);
		timeLabel = new JLabel(getTimeString(kuvidGame.getTimeLeft()));
		timeLabel.setForeground(Color.white);
		healthLabel = new JLabel(Double.toString(kuvidGame.getPlayer().getHealth()));
		healthLabel.setForeground(Color.white);

		gbc.gridx=0;
		gbc.gridy=0;
		topPanel.add(score,gbc);
		gbc.gridx=1;
		topPanel.add(scoreLabel,gbc);
		gbc.gridy=1;
		gbc.gridx=0;
		topPanel.add(time,gbc);
		gbc.gridx=1;
		topPanel.add(timeLabel,gbc);
		gbc.gridy=2;
		gbc.gridx=0;
		topPanel.add(health,gbc);
		gbc.gridx=1;
		topPanel.add(healthLabel,gbc);
		Border blackLine = BorderFactory.createLineBorder(Color.black);
		topPanel.setBorder(blackLine);
		topPanel.setOpaque(false);
	}

	private void initializeMiddlePanel(JPanel middlePanel, GridBagConstraints gbc) throws IOException {
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.VERTICAL;

		GameSettings gameSettings = KUVidGame.getInstance().getGameSettings();
		Image alphaImage= ImageIO.read(new File("src/images/alphaPowerup.png")).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		Image betaImage= ImageIO.read(new File("src/images/betaPowerup.png")).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		Image sigmaImage= ImageIO.read(new File("src/images/sigmaPowerup.png")).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		Image gammaImage= ImageIO.read(new File("src/images/gammaPowerup.png")).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);

		ImageIcon alphaIcon = new ImageIcon(alphaImage);
		ImageIcon betaIcon = new ImageIcon(betaImage);
		ImageIcon sigmaIcon = new ImageIcon(sigmaImage);
		ImageIcon gammaIcon = new ImageIcon(gammaImage);

		alphaIconLabel = new JLabel(alphaIcon);
		alphaPowerupLabel = new JLabel(Integer.toString(gameSettings.alphaPowerups));
		alphaPowerupLabel.setForeground(Color.white);
		betaIconLabel = new JLabel(betaIcon);
		betaPowerupLabel = new JLabel(Integer.toString(gameSettings.betaPowerups));
		betaPowerupLabel.setForeground(Color.white);
		sigmaIconLabel = new JLabel(sigmaIcon);
		sigmaPowerupLabel = new JLabel(Integer.toString(gameSettings.sigmaPowerups));
		sigmaPowerupLabel.setForeground(Color.white);
		gammaIconLabel = new JLabel(gammaIcon);
		gammaPowerupLabel = new JLabel(Integer.toString(gameSettings.gammaPowerups));
		gammaPowerupLabel.setForeground(Color.white);
		
		etaLabel = new JLabel(Integer.toString(gameSettings.etaShields));
		etaLabel.setForeground(Color.white);
		lotaLabel = new JLabel(Integer.toString(gameSettings.lotaShields));
		lotaLabel.setForeground(Color.white);
		thetaLabel = new JLabel(Integer.toString(gameSettings.thetaShields));
		thetaLabel.setForeground(Color.white);
		zetaLabel = new JLabel(Integer.toString(gameSettings.zetaShields));
		zetaLabel.setForeground(Color.white);
		
		JButton etaButton = new JButton("Eta");
		etaButton.setFocusable(false);
		JButton lotaButton = new JButton("Lota");
		lotaButton.setFocusable(false);
		JButton thetaButton = new JButton("Theta");
		thetaButton.setFocusable(false);
		JButton zetaButton = new JButton("Zeta");
		zetaButton.setFocusable(false);
		setShieldActionListeners(etaButton, lotaButton, thetaButton, zetaButton);
		
		gbc.gridx=0;
		gbc.gridy=0;
		middlePanel.add(etaLabel,gbc);
		gbc.gridx=1;
		middlePanel.add(etaButton, gbc);
		gbc.gridx=2;
		middlePanel.add(alphaIconLabel,gbc);
		gbc.gridx=3;
		middlePanel.add(alphaPowerupLabel,gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		middlePanel.add(lotaLabel,gbc);
		gbc.gridx=1;
		middlePanel.add(lotaButton, gbc);
		gbc.gridx=2;
		middlePanel.add(betaIconLabel,gbc);
		gbc.gridx=3;
		middlePanel.add(betaPowerupLabel,gbc);
		gbc.gridx=0;
		gbc.gridy=2;
		middlePanel.add(thetaLabel,gbc);
		gbc.gridx=1;
		middlePanel.add(thetaButton, gbc);
		gbc.gridx=2;
		middlePanel.add(sigmaIconLabel,gbc);
		gbc.gridx=3;
		middlePanel.add(sigmaPowerupLabel,gbc);
		gbc.gridx=0;
		gbc.gridy=3;
		middlePanel.add(zetaLabel,gbc);
		gbc.gridx=1;
		middlePanel.add(zetaButton, gbc);
		gbc.gridx=2;
		middlePanel.add(gammaIconLabel,gbc);
		gbc.gridx=3;
		middlePanel.add(gammaPowerupLabel,gbc);
		Border blackLine = BorderFactory.createLineBorder(Color.black);
		middlePanel.setBorder(blackLine);
		middlePanel.setOpaque(false);
	}

	private void initializeBottomPanel(JPanel bottomPanel, GridBagConstraints gbc) throws IOException {
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.VERTICAL;

		GameSettings gameSettings = KUVidGame.getInstance().getGameSettings();
		Image blenderImage= ImageIO.read(new File("src/images/blender.png")).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image alphaImage= ImageIO.read(new File("src/images/alphaAtom.png")).getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Image betaImage= ImageIO.read(new File("src/images/betaAtom.png")).getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Image sigmaImage= ImageIO.read(new File("src/images/sigmaAtom.png")).getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Image gammaImage= ImageIO.read(new File("src/images/gammaAtom.png")).getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);

		ImageIcon blenderIcon = new ImageIcon(blenderImage);
		ImageIcon alphaIcon = new ImageIcon(alphaImage);
		ImageIcon betaIcon = new ImageIcon(betaImage);
		ImageIcon sigmaIcon = new ImageIcon(sigmaImage);
		ImageIcon gammaIcon = new ImageIcon(gammaImage);

		JLabel blenderLabel = new JLabel(blenderIcon);
		JLabel alphaIconLabel = new JLabel(alphaIcon);
		alphaAtomLabel = new JLabel(Integer.toString(gameSettings.alphaAtoms));
		alphaAtomLabel.setForeground(Color.white);
		JLabel betaIconLabel = new JLabel(betaIcon);
		betaAtomLabel = new JLabel(Integer.toString(gameSettings.betaAtoms));
		betaAtomLabel.setForeground(Color.white);
		JLabel sigmaIconLabel = new JLabel(sigmaIcon);
		sigmaAtomLabel = new JLabel(Integer.toString(gameSettings.sigmaAtoms));
		sigmaAtomLabel.setForeground(Color.white);
		JLabel gammaIconLabel = new JLabel(gammaIcon);
		gammaAtomLabel = new JLabel(Integer.toString(gameSettings.gammaAtoms));
		gammaAtomLabel.setForeground(Color.white);


		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth = 2;
		bottomPanel.add(blenderLabel,gbc);
		gbc.gridwidth = 1;
		gbc.gridy=1;
		bottomPanel.add(alphaIconLabel,gbc);
		gbc.gridx=1;
		bottomPanel.add(alphaAtomLabel,gbc);
		gbc.gridy=2;
		gbc.gridx=0;
		bottomPanel.add(betaIconLabel,gbc);
		gbc.gridx=1;
		bottomPanel.add(betaAtomLabel,gbc);
		gbc.gridy=3;
		gbc.gridx=0;
		bottomPanel.add(sigmaIconLabel,gbc);
		gbc.gridx=1;
		bottomPanel.add(sigmaAtomLabel,gbc);
		gbc.gridy=4;
		gbc.gridx=0;
		bottomPanel.add(gammaIconLabel,gbc);
		gbc.gridx=1;
		bottomPanel.add(gammaAtomLabel,gbc);
		Border blackLine = BorderFactory.createLineBorder(Color.black);
		bottomPanel.setBorder(blackLine);
		bottomPanel.setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	private static String getTimeString(int timeLeft) {
		int timeInSeconds = timeLeft/1000;
		String timeString = (timeInSeconds / 60) + ":";
		if(timeInSeconds%60<10)timeString+="0"+timeInSeconds%60;
		else timeString+=timeInSeconds%60;
		return timeString;
	}

	public void setTime() {
		timeLabel.setText(getTimeString(kuvidGame.getTimeLeft()));
	}
	
	public void setHealth() {
		healthLabel.setText(Double.toString(kuvidGame.getPlayer().getHealth()));

	}
	
	public void setScore() {
		scoreLabel.setText(Double.toString(kuvidGame.getPlayer().getScore()));
	}
	
	public void setPowerupNumbers() {
		HashMap<String, Integer> pMap = kuvidGame.getPlayer().inventory.numberOfPowerups;
		alphaPowerupLabel.setText(Integer.toString(pMap.get("alpha")));
		betaPowerupLabel.setText(Integer.toString(pMap.get("beta")));
		gammaPowerupLabel.setText(Integer.toString(pMap.get("gamma")));
		sigmaPowerupLabel.setText(Integer.toString(pMap.get("sigma")));

	}
	
	public void setShieldNumbers() {
		HashMap<String, Integer> sMap = kuvidGame.getPlayer().inventory.numberOfShields;
		etaLabel.setText(Integer.toString(sMap.get("eta")));
		lotaLabel.setText(Integer.toString(sMap.get("lota")));
		thetaLabel.setText(Integer.toString(sMap.get("theta")));
		zetaLabel.setText(Integer.toString(sMap.get("zeta")));

	}
	
	public void setAtomNumbers() {
		HashMap<String, Integer> aMap = kuvidGame.getPlayer().inventory.numberOfAtoms;
		alphaAtomLabel.setText(Integer.toString(aMap.get("alpha")));
		betaAtomLabel.setText(Integer.toString(aMap.get("beta")));
		gammaAtomLabel.setText(Integer.toString(aMap.get("gamma")));
		sigmaAtomLabel.setText(Integer.toString(aMap.get("sigma")));
		
	}
	
	public void setShieldActionListeners(JButton eta, JButton lota, JButton theta, JButton zeta) {
		eta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				kuvidGame.addShield("eta");
			}
		});
		lota.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				kuvidGame.addShield("lota");
			}
		});
		theta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				kuvidGame.addShield("theta");
			}
		});
		zeta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				kuvidGame.addShield("zeta");
			}
		});
	}
		
}
