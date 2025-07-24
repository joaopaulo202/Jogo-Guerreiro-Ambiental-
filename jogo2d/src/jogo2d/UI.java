package jogo2d;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.awt.image.BufferedImage;

import object.OBJ_heart;
import object.SuperObject;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40,arial_80B;
	BufferedImage heart_full, heart_half, heart_blank;
	 
	double PlayTime = 70;
	DecimalFormat dFormat = new DecimalFormat("#0.0");
	public int CommandNum = 0;
	public boolean GameFinished = false;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
		//HUD de vida
		SuperObject heart = new OBJ_heart(gp);
		heart_full = heart.image; 
		heart_half = heart.image2; 
		heart_blank = heart.image3; 
	}
	
	public void draw(Graphics2D g2) {
		//os comandos que estão dentro do if (gp.GameState == gp.PlayState) estavam aqui abaixo.
		
		//os comandos que estão dentro do if (gp.GameState == gp.PlayState) estavam aqui acima
		this.g2 = g2;
		
		//tela de vencedor
		if (GameFinished == true) {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			String text;
			int textLength;
			int x;
			int y;
			
			text = "Você salvou o parque!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			text = "Parabéns!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*2);
			g2.drawString(text, x, y);
			gp.gameThread = null;
		}
		
		//Titulo do jogo
		if (gp.GameState == gp.TitleState) {
			DrawTitleScreen();
		}
		
		//Play State
		if (gp.GameState == gp.PlayState) {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			drawPlayerLife();
			
			//Tempo
			PlayTime -= (double)1/60;
			if (PlayTime < 0) {
				PlayTime = 0;
			}
			g2.drawString("Tempo: "+ dFormat.format(PlayTime), gp.tileSize*11, 65);
		}
	}
	public void DrawTitleScreen () {
		g2.setColor(new Color(70,120,80));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		// ✅ Adicionar instruções para o jogador
		g2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		g2.setColor(Color.white);

		String instr1 = "* Tecla Enter Para Iniciar o Jogo";
		String instr2 = "* As Teclas W A S D Movem o Personagem";
		String instr3 = "*Tecla Enter para atacar";
		String instr4 = "*Apague o Fogo Para Vencer"; 

		int yBase = gp.screenHeight - 100;  // altura base, ajuste se quiser
		int xCenter;

		xCenter = gp.screenWidth / 2 - (int) g2.getFontMetrics().getStringBounds(instr1, g2).getWidth() / 2;
		g2.drawString(instr1, xCenter, yBase);

		xCenter = gp.screenWidth / 2 - (int) g2.getFontMetrics().getStringBounds(instr2, g2).getWidth() / 2;
		g2.drawString(instr2, xCenter, yBase + 25);

		xCenter = gp.screenWidth / 2 - (int) g2.getFontMetrics().getStringBounds(instr3, g2).getWidth() / 2;
		g2.drawString(instr3, xCenter, yBase + 50);
		
		xCenter = gp.screenWidth / 2 - (int) g2.getFontMetrics().getStringBounds(instr1, g2).getWidth() / 2;
		g2.drawString(instr4, xCenter, yBase + 75);


		
		//Nome do titulo
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,75F));
		String text = "Guerreiro Ambiental";
		int x = GetXforCenteredText(text);
		int y = gp.tileSize*3;
		
		//cor da letra do titulo
		g2.setColor(Color.red);
		g2.drawString(text, x, y);
		
		//imagem de fundo do titulo
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.right1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		//menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		text = "Jogar";
		x = GetXforCenteredText(text);
		y += gp.tileSize*3.5;
		g2.drawString(text, x, y);
		if (CommandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
	}
	
	//X no centro
	public int GetXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	
	
	

	public void drawPlayerLife() {
		

	    int x = gp.tileSize/2;
	    int y = gp.tileSize/2;
	    int i = 0;

	    while(i < gp.player.MaxLife/2) {
	        g2.drawImage(heart_blank, x, y, null);
	        i++;
	        x += gp.tileSize;
	    }
	    x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        while (i < gp.player.Life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.Life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
}
	public void DrawGameOverScreen(Graphics2D g2) {
	    // Fundo escuro semi-transparente
	    g2.setColor(new Color(0, 0, 0, 220));
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

	    // Texto principal
	    g2.setFont(new Font("SansSerif", Font.PLAIN, 60));  
	    g2.setColor(Color.red);
	    String text = "FIM DE JOGO";

	    int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
	    int x = gp.screenWidth / 2 - textLength / 2;
	    int y = gp.screenHeight / 2;

	    g2.drawString(text, x, y);

	    g2.setFont(new Font("SansSerif", Font.PLAIN, 25));
	    g2.setColor(Color.white);
	    String retryText = "Pressione ENTER para jogar novamente";
	    int retryLength = (int) g2.getFontMetrics().getStringBounds(retryText, g2).getWidth();
	    int retryX = gp.screenWidth / 2 - retryLength / 2;
	    int retryY = y + 60;  // abaixo do "FIM DE JOGO"
	    g2.drawString(retryText, retryX, retryY);

	    // Parar o game loop e música
	    gp.gameThread = null;
	    gp.StopMusic();
	}



}
