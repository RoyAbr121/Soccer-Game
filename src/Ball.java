
//Name: Roy Asher, ID: 200844009 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ball implements Moveable {

	private double bx, by;
	private double alpha;
	private double speed;
	private Player holder;
	public final int AFTER_COLLISION_SPEED = 3;
	public final int FAST_SPEED = 5;
	public final int VERY_FAST_SPEED = 7;
	public final int RADIUS = 10;

	public Ball() {
		speed = FAST_SPEED;
		alpha = 2 * Math.PI * Math.random();
		holder = null;
	}

	public int[] initialPosition(int width, int height) {
		int px, py;

		px = width / 2;
		py = height / 2;
		int[] position = { px, py };
		return position;
	}

	public void move() {
		double dx, dy;
		dx = Math.cos(alpha);
		dy = Math.sin(alpha);

		bx = bx + speed * dx;
		by = by + speed * dy;
	}

	// change angle when ball meets borders
	public void checkAngle(SoccerField soccerfield, Ball ball) {

		double dx, dy;
		Rectangle field = soccerfield.getBorders();

		dx = Math.cos(alpha);
		dy = Math.sin(alpha);

		if (dx > 0) { // check right border
			if (bx + RADIUS + dx * speed > field.getMaxX() && ball.getHolder() == null) {
				alpha = Math.PI - alpha;
				ball.setSpeed(AFTER_COLLISION_SPEED);
			}
		} else { // check left border
			if (bx - RADIUS + dx * speed < field.getMinX() && ball.getHolder() == null) {
				alpha = Math.PI - alpha;
				ball.setSpeed(AFTER_COLLISION_SPEED);
			}
		}

		if (dy > 0) { // check bottom border
			if (by + RADIUS + dy * speed > field.getMaxY() && ball.getHolder() == null) {
				alpha = -alpha;
				ball.setSpeed(AFTER_COLLISION_SPEED);
			}
		} else { // check top border
			if (by - RADIUS + dy * speed < field.getMinY() && ball.getHolder() == null) {
				alpha = -alpha;
				ball.setSpeed(AFTER_COLLISION_SPEED);
			}
		}

	}

	public void drawMe(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File("ball.jpg"));
		} catch (IOException ex) {
			System.out.println("File ball.jpg was not found\n");
		}
		TexturePaint tp = new TexturePaint(bi, new Rectangle((int) bx + 10, (int) by, 50, 50));
		g2d.setPaint(tp);
		g2d.fillOval((int) bx - RADIUS, (int) by - RADIUS, 2 * RADIUS, 2 * RADIUS);
		g2d.setColor(Color.BLACK);
		g2d.drawOval((int) bx - RADIUS, (int) by - RADIUS, 2 * RADIUS, 2 * RADIUS);
		g2d.dispose();
	}

	public void setAlpha(double angle) {
		alpha = angle;
	}

	public void setCenter(double x, double y) {
		bx = x;
		by = y;
	}

	public void setHolder(Player player) {
		holder = player;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getX() {
		return bx;
	}

	public double getY() {
		return by;
	}

	public Player getHolder() {
		return holder;
	}

	public double getAlpha() {
		return alpha;
	}
}
