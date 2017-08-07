package vcreations.shapes;

import interpolation.Lerp;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;
import vcreations.utils.IterativeColour;
import vlib.VShape;

public class ScanFoldPoints implements VShape {
	final PApplet papp;
	PVector loc, tar;
	PVector[] p;
	float[] strokes;
	int trig1 = 0, trig2 = 1;    //0 = sin, 1 = cos, 2 = tan, 3 = atan
	int reloadCount = 0;
	int hue = 150, sat = 200;
	float t_mult1 = 0.04f, t_mult2 = 0.04f;
	public IterativeColour col;
	
	public ScanFoldPoints(PApplet p) {
		papp = p;
		initMotion();
	    this.p = new PVector[40];
	    strokes = new float[this.p.length];
	    for(int i = 0; i < this.p.length; i++) {
	      this.p[i] = new PVector(papp.random(-150, 150), papp.random(-150, 150));
	      strokes[i] = 25 + i / 2;
	    }
	    
	    col = new IterativeColour(hue, 255);
	    col.setSinFreqDiv(50);
	}

	@Override
	public void render(float t) {
		render(papp.g, t);
	}

	@Override
	public void render(PGraphics pg, float t) {
		loc.x = Lerp.lerp(loc.x, tar.x, 1.f);
		loc.y = Lerp.lerp(loc.y, tar.y, 1.f);
		if(PApplet.dist(loc.x, loc.y, tar.x, tar.y) < 2.) {
			initMotion();
			reloadCount++;
			if(reloadCount % 3 == 0) randomise();
			hue = 100 + (int)(Math.random() * 100);
			sat = 20 + (int)(Math.random() * 180);
		}
		initRand();
		for(int i = 0; i < 200; i++) {
			update();
			drawParticles(pg, i);
		}
	}
	
	public void update() {
		float d = 0, trig = 0, a = 0;
		for(int i = 0; i < p.length; i++) {
			for(int j = 0; j < p.length; j++) {
				d = PApplet.dist(p[i].x, p[i].y, papp.width - p[j].x, p[j].y);
				if(reloadCount % 2 == 0) a = d / p[i].y;
				else a = d / p[j].y;
				switch(trig1) {
				case 0:  trig = PApplet.sin(a);  break;
				case 1:  trig = PApplet.cos(a);  break;
				case 2:  trig = PApplet.tan(a);  break;
				case 3:  trig = PApplet.atan(a);  break;
				}
				p[i].x += (t_mult1 * trig);
				if(reloadCount % 3 <= 1) a = d / p[i].x;
				else a = d / p[j].x;
				switch(trig2) {
				case 0:  trig = PApplet.sin(a);  break;
				case 1:  trig = PApplet.cos(a);  break;
				case 2:  trig = PApplet.tan(a);  break;
				case 3:  trig = PApplet.atan(a);  break;
				}
				p[i].y += (t_mult2 * trig);
				strokes[i] = (float)((int)(strokes[i] + (d / 1500)) % 255);
			}
		}
	}
	
	  
	void initRand() {
		papp.randomSeed(1);
		for(int i = 0; i < p.length; i++) {
			p[i].x = loc.x + papp.random(-50, 50);  p[i].y = loc.y + papp.random(-250, 250);
		}
	}

	void initHorizontal() {
		for(int i = 0; i < p.length; i++) {
			p[i].x = 400 + (-i * i);  p[i].y = -150 + (papp.frameCount % 300) + PApplet.sq(1);
		}
	}
	
	public void initMotion() {
		papp.randomSeed(papp.frameCount % 10000);

		loc = new PVector(papp.random(-papp.width / 2, papp.width / 2), papp.random(-papp.height / 2, -papp.height / 4));
		tar = new PVector(loc.x * -1, papp.random(papp.height / 4, papp.height / 2));

		if(papp.random(1) > 0.5) {
			loc.y *= -1;
			tar.y *= -1;
		}
	}
	  
	void randomise() {
		trig1 = (int)papp.random(4);
		trig2 = (int)papp.random(4);
		t_mult1 = papp.random(0.02f, 0.08f);
		t_mult2 = papp.random(0.02f, 0.08f);
	}

	void drawParticles(PGraphics pg, int index) {
		pg.pushMatrix();
		pg.translate(pg.width / 2, pg.height / 2);
		pg.strokeWeight(2);
		pg.colorMode(PConstants.HSB);
		col.setBase(hue);
		for(int i = 0; i < p.length; i++) {
			//col.setBase((int)((loc.x + p[i].x) * 255 / pg.width));
			pg.stroke(col.get(index), sat, 255);  // MAKE COLOR LINKED TO ITERATIVECOLOUR -> maybe decouple colour eventually... more general: should this always be the case ?
			pg.point(p[i].x, p[i].y);
		}
		pg.popMatrix();
	}
}
