package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.game.StartGame;

public class BuildingModeFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private static final Color BACKGROUND_COLOR = new Color(140, 140, 140);
	
	JTextField alphaAtom = new JTextField(5);
	JTextField betaAtom = new JTextField(5);
	JTextField sigmaAtom = new JTextField(5);
	JTextField gammaAtom = new JTextField(5);
	JTextField alphaMolecule = new JTextField(5);
	JTextField betaMolecule = new JTextField(5);
	JTextField sigmaMolecule = new JTextField(5);
	JTextField gammaMolecule = new JTextField(5);
	JTextField linearAlphaMolecule = new JTextField(5);
	JTextField linearBetaMolecule = new JTextField(5);
	JTextField alphaReactionBlocker = new JTextField(5);
	JTextField betaReactionBlocker = new JTextField(5);
	JTextField sigmaReactionBlocker = new JTextField(5);
	JTextField gammaReactionBlocker = new JTextField(5);
	JTextField alphaPowerup = new JTextField(5);
	JTextField betaPowerup = new JTextField(5);
	JTextField sigmaPowerup = new JTextField(5);
	JTextField gammaPowerup = new JTextField(5);
	// shield stuff
	JTextField etaShield = new JTextField(5);
	JTextField lotaShield = new JTextField(5);
	JTextField thetaShield = new JTextField(5);
	JTextField zetaShield = new JTextField(5);
	// end
	JCheckBox alphaStationaryCheckBox = new JCheckBox("Stationary");
	JCheckBox betaStationaryCheckBox = new JCheckBox("Stationary");
	JLabel unitLengthLabel = new JLabel("Unit Length (L)");
	JTextField unitLengthField = new JTextField(5);
	JLabel levelLabel = new JLabel("Level");
	JComboBox<String> levelComboBox = new JComboBox<>();
	String savePlace;
	public BuildingModeFrame(String savePlace) {
		super("Building Window");
		this.savePlace=savePlace;
		setBounds(300, 200, 1000, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = initializeMainPanel();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(10, 10, 10, 10);
		JPanel numberPanel = new JPanel(new GridBagLayout());
		
		initializeColumns(gbc, numberPanel);
		initializeAtomNumbers(gbc, numberPanel);
		initializeMoleculeNumbers(gbc, numberPanel);		
		initializeLinearMoleculeNumbers(gbc, numberPanel);
		initializeReactionBlockerNumbers(gbc, numberPanel);		
		initializePowerupNumbers(gbc, numberPanel);
		initializeShieldColumns(gbc, numberPanel);
		initializeShieldNumbers(gbc, numberPanel);
		
		numberPanel.setBackground(BACKGROUND_COLOR);
		mainPanel.add(numberPanel);

		JPanel otherPanel = new JPanel(new GridBagLayout());
		initializeOthers(gbc, otherPanel);
		otherPanel.setBackground(BACKGROUND_COLOR);
		mainPanel.add(otherPanel);
		
		setVisible(true);
	}

	private void initializeOthers(GridBagConstraints gbc, JPanel otherPanel) {
		levelComboBox.addItem("Easy");
		levelComboBox.addItem("Normal");
		levelComboBox.addItem("Hard");
		gbc.gridx = 0;
		gbc.gridy = 0;
		otherPanel.add(unitLengthLabel,gbc);
		gbc.gridx=1;
		otherPanel.add(unitLengthField,gbc);
		gbc.gridx=2;
		otherPanel.add(levelLabel,gbc);
		gbc.gridx=3;
		otherPanel.add(levelComboBox,gbc);
		gbc.gridx=4;
		JButton startButton = new JButton("Start Game");
		otherPanel.add(startButton,gbc);
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String [] atom = {alphaAtom.getText(),betaAtom.getText(),sigmaAtom.getText(), gammaAtom.getText()};
				String [] molecule = {alphaMolecule.getText(), betaMolecule.getText(), sigmaMolecule.getText(), gammaMolecule.getText(),linearAlphaMolecule.getText(), linearBetaMolecule.getText()};
				String [] reactionBlocker = {alphaReactionBlocker.getText(), betaReactionBlocker.getText(), sigmaReactionBlocker.getText(), gammaReactionBlocker.getText()};
				String [] powerup = {alphaPowerup.getText(), betaPowerup.getText(), sigmaPowerup.getText(), gammaPowerup.getText()};
				String [] shield = {etaShield.getText(), lotaShield.getText(), thetaShield.getText(), zetaShield.getText()};
				//StartGame startGame = 
				new StartGame(atom,molecule,reactionBlocker,powerup,shield,
						alphaStationaryCheckBox.isSelected(), betaStationaryCheckBox.isSelected(),
						levelComboBox.getSelectedIndex(),unitLengthField.getText(),savePlace);
				dispose();				
			}
		});
	}

	private void initializePowerupNumbers(GridBagConstraints gbc, JPanel numberPanel) {
		JLabel powerupNumberLabel = new JLabel("Powerup Number:");
		gbc.gridx=0;
		gbc.gridy=6;
		numberPanel.add(powerupNumberLabel,gbc);
		gbc.gridx=1;
		numberPanel.add(alphaPowerup,gbc);
		gbc.gridx=2;
		numberPanel.add(betaPowerup,gbc);
		gbc.gridx=3;
		numberPanel.add(sigmaPowerup,gbc);
		gbc.gridx=4;
		numberPanel.add(gammaPowerup,gbc);
	}

	private void initializeReactionBlockerNumbers(GridBagConstraints gbc, JPanel numberPanel) {
		JLabel reactionBlockerNumberLabel = new JLabel("Reaction Blocker Number:");
		gbc.gridx=0;
		gbc.gridy=5;
		numberPanel.add(reactionBlockerNumberLabel,gbc);
		gbc.gridx=1;
		numberPanel.add(alphaReactionBlocker,gbc);
		gbc.gridx=2;
		numberPanel.add(betaReactionBlocker,gbc);
		gbc.gridx=3;
		numberPanel.add(sigmaReactionBlocker,gbc);
		gbc.gridx=4;
		numberPanel.add(gammaReactionBlocker,gbc);
	}

	private void initializeLinearMoleculeNumbers(GridBagConstraints gbc, JPanel numberPanel) {
		JLabel linearMoleculeNumberLabel = new JLabel("Linear Molecule Number:");
		alphaStationaryCheckBox.setBackground(BACKGROUND_COLOR);
		betaStationaryCheckBox.setBackground(BACKGROUND_COLOR);
		
		gbc.gridx=0;
		gbc.gridy=3;
		numberPanel.add(linearMoleculeNumberLabel,gbc);
		gbc.gridx=1;
		numberPanel.add(linearAlphaMolecule,gbc);
		gbc.gridx=2;
		numberPanel.add(linearBetaMolecule,gbc);
		
		gbc.gridx=1;
		gbc.gridy=4;
		numberPanel.add(alphaStationaryCheckBox,gbc);
		gbc.gridx=2;
		numberPanel.add(betaStationaryCheckBox,gbc);
	}

	private void initializeMoleculeNumbers(GridBagConstraints gbc, JPanel numberPanel) {
		JLabel moleculeNumberLabel = new JLabel("Molecule Number:");
		gbc.gridx=0;
		gbc.gridy=2;
		numberPanel.add(moleculeNumberLabel,gbc);
		gbc.gridx=1;
		numberPanel.add(alphaMolecule,gbc);
		gbc.gridx=2;
		numberPanel.add(betaMolecule,gbc);
		gbc.gridx=3;
		numberPanel.add(sigmaMolecule,gbc);
		gbc.gridx=4;
		numberPanel.add(gammaMolecule,gbc);
	}

	private void initializeAtomNumbers(GridBagConstraints gbc, JPanel numberPanel) {
		JLabel atomNumberLabel = new JLabel("Atom Number:");
		gbc.gridx=0;
		gbc.gridy=1;
		numberPanel.add(atomNumberLabel,gbc);
		gbc.gridx=1;
		numberPanel.add(alphaAtom,gbc);
		gbc.gridx=2;
		numberPanel.add(betaAtom,gbc);
		gbc.gridx=3;
		numberPanel.add(sigmaAtom,gbc);
		gbc.gridx=4;
		numberPanel.add(gammaAtom,gbc);
	}
	
	private void initializeShieldNumbers(GridBagConstraints gbc, JPanel numberPanel) {
		JLabel shieldNumberLabel = new JLabel("Shield Number:");
		gbc.gridx=0;
		gbc.gridy=8;
		numberPanel.add(shieldNumberLabel,gbc);
		gbc.gridx=1;
		numberPanel.add(etaShield,gbc);
		gbc.gridx=2;
		numberPanel.add(lotaShield,gbc);
		gbc.gridx=3;
		numberPanel.add(thetaShield,gbc);
		gbc.gridx=4;
		numberPanel.add(zetaShield,gbc);
	}
	
	private void initializeShieldColumns(GridBagConstraints gbc, JPanel numberPanel) {
		JLabel column1 = new JLabel("Eta");
		JLabel column2 = new JLabel("Lota");
		JLabel column3 = new JLabel("Theta");
		JLabel column4 = new JLabel("Zeta");

		gbc.gridy = 7;
		gbc.gridx = 1;
		numberPanel.add(column1,gbc);
		gbc.gridx=2;
		numberPanel.add(column2,gbc);
		gbc.gridx=3;
		numberPanel.add(column3,gbc);
		gbc.gridx=4;
		numberPanel.add(column4,gbc);
	}

	private void initializeColumns(GridBagConstraints gbc, JPanel numberPanel) {
		JLabel column1 = new JLabel("Alpha");
		JLabel column2 = new JLabel("Beta");
		JLabel column3 = new JLabel("Sigma");
		JLabel column4 = new JLabel("Gamma");

		gbc.gridy = 0;
		gbc.gridx = 1;
		numberPanel.add(column1,gbc);
		gbc.gridx=2;
		numberPanel.add(column2,gbc);
		gbc.gridx=3;
		numberPanel.add(column3,gbc);
		gbc.gridx=4;
		numberPanel.add(column4,gbc);
	}

	private JPanel initializeMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
		return mainPanel;
	}
	
	

	
}
