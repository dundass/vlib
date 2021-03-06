package systems.engine;

import java.io.File;

import chaoscore.OctaveTable;
import control.KeysVisualMap;
import processing.core.*;
import vlib.*;
import vcreations.layers.Layer;
import vcreations.pixel.*;
import vcreations.shapes.*;

// for now, track by track, but later, move to multiscale shapelayer/noiselayer improviseable machine

// truth be told, moody duke, formulate, dont u know, yankee station, fortunate, (funeral blues)

// AsciiCA (tilde=off, something larger=on), OrbitingRects, sporadic sparse flickers, 

public class EngineGreatestHits extends PApplet {
	//controllers
	KeysVisualMap map;
	//layers
	Layer shapeLayer, noiseLayer;
	//shapes
	BlankShape blank;
	ImageShape[] images;
	OrbitingRects formulate;
	ColouredCA2D yankee;
	RotarySinCells dontuno;
	StormyOrbitCA2D moodyduke;
	BreathingEye closerto;
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
		PApplet.main("systems.engine.EngineGreatestHits");
	}
	
	public void settings() {
		//size(1280, 800, P2D);
		fullScreen(P2D);
	}
	
	public void setup() {
		map = new KeysVisualMap();
		
		shapeLayer = new Layer(this);
		noiseLayer = new Layer(this);
		
		gain = new PixelGain(.9f);
		growth = new PixelDirectionalGrowth();

//		images = new ImageShape[3];
//		for(int i = 0; i < images.length; i++) {
//			images[i] = new ImageShape(this, "DSC_0090.JPG");
//			images[i].setLoc(new PVector((width / 2) + i * 20, (height / 2) + 10));
//			images[i].scale(0.4f * (images.length - i));
//			images[i].alpha(0.7f);
//			images[i].process(new PixelTransparency(0.8f));
//			images[i].process(new PixelProcess[]{new PixelColor(-10 + i * 10)});
//		}
		
		blank = new BlankShape(this);
		formulate = new OrbitingRects(this, 3, 3);
		yankee = new ColouredCA2D(this, 8, 80);
		dontuno = new RotarySinCells(this, 1000);
		moodyduke = new StormyOrbitCA2D(this);
		closerto = new BreathingEye(this);
		
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
		
		VShape[] s = {blank};
		
		switch(currentShape) {
		case 1:
			s[0] = formulate;
			break;
		case 2:
			s[0] = yankee;
			break;
		case 3:
			s[0] = dontuno;
			break;
		case 4:
			s[0] = moodyduke;
			break;
		case 5:
			s[0] = closerto;
			break;
		}
		
		noiseLayer.renderToLayer(s, t);
		
		PixelProcess[] p = {gain};
		if(currentShape == 9) p[0] = growth;
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
				formulate.changeLength(map.getSlider('q').val * 2);
				formulate.changeRects(map.getSlider('w').val);
				map.getSlider('w').release();
				break;
			case 2:
				if(map.getButton('z').state) yankee.setCentreState();
				if(map.getButton('x').state) yankee.setRandomStates(0.05f);
				if(map.getButton('c').state) yankee.setRandomStates(0.3f);
				if(map.getButton('v').state) yankee.setLambdaRuleset(0.4f);
				break;
			case 3:
				
				break;
			case 4:
				if(map.getButton('z').state) moodyduke.killStates();
				if(map.getButton('x').state) moodyduke.setCentreState();
				if(map.getButton('c').state) moodyduke.setRandomStates(.05f);
				if(map.getButton('v').state) moodyduke.setRandomStates(.1f);
				if(map.getButton('b').state) moodyduke.setLambdaRuleset(.28f);
				if(map.getButton('n').state) moodyduke.setLambdaRuleset(.38f);
				if(map.getButton('m').state) moodyduke.setLambdaRuleset(.48f);
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
	}
	
	public void keyPressed() {
		map.pressed(key);
		System.out.println(key + " " + (int)key);
		int keyNum = Character.getNumericValue(key);
		if(!map.ctrl() && keyNum < 10 && keyNum > -1) currentShape = keyNum;
		if(key == 'p') yankee.printRuleset();
	}
	
	public void keyReleased() {
		map.released(key);
	}
	
}
