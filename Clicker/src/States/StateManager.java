package States;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Main.Main;
import Misc.Graphics;
import Misc.KeyManager;
import Misc.MouseManager;

public class StateManager {
	ArrayList<State> states = new ArrayList<State>();
	
	public StateManager() {
		State s;
		
		s = new StateConsole();
		//s.visible = true;
		s.x = 0;
		s.y = 20;
		s.width = 200;
		s.height = 200;
		states.add(s);
		
		s = new StateIncubator();
		s.visible = true;
		states.add(s);
		
		s = new Galaxy();
		s.visible = true;
		states.add(s);
		
		s = new WorldViewer();
		states.add(s);
	}
	public void tick() {
		for(State s:states) {
			s.stick();
		}
		if(KeyManager.keyRelease(KeyEvent.VK_SPACE)) allStatesVisible();
	}
	
	boolean mouseHeld = false;
	int mouseHeldX = 0,mouseHeldY = 0;
	State heldState = null;
	public void render(Graphics g) {
		for(State s:states) {
			if(s.visible) {
				BufferedImage image = new BufferedImage(s.width, s.height, BufferedImage.TYPE_INT_ARGB);
				
				Graphics i = new Graphics((Graphics2D)image.getGraphics());
				s.srender(i);
				//s.debugRender(i);
				
				if(s.selected) i.setColor(Color.white);
				else i.setColor(Color.gray);
				i.drawRect(0, 0, s.width-1, s.height-1);
				
				g.drawImage(image, s.x, s.y, s.width, s.height);
				
				g.setColor(Color.gray); g.fillRect(s.x, s.y-20, s.width, 20);//Color Bar
				g.setColor(Color.red); g.fillOval(s.x+s.width-17.5, s.y+2.5-20, 15, 15);//X button
				g.setFont( "Serif",Font.PLAIN,15 ); g.setColor(Color.white);
				g.drawString(s.name, s.x+5, g.fontSize-20+s.y);//Name button
				
				int mx = MouseManager.mouseX,my = MouseManager.mouseY;
				if(MouseManager.leftPressed)
				if(mx>s.x & mx<s.x+s.width & my>s.y-20 & my<s.y ) {//Mouse on title bar
					if(mx<s.x+s.width-20){
						if(heldState==s | heldState == null) {
							if(!mouseHeld) {
								mouseHeldX = (s.x - mx);
								mouseHeldY = (s.y - my);
							}
							s.x = mx+mouseHeldX;
							s.y = my+mouseHeldY;
							
							mouseHeld = true;
							heldState = s;
							moveStateToTop(s);
						}
					}
					if(mx>s.x+s.width-20 & mx<s.x+s.width)
						s.visible = false;
				}
				if(!MouseManager.leftPressed) {
					mouseHeld = false;
					heldState = null;
				}
			}
		}
	}
	public void scroll(int scrollAmount) {
		for(State s:states)
			if(s.mouseOverState)
				s.scroll(scrollAmount);
	}
	public void keyTyped(KeyEvent e) {
		for(State s:states)
			if(s.selected)
				s.keyTyped(e);
	}
	public void moveStateToTop(State state) {
		ArrayList<State> newList = new ArrayList<State>();
		for(State s:states)
			if(s!=state)
				newList.add(s);
		newList.add(state);
		states = newList;
	}
	public void allStatesVisible() {
		for(State s:states)
			s.visible = true;
	}
}