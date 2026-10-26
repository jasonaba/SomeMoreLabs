import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Launcher {
	private int x1, x2, x3, y2, y3, length;
	private static double tilt;
	private Color c, confet_c;
	private Confetti confetti;
	private static int y1;

	public Launcher(int launcherX, double angle, Color c, Color confetti_c) {
		super();
		this.x1 = launcherX;
		y1 = 500;//this is the top of the ground (so it is sitting on the top)
		length = 50;//the length of the sides of the triangle
		tilt = angle;//the angle given by the slider
		//launcher color
		this.c = c;
		//get confetti color
		confet_c = confetti_c;
		
		
	}

	public int getLauncherX() {
		return x1;
	}

	public void setLauncherX(int launcherX) {
		this.x1 = launcherX;
	}

	public int getLauncherY() {
		return y1;
	}

	public void setLauncherY(int launcherY) {
		y1 = launcherY;
	}

	public double getTilt() {
		return tilt;
	}

	public static void setTilt(double angle) {
		tilt = angle;
	}

	public double getLength() {
		return length;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}
	

	public Confetti getConfetti() {
		return confetti;
	}

	public void fire() {
		
	}
	
	/**
	 * Draws the launcher and the confetti that goes with it
	 * 
	 * @param g (the "drawer")
	 * @param
	 */
	public void drawLauncher(Graphics g) {

		/*
		 * this took me a long time and I actually wanted to ask in class why it worked
		 * (I got it through trial and error)
		 */
		
		g.setColor(c);
		// get values in radians of the tilts
		double t_60 = Math.toRadians(tilt + 60);
		double t = Math.toRadians(tilt);
		// find x2, x3, y2, y3
		x2 = (int) (x1 + (Math.cos(t) * length));
		y2 = (int) (y1 - (Math.sin(t) * length));// make tilt+60 in radians
		x3 = (int) (x1 + (Math.cos(t_60) * length));
		y3 = (int) (y1 - (Math.sin(t_60) * length));
		// create confetti in here
		confetti = new Confetti(x1, y1, x2, y2, x3, y3, confet_c, tilt);
		
		//draw launcher and confetti
		int[] launcher_x = { x1, x2, x3 };
		int[] launcher_y = { y1, y2, y3 };
		//draw the triangle
		g.fillPolygon(launcher_x, launcher_y, 3);
		g.setColor(Color.black);
		g.drawPolygon(launcher_x, launcher_y, 3);
		confetti.drawConfetti(g);
		confetti.update();
		confetti.drawConfetti(g);
	}

}