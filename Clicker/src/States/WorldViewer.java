package States;

import java.awt.Color;
import java.awt.Font;

import Main.World;
import Misc.Graphics;

public class WorldViewer extends State {
	//This is static so it can be set and accessed with other functions such as stateincubator
	public static World world;
	
	public WorldViewer() {
		x = 0; y = 20;
		width = 200; height = 100;
	}
	public void tick() {
		if(world!=null) visible = true;
		else visible = false;
	}
	
	public void render(Graphics g) {
		g.setFont( "Serif",Font.PLAIN,15 ); g.setColor(Color.white);
		g.drawString("Difficulty: "+Math.round(world.difficulty), 5, g.fontSize);
		g.drawString("Temperature: "+Math.round(world.temperature), 5, g.fontSize*2);
		g.drawString("Size: "+Math.round(world.size), 5, g.fontSize*3);
		g.drawString("O2: "+Math.round(world.o2), 5, g.fontSize*4);
	}
}
