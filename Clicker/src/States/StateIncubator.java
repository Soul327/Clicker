package States;

import java.awt.Color;
import java.util.ArrayList;

import Misc.Graphics;
import Parts.Food;
import Parts.Part;

public class StateIncubator extends State{
	
	public StateIncubator() {
		x = 200;
		y = 20;
		width = 400;
		height = 600;
		name = "Creation";
	}
	
	public void render(Graphics g) {
		ArrayList<Part> parts = WorldViewer.world.parts;
		for(Part p:parts) {
			p.render(g,mx-x,my-y);
		}
	}
}
