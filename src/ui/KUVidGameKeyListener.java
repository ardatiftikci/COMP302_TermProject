package ui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import domain.game.KUVidGame;

public class KUVidGameKeyListener implements KeyListener{
	KUVidGame kuvidGame;
	int lastKey = -1;
	Scanner scanner = new Scanner(System.in);
	public KUVidGameKeyListener(KUVidGame kuvidGame) {
		this.kuvidGame=kuvidGame;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_P) kuvidGame.setPaused(true);

		else if(arg0.getKeyCode()==KeyEvent.VK_R) kuvidGame.setPaused(false);

		else if(arg0.getKeyCode()==KeyEvent.VK_B) {
			kuvidGame.setPaused(true);
			kuvidGame.setInBlender(true);
		}

		if(kuvidGame.inBlender()&&(arg0.getKeyCode()==KeyEvent.VK_1||arg0.getKeyCode()==KeyEvent.VK_2||
									arg0.getKeyCode()==KeyEvent.VK_3||arg0.getKeyCode()==KeyEvent.VK_4)) {
			if(lastKey==-1) lastKey= (int) (arg0.getKeyChar()-48);
			else {
				int first = lastKey;
				int second = (int) (arg0.getKeyChar()-48);
				kuvidGame.breakOrBlend(first, second);
				lastKey=-1;
				kuvidGame.setPaused(false);
				kuvidGame.setInBlender(false);
			}
		}
		
		if(!kuvidGame.isPaused()) {
			if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) kuvidGame.moveShooter("right");
			else if(arg0.getKeyCode()==KeyEvent.VK_LEFT) kuvidGame.moveShooter("left");
			else if(arg0.getKeyCode()==KeyEvent.VK_D) kuvidGame.rotateShooter("right");
			else if(arg0.getKeyCode()==KeyEvent.VK_A) kuvidGame.rotateShooter("left");
			else if(arg0.getKeyCode()==KeyEvent.VK_C) kuvidGame.pickAtom();
			else if(arg0.getKeyCode()==KeyEvent.VK_UP) kuvidGame.shoot();
		}
		
		if(kuvidGame.isPaused()) {
			if(arg0.getKeyCode()==KeyEvent.VK_S) {
				System.out.print("Enter username: ");
				String username = scanner.next();
				kuvidGame.saveGame(username);				
			}
			else if(arg0.getKeyCode()==KeyEvent.VK_L) {
				System.out.print("Enter username: ");
				String username = scanner.next();
				kuvidGame.loadGame(username);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
