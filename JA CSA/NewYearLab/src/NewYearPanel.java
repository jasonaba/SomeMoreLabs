import java.awt.*;
import javax.swing.*;

public class NewYearPanel extends JPanel {
	private Point[] flakes;
	Launcher[] launchers;
	Confetti[] confettis;
	Color ballC;
	int ballX;
	double angle;

	public NewYearPanel(int launchernum, Color ballColor, Color launcher_c, Color confetti_c) {
		// makes the background gray
		Color c = new Color(128, 128, 128);
		this.setBackground(c);// background color
		// make the ball color the desired color
		this.ballC = ballColor;

		// make angle in radians
		angle = Math.toRadians(angle);

		// create the launchers
		launchers = new Launcher[launchernum];// creates the launcher array (all the diff launchers)
		int launcher_distance = 50;// where the first launcher should be made (x-value of first corner of the
									// triangle) so it isn't off screen
		//create confettis array
		confettis = new Confetti[launchernum];//one confetti for each launcher
		//create all the launchers
		for (int i = 0; i < launchernum; i++) {
			// make the launchers not null
			launchers[i] = new Launcher(launcher_distance, angle, launcher_c, confetti_c);
			// this makes the x value for the launchers equidistant and not off the screen
			launcher_distance += (800 - launchers[0].getLength()) / launchers.length;
		}
		//create the snowflakes
		flakes = new Point[200];
		for(int i = 0; i < flakes.length; i++) {
			int flakeX = (int)(Math.random()*800);
			int flakeY = (int)(Math.random()*600);
			flakes[i] = new Point(flakeX, flakeY);
		}
		
		/* 
		 * This is an example of me trying to make the confetti fly out
		 * 
		Timer timer = new Timer(100, e-> {
			for(int i = 0; i<launchernum; i++) {
				confettis[i].update();
				confettis[i].drawConfetti(getGraphics());
			}
			repaint();
			System.out.println("Repainted");
		});
		timer.start();
		*/
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//draw snowflakes falling (just to get the points for a loop)

		g.setColor(Color.white);
		for (Point flake : flakes) {
			g.fillOval(flake.x, flake.y, 5, 5);
		}
		
		// draw the new york ball
		Color c = new Color(40, 40, 40);
		g.setColor(c);
		g.fillRect(390, 100, 25, 100);
		g.setColor(ballC);
		g.fillOval(365, 50 - ballX, 75, 75);
		// draw the structure holding the ball
		c = new Color(75, 75, 75);
		g.setColor(c);
		g.fillRect(350, 200, 100, 300);
		g.setColor(Color.black);
		g.drawRect(350, 200, 100, 300);
		g.setColor(Color.white);
		//draw wprds
		Font words = new Font("Helvetica", Font.ITALIC, 45);
		g.setFont(words);
		if(ballX>-90) {
		g.drawString("Wait for it...", 50, 200);
		}
		else {
			g.drawString("Happy New Year!", 50, 200);
		}
		
		// draw the ground
		g.setColor(Color.black);
		g.fillRect(0, 500, 800, 100);
		
		// draw launchers
		for (int i = 0; i < launchers.length; i++) {
			launchers[i].drawLauncher(g);
			confettis[i] = launchers[i].getConfetti();
			/*
			 * as you can see, I tried to update the confetti here as well (but it didn't work)
			 * 
			//confettis[i].drawConfetti(g);
			//confettis[i].update();
			*/
			 
		}

	}

	public int getBallX() {
		return ballX;
	}

	public void setBallX(int ballX) {
		this.ballX = ballX;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
		// makes all the launchers have this tilt
		Launcher.setTilt(this.angle);
	}
	
	/*public void dropFlakes() {
		for(Point flake : flakes) {
			int dropspeed = 500;
			int RandoX = (int)(Math.random()*7)-6;
			flake.setLocation(flake.x + RandoX, flake.y+dropspeed);
			if(flake.y>this.getHeight()) {
				flake.setLocation((int)(Math.random()*this.getWidth()), 0);
			}
			if(flake.x<0) {
				flake.setLocation((int)(Math.random()*this.getWidth()),flake.y);
			}
		}
	}*/
	public void dropFlakes() {
		int dropspeed = 3;
		//makes the flakes fall (increasing their Y)
		for(Point flake:flakes) {
			int xRando = (int) (Math.random()*7)-6;//why does this cause wind?
			int RandoY = (int)(Math.random()*3)+1;
			flake.setLocation(flake.x+xRando,flake.y+dropspeed+RandoY);
			if(flake.y>600)
				flake.setLocation(flake.x, -(int)(Math.random()*50));
			if(flake.x<0)
				flake.setLocation((int) (Math.random() * 800)+200, flake.y);
		}
	}

}