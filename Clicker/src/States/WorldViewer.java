package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Main.World;
import Misc.Graphics;
import Misc.KeyManager;

public class WorldViewer extends State {
	public static World world;
	public static Entity selectedEntity;
	
	double camSpeed = 10;
	
	public WorldViewer() {
		x = 5; y = 25;
		width = 1200; height = 970;
	}
	public void tick() {
		if(world!=null) visible = true;
		else visible = false;
		if(selected & world!=null) {
			if(KeyManager.keys[KeyEvent.VK_UP]) { world.camY+=camSpeed; }
			if(KeyManager.keys[KeyEvent.VK_DOWN]) { world.camY-=camSpeed; }
			if(KeyManager.keys[KeyEvent.VK_LEFT]) { world.camX+=camSpeed; }
			if(KeyManager.keys[KeyEvent.VK_RIGHT]) { world.camX-=camSpeed; }
		}
	}
	
	public void render(Graphics g) {
		g.setFont( "Serif",Font.PLAIN,15 ); g.setColor(Color.white);
		if(selectedEntity!=null) {
			world.camX = selectedEntity.x*-world.zoom+width/2;
			world.camY = selectedEntity.y*-world.zoom+height/2;
		}
		world.render(g);
		g.drawString("Difficulty: "+Math.round(world.difficulty), 5, g.fontSize);
		g.drawString("Temperature: "+Math.round(world.temperature), 5, g.fontSize*2);
		g.drawString("Size: "+Math.round(world.size)+" "+Math.round(Math.sqrt(world.size)), 5, g.fontSize*3);
		g.drawString("O2: "+Math.round(world.o2), 5, g.fontSize*4);
	}
	
	public void scroll(int scrollAmount) {
		world.zoom -= scrollAmount/3;
		if(world.zoom<1) world.zoom = 1;
	}
	
	public void setWorld(World world) {
		WorldViewer.world = world;
		selectedEntity = null;
	}
}
