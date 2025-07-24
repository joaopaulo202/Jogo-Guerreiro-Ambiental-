package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import jogo2d.GamePanel;

public class Entity {
	GamePanel gp;
	public int WorldX, WorldY;
	public int speed;
	public BufferedImage left1,left2,left3,left4,left5,left6,left7,right1,right2,right3,right4,right5,right6,right7;
	public BufferedImage attackup, attackdown1, attackdown2, attackleft1, attackleft2, attackright1, attackright2;
	public String Direction = "down";
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackarea = new Rectangle(0, 0, 0, 0);
	public boolean collisionOn = false;
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	public int SolidAreaDefaultX, SolidAreaDefaultY;
	public int MaxLife;
	public int Life;
	public int ActionLockCounter = 0;
	public boolean invincible = false;
	public int invinciblecounter = 0;
	boolean attacking = false;
	
	
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void SetAction() {}
	public void update() {
		SetAction();
		collisionOn = false;
		gp.cChecker.CheckTile(this);
		gp.cChecker.CheckEntity(this, gp.fire);
		
		if (collisionOn == false) {
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
		if (invincible == true) {
			invinciblecounter++;
			if (invinciblecounter > 40) {
				invincible = false;
				invinciblecounter = 0;
			}
		}
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int ScreenX = WorldX - gp.player.WorldX + gp.player.ScreenX;
		int ScreenY = WorldY - gp.player.WorldY + gp.player.ScreenY;
		
		if (WorldX + gp.tileSize > gp.player.WorldX - gp.player.ScreenX &&
	        	WorldX - gp.tileSize < gp.player.WorldX + gp.player.ScreenX &&
	        	WorldY + gp.tileSize > gp.player.WorldY - gp.player.ScreenY &&
	        	WorldY - gp.tileSize < gp.player.WorldY + gp.player.ScreenY) {
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
			g2.drawImage(image,ScreenX, ScreenY, gp.tileSize, gp.tileSize, null);
		}
	}

}
