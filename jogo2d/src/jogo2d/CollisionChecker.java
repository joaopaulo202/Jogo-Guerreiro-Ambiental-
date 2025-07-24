package jogo2d;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	public CollisionChecker (GamePanel gp) {
		this.gp = gp;
	}
	public void CheckTile (Entity entity) {
		int EntityLeftWorldX = entity.WorldX + entity.solidArea.x;
		int EntityRightWorldX = entity.WorldX + entity.solidArea.x + entity.solidArea.width;
		int EntityTopWorldY = entity.WorldY + entity.solidArea.y;
		int EntityBottomWorldY = entity.WorldY + entity.solidArea.y + entity.solidArea.height;
		
		int EntityLeftCol = EntityLeftWorldX/gp.tileSize;
		int EntityRightCol = EntityRightWorldX/gp.tileSize;
		int EntityTopRow = EntityTopWorldY/gp.tileSize;
		int EntityBottomRow = EntityBottomWorldY/gp.tileSize;
		
		int TileNum1, TileNum2;
		
		switch (entity.Direction) {
		case "up":
			EntityTopRow = (EntityTopWorldY - entity.speed)/gp.tileSize;
			TileNum1 = gp.tileM.MapTileNum[EntityLeftCol][EntityTopRow];
			TileNum2 = gp.tileM.MapTileNum[EntityRightCol][EntityTopRow];
			if (gp.tileM.tile[TileNum1].collision == true || gp.tileM.tile[TileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			EntityBottomRow = (EntityBottomWorldY + entity.speed)/gp.tileSize;
			TileNum1 = gp.tileM.MapTileNum[EntityLeftCol][EntityBottomRow];
			TileNum2 = gp.tileM.MapTileNum[EntityRightCol][EntityBottomRow];
			if (gp.tileM.tile[TileNum1].collision == true || gp.tileM.tile[TileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			EntityLeftCol = (EntityLeftWorldX - entity.speed)/gp.tileSize;
			TileNum1 = gp.tileM.MapTileNum[EntityLeftCol][EntityTopRow];
			TileNum2 = gp.tileM.MapTileNum[EntityLeftCol][EntityBottomRow];
			if (gp.tileM.tile[TileNum1].collision == true || gp.tileM.tile[TileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			EntityRightCol = (EntityRightWorldX + entity.speed)/gp.tileSize;
			TileNum1 = gp.tileM.MapTileNum[EntityRightCol][EntityTopRow];
			TileNum2 = gp.tileM.MapTileNum[EntityRightCol][EntityBottomRow];
			if (gp.tileM.tile[TileNum1].collision == true || gp.tileM.tile[TileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
			}
		}
	public int CheckEntity(Entity entity, Entity[] target) {
		int index = 999;
		
		for (int i = 0; i < target.length; i++) {
			if (target[i] != null) {
				entity.solidArea.x = entity.WorldX + entity.solidArea.x;
				entity.solidArea.y = entity.WorldY + entity.solidArea.y;
				
				target[i].solidArea.x = target[i].WorldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].WorldY + target[i].solidArea.y;
				
				switch (entity.Direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					
					break;
				
				case "down": 
					entity.solidArea.y += entity.speed;
					break;
				
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				if (entity.solidArea.intersects (target[i].solidArea)) {
					if (target[i] != entity) {
						entity.collisionOn = true;
						index = i;
					}
				}
				entity.solidArea.x = entity.SolidAreaDefaultX;
				entity.solidArea.y = entity.SolidAreaDefaultY;
				target[i].solidArea.x = target[i].SolidAreaDefaultX;
				target[i].solidArea.y = target[i].SolidAreaDefaultY;	
			}
		}
		return index;
	}
	public void CheckPlayer(Entity entity) {
		entity.solidArea.x = entity.WorldX + entity.solidArea.x;
		entity.solidArea.y = entity.WorldY + entity.solidArea.y;
		
		gp.player.solidArea.x = gp.player.WorldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.WorldY + gp.player.solidArea.y;
		
		switch (entity.Direction) {
		case "up":
			entity.solidArea.y -= entity.speed;
			if (entity.solidArea.intersects (gp.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		
		case "down": 
			entity.solidArea.y += entity.speed;
			if (entity.solidArea.intersects (gp.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		
		case "left":
			entity.solidArea.x -= entity.speed;
			if (entity.solidArea.intersects (gp.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entity.solidArea.x -= entity.speed;
			if (entity.solidArea.intersects (gp.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		}
		
		entity.solidArea.x = entity.SolidAreaDefaultX;
		entity.solidArea.y = entity.SolidAreaDefaultY;
		gp.player.solidArea.x = gp.player.SolidAreaDefaultX;
		gp.player.solidArea.y = gp.player.SolidAreaDefaultY;	
	}

}
