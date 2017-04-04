package vcreations.layers;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import vlib.PixelOperation;
import vlib.VShape;
import vlib.VSurface;

public class BasicLayer implements VSurface {
	final PApplet papp;
	PGraphics pg;
	
	public BasicLayer(PApplet p) {
		papp = p;
		pg = papp.createGraphics(papp.width, papp.height, PConstants.P2D);
		pg.beginDraw();
		pg.background(0, 0);
		pg.endDraw();
	}

	@Override
	public PGraphics get() {
		return pg;
	}

	@Override
	public void set(PImage p) {
		p.loadPixels();
		pg.beginDraw();
		pg.loadPixels();
		pg.pixels = p.pixels;
		pg.updatePixels();
		pg.endDraw();
	}

	@Override
	public void clear() {
		pg.beginDraw();
		pg.clear();
		pg.endDraw();
	}

	@Override
	public void process(PixelOperation op) {
		pg.beginDraw();
		pg.loadPixels();
		op.apply(pg);
		pg.updatePixels();
		pg.endDraw();
	}

	@Override
	public void imprint(VSurface s) {
		PImage in = s.get();
		in.loadPixels();
		pg.beginDraw();
		pg.loadPixels();
		//save pixel to surface if pixel isn't black or transparent
		int r, g, b, a, p;
		for(int i = 0; i < in.pixels.length; i++) {
			p = in.pixels[i];
//			a = (p >> 24) & 0xFF;
//			r = (p >> 16) & 0xFF;
//			g = (p >> 8) & 0xFF;
//			b = p & 0xFF;
//			if(a > 0 || (r > 0 && g > 0 && b > 0)) pg.pixels[i] = p;
			if(p != 0) pg.pixels[i] = p;
		}
		pg.updatePixels();
		pg.endDraw();
	}

	@Override
	public void render(float t) {
		papp.imageMode(PConstants.CORNER);
		papp.image(pg, 0, 0);
	}

	@Override
	public void renderToSurface(VShape[] s, float t) {
		pg.beginDraw();
		for(VShape shape : s) shape.render(pg, t);
		pg.endDraw();
	}

//	@Override
//	public void setPApplet(PApplet p) {
//		papp = p;
//	}

}
