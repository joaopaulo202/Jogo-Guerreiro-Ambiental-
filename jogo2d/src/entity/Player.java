package entity;
//testando gamefinished
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import jogo2d.GamePanel;
import jogo2d.KeyHandler;


public class Player extends Entity {
	GamePanel gp;
	KeyHandler KeyH;
	public final int ScreenX;
	public final int ScreenY;
	
	
	//player status
	
	
	
	public Player (GamePanel gp, KeyHandler KeyH) {
		super(gp);
		this.gp = gp;
		this.KeyH = KeyH;
		ScreenX = gp.screenWidth/2 - (gp.tileSize/2);
		ScreenY = gp.screenHeight/2 - (gp.tileSize/2);
		SetDefaultValues ();
		GetPlayerImage();
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		GetAttackImage();
		attackarea.width = 36;
		attackarea.height = 36;
		
		
	}
	public void SetDefaultValues () {
		WorldX = gp.tileSize * 23 ;
		WorldY = gp.tileSize * 21;
		speed = 4;
		Direction = "down";
		MaxLife = 6;
		Life = MaxLife;
	}
	
	
	
	
	
	public void GetPlayerImage() {
		try {
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/andar1in.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/andar2in.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/andar3in.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/andar4in.png"));
			left5 = ImageIO.read(getClass().getResourceAsStream("/player/andar5in.png"));
			left6 = ImageIO.read(getClass().getResourceAsStream("/player/andar6in.png"));
			left7 = ImageIO.read(getClass().getResourceAsStream("/player/andar7in.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/andar1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/andar2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/andar3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/andar4.png"));
			right5 = ImageIO.read(getClass().getResourceAsStream("/player/andar5.png"));
			right6 = ImageIO.read(getClass().getResourceAsStream("/player/andar6.png"));
			right7 = ImageIO.read(getClass().getResourceAsStream("/player/andar7.png"));
		} 
		catch(IOException e) {
			e.printStackTrace ();
		}
	}
	
	public void GetAttackImage() {
		try {
			attackup = ImageIO.read(getClass().getResourceAsStream("/player/attackup.png"));
			attackleft1 = ImageIO.read(getClass().getResourceAsStream("/player/attackleft1.png"));
			attackleft2 = ImageIO.read(getClass().getResourceAsStream("/player/attackleft2.png"));
			attackright1 = ImageIO.read(getClass().getResourceAsStream("/player/attackright1.png"));
			attackright2 = ImageIO.read(getClass().getResourceAsStream("/player/attackright2.png"));
			attackdown1 = ImageIO.read(getClass().getResourceAsStream("/player/attackdown1.png"));
			attackdown2 = ImageIO.read(getClass().getResourceAsStream("/player/attackdown2.png"));
		}
		catch(IOException e) {
			e.printStackTrace ();
		}
	}
	
	public void Update() {
		if (attacking == true) {
			Attacking();
		}
		else if (KeyH.UpPressed == true || KeyH.DownPressed == true || KeyH.LeftPressed == true || KeyH.RightPressed == true || KeyH.EnterPressed == true ) {
			if (KeyH.UpPressed == true) {
			     Direction = "up";
				 
				 }
			else if (KeyH.DownPressed == true) {
				 Direction = "down";
				 
				 }
			else if (KeyH.LeftPressed == true) {
				 Direction = "left";
				 
				 }
			else if (KeyH.RightPressed == true) {
				 Direction = "right"; 
				 }
			else if (KeyH.EnterPressed == true) {
				 attacking = true; 
				 }
			
			//Check Tile Collision
			collisionOn = false;
			gp.cChecker.CheckTile(this);
			
			//Check fire Collision
			int fireIndex = gp.cChecker.CheckEntity(this, gp.fire);
			ContactFire(fireIndex);
			
			
			//Se a colisão for false, o player pode se mover
			if (collisionOn == false && KeyH.EnterPressed == false) {
				switch(Direction) {
				case "up":
					WorldY -= speed;
					break;
				case "down":
					WorldY += speed;
					break;
				case "left":
					WorldX -= speed;
					break;
				case "right":
					WorldX += speed;
					break;
					
				}
			}
			gp.keyH.EnterPressed = false;
			
			spriteCounter++;
			if (spriteCounter > 12) {
				 if (spriteNum == 1) {
					spriteNum = 2;
					 }
				 else if (spriteNum == 2) {
			    	 spriteNum = 3;
					 }
				 else if (spriteNum == 3) {
			    	 spriteNum = 4;
				 }
				 else if (spriteNum == 4) {
			    	 spriteNum = 5;
				 }
				 else if (spriteNum == 5) {
			    	 spriteNum = 6;
				 }
				 else if (spriteNum == 6) {
			    	 spriteNum = 7;
				 }
				 else if (spriteNum == 7) {
			    	 spriteNum = 1;
				 }
			     spriteCounter = 0;
				 }
		}
		if (invincible == true) {
			invinciblecounter++;
			if (invinciblecounter > 60) {
				invincible = false;
				invinciblecounter = 0;
			}
		}
	 }
	public void Attacking() {
		spriteCounter++;
		if (spriteCounter <= 5) {
			spriteNum = 1;
		}
		if (spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			
			//salvando WorldX, Y, solidArea originais
			int currentWorldX = WorldX;
			int currentWorldY = WorldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			//ajustando WorldX, Y para attackarea
			switch(Direction) {
			case "up": WorldY -= attackarea.height; break;
			case "down": WorldY += attackarea.height; break;
			case "left": WorldX -= attackarea.width; break;
			case "right": WorldX += attackarea.width; break;
			}
			
			solidArea.width = attackarea.width;
			solidArea.height = attackarea.height;
			
			int fireIndex = gp.cChecker.CheckEntity(this, gp.fire);
			DamageFire(fireIndex);
			
			WorldX = currentWorldX;
			WorldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if (spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	
	
	
	public void ContactFire(int i) {
		if (i != 999) {
			if (invincible == false) {
				gp.PlaySE(1);
				Life -= 1;
				invincible = true;
			}
		}
	}
	
	public void DamageFire (int i) {
		if (i != 999) {
			if (gp.fire[i].invincible == false) {
				gp.PlaySE(2);
				gp.fire[i].Life -= 1;
				gp.fire[i].invincible = true;
				if (gp.fire[i].Life <= 0) {
					gp.fire[i] = null;
					
				}
				}
		}
		boolean fogos = true;
	    for (Entity fire : gp.fire) {
	        if (fire != null && fire.Life > 0) {
	            fogos = false;
	            break;
	        }
	    }
	    if (fogos) {
	        gp.ui.GameFinished = true;
	        gp.StopMusic();
	    }
	}
		
	public void Draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(Direction) {
		case "up":
			if (attacking == false) {
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
				}
				if (spriteNum == 3) {
					image = right3;
				}
				if (spriteNum == 4) {
					image = right4;
				}
				if (spriteNum == 5) {
					image = right5;
				}
				if (spriteNum == 6) {
					image = right6;
				}
				if (spriteNum == 7) {
					image = right7;
				}
			}
			if (attacking == true) {
				if (spriteNum == 1) {
					image = attackup;
				}
				if (spriteNum == 2) {
					image = attackup;
				}
				if (spriteNum == 3) {
					image = attackup;
				}
				if (spriteNum == 4) {
					image = attackup;
				}
				if (spriteNum == 5) {
					image = attackup;
				}
				if (spriteNum == 6) {
					image = attackup;
				}
				if (spriteNum == 7) {
					image = attackup;
				}
			}
			break;
	    case "down":
	    	if (attacking == false) {
	    		if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
				}
				if (spriteNum == 3) {
					image = right3;
				}
				if (spriteNum == 4) {
					image = right4;
				}
				if (spriteNum == 5) {
					image = right5;
				}
				if (spriteNum == 6) {
					image = right6;
				}
				if (spriteNum == 7) {
					image = right7;
				}
	    	}
			if (attacking == true) {
				if (spriteNum == 1) {
					image = attackdown1;
				}
				if (spriteNum == 2) {
					image = attackdown2;
				}
				if (spriteNum == 3) {
					image = attackdown1;
				}
				if (spriteNum == 4) {
					image = attackdown2;
				}
				if (spriteNum == 5) {
					image = attackdown1;
				}
				if (spriteNum == 6) {
					image = attackdown2;
				}
				if (spriteNum == 7) {
					image = attackdown1;
				}
			}
			break;
		case "left":
			if (attacking == false) {
				if (spriteNum == 1) {
					image = left1;
				}
				if (spriteNum == 2) {
					image = left2;
				}
				if (spriteNum == 3) {
					image = left3;
				}
				if (spriteNum == 4) {
					image = left4;
				}
				if (spriteNum == 5) {
					image = left5;
				}
				if (spriteNum == 6) {
					image = left6;
				}
				if (spriteNum == 7) {
					image = left7;
				}
				
			}
			if (attacking == true) {
				if (spriteNum == 1) {
					image = attackleft1;
				}
				if (spriteNum == 2) {
					image = attackleft2;
				}
				if (spriteNum == 3) {
					image = attackleft1;
				}
				if (spriteNum == 4) {
					image = attackleft2;
				}
				if (spriteNum == 5) {
					image = attackleft1;
				}
				if (spriteNum == 6) {
					image = attackleft2;
				}
				if (spriteNum == 7) {
					image = attackleft1;
				}
			}
			break;
		case "right":
			if (attacking == false) {
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
				}
				if (spriteNum == 3) {
					image = right3;
				}
				if (spriteNum == 4) {
					image = right4;
				}
				if (spriteNum == 5) {
					image = right5;
				}
				if (spriteNum == 6) {
					image = right6;
				}
				if (spriteNum == 7) {
					image = right7;
				}
			}
			if (attacking == true) {
				if (spriteNum == 1) {
					image = attackright1;
				}
				if (spriteNum == 2) {
					image = attackright2;
				}
				if (spriteNum == 3) {
					image = attackright1;
				}
				if (spriteNum == 4) {
					image = attackright2;
				}
				if (spriteNum == 5) {
					image = attackright1;
				}
				if (spriteNum == 6) {
					image = attackright2;
				}
				if (spriteNum == 7) {
					image = attackright1;
				}
			}
			break;
		}
		g2.drawImage (image, ScreenX, ScreenY, gp.tileSize, gp.tileSize, null);
		
	}
	
	

}
