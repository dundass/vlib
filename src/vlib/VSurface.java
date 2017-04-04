package vlib;

import processing.core.PGraphics;
import processing.core.PImage;

public interface VSurface extends VObject {

	public PGraphics get();	//or PImage?
	public void set(PImage p);	//this is also in VImageShape -> no connection tho??
	public void clear();
	public void process(PixelOperation op);
	public void imprint(VSurface s);
	public void renderToSurface(VShape[] s, float t);
	
}
