package States;

import java.awt.Color;

import Misc.Graphics;
import Parts.Food;

public class StateIncubator extends State{
	
	Food food = new Food();
	
	public StateIncubator() {
		x = 200;
		y = 20;
		width = 300;
		height = 600;
		name = "The Incubator";
	}
	public void render(Graphics g) {
		food.render(g);
	}
}
