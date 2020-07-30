package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Entity;
import Entities.Peep;
import Entities.Tree;
import Misc.Graphics;
import Misc.Mat;
import States.WorldViewer;

public class World {
	
	public static int tid = 0;
	public int id = 0;
	public boolean visable = false;
	public double 
		x = 0, y = 0,
		difficulty = 100, temperature = 58.62, o2 = 100, size = 100;
	
	public Tile[][] tiles;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public double camX = 0, camY = 0, zoom = 9;
	
	public World(double difficulty) {
		this.difficulty = difficulty;
		temperature += difficulty-( Math.random()*(difficulty*2) );
		size += size * Math.random()*(difficulty/10);
		o2 = size * Math.random();
		double spread = .99;
		x = Math.pow(Math.random()*250,spread);
		y = Math.pow(Math.random()*250,spread);
		if(Math.random()>.5) x = -x;
		if(Math.random()>.5) y = -y;
		
		//Create tiles 
		tiles = new Tile[(int)size][(int)size];
		for(int x=0;x<tiles.length;x++)
			for(int y=0;y<tiles[x].length;y++)
				tiles[x][y] = new Tile(this,x,y);
		//Add Water
		if(Settings.enableWater)
			for(int x=0;x<tiles.length;x++)
				for(int y=0;y<tiles[x].length;y++)
					tiles[x][y].water();
		
		for(int z=0;z<3;z++) entities.add(new Peep(this));
		//followed = entities.get(0);
		for(int z=0;z<Math.random()*size;z++) entities.add(new Tree(this, null));
	}
	public void tick() {
		for(Entity e:entities) e.tick();
	}
	public void render(Graphics g) {
		//Render tiles
		if(Settings.trueRenderTiles) {
			renderTiles(g);
		} else {
			if(tileImage==null) {
				tileImage = new BufferedImage(32*(int)size, 32*(int)size, BufferedImage.TYPE_INT_ARGB);
				Graphics i = new Graphics((Graphics2D)tileImage.getGraphics());
				updateTiles(i);
			}
			g.drawImage(tileImage, camX, camY, zoom*(int)size, zoom*(int)size );
		}
		//Render entities
		for(Entity e:entities) {
			BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
			Graphics i = new Graphics((Graphics2D)image.getGraphics());
			e.render(i);
			g.drawImage(image, e.x*zoom+camX, e.y*zoom+camY, (e.width/100)*zoom, (e.height/100)*zoom);
		}
	}
	
	//Stores render of background unless updates
	public BufferedImage tileImage = null;
	public void updateTiles(Graphics g) {
		for(int x=0;x<tiles.length;x++)
			for(int y=0;y<tiles.length;y++) {
				BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
				Graphics i = new Graphics((Graphics2D)image.getGraphics());
				tiles[x][y].render(i);
				//g.drawImage(image, x*zoom+camX, y*zoom+camY, zoom, zoom);
				g.drawImage(image, x*32, y*32, 32, 32);
			}
	}
	
	//Renders all tiles in real time
	public void renderTiles(Graphics g) {
		for(int x=0;x<tiles.length;x++)
			for(int y=0;y<tiles.length;y++) {
				BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
				Graphics i = new Graphics((Graphics2D)image.getGraphics());
				tiles[x][y].render(i);
				if(Settings.trueRenderTiles)
					g.drawImage(image, x*zoom+camX, y*zoom+camY, zoom, zoom);
				else
					g.drawImage(image, x*32, y*32, 32, 32);
			}
	}
	
	public Tile getTile(int x, int y) {
		if(x>0 & y>0)
			if(x<tiles.length)
				if(y<tiles[x].length)
					return tiles[x][y];
		return null;
	}
	
	public String toString() {
		return "Difficulty: "+difficulty+" Temperature:"+temperature;
	}
}