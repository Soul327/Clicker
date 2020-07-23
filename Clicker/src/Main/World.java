package Main;

import java.util.ArrayList;

import Parts.Part;

public class World {
	
	public double x = 0, y = 0;
	double difficulty = 100;
	public double temperature = 58.62;
	ArrayList<Part> parts = new ArrayList<Part>();
	
	public World(double difficulty) {
		this.difficulty = difficulty;
		temperature += difficulty-( Math.random()*(difficulty*2) );
		double spread = .99;
//		x = 250-(Math.random()*500);
//		y = 250-(Math.random()*500);
		x = Math.pow(Math.random()*250,spread);
		y = Math.pow(Math.random()*250,spread);
		if(Math.random()>.5) x = -x;
		if(Math.random()>.5) y = -y;
	}
	public String toString() {
		return "Difficulty: "+difficulty+" Temperature:"+temperature;
	}
}
