package Entities;

import java.awt.Color;

import Main.Tile;
import Main.World;
import Misc.Graphics;

public class Peep extends Entity{
	Location loc;
	
	public Peep(World world) {
		super(world);
		x = 50;
		y = 50;
	}
	
	public void tick() {
		wander();
		checkCollision();
	}
	public void wander() {
		if( moveToPoint(loc) ) loc = new Location( (Math.random()*10)+x-5, (Math.random()*10)+y-5 );
		if( Math.random()<.01 ) loc = new Location( (Math.random()*10)+x-5, (Math.random()*10)+y-5 );
		if( x<0 | y<0 | x> world.size - 1 | y> world.size - 1) loc = new Location( (Math.random()*10)+x-5, (Math.random()*10)+y-5 );
	
	}
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillOval(0, 0, 100, 100);
	}
}
