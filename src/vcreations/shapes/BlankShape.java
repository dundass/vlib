package vcreations.shapes;

import processing.core.PApplet;
import processing.core.PGraphics;
import vlib.VShape;

public class BlankShape implements VShape {
	final PApplet papp;
	
	public BlankShape(PApplet p) {
		papp = p;
	}

	@Override
	public void render(float t) {
		render(papp.g, t);
	}

	@Override
	public void render(PGraphics pg, float t) {
		
	}

}
