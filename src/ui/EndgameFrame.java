package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.game.KUVidGame;


public class EndgameFrame extends JFrame{
	private static final Color BACKGROUND_COLOR = new Color(150, 242, 66);
	private static final long serialVersionUID = 1L;
	double finalScore = KUVidGame.getInstance().getPlayer().getScore();
	
	public EndgameFrame() {
		super("Endgame");
		setBounds(580, 350, 600, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(BACKGROUND_COLOR);

		JPanel mainPanel = new JPanel();
		initializeMainPanel(mainPanel);

		JPanel topPanel = new JPanel();
		initializeTopPanel(mainPanel, topPanel);

		JPanel bottomPanel = new JPanel();
		initializeBottomPanel(mainPanel, bottomPanel);

		setVisible(true);	
	}

	private void initializeMainPanel(JPanel mainPanel) {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
	}

	private void initializeTopPanel(JPanel mainPanel, JPanel topPanel) {
		topPanel.setLayout(new GridBagLayout());
		JLabel welcome = new JLabel("Game Over!");
		welcome.setSize(1000, 200);
		welcome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		topPanel.add(welcome);
		topPanel.setBackground(BACKGROUND_COLOR);
		mainPanel.add(topPanel);
	}

	private void initializeBottomPanel(JPanel mainPanel, JPanel bottomPanel) {
		bottomPanel.setLayout(new GridBagLayout());
		JLabel message = new JLabel("Score: "+ finalScore);
		message.setSize(300, 200);
		message.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		bottomPanel.add(message);
		bottomPanel.setBackground(BACKGROUND_COLOR);
		mainPanel.add(bottomPanel);
	}

}