package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartingFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private static final Color BACKGROUND_COLOR = new Color(57, 240, 220);
	private static String savePlace;
	public static void main(String[] args) {
		if(args.length>0){
			savePlace=args[0];
		}else {
			savePlace="file";
		}
		new StartingFrame();
	}
	
	public StartingFrame() {
		super("Start Window");
		setBounds(300, 200, 1000, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		initializeMainPanel(mainPanel);

		JPanel topPanel = new JPanel();
		initializeTopPanel(mainPanel, topPanel);

		JPanel bottomPanel = new JPanel();
		initializeBottomPanel(mainPanel, bottomPanel);

		JPanel middlePanel = new JPanel(new GridBagLayout());
		initializeMiddlePanel(mainPanel, middlePanel);

		setVisible(true);	
	}

	private void initializeMainPanel(JPanel mainPanel) {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
	}

	private void initializeTopPanel(JPanel mainPanel, JPanel topPanel) {
		topPanel.setLayout(new GridBagLayout());
		JLabel welcome = new JLabel("Welcome to KUVid!");
		welcome.setSize(1000, 200);
		welcome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		topPanel.add(welcome);
		topPanel.setBackground(BACKGROUND_COLOR);
		mainPanel.add(topPanel);
	}

	private void initializeBottomPanel(JPanel mainPanel, JPanel bottomPanel) {
		bottomPanel.setLayout(new GridBagLayout());
		JLabel message = new JLabel("Press the button to start.");
		message.setSize(300, 200);
		message.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		bottomPanel.add(message);
		bottomPanel.setBackground(BACKGROUND_COLOR);
		mainPanel.add(bottomPanel);
	}

	private void initializeMiddlePanel(JPanel mainPanel, JPanel middlePanel) {
		JButton startButton = new JButton("Start Game");
		middlePanel.add(startButton);
		middlePanel.setBackground(BACKGROUND_COLOR);
		mainPanel.add(middlePanel);

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//BuildingModeFrame buildingModeFrame = 
				new BuildingModeFrame(savePlace);
				dispose();				
			}
		});	
	}
}
