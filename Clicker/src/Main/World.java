package Main;

import java.util.ArrayList;

import Parts.Automat;
import Parts.Part;
import States.WorldViewer;

public class World {
	
	public boolean visable = false;
	public double x = 0, y = 0;
	public double difficulty = 100;
	public double temperature = 58.62;
	public double o2 = 100;
	public double size = 100;
	public ArrayList<Part> parts = new ArrayList<Part>();
	
	public World(double difficulty) {
		this.difficulty = difficulty;
		temperature += difficulty-( Math.random()*(difficulty*2) );
		size += size * Math.random()*(difficulty/5);
		o2 = size * Math.random();
		double spread = .99;
//		x = 250-(Math.random()*500);
//		y = 250-(Math.random()*500);
		x = Math.pow(Math.random()*250,spread);
		y = Math.pow(Math.random()*250,spread);
		if(Math.random()>.5) x = -x;
		if(Math.random()>.5) y = -y;
	}
	public void tick() {
		for(Part p:parts) p.tick();
		//Check progress
		Part p;
		p = getPart("Food");
		if(p!=null)
			if(p.stored>=5 & !hasPart("Automat")) {
				parts.add(new Automat(this));
				System.out.println("lkjhgfdsa");
			}
	}
	public Part getPart(String name) {
		for(Part p:parts)
			if(p.name.equals(name))
				return p;
		return null;
	}
	public boolean hasPart(String name) {
		for(Part p:parts)
			if(p.name.equals(name))
				return true;
		return false;
	}
	public String toString() {
		return "Difficulty: "+difficulty+" Temperature:"+temperature;
	}
}
