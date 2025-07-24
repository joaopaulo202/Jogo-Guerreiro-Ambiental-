package object;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import jogo2d.GamePanel;
import jogo2d.UtilityTool;
public class SuperObject {

    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.WorldX + gp.player.ScreenX;
        int screenY = worldY - gp.player.WorldY + gp.player.ScreenY;

        if(worldX + gp.tileSize > gp.player.WorldX - gp.player.ScreenX &&
           worldX - gp.tileSize < gp.player.WorldX + gp.player.ScreenX &&
           worldY + gp.tileSize > gp.player.WorldY - gp.player.ScreenY &&
           worldY - gp.tileSize < gp.player.WorldY + gp.player.ScreenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}

