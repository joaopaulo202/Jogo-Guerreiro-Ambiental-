package monster;

import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.*;
import entity.Entity;
import jogo2d.GamePanel;

public class Fire extends Entity {
	public Fire(GamePanel gp) {
		super(gp);
		name = "Fire";
		speed = 0;
		MaxLife = 2; 
		Life = MaxLife; 
		solidArea.x = 1;
		solidArea.y = 2;
		solidArea.width = 40;
		solidArea.height = 30;
		SolidAreaDefaultX = solidArea.x;
		SolidAreaDefaultY = solidArea.y;
		GetImage();
	}
	public void GetImage() {
		try {
			left1 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(1).png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(2).png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(3).png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(4).png"));
			left5 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(1).png"));
			left6 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(2).png"));
			left7 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(3).png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(4).png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(1).png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(2).png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(3).png"));
			right5 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(4).png"));
			right6 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(1).png"));
			right7 = ImageIO.read(getClass().getResourceAsStream("/monster/fogo(2).png"));
		}
		catch(IOException e) {
			e.printStackTrace ();
		}
	}
	public void setAction() {
		ActionLockCounter++;
		if (ActionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(50) + 1;
			if (i <= 25) {
				Direction = "left";
			}
			if (i > 25 && i <= 50) {
				Direction = "right";
			}
			ActionLockCounter = 0;
		}
	}

}
