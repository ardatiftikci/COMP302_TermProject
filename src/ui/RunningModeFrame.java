package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import domain.game.KUVidGame;

class Background extends JComponent {

	private static final long serialVersionUID = 1L;
	private Image image;

	public Background(Image image) {
		this.image = image;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}

public class RunningModeFrame extends JFrame{
	private static final String BACKGROUND_IMAGE_ADDRESS = "src/images/background.png";
	private static final long serialVersionUID = 1L;
	public int clockMiliSeconds;
	private int gameStatus = 0;
	KUVidGame kuvidGame;
	public RunningModeFrame() {
		super("Running Mode");

		kuvidGame = KUVidGame.getInstance();
		clockMiliSeconds = KUVidGame.getInstance().getGameSettings().getClockTime();

		//initialize frame
		setBounds(300,200, (4*kuvidGame.getGameSettings().width)/3, kuvidGame.getGameSettings().height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		initializeContentPane();

		//add game panel
		GamePanel gamePanel = new GamePanel();
		gamePanel.setOpaque(false);
		add(gamePanel);

		//add statistics panel
		StatsPanel statsPanel = new StatsPanel();
		statsPanel.setBackground(Color.GRAY);
		add(statsPanel);

		setVisible(true); 
		pack();

		//timer tick
		ActionListener tickListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!kuvidGame.isOver()) {
					gamePanel.repaint();
					statsPanel.setHealth();
					if(!kuvidGame.isPaused()) {
						statsPanel.setTime();
						statsPanel.setPowerupNumbers();
						statsPanel.setAtomNumbers();
						statsPanel.setShieldNumbers();
						statsPanel.setScore();
						kuvidGame.doClockTickActions(kuvidGame.getTimeLeft()-clockMiliSeconds);
					}	
				}else {
					if(gameStatus==0) {
						gameStatus=1;
						new EndgameFrame();
						dispose();			
					}
				}

			}
		};

		Timer timer = new Timer(clockMiliSeconds, tickListener);
		timer.start();

		addKeyListener(new KUVidGameKeyListener(kuvidGame));
	}

	private void initializeContentPane() {
		Image background = new ImageIcon(BACKGROUND_IMAGE_ADDRESS).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
		setContentPane(new Background(background));
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
	}
}
