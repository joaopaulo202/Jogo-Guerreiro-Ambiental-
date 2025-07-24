package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import javax.imageio.ImageIO;
import jogo2d.GamePanel;
import jogo2d.UtilityTool;



public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int MapTileNum [] [];

	public TileManager(GamePanel gp) {
		MapTileNum = new int[gp.MaxWorldCol] [gp.MaxWorldRow];
		loadMap("/maps/world01.txt");
	    this.gp = gp;
	    tile = new Tile[10];

	    getTileImage();
	    loadMap("/maps/world01.txt") ;  
	}

	public void getTileImage() {
	    
	        setup(1,"agua", true);
	        setup(2,"arvore", true);
	        setup(3,"predio2", true);
	        setup(4,"grama", false);
	        setup(5,"estrada", true);
	        setup(6,"estradaver", true);
	}
	
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
		    tile[index].collision = collision;
		} 
		
		catch (IOException e) {
	        e.printStackTrace();
	       }
	}
	
	public void loadMap(String filePath) {

	    try {
	        InputStream is = getClass().getResourceAsStream(filePath);
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));

	        int col = 0;
	        int row = 0;

	        while (col < gp.MaxWorldCol && row < gp.MaxWorldRow) {
	            String line = br.readLine();

	            while (col < gp.MaxWorldCol) {
	                String numbers[] = line.split(" ");

	                int num = Integer.parseInt(numbers[col]);

	                MapTileNum[col][row] = num;
	                col++;
	            }
	            if (col == gp.MaxWorldCol) {
	                col = 0;
	                row++;
	            }
	        }
	        br.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public void draw(Graphics2D g2) {
	    int worldCol = 0;
	    int worldRow = 0;
	    

	    while (worldCol < gp.MaxWorldCol && worldRow < gp.MaxWorldRow) {
	        int tileNum = MapTileNum[worldCol][worldRow];
	        int WorldX = worldCol * gp.tileSize;
	        int WorldY = worldRow * gp.tileSize;
	        int ScreenX = WorldX - gp.player.WorldX + gp.player.ScreenX;
	        int ScreenY = WorldY - gp.player.WorldY + gp.player.ScreenY;
	        
	        if (WorldX + gp.tileSize > gp.player.WorldX - gp.player.ScreenX &&
	        	WorldX - gp.tileSize < gp.player.WorldX + gp.player.ScreenX &&
	        	WorldY + gp.tileSize > gp.player.WorldY - gp.player.ScreenY &&
	        	WorldY - gp.tileSize < gp.player.WorldY + gp.player.ScreenY) {
	        	g2.drawImage(tile[tileNum].image, ScreenX, ScreenY, null);
		        }
	        worldCol++;

	        if (worldCol == gp.MaxWorldCol) {
	            worldCol = 0;
	            worldRow++;
	            
	        }
	    }
	}


}
