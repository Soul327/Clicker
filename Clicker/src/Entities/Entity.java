package Entities;

import Main.World;
import Misc.Graphics;
import Misc.Mat;

public abstract class Entity {
	public double x = 1, y = 1, width = 100, height = 100, speed = 10;
	World world;
	
	public Entity(World world) {
		this.world = world;
	}
	
	public void tick() {}
	public void render(Graphics g) {}
	
	public boolean moveToPoint(Location loc) {
		if(loc == null) return true;
		double angleToPoint = Mat.getAngle(x, y, loc.x, loc.y);
		double radiusToPoint = Mat.distance(x, y, loc.x, loc.y);
		if(radiusToPoint<speed/60) {
			x = loc.x;
			y = loc.y;
			return true;
		} else {
			x += speed/60 * Math.cos(angleToPoint);
			y += speed/60 * Math.sin(angleToPoint);
			return false;
		}
	}
}
class Location {
	public double x,y;
	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}
}