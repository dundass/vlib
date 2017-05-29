package vlib;

// the interface to implement if defining a shape class

import processing.core.PGraphics;
import processing.core.PVector;

public interface VShape extends VObject {
	
	public void render(PGraphics pg, float t);
	
}