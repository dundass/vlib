package vcreations.shapes;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import vlib.PixelOperation;
import vlib.PixelProcess;
import vlib.VImageShape;
import vlib.VLayer;
import vlib.VShape;
import vlib.VSurface;

public class ImageShape implements VImageShape {
	final PApplet papp;
	PImage img;
	PVector loc;
	float scale = 1.0f, rotate = 0, alpha = 1.0f;
	boolean visible = true;
	
	public ImageShape(PApplet p, String imgName) {
		papp = p;
		img = papp.loadImage(imgName);
		img.format = PConstants.ARGB;
		loc = new PVector(papp.width / 2, papp.height / 2);
	}
	
	public void alpha(float amt) {
		alpha = amt;
	}

	public void scale(float amt) {
		scale = amt;
	}
	
	public float scale() {
		return scale;
	}
	
	public void rotate(float amt) {
		rotate = amt;
	}
	
	public void visible(boolean vis) {
		visible = vis;
	}

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

	public PGraphics get() {
		return (PGraphics) img;
	}

	public void set(PImage p) {
		img = p;
	}

	public void clear() {
		
	}

	public void process(PixelOperation op) {
		img.loadPixels();
		op.apply(img);
		img.updatePixels();
	}

	@Override
	public void renderToLayer(VShape[] s, float t) {
		
	}

	@Override
	public void process(PixelProcess[] op) {
		img.loadPixels();
		
		//aggregate the operations to reduce iteration
		
//		for(int x = 1; x < img.width - 1; x++) {
//			for(int y = 1; y < img.height - 1; y++) {
//				for(int i = 0; i < op.length; i++) {	//sequential: process w op[0], then w op[1], etc
//					img.pixels[y * img.width + x] = op[i].apply((PGraphics)img, x, y);
//				}
//			}
//		}
		
		img.updatePixels();
	}

	@Override
	public void imprint(VLayer l) {
		// TODO Auto-generated method stub
		
	}

}
