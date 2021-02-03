
//Name: Roy Asher, ID: 200844009 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SoccerField {

	private int width, height;
	private Gate left_gate, right_gate;

	public SoccerField() {
		left_gate = new Gate();
		right_gate = new Gate();
	}

	public void setSize(int w, int h) {
		width = w;
		height = h;
	}

	public Rectangle getBorders() {
		// return left-top corner and width-height
		return new Rectangle(10, 10, width - 20, height - 20);
	}

	public void drawMe(Graphics g) {
		int delta = width / 20;
		// background
		for (int i = 0; i < 20; i++) {
			if (i % 2 == 0)
				g.setColor(new Color(100, 200, 100));
			else
				g.setColor(new Color(120, 230, 120));
			g.fillRect(i * delta, 0, delta, height);
		}
		// foreground
		g.setColor(Color.WHITE);
		g.drawRect(10, 10, width - 20, height - 20);
		g.drawRect(11, 11, width - 22, height - 22);
		g.drawRect(12, 12, width - 24, height - 24);
		g.drawLine(width / 2, 10, width / 2, height - 10);
		g.drawOval(width / 2 - 100, height / 2 - 100, 200, 200);
		g.fillOval(width / 2 - 5, height / 2 - 5, 10, 10);

		// keeper sector
		g.drawRect(10, height / 3, width / 10, height / 3);
		g.drawRect(9 * width / 10 - 10, height / 3, width / 10, height / 3);
		left_gate.setAll(10, height / 2 - 70, 20, 140);
		right_gate.setAll(width - 30, height / 2 - 70, 20, 140);
		left_gate.drawMe(g);
		right_gate.drawMe(g);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
