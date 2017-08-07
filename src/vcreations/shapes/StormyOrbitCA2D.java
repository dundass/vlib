package vcreations.shapes;

import cellularcore.CA2D;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import vlib.VShape;

public class StormyOrbitCA2D extends CA2D implements VShape {
	final PApplet papp;
	
	public StormyOrbitCA2D(PApplet p) {
		super(8, 80);
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
		if(papp.frameCount % 3 == 0) update();

		for(int i = 0; i < getXsize(); i++) {
			for(int j = 0; j < getYsize(); j++) {
				pg.noStroke();
				if(getState(i, j) > 0) pg.fill(255 - (getState(i, j) * 255 / getNumStates()), 255, 255, 180);
				else pg.fill(0, 150);
				pg.rectMode(PConstants.CENTER);
				pg.translate(pg.width / 2 + getState(i, j), pg.height / 2);
				pg.rotate(2 * t + (float)getState(i, j) / 200);
				pg.pushMatrix();
				pg.rotate(getState(i, j) / 3);
				pg.rect(pg.width / 2 + i * (pg.width / 2) / getXsize(), pg.height / 2 + j * (pg.height / 2) / getYsize(), getNumStates() - getState(i, j), getNumStates() - getState(i, j));
				pg.popMatrix();
			}
		}
	}

	public void setCentreState() {
		setState(getXsize() / 2, getYsize() / 2, (int)(Math.random() * getNumStates()));
	}

	public void killStates() {
		for(int i = 0; i < getXsize(); i++) {
			for(int j = 0; j < getYsize(); j++) {
				setState(i, j, 0);
			}
		}
	}

}
