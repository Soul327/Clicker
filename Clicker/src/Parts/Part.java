package Parts;

import java.awt.Color;

import Misc.Graphics;

public abstract class Part {
	
	int x=0,y=0,width =300,height = 30;
	int maxStored = 100; double stored = 75;
	double maxTime = 100; double time = 75;
	
	public void render(Graphics g) {
		//g.setColor(Color.orange); g.fillRect(0, 0, width, height);
		
		g.setColor(Color.DARK_GRAY); g.fillRect(5, 5, 290, 40); //Backgound
		
		//Produce button
		g.setColor(Color.gray); g.fillRect(10, 10, 55, 20);
		g.setColor(Color.white); g.drawRect(10, 10, 55, 20);
		g.setColor(Color.white); g.drawOutlinedString("Heat", 47-g.getStringLength("Heat"), 25);
		
		//Extra button
		g.setColor(new Color(230,230,230)); g.fillRect(5, 35, width-10, 10);
		
		//Storage view
		g.setColor(Color.red); g.fillRect(70, 10, (stored/maxStored)*(220), 13);
		g.setColor(Color.white); g.drawRect(70, 10, 220, 13);
		
		//Production view
		g.setColor(Color.white); g.fillRect(70, 25, (time/maxTime)*(220), 5);
		g.setColor(Color.gray); g.drawRect(70, 25, 220, 5);
		
		//
		
		//		g.setColor(Color.WHITE); g.fillRect(10, 10, 20, 20);
		//		g.setColor(Color.WHITE); g.fillRect(35, 10, 20, 20);
	}
}
