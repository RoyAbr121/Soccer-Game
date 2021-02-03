
//Name: Roy Asher, ID: 200844009 

import java.util.Scanner;
import javax.swing.JFrame;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		DrawPanel drawpanel = new DrawPanel();

		frame.add(drawpanel);
		frame.setSize(1000, 600);
		frame.setLocation(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static boolean gameType() {
		boolean run = true;
		;
		@SuppressWarnings("resource")
		Scanner user_input = new Scanner(System.in);

		while (run) {
			System.out.println("What kind of soccer game would you like to play?\n1 - AI versus AI\n2 - User versus AI\n");
			switch (user_input.nextInt()) {
			case 1:
				run = false;
				break;
			case 2:
				System.out.println("\nUse the up and down keys in order to move player holding the ball\n"
						+ "Use the right and left keys in order to give the player the direction you want\n"
						+ "Use the Space bar in order to kick the ball in the direction the player is facing\n"
						+ "Be sure to click on the window frame so the keyboard events are recieved by the program");
				return true;
			}
		}
		return false;
	}

}
