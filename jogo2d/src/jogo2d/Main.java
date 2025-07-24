package jogo2d;
import javax.swing.JFrame;
public class Main {
	public static void main (String [] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Guerreiro Ambiental");
		
		GamePanel gamepanel = new GamePanel();
		window.add(gamepanel);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamepanel.SetupGame();
		gamepanel.StartGameThread();
		
	}

}
