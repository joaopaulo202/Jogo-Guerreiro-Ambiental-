package jogo2d;

import monster.Fire;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		}
	public void SetFire() {
		gp.fire[0] = new Fire(gp);
		gp.fire[0].WorldX = gp.tileSize*23;
		gp.fire[0].WorldY = gp.tileSize*36;
		
		gp.fire[1] = new Fire(gp);
		gp.fire[1].WorldX = gp.tileSize*25;
		gp.fire[1].WorldY = gp.tileSize*39;
		
		gp.fire[2] = new Fire(gp);
		gp.fire[2].WorldX = gp.tileSize*10;
		gp.fire[2].WorldY = gp.tileSize*18;
		
		gp.fire[3] = new Fire(gp);
		gp.fire[3].WorldX = gp.tileSize*23;
		gp.fire[3].WorldY = gp.tileSize*12;
		
		gp.fire[4] = new Fire(gp);
		gp.fire[4].WorldX = gp.tileSize*33;
		gp.fire[4].WorldY = gp.tileSize*11;
		
		gp.fire[5] = new Fire(gp);
		gp.fire[5].WorldX = gp.tileSize*34;
		gp.fire[5].WorldY = gp.tileSize*15;
		
		gp.fire[6] = new Fire(gp);
		gp.fire[6].WorldX = gp.tileSize*30;
		gp.fire[6].WorldY = gp.tileSize*12;
		
		gp.fire[7] = new Fire(gp);
		gp.fire[7].WorldX = gp.tileSize*25;
		gp.fire[7].WorldY = gp.tileSize*15;
		
		gp.fire[8] = new Fire(gp);
		gp.fire[8].WorldX = gp.tileSize*18;
		gp.fire[8].WorldY = gp.tileSize*11;
		
		gp.fire[9] = new Fire(gp);
		gp.fire[9].WorldX = gp.tileSize*15;
		gp.fire[9].WorldY = gp.tileSize*36;
		
		gp.fire[10] = new Fire(gp);
		gp.fire[10].WorldX = gp.tileSize*30;
		gp.fire[10].WorldY = gp.tileSize*36;
		
		gp.fire[11] = new Fire(gp);
		gp.fire[11].WorldX = gp.tileSize*35;
		gp.fire[11].WorldY = gp.tileSize*30;
		
		gp.fire[12] = new Fire(gp);
		gp.fire[12].WorldX = gp.tileSize*35;
		gp.fire[12].WorldY = gp.tileSize*25;
		
		gp.fire[13] = new Fire(gp);
		gp.fire[13].WorldX = gp.tileSize*30;
		gp.fire[13].WorldY = gp.tileSize*20;
		
		gp.fire[14] = new Fire(gp);
		gp.fire[14].WorldX = gp.tileSize*25;
		gp.fire[14].WorldY = gp.tileSize*20;
		
		gp.fire[15] = new Fire(gp);
		gp.fire[15].WorldX = gp.tileSize*25;
		gp.fire[15].WorldY = gp.tileSize*25;
		
		gp.fire[16] = new Fire(gp);
		gp.fire[16].WorldX = gp.tileSize*18;
		gp.fire[16].WorldY = gp.tileSize*25;
		
	}

}
