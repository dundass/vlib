package processing_eclipse;

import processing.core.PApplet;

public class Circle {
	private PApplet parent;
	private float size, x, y;
	
	public Circle() {
		size = 100;
		x = 100;
		y = 100;
	}
	
	public void render(PApplet p) {
		parent = p;
		parent.fill(parent.frameCount % 255);
		parent.ellipse(x, y, size, size);
	}
	
	public void print(int n) {
		System.out.println("stuff " + n);
	}
}
