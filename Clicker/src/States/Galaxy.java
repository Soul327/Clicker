package States;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import Main.World;
import Misc.Graphics;
import Misc.Mat;

public class Galaxy extends State{
	
	String galaxyName = "A1";
	ArrayList<World> worlds = new ArrayList<World>();
	
	public Galaxy() {
		//Create new galaxy
		double numOfWorlds = Math.random()*10+20;
		for(int z=0;z<numOfWorlds;z++) worlds.add(new World(z));
		x = 500; y = 20;
		width = 500; height = 500;
		name = "Galaxy Viewer: "+galaxyName;
		System.out.println(worlds.size());
		
		WorldViewer.world = worlds.get( (int)(Math.random()*worlds.size()) );
	}
	public void render(Graphics g) {
		mx = mx-x;
		my = my-y;
		int z = 0;
		for(World w:worlds) {
			g.setColor(Color.white);
			g.fillCenterCircle(w.x+width/2, w.y+height/2, 10);
			if(WorldViewer.world == w) {
				g.setColor(Color.green.darker());
				g.drawCenterCircle(w.x+width/2, w.y+height/2, 10);
				g.drawCenterCircle(w.x+width/2, w.y+height/2, 12);
				g.drawCenterCircle(w.x+width/2, w.y+height/2, 14);
			}
			double dis = Mat.distance(w.x, w.y, mx, my);
			if(dis < 20) WorldViewer.world = w;
			
			g.setFont( "Serif",Font.PLAIN,15 ); g.setColor(Color.white);
			g.drawString(dis+"",5,15*(z+++1));
			g.drawCenterCircle(mx, my, 5);
		}
	}
	
	public String toString() {
		String re = "";
		for(World w:worlds) {
			re += w.toString()+"\n";
		}
		return re;
	}
}
