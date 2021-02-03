
//Name: Roy Asher, ID: 200844009 

import java.awt.Color;
import java.awt.Graphics;

public abstract class Player implements Moveable {

	public final int BOTTOM_QUADRANT = 0;
	public final int UPPER_QUADRANT = 1;
	public final int RADIUS = 10;
	public final int PLAYER_BASIC_SPEED = 2;
	public final int PLAYER_FAST_SPEED = 3;
	public final int TEAM1 = 1;
	public final int TEAM2 = 2;
	protected int quadrent, team;
	protected double cx, cy;
	protected double alpha;
	protected double player_speed;
	protected Color team_color;

	public Player() {
		this(0, 0, 0, Color.WHITE, 0, 0);
	}

	public Player(int x, int y, double alpha, Color team_color, int team, int quadrent) {
		setCenter(x, y);
		setAlpha(alpha);
		this.player_speed = PLAYER_BASIC_SPEED;
		this.team = team;
		this.team_color = team_color;
		this.quadrent = quadrent;
	}

	public Player(Color c, int t, int q) { // gets color and side
		this(0, 0, 0, c, t, q);
		if (team == TEAM1) // if this is a right side player
			setAlpha(Math.PI);
	}

	public abstract void decideWhatToDo(SoccerField soccerfield, Ball ball, boolean control, int width, int height,
			boolean user_control);
	// must be implemented in concrete classes

	public void move() {
		double dx, dy;

		dx = Math.cos(alpha);
		dy = Math.sin(alpha);
		cx = cx + player_speed * dx;
		cy = cy + player_speed * dy;
	}

	public void faceBall(Ball ball) {
		double dx, dy;

		dx = ball.getX() - this.getX();
		dy = ball.getY() - this.getY();
		setAlpha(Math.atan2(dy, dx));
	}

	public void followBall(Ball ball) {
		this.setSpeed(PLAYER_BASIC_SPEED);
		if (ball.getHolder() != null) {
			if (this.distanceToBall(ball) > 4 * RADIUS)
				move();
		} else
			move();
	}

	public void backToPosition(int width, int height) {
		int dx, dy;

		dx = (int) (initialPosition(width, height)[0] - getX());
		dy = (int) (initialPosition(width, height)[1] - getY());

		if (dx != 0 && dy != 0) {
			setAlpha(Math.atan2(dy, dx));
			move();
		}
	}

	public double distanceToBall(Ball ball) {
		return Math.sqrt((ball.getX() - this.getX()) * (ball.getX() - this.getX())
				+ (ball.getY() - this.getY()) * (ball.getY() - this.getY()));
	}

	public double distanceToPlayer(Player player) {
		return Math.sqrt((player.getX() - this.getX()) * (player.getX() - this.getX())
				+ (player.getY() - this.getY()) * (player.getY() - this.getY()));
	}

	public void userKick(Ball ball) {
		ball.setAlpha(this.getAlpha());
		ball.setSpeed(ball.VERY_FAST_SPEED);
		ball.setHolder(null);
	}

	public void keeperKick(Ball ball) {
		if (this.getTeam() == TEAM1)
			ball.setAlpha(Math.random() * 180 * Math.PI / 180 - 90 * Math.PI / 180);
		else
			ball.setAlpha(Math.random() * 180 * Math.PI / 180 + 90 * Math.PI / 180);
		ball.setSpeed(ball.VERY_FAST_SPEED);
	}

	public void goalKick(SoccerField soccerfield, Ball ball) {
		double dx, dy;

		ball.setHolder(this);

		if (team == TEAM1)
			dx = soccerfield.getWidth() - 10 - this.getX();
		else
			dx = 10 - this.getX();
		dy = soccerfield.getHeight() / 2 - 70 + (Math.random() * 140) - this.getY();

		ball.setSpeed(ball.VERY_FAST_SPEED);
		ball.setAlpha(Math.atan2(dy, dx));
		ball.setHolder(null);
	}

	public void catchBall(Ball ball) {
		this.setSpeed(PLAYER_FAST_SPEED);
		ball.setSpeed(0);
		ball.setHolder(this);
		ball.setAlpha(this.getAlpha());
	}

	public void drawMe(Graphics g) {
		g.setColor(team_color);
		g.fillOval((int) cx - RADIUS, (int) cy - RADIUS, 2 * RADIUS, 2 * RADIUS);
		g.setColor(Color.BLACK);
		g.drawOval((int) cx - RADIUS, (int) cy - RADIUS, 2 * RADIUS, 2 * RADIUS);
		g.drawLine((int) cx, (int) cy, (int) (cx + RADIUS * Math.cos(alpha)), (int) (cy + RADIUS * Math.sin(alpha)));
	}

	public void drawMarker(Graphics g) {
		g.drawOval((int) (cx - 1.5 * RADIUS), (int) (cy - 1.5 * RADIUS), 3 * RADIUS, 3 * RADIUS);
		g.drawLine((int) (cx + 1.5 * RADIUS * Math.cos(alpha)), (int) (cy + 1.5 * RADIUS * Math.sin(alpha)),
				(int) (cx + 4 * RADIUS * Math.cos(alpha)), (int) (cy + 4 * RADIUS * Math.sin(alpha)));
	}

	public void setCenter(double x, double y) {
		cx = x;
		cy = y;
	}

	public void setSpeed(double player_speed) {
		this.player_speed = player_speed;
	}

	public void setAlpha(double a) {
		alpha = a;
	}

	public double getX() {
		return cx;
	}

	public double getY() {
		return cy;
	}

	public double getSpeed() {
		return player_speed;
	}

	public double getAlpha() {
		return alpha;
	}

	public int getTeam() {
		return team;
	}
}
