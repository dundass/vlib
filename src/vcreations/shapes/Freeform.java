package vcreations.shapes;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;
import vcreations.utils.IterativeColour;
import vlib.VShape;

// should be an extremely sensitive, flexible line-based form

public class Freeform implements VShape {
	final PApplet papp;
	static int maxTendrils = 20;
	static IterativeColour col = new IterativeColour(0, 0);
	private float size, symmetry, tendrils;
	private PVector loc;
	private boolean visible;
	
	public Freeform(PApplet p) {
		papp = p;
		//size = .1f + (float)(Math.random() / 10.);
		symmetry = (float)(Math.random());
		size = 1;
		//symmetry = 1;
		tendrils = (float)(Math.random());
		loc = new PVector((float)(.15 + Math.random() / 1.5) * papp.width, (float)(.15 + Math.random() / 1.5) * papp.height);
		visible = true;
	}

	@Override
	public void render(float t) {
		render(papp.g, t);
	}

	@Override
	public void render(PGraphics pg, float t) {
		// one 'spine', several morphing 'tendrils'
		
		if(!visible) return;
		
		pg.pushMatrix();
		pg.colorMode(PConstants.HSB);
		pg.translate(loc.x, loc.y);
		pg.stroke(255);
		float s = size * 200;
		pg.line(0, -s, 0, 0);		// spine
		for(int i = 0; i < (tendrils * maxTendrils); i++) {
			pg.noFill();
			pg.bezier(0, -s * symmetry, i * (s / 10), -s, s * symmetry, 0, i * (s / 10), 0);
			pg.bezier(0, -s, -i * (s / 10) * symmetry, -s * symmetry, -s, 0, -i * (s / 10), 0);
		}
		pg.popMatrix();
	}
	
	public PVector getLoc() {
		return loc;
	}
	
	public float getSize() {
		return size;
	}
	
	public float getSymmetry() {
		return symmetry;
	}
	
	public void setLoc(float x, float y) {
		loc.x = x;	loc.y = y;
	}
	
	public void setSize(float s) {
		size = s;
	}
	
	public void setSymmetry(float s) {
		symmetry = s;
	}
	
	public void setVisible(boolean v) {
		visible = v;
	}
}
