import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
public class Confetti {
	private int x1, y1, x2, y2;
	private static double vx, vy;//the change in x and y the confetti will have
	private static double speed;//the amount of pixels moving
	private Color c;
	private boolean launched;

	public Confetti(int x1, int y1, int x2, int y2, int x3, int y3, Color confetti_color, double angle) {

		// make confetti have location of middle of launcher
		this.x1 = (x3 + x2)/2;//midpoint
		this.y1 = (y3+y2)/2;//midpoint of the line
		//length of the confetti
		double length = 40;
		//make the tilt the confetti needs to make
		double a= Math.toRadians(angle+30);
		//find the x and y the confetti's end will have
		this.x2 = (int)(this.x1 + Math.cos(a)*length);
		this.y2 = (int)(this.y1 - Math.sin(a)*length);
		//set color for confetti
		c = confetti_color;

		//schedule the launch after .5 secs
		speed = 100;
		new javax.swing.Timer(500, e-> {
			launched = true;
			vx = Math.cos(a) * speed;
			vy = -Math.sin(a) * speed;//subtracting because y-axis is flipped 
			
		}).start();
		this.update();
		
	}
	
	public void update() {
		if(launched) {
			x1 += vx;
			x2 += vx;
			y1 += vy;
			y2 += vy;
		}
	}
	
	public int getX1() {
		return x1;
	}



	public void setX1(int x1) {
		this.x1 = x1;
	}



	public int getY1() {
		return y1;
	}



	public void setY1(int y1) {
		this.y1 = y1;
	}



	public int getX2() {
		return x2;
	}



	public void setX2(int x2) {
		this.x2 = x2;
	}



	public int getY2() {
		return y2;
	}



	public void setY2(int y2) {
		this.y2 = y2;
	}



	public Color getC() {
		return c;
	}



	public void setC(Color c) {
		this.c = c;
	}



	public void drawConfetti(Graphics g) {
		g.setColor(c);
		g.drawLine(x1, y1, x2, y2);
		update();
		
	}

}
