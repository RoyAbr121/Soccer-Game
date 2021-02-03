
//Name: Roy Asher, ID: 200844009

import java.awt.Color;

public class Defender extends Player {

	public Defender(int x, int y, double alpha, Color color, int team, int quarter) {
		super(x, y, alpha, color, team, quarter);
	}

	public Defender(Color color, int team, int quarter) {
		super(color, team, quarter);
	}

	public int[] initialPosition(int width, int height) {
		int px, py;

		if (team == TEAM1)
			px = 4 * width / 20;
		else
			px = 16 * width / 20;
		if (quadrent == BOTTOM_QUADRANT)
			py = height / 3;
		else
			py = 2 * height / 3;
		int[] position = { px, py };
		return position;
	}

	public void decideWhatToDo(SoccerField soccerfield, Ball ball, boolean control, int width, int height,
			boolean user_control) {

		faceBall(ball);
		if (ball.getHolder() == null) {
			if (this.ballInSector(ball, width, height))
				followBall(ball);
			else
				backToPosition(width, height);
		} else if (ball.getHolder().getTeam() == TEAM2 && ball.getHolder() != this && user_control) {
			if (this.getTeam() == TEAM1) {
				if (this.ballInSector(ball, width, height)) {
					if (this.distanceToBall(ball) > 6 * RADIUS)
						followBall(ball);
					else if (this.distanceToBall(ball) <= 6 * RADIUS && this.distanceToBall(ball) >= 5 * RADIUS)
						faceBall(ball);
				} else
					backToPosition(width, height);
			} else if (this.getTeam() == TEAM2)
				backToPosition(width, height);
		}
		if (distanceToBall(ball) <= 1.2 * RADIUS && ball.getHolder() == null)
			if (user_control && this.getTeam() == TEAM2)
				catchBall(ball);
			else
				goalKick(soccerfield, ball);
	}

	public boolean ballInSector(Ball ball, int width, int height) {
		double bx, by;

		bx = ball.getX();
		by = ball.getY();

		if (team == TEAM1) {
			if (bx >= 10 && bx <= width / 2 && by >= 10 && by <= 2 * height / 3 && quadrent == BOTTOM_QUADRANT)
				return true;
			else if (bx >= 10 && bx <= width / 2 && by >= height / 3 && by <= height - 10 && quadrent == UPPER_QUADRANT)
				return true;
		} else {
			if (bx >= width / 2 && bx <= width - 10 && by >= 10 && by <= 2 * height / 3 && quadrent == BOTTOM_QUADRANT)
				return true;
			else if (bx >= width / 2 && bx <= width - 10 && by >= height / 3 && by <= height - 10
					&& quadrent == UPPER_QUADRANT)
				return true;
		}
		return false;
	}

}
