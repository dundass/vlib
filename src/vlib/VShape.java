package vlib;

import processing.core.PGraphics;
import processing.core.PVector;

public interface VShape extends VObject {
	
	public void scale(float amt);
	public void rotate(float amt);	// y/n?
	public PVector getLoc();
	public void render(PGraphics pg, float t);
	
}