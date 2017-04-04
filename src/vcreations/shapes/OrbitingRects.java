package vcreations.shapes;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
import vlib.VShape;

public class OrbitingRects implements VShape {
	final PApplet papp;
	private int numRects;
	private boolean[] clockwise;
	private PVector[] locs;
	private PVector centre;
	
	public OrbitingRects(PApplet p, int numRects) {
		papp = p;
		this.numRects = numRects;
		clockwise = new boolean[numRects];
		locs = new PVector[numRects];
		for(int i = 0; i < locs.length; i++) locs[i] = new PVector(0, 0);
		centre = new PVector(0, 0);
	}
	
	@Override
	public void render(float t) {
		
	}

	@Override
	public void scale(float amt) {
		
	}

	@Override
	public void rotate(float amt) {
		
	}

	@Override
	public PVector getLoc() {
		return centre;
	}
	
	public PVector getLocForRect(int i) {
		return locs[i];
	}

	@Override
	public void render(PGraphics pg, float t) {
		pg.pushMatrix();
		
		pg.popMatrix();
	}

}
