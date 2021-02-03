
//Name: Roy Asher, ID: 200844009 

import java.awt.Color;

public class Goalkeeper extends Player {

	public Goalkeeper(int x, int y, double alpha, Color color, int team) {
		super(x, y, alpha, color, team, -1);
	}

	public Goalkeeper(Color color, int team, int quarter) {
		super(color, team, quarter);
	}

	public int[] initialPosition(int width, int height) {
		int px, py;

		if (team == TEAM1) {
			px = width / 20;
			py = height / 2;
		} else {
			px = 19 * width / 20;
			py = height / 2;
		}
		int[] position = { px, py };
		return position;
	}

	public void decideWhatToDo(SoccerField soccerfield, Ball ball, boolean control, int width, int height,
			boolean user_control) {

		faceBall(ball);
		if (ball.getHolder() == null)
			if (this.ballInSector(ball, width, height))
				followBall(ball);
			else
				backToPosition(width, height);

		if (distanceToBall(ball) <= 1.2 * RADIUS && ball.getHolder() == null)
			if (user_control && this.getTeam() == TEAM2)
				catchBall(ball);
			else
				keeperKick(ball);
	}

	public boolean ballInSector(Ball ball, int width, int height) {
		double bx, by;

		bx = ball.getX();
		by = ball.getY();

		if (team == TEAM1) {
			if (bx >= 10 && bx <= width / 10 + 10 && by >= height / 3 && by <= 2 * height / 3)
				return true;
		} else {
			if (bx >= 9 * width / 10 - 10 && bx <= width - 10 && by >= height / 3 && by <= 2 * height / 3)
				return true;
		}
		return false;
	}
}
