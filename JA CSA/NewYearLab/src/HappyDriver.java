import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class HappyDriver {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("New Year Ball Drop");
		frame.setSize(800,600);
		frame.setLocation(100,100);
		// ask the user the necessary questions to create the panel
		String input = JOptionPane.showInputDialog("How many launchers do you want?");
		int launcher_number = Integer.parseInt(input);
		Color ball_c = JColorChooser.showDialog(null, "What will the ball's color be?", Color.black);
		Color launcher_c = JColorChooser.showDialog(null, "Choose the launchers' color", Color.red);
		Color confetti_c = JColorChooser.showDialog(null, "Choose a the color for the confetti", Color.white);
		NewYearPanel panel = new NewYearPanel(launcher_number, ball_c, launcher_c, confetti_c);
		
		//get the launcher's angle slider at the bottom of the screen
		JSlider launcher_angle = new JSlider(SwingConstants.HORIZONTAL, 0, 120, 60);
		launcher_angle.setMinorTickSpacing(5);
		launcher_angle.setMajorTickSpacing(10);
		launcher_angle.setPaintLabels(true);
		launcher_angle.setPaintTicks(true);
		frame.add(launcher_angle,BorderLayout.SOUTH);
		//create the slider that will move the ball
		JSlider move_ball = new JSlider(SwingConstants.VERTICAL, -180, 0, 0);
		move_ball.setMinorTickSpacing(5);
		move_ball.setMajorTickSpacing(10);
		move_ball.setPaintLabels(true);
		move_ball.setPaintTicks(true);
		frame.add(move_ball,BorderLayout.EAST);
		
		
		//shows the screen and makes sure to terminate the code after it is closed
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true) {//repeats always
			//feeds the value of the slider to the panel, which feeds it to all the launchers
			double angle = launcher_angle.getValue();
			panel.setAngle(angle);
			
			//feed the ball_x into the panel (so it can move)
			int ball_x = move_ball.getValue();
			panel.setBallX(ball_x);
			//drop the snowflakes
			panel.dropFlakes();
			//redraw every .1 of a sec
			Thread.sleep(100);
			
			//redraw
			frame.repaint();
		}
	}
}