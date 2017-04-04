package vlib;

import processing.core.PImage;

public interface PixelOperation {

	public void apply(PImage in);
	
	default int makeColor(int r, int g, int b, int a) {
		int rgba = 0;
		rgba |= ((a & 0xFF) << 24);
		rgba |= ((r & 0xFF) << 16);
		rgba |= ((g & 0xFF) << 8);
		rgba |= (b & 0xFF);
		return rgba;
	}
	
}