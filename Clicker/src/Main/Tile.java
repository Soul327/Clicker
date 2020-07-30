package Main;

import java.awt.Color;

import Misc.Graphics;
import Misc.Mat;

public class Tile{
	public boolean isLand = true;
	Color color = new Color(0,100,0);
	Tile[][] adjacent = new Tile[3][3];
	World world;
	int tileX,tileY;
	
	public Tile(World world,int tileX, int tileY) {
		this.world = world;
		this.tileX = tileX;
		this.tileY = tileY;
		getAdjacent();
		if(world.temperature<32 ) color = new Color(200,200,200);//Grass
		if(world.temperature>100) color = new Color(238,212,173);//Sand
		if(world.temperature>200) color = new Color(100,100,100);//Death
		color = getColor();
		
	}
	public void water() {
		//Become water?
		double chance = .0001;
		getAdjacent();
		for(int x=0;x<adjacent.length;x++)
			for(int y=0;y<adjacent[x].length;y++)
				if(adjacent[x][y]!=null) {
					if(!adjacent[x][y].isLand)
						chance += .3;
				}
		if(Math.random()<chance) isLand = false;
	}
	public void getAdjacent() {
		adjacent = new Tile[3][3];
		for(int x=0;x<world.tiles.length;x++)
			for(int y=0;y<world.tiles[x].length;y++) {
				Tile tile = world.tiles[x][y];
				if(tile != null) {
					if(x==tileX-1 & y==tileY-1) adjacent[0][0] = tile;
					if(x==tileX   & y==tileY-1) adjacent[1][0] = tile;
					if(x==tileX+1 & y==tileY-1) adjacent[2][0] = tile;
					
					if(x==tileX-1 & y==tileY  ) adjacent[0][1] = tile;
					//This is this tile
					if(x==tileX+1 & y==tileY  ) adjacent[2][1] = tile;
					
					if(x==tileX-1 & y==tileY+1) adjacent[0][2] = tile;
					if(x==tileX   & y==tileY+1) adjacent[1][2] = tile;
					if(x==tileX+1 & y==tileY+1) adjacent[2][2] = tile;
				}
			}
	}
	public Color getColor() {
		int amount = 0;
		double advR = 0, advG = 0, advB = 0;
		for(int x=0;x<adjacent.length;x++)
			for(int y=0;y<adjacent.length;y++) {
				Tile tile = adjacent[x][y];
				if(adjacent[x][y]!=null) {
					amount++;
					advR += tile.color.getRed();
					advG += tile.color.getGreen();
					advB += tile.color.getBlue();
				}
			}
		if(amount>0) {
			advR/=amount; advG/=amount; advB/=amount;
			double change = 5;
			advR += (int)( change - (Math.random()*(change*2)) );
			advG += (int)( change - (Math.random()*(change*2)) );
			advB += (int)( change - (Math.random()*(change*2)) );
			advR = Mat.inRange(advR, 0, 255);
			advG = Mat.inRange(advG, 0, 255);
			advB = Mat.inRange(advB, 0, 255);
			return new Color((int)advR,(int)advG,(int)advB);
		}
		return color;
	}
	public void render(Graphics g) {
		if(isLand) {
			g.setColor(color);
		}else {
			g.setColor(new Color(128, 197, 222));
		}
		g.fillRect(0, 0, 32, 32);
		//g.setColor(Color.gray); g.drawRect(1, 1, 30, 30);
		//g.setColor(Color.white); g.drawRect(0, 0, 32, 32);
	}
}