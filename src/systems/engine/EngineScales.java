package systems.engine;

import chaoscore.OctaveTable;
import processing.core.*;
import vlib.*;
import vcreations.layers.Layer;
import vcreations.pixel.*;
import vcreations.shapes.*;

// needs updating to the new PixelProcess operators & Layer class

public class EngineScales extends PApplet {
	//layers
	Layer shapeLayer, noiseLayer;
	//shapes
	BasicImageShape[] images;
	PImage[] srcs;
	//'shaders'
	//PixelFade fade
	PixelDirectionalGrowth growth;
	//modulators
	OctaveTable oct;
	//primitives
	int currentImage = 0, currentNoise = 0;
	boolean imprinting = false;
	
	public static void main(String[] args) {
		PApplet.main("systems.engine.EngineScales");
	}
	
	public void settings() {
		size(1280, 800, P2D);
		//fullScreen(P2D);
	}
	
	public void setup() {
		shapeLayer = new Layer(this);
		noiseLayer = new Layer(this);
		
		growth = new PixelDirectionalGrowth();
		
		images = new BasicImageShape[3];
		for(int i = 0; i < images.length; i++) {
			images[i] = new BasicImageShape(this, "DSC_0090.JPG");
			images[i].setLoc(new PVector((width / 2) + i * 20, (height / 2) + 10));
			images[i].scale(0.4f * (images.length - i));
			images[i].alpha(0.7f);
			images[i].process(new PixelTransparency(0.8f));
			images[i].process(new PixelColour(-10 + i * 10));
		}
		oct = new OctaveTable(4, 2);
		for(int i = 0; i < oct.getNumHarmonics(); i++) {
			oct.setHarmonic(0, i, (oct.getNumHarmonics() - i) / 3.f);
		}
		for(int i = 0; i < oct.getResolution(); i++) {
			oct.setTablePoint(i, (float)i / oct.getResolution());
		}
		
		background(0);
	}
	
	public void draw() {
		
		final float t = (float)frameCount / 100.f;
		
		for(BasicImageShape b : images) {
			b.rotate(b.scale() * 3.f);
			b.setLoc(new PVector(width / 2 + b.scale() * 10, height / 2 + b.scale() * 20));
		}
		
		noiseLayer.renderToLayer(images, t);
		//noiseLayer.process(growth);
		if(imprinting) noiseLayer.imprint(shapeLayer);
		
		noiseLayer.render(0);
		shapeLayer.render(0);
		
		surface.setTitle((int)frameRate + "");
		
	}
	
	public void keyPressed() {
		if(keyCode == PConstants.CONTROL) imprinting = true;
		
		int n = Character.getNumericValue(key);
		if(n >= 0 && n < 10) growth.setPreset((int)(Math.random() * 3));
	}
	
	public void keyReleased() {
		if(keyCode == PConstants.CONTROL) imprinting = false;
	}
	
}
