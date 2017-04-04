package vcreations.utils;

import processing.core.PImage;
import processing.core.PVector;

// grid-based pixels[] analysis for Chromaphonics use

public class PixelsAnalysis {
	private PVector numDivs, divSize;
	
	public PixelsAnalysis(int numDivsX, int numDivsY) {
		numDivs = new PVector(numDivsX, numDivsY);
		divSize = new PVector(0, 0);
	}
	
	public void analyse(PImage img) {		//return? 2D array of [[div1hue, div1bright], [div2hue, div2bright]....]?
		
		//recalculate div sizes based on input img dimensions
		divSize.x = img.width / numDivs.x;
		divSize.y = img.height / numDivs.y;
		
		//iterate pixels and analyse
		float totHuePerBlock = 0, totBrightPerBlock = 0,
			  totHuePerRow = 0, totBrightPerRow = 0, totRoughPerRow = 0;
		int x = 0, y = 0;
		img.loadPixels();
		for(int i = 0; i < img.pixels.length; i++) {
			x = i % img.width;
			y = i / img.width;
			//measures: avg hue per row (freq), avg bright per row (amp), weighted x loc per row (pan), avg 'roughness' per row (noise xfade)
		}
		
	}
}
