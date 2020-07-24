package States;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import Main.World;
import Misc.Graphics;
import Misc.Mat;
import Misc.MouseManager;
import Parts.Food;

public class Galaxy extends State{
	
	String galaxyName = "A1";
	ArrayList<World> worlds = new ArrayList<World>();
	
	public Galaxy() {
		//Create new galaxy
		double numOfWorlds = Math.random()*10+20;
		for(int z=0;z<numOfWorlds;z++) worlds.add(new World(z+1));
		x = 600; y = 20;
		width = 500; height = 500;
		name = "Galaxy Viewer: "+galaxyName;
		System.out.println(worlds.size());
		
		WorldViewer.world = worlds.get(0);
		worlds.get(0).parts.add(new Food(worlds.get(0)));
		
	}
	
	public void tick() {
		for(World w:worlds) w.tick();
	}
	public void render(Graphics g) {
		mx = mx-x;
		my = my-y;
		double closeDis = Double.MAX_VALUE;
		World close = null;
		for(World w:worlds) {
			g.setColor(new Color(100,100,100));
			double drawSize = w.size/20;
			if(drawSize<2) drawSize = 2;
			g.fillCenterCircle(w.x+width/2, w.y+height/2, drawSize);
			if(WorldViewer.world == w) {
				g.setColor(Color.green.darker());
				g.drawCenterCircle(w.x+width/2, w.y+height/2, drawSize);
				g.drawCenterCircle(w.x+width/2, w.y+height/2, drawSize+2);
				g.drawCenterCircle(w.x+width/2, w.y+height/2, drawSize+4);
			}
			double dis = Mat.distance(w.x+width/2, w.y+height/2, mx, my);
			if(dis < closeDis) {
				close = w;
				closeDis = dis;
			}
			
			g.drawCenterCircle(mx, my, 5);
		}
		if(MouseManager.leftPressed & selected) WorldViewer.world = close;
	}
	
	public String toString() {
		String re = "";
		for(World w:worlds) {
			re += w.toString()+"\n";
		}
		return re;
	}
}
