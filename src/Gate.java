
//Name: Roy Asher, ID: 200844009 

import java.awt.Color;
import java.awt.Graphics;

public class Gate {

	private int left, top, width, height;

	public void setAll(int left, int top, int width, int height) {
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
	}

	public void drawMe(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(left, top, width, height);
	}

	public int getLeft() {
		return left;
	}

	public int getTop() {
		return top;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
