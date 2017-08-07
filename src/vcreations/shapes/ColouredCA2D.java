package vcreations.shapes;

import cellularcore.CA2D;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import vlib.VShape;

public class ColouredCA2D extends CA2D implements VShape {
	final PApplet papp;

	public ColouredCA2D(PApplet p, int numStates, int size) {
		super(numStates, size);
		papp = p;
		setLambdaRuleset(.4f);
	}

	@Override
	public void render(float t) {
		render(papp.g, t);
	}

	@Override
	public void render(PGraphics pg, float t) {
		pg.colorMode(PConstants.HSB, 255);
		pg.rectMode(PConstants.CORNER);
		//if(papp.frameCount % 3 == 0) update();
		update();

		for(int i = 0; i < getXsize(); i++) {
			for(int j = 0; j < getYsize(); j++) {
				pg.noStroke();
				if(getState(i, j) > 0) pg.fill(255 - (getState(i, j) * 255 / getNumStates()), 255, 255);
				else pg.fill(0);
				pg.rect(i * pg.width / getXsize(), j * pg.height / getYsize(), pg.width / getXsize(), pg.height / getYsize());
			}
		}
	}
	
	public void setCentreState() {
	    setState(getXsize() / 2, getYsize() / 2, (int)(Math.random() * getNumStates()));
	}

}
