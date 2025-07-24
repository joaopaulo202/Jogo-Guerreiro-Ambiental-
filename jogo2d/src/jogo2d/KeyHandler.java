package jogo2d;
//testando enter
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	GamePanel gp;
	public boolean UpPressed, DownPressed, LeftPressed, RightPressed, EnterPressed;
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (gp.GameState == gp.TitleState) {
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.CommandNum == 0) {
					gp.GameState = gp.PlayState;
					gp.PlayMusic(0);
				}
			}
	        
	        
		}
		
		else if (gp.GameState == gp.PlayState) {
			if (code == KeyEvent.VK_W) {
				UpPressed = true;
			}
	        if (code == KeyEvent.VK_S) {
	        	DownPressed = true;
			}
	        if (code == KeyEvent.VK_A) {
	        	LeftPressed = true;
			}
	        if (code == KeyEvent.VK_D) {
	        	RightPressed = true;
		}
	        if (code == KeyEvent.VK_ENTER) {
	        	EnterPressed = true;
		}
		}
		
		if (gp.GameState == gp.GameOverState) {
			if (code == KeyEvent.VK_ENTER) {
				
				gp.RestartGame();
					
		}
	        
	        
		}
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (gp.GameState == gp.PlayState) {
			if (code == KeyEvent.VK_W) {
				UpPressed = false;
			}
	        if (code == KeyEvent.VK_S) {
	        	DownPressed = false;
			}
	        if (code == KeyEvent.VK_A) {
	        	LeftPressed = false;
			}
	        if (code == KeyEvent.VK_D) {
	        	RightPressed = false;
		}
	        if (code == KeyEvent.VK_ENTER) {
	        	EnterPressed = false;
		}
		}
		
	}

}
