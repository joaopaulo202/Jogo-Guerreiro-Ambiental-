package jogo2d;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entity.Player;
import tile.TileManager;
import java.util.*;
import entity.Entity;
import jogo2d.KeyHandler;
public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; 
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; 
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; 
    public final int screenHeight = tileSize * maxScreenRow;
    
  //configurações do mundo
  	public final int MaxWorldCol = 50;
  	public final int MaxWorldRow = 50;
  	

    //FPS
    int FPS = 60;
    
    //Sistema
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound Music = new Sound();
    Sound SE = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    
    Thread gameThread;
    
    //Entidade
    public Player player = new Player(this, keyH);
    public Entity fire[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();
    
    //Game State
    public int GameState;
    public final int TitleState = 0;
    public final int PlayState = 1;
    public final int GameOverState = 2;
    


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void SetupGame() {
    	//PlayMusic(0);
    	GameState = TitleState;
    	aSetter.SetFire();
    	player.SetDefaultValues(); 
    	ui.PlayTime = 70.0;
        ui.GameFinished = false;
        ui.CommandNum = 0;
        keyH.UpPressed    = false;
        keyH.DownPressed  = false;
        keyH.LeftPressed  = false;
        keyH.RightPressed = false;
        keyH.EnterPressed = false;
    }
    
    public void StartGameThread () {
		gameThread = new Thread(this);
		gameThread.start();
	}

	
	public void run() {
		double DrawInterval = 1000000000/FPS; //0,01666 segundos
		double Delta = 0;
		long LastTime = System.nanoTime ();
		long CurrentTime;
		long Timer = 0;
		int DrawCount = 0;
		
		while (gameThread != null) {
			CurrentTime = System.nanoTime ();
			Delta += (CurrentTime - LastTime) / DrawInterval;
			Timer += (CurrentTime - LastTime);
			LastTime = CurrentTime;
			if (Delta >= 1) {
				update();
				repaint();
				Delta--;
				DrawCount++;
			}
			if (Timer >= 1000000000) {
				
				DrawCount = 0;
				Timer = 0;
			}
		}
		
	}
	public void update() {
		 if (GameState == PlayState && (player.Life <= 0 || ui.PlayTime <= 0)) {
	            GameState = GameOverState;
	        }

		
		
		if (GameState == PlayState) {
			//jogador
			player.Update();
			
			//Fire
			for (int i = 0; i < fire.length; i++) {
				if (fire[i] != null) {
					fire[i].update();
				}
			}
		}
		
		}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//Title Screen
		if (GameState == TitleState) {
			ui.draw(g2);
		}
		
		else if (GameState == GameOverState) {
            ui.DrawGameOverScreen(g2);
            
        }

		
		
		else {
			//Tile
			tileM.draw(g2);  
			
			//jogador
			entityList.add(player);
			
			//fire
			for (int i = 0; i < fire.length; i++) {
				if (fire[i] != null) {
					entityList.add(fire[i]);
				}
			}
			
			
			//player.Draw(g2);
			
			
			
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.WorldY, e2.WorldY);
					return result;
				}
				
			});
			
			//desenha entidades
			for (int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			
			//entity list vazio
			entityList.clear();
			
			//UI
			ui.draw(g2);
			
			
		}
	}
	
	public void PlayMusic(int i) {
		Music.SetFile(i);
		Music.Play();
		Music.Loop();
	}
	
	public void StopMusic() {
		Music.Stop();
	}
	
	public void PlaySE(int i) {
		SE.SetFile(i);
		SE.Play();
	}
	
	public void RestartGame() {
	    // Reinicia o jogo
		
		SetupGame();
		PlayMusic(0);
		
		gameThread = new Thread(this);
	    gameThread.start();
	    GameState = PlayState;
	    
	}
 

}
