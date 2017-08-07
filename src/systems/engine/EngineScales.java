package systems.engine;

import chaoscore.OctaveTable;
import control.KeysVisualMap;
import processing.core.*;
import vlib.*;
import vcreations.layers.Layer;
import vcreations.pixel.*;
import vcreations.shapes.*;

// for now, EngineGreatestHits, but later, move to multiscale shapelayer/noiselayer improviseable machine -> OctTable as multiscale time

// maybe also port some of the other shapes to multiscale - Formulate is already so (3 rings), ColouredCA2D could have 3 layers w 4 & 16 on the other 2 layers, etc

public class EngineScales extends PApplet {
	//controllers
	KeysVisualMap map;
	//layers
	Layer shapeLayer, noiseLayer;
	//shapes
	BlankShape blank;
	ImageShape[] images;
	PImage[] srcs;	// ?
	//'shaders'
	PixelGain gain;
	PixelDirectionalGrowth growth;
	//modulators
	OctaveTable oct;
	//primitives
	float t = 0, spd = .01f;
	int currentShape = 0, currentNoise = 0;
	boolean imprinting = false;
	
	public static void main(String[] args) {
		PApplet.main("systems.engine.EngineScales");
	}
	
	public void settings() {
		size(1280, 800, P2D);
		//fullScreen(P2D);
	}
	
	public void setup() {
		map = new KeysVisualMap();
		
		shapeLayer = new Layer(this);
		noiseLayer = new Layer(this);
		
		gain = new PixelGain(.9f);
		growth = new PixelDirectionalGrowth();
		
		images = new ImageShape[3];
		for(int i = 0; i < images.length; i++) {
			images[i] = new ImageShape(this, "DSC_0090.JPG");
			images[i].setLoc(new PVector((width / 2) + i * 20, (height / 2) + 10));
			images[i].scale(0.4f * (images.length - i));
			images[i].alpha(0.7f);
			images[i].process(new PixelProcess[]{new PixelTransparency(0.9999f), new PixelColor(-10 + i * 10)});
		}
		
		blank = new BlankShape(this);
		
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
		
		route();
		
		//float t = (float)frameCount / 100.f;
		t += spd;
		if(imprinting) t *= -1;
		
		for(ImageShape b : images) {
			b.rotate(b.scale() * 3.f);
			b.alpha(0.6f);
			b.setLoc(new PVector(width / 2 + b.scale() * 10, height / 2 + b.scale() * 20));
		}
		
		VShape[] s = {images[0], images[1], images[2]};
		
		switch(currentShape) {
		case 1:
			
			break;
		case 2:
			
			break;
		}
		
		noiseLayer.renderToLayer(s, t);
		
		PixelProcess[] p = {gain};
		if(currentShape == 9) p[0] = growth;	// find a better way to choose which PixelProcesses to use
		noiseLayer.process(p);
		//if(imprinting) noiseLayer.imprint(shapeLayer);
		
		noiseLayer.render();
		shapeLayer.render();
		
		surface.setTitle((int)frameRate + "");
		
	}
	
	public void route() {
//		
//		int n = Character.getNumericValue(key);
//		if(n >= 0 && n < 10) growth.setPreset((int)(Math.random() * 3));

		//globals
		imprinting = map.getButton('m').state;
		gain.gain += (map.getSlider('o').val / 20.f);
		spd += (map.getSlider('i').val * .001f);
		
		//shapes
		switch(currentShape) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 9:
				if(map.getButton('1').state) growth.setPreset(0);
				if(map.getButton('2').state) growth.setPreset(1);
				if(map.getButton('3').state) growth.setPreset(2);
				growth.incDirection(map.getControlIndex('q'), map.getSlider('q').val * 0.01f);
				growth.incDirection(map.getControlIndex('w'), map.getSlider('w').val * 0.01f);
				growth.incDirection(map.getControlIndex('e'), map.getSlider('e').val * 0.01f);
				growth.incDirection(map.getControlIndex('r'), map.getSlider('r').val * 0.01f);
				growth.incDirection(map.getControlIndex('t'), map.getSlider('t').val * 0.01f);
				growth.incDirection(map.getControlIndex('y'), map.getSlider('y').val * 0.01f);
				break;
		}
		
		//single push controls
		map.getSlider('o').release();
		map.getSlider('w').release();
	}
	
	public void keyPressed() {
		map.pressed(key);
		System.out.println(key + " " + (int)key);
		int keyNum = Character.getNumericValue(key);
		if(!map.ctrl() && keyNum < 10 && keyNum > 0) currentShape = keyNum;
	}
	
	public void keyReleased() {
		map.released(key);
	}
	
}
