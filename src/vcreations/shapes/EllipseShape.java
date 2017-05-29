package vcreations.shapes;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
import vlib.VShape;

public class EllipseShape implements VShape {
	final PApplet papp;
	PVector loc;
	float scale, rotate, size;
	boolean visible;
	
	public EllipseShape(PApplet p, PVector l) {
		papp = p;
		loc = l;
		size = 100;
		rotate = 0;
		scale = 1;
		visible = true;
	}

	@Override
	public void render(float t) {
		papp.pushMatrix();
		papp.translate((papp.width / 2), (papp.height / 2));
		papp.ellipse(loc.x, loc.y, size * scale, size * scale);
		papp.popMatrix();
	}
	
	@Override
	public void render(PGraphics pg, float t) {
		if(!visible) return;
		pg.pushMatrix();
		pg.translate((papp.width / 2), (papp.height / 2));
		pg.rotate(t * rotate);
		pg.stroke(1, 255, 255);
		pg.fill((float)Math.sin(t) * 255);
		pg.ellipse(loc.x * (float)Math.sin(t), loc.y + (t * 1000) % 500, size * scale, size * scale);
		pg.popMatrix();
	}

	public void scale(float amt) {
		scale = amt;
	}
	
	public void rotate(float amt) {
		rotate = amt;
	}
	
	public void visible(boolean vis) {
		visible = vis;
	}
	
	public void size(float s) {
		size = s;
	}

	public PVector getLoc() {
		return loc;
	}

}
