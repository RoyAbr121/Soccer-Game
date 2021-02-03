
//Name: Roy Asher, ID: 200844009 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Team {

	public final int NUM_PLAYERS = 5;
	private Player[] players;
	private Color team_color;

	public Team() {
		this(Color.WHITE, 0);
	}

	public Team(Color color, int team) {
		team_color = color;
		players = new Player[NUM_PLAYERS];
		players[0] = new Goalkeeper(team_color, team, -1);
		players[1] = new Defender(team_color, team, 1);
		players[2] = new Defender(team_color, team, 0);
		players[3] = new Attacker(team_color, team, 1);
		players[4] = new Attacker(team_color, team, 0);
	}

	public void arrangePlayers(int width, int height) {
		for (int i = 0; i < players.length; i++) {
			players[i].setCenter(players[i].initialPosition(width, height)[0],
					players[i].initialPosition(width, height)[1]);
		}
	}

	public void drawMe(Graphics g) {
		for (int i = 0; i < players.length; i++) {
			players[i].drawMe(g);
		}
	}

	public void playAI(SoccerField soccerfield, Ball ball, int width, int height, boolean user_control) { // checks
																											// distance
																											// to ball
																											// and
																											// decides
																											// what to
																											// do
		for (int i = 0; i < players.length; i++) {
			players[i].decideWhatToDo(soccerfield, ball, true, width, height, user_control);
		}
	}

	public void playUser(SoccerField soccerfield, Ball ball, KeyEvent event, int width, int height,
			boolean user_control) {
		if (event == null) {
			playAI(soccerfield, ball, width, height, user_control);
		} else {
			for (int i = 0; i < players.length; i++) {
				if (ball.getHolder() == players[i]) {
					switch (event.getKeyCode()) {
					case KeyEvent.VK_UP: // move forward
						players[i].setCenter(
								players[i].getX() + players[i].getSpeed() * Math.cos(players[i].getAlpha()),
								players[i].getY() + players[i].getSpeed() * Math.sin(players[i].getAlpha()));
						break;
					case KeyEvent.VK_DOWN: // move backward
						players[i].setCenter(
								players[i].getX() - players[i].getSpeed() * Math.cos(players[i].getAlpha()),
								players[i].getY() - players[i].getSpeed() * Math.sin(players[i].getAlpha()));
						break;
					case KeyEvent.VK_LEFT: // change movement direction counter clockwise
						players[i].setAlpha(players[i].getAlpha() - Math.PI / 27);
						ball.setAlpha(players[i].getAlpha());
						break;
					case KeyEvent.VK_RIGHT: // change movement direction clockwise
						players[i].setAlpha(players[i].getAlpha() + Math.PI / 27);
						ball.setAlpha(players[i].getAlpha());
						break;
					case KeyEvent.VK_SPACE: // kick the ball
						players[i].userKick(ball);
					}
					ball.setCenter(players[i].getX() + 1.2 * ball.RADIUS * Math.cos(ball.getAlpha()),
							players[i].getY() + 1.2 * ball.RADIUS * Math.sin(ball.getAlpha()));
				} else {
					players[i].decideWhatToDo(soccerfield, ball, true, width, height, user_control);
				}
			}
		}
	}
}