
//Name: Roy Asher, ID: 200844009 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean game_start, user_control;
	private SoccerField soccerfield;
	private Team team1, team2;
	private Ball ball;
	private Timer timer;

	public DrawPanel() {
		user_control = Program.gameType();
		soccerfield = new SoccerField();
		team1 = new Team(Color.YELLOW, 1); // team 1 is on the left side of the field
		team2 = new Team(Color.RED, 2); // team 2 is on the right side of the field
		game_start = false;
		ball = new Ball();
		timer = new Timer();

		timer.schedule(new MyTimerTask(), 1000, 30);
		addKeyListener(new MyKeyboardAdapter());
		setFocusable(true); // to get focus for keyboard
		requestFocusInWindow();
		addMouseListener(new MouseHelper());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();

		soccerfield.setSize(width, height);
		soccerfield.drawMe(g);
		if (!game_start) {
			team1.arrangePlayers(width, height);
			team2.arrangePlayers(width, height);
			ball.setCenter(ball.initialPosition(width, height)[0], ball.initialPosition(width, height)[1]);
		}
		team1.drawMe(g);
		team2.drawMe(g);
		if (ball.getHolder() != null)
			ball.getHolder().drawMarker(g);
		ball.drawMe(g);
	}

	private class MyTimerTask extends TimerTask {
		public void run() {
			int width = getWidth();
			int height = getHeight();

			game_start = true; // game begins
			ball.move(); // ball moves
			ball.checkAngle(soccerfield, ball); // checks border
			team1.playAI(soccerfield, ball, width, height, user_control); // team 1 moves
			team2.playUser(soccerfield, ball, null, width, height, user_control); // team 2 moves (AI or user mode)
			repaint();
		}
	}

	private class MyKeyboardAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent event) {
			int width = getWidth();
			int height = getHeight();

			if (user_control)
				team2.playUser(soccerfield, ball, event, width, height, user_control); // user moves team 2
		}
	}

	private class MouseHelper extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			int mx, my;

			mx = event.getX();
			my = event.getY();
			ball.setCenter(mx, my);
			if (ball.getHolder() != null) {
				ball.setAlpha(Math.random() * Math.PI);
				ball.setHolder(null);
			}
			ball.setSpeed(ball.FAST_SPEED);
		}
	}

}
