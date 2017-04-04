package processing_eclipse;

import processing.core.PApplet;

//an example drawing class for LiveClassAccess

public final class Circles {
	static private PApplet parent;
	static float size = 100, x = 100, y = 100;
	
	private Circles() {
		size = 100;
		x = 100;
		y = 100;
	}
	
	static void render(PApplet p) {
		parent = p;
		parent.fill(parent.frameCount % 255);
		parent.ellipse(x, y, size, size);
	}
	
	static void print(int n) {
		System.out.println("stuff " + n);
	}
}
