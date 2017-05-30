package vcreations.shapes;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;
import vlib.VShape;
import interpolation.Easing;

public class OrbitingRects implements VShape {
	final PApplet papp;
	private int numRects, numLayers;
	private float length, lengthTar;
	
	public OrbitingRects(PApplet p, int numRects, int numLayers) {
		papp = p;
		this.numRects = numRects;
		this.numLayers = numLayers;
		length = 19;
		lengthTar = 19;
	}
	
	@Override
	public void render(float t) {
		render(papp.g, t);
	}

	@Override
	public void render(PGraphics pg, float t) {
		// have rect width inversely proportional to rect height, so when they become really long, they become fairly thin -> complex pattterns
		// remember, old version implements by spinning each only by the distance to the next before resetting to 0 rotation (modulo...) -> why?
		length = Easing.ease(length, lengthTar, 0.1f);
		
		for(int i = 0; i < numLayers; i++) {
			for(int j = 0; j < numRects; j++) {
				pg.pushMatrix();
				pg.rectMode(PConstants.CENTER);
				pg.translate(pg.width / 2, pg.height / 2);
				float r = (t + ((i-1) * t / 3)) + (j * 2 * (float)Math.PI / numRects);
				pg.rotate(-r);
				pg.noStroke();
				pg.fill(70, 140, 250);
				float y = ((i+1) * pg.height / 7) + ((float)Math.sin(t) * pg.height / 25);
				float xs = length * pg.height / 340;
				float ys = pg.height / (length);
				pg.rect(0, y, xs, ys);
				pg.rotate(2 * r);
				pg.fill(140, 250, 140);
				pg.rect(0, y, xs, ys);
				pg.popMatrix();
			}
		}
	}
	
	public void changeRects(int n) {
		numRects += n;
		if(numRects < 0) numRects = 0;
	}
	
	public void changeLength(float v) {
		lengthTar += v;
	}
	
}
