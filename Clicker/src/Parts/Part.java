package Parts;

import java.awt.Color;
import java.awt.Font;

import Main.World;
import Misc.Graphics;
import Misc.MouseManager;

public abstract class Part {
	
	int x=0,y=0,width = 300,height = 30;
	int maxStored = 100; public double stored = 0;
	double maxTime = 60; double time = 0;
	public String name = "BLANK";
	Color barColor = Color.white;
	World world;
	public int workers = 0;
	
	public Part(World world) {
		this.world = world;
	}
	public void tick() {
		tickTime();
		tickWorkers();
	}
	public void tickWorkers() {
		if(stored<maxStored) {
			stored += workers*1/maxTime;
			if(stored>maxStored) stored = maxStored;
		}
	}
	public void tickTime() {
		if(time>0) {
			time++;
			if(time>maxTime) {
				time = 0;
				produce(1);
			}
		}
	}
	
	public void produce(double amount) {
		stored += amount;
	}
	
	public void render(Graphics g,int mx, int my) {
		g.setColor(Color.DARK_GRAY); g.fillRect(x+5, y+5, 290, 40); //Backgound
		
		//Produce button
		g.setColor(new Color(100,100,100));
		if(mx>x+10 & mx<x+60 & my>y+10 & my<y+30) {
			if(MouseManager.leftPressed & stored<maxStored & time==0) { 
				g.setColor(new Color(250,250,250));
				time++;
			} else
				g.setColor(new Color(125,125,125));
		}
		g.fillRect(x+10, y+10, 55, 20);
		g.setColor(Color.white);
		g.drawRect(x+10, y+10, 55, 20);
		g.setColor(Color.white);
		g.drawOutlinedString(name, x+10+27.5-g.getStringLength(name)/2, y+25);
		
		//Extra button
		g.setColor(new Color(230,230,230)); g.fillRect(x+5, y+35, width-10, 10);
		
		//Storage view
		g.setFont( "Serif",Font.PLAIN,10 );
		g.setColor(barColor); g.fillRect(x+70, y+10, (stored/maxStored)*(220), 13);
		g.setColor(Color.white); g.drawRect(x+70, y+10, 220, 13);
		g.drawOutlinedString(stored+"/"+maxStored, x+75, y+10+g.fontSize);
		
		//Production view
		g.setColor(Color.white); g.fillRect(x+70, y+25, ((double)time/maxTime)*(220), 5);
		g.setColor(Color.gray); g.drawRect(x+70, y+25, 220, 5);

		g.setColor(Color.WHITE); g.fillRect(x+300, y+10, 20, 20);
		g.setColor(Color.WHITE); g.fillRect(x+330, y+10, 20, 20);
	}
}
