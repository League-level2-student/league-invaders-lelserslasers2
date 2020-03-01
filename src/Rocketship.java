import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {

	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
	}
	
	public void right() {
		x = x + speed;
	}
	public void left() {
		x = x - speed;
	}
	public void up() {
		y = y - speed;
	}
	public void down() {
		y = y + speed;
	}
	
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
	}

}
