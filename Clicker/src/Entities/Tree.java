package Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.Settings;
import Main.Tile;
import Main.World;
import Misc.Graphics;
import Misc.Mat;

public class Tree extends Entity{
	
	Location loc;
	Color color;
	
	public Tree(World world, Tree parent) {
		super(world);
		Tile tile;
		do {
			if(parent==null) {
				x = Math.random()*world.size;
				y = Math.random()*world.size;
			} else {
				double r = 1 * Math.random()*5;
				double angle = Math.toRadians( Math.random()*360 );
				x = r * Math.cos(angle) + parent.x;
				y = r * Math.sin(angle) + parent.y;
			}
			tile = world.getTile((int)(x+.5),(int)(y+.5));
			if(tile!=null)
				if(tile.isLand)
					break;
		}while(true);
		color = tile.getColor();
		int val = 25;
		color = new Color(
				Mat.inRange(color.getRed()+val, 0, 255),
				Mat.inRange(color.getGreen()+val, 0, 255),
				Mat.inRange(color.getBlue()+val, 0, 255) );
		if(Math.random() < .975) world.entities.add(new Tree(world,this));
	}
	
	
	public void tick() {
		if( x<0 ) x = 0;
		if( y<0 ) y = 0;
		if( x> world.size - 1) x = world.size-1;
		if( y> world.size - 1) y = world.size-1;
	}
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval(0, 0, 100, 100);
	}
}
