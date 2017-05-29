package vlib;

import processing.core.PImage;

public interface VImageShape extends VShape, VLayer {

	// can be rendered to a Surface AND treated as a Surface? if not, remove VSurface inheritance and uncomment below
	//public void set(PImage p);
	
}