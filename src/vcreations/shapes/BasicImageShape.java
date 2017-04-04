package vcreations.shapes;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import vlib.PixelOperation;
import vlib.VImageShape;
import vlib.VShape;
import vlib.VSurface;

public class BasicImageShape implements VImageShape {
	final PApplet papp;
	PImage img;
	PVector loc;
	float scale = 1.0f, rotate = 0, alpha = 1.0f;
	boolean visible = true;
	
	public BasicImageShape(PApplet p, String imgName) {
		papp = p;
		img = papp.loadImage(imgName);
		img.format = PConstants.ARGB;
		loc = new PVector(papp.width / 2, papp.height / 2);
	}
	
	public void alpha(float amt) {
		alpha = amt;
	}

	@Override
	public void scale(float amt) {
		scale = amt;
	}
	
	public float scale() {
		return scale;
	}
	
	@Override
	public void rotate(float amt) {
		rotate = amt;
	}
	
	public void visible(boolean vis) {
		visible = vis;
	}

	@Override
	public PVector getLoc() {
		return loc;
	}
	
	public void setLoc(PVector p) {
		loc = p;
	}

	@Override
	public void render(PGraphics pg, float t) {
		if(!visible) return;
		pg.pushMatrix();
		pg.imageMode(PConstants.CENTER);
		pg.translate(loc.x, loc.y);
		pg.rotate(t * rotate);
		pg.tint(255, alpha * 255);
		pg.image(img, 0, 0, img.width * scale, img.height * scale);
		pg.popMatrix();
	}

	@Override
	public void render(float t) {
		
	}

	@Override
	public PGraphics get() {
		return (PGraphics) img;
	}

	@Override
	public void set(PImage p) {
		img = p;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public void process(PixelOperation op) {
		img.loadPixels();
		op.apply(img);
		img.updatePixels();
	}

	@Override
	public void imprint(VSurface s) {
		
	}

	@Override
	public void renderToSurface(VShape[] s, float t) {
		
	}

}
