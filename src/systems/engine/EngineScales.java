package systems.engine;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import chaoscore.OctaveTable;
import control.KeysVisualMap;
import processing.core.*;
import vlib.*;
import vcreations.layers.Layer;
import vcreations.pixel.*;
import vcreations.shapes.*;

// needs updating to the new PixelProcess operators & Layer class

// truth be told, moody duke, formulate, dont u know, yankee station, fortunate, (funeral blues)

// AsciiCA (tilde=off, something larger=on), OrbitingRects, sporadic sparse flickers, 

public class EngineScales extends PApplet {
	//controllers
	KeysVisualMap map;
	//layers
	Layer shapeLayer, noiseLayer;
	//shapes
	ImageShape[] images;
	PImage[] srcs;	// ?
	OrbitingRects formulate;
	//'shaders'
	PixelGain gain;
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
		map = new KeysVisualMap();
		
		shapeLayer = new Layer(this);
		noiseLayer = new Layer(this);
		
		gain = new PixelGain(.85f);
		growth = new PixelDirectionalGrowth();
		
		images = new ImageShape[3];
		for(int i = 0; i < images.length; i++) {
			images[i] = new ImageShape(this, "DSC_0090.JPG");
			images[i].setLoc(new PVector((width / 2) + i * 20, (height / 2) + 10));
			images[i].scale(0.4f * (images.length - i));
			images[i].alpha(0.7f);
			images[i].process(new PixelTransparency(0.8f));
			images[i].process(new PixelProcess[]{new PixelColor(-10 + i * 10)});
		}
		formulate = new OrbitingRects(this, 3, 3);
		
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
		
//		noiseLayer.get().loadPixels();
//		java.util.List<Integer> list = IntStream.of(noiseLayer.get().pixels).boxed().collect(Collectors.toList());
//		System.out.println(list.parallelStream().filter(i -> i != 0).count() + " / " + noiseLayer.get().pixels.length);
		
		route();
		
		float t = (float)frameCount / 100.f;
		if(imprinting) t *= -1;
		
		for(ImageShape b : images) {
			b.rotate(b.scale() * 3.f);
			b.alpha(0.6f);
			b.setLoc(new PVector(width / 2 + b.scale() * 10, height / 2 + b.scale() * 20));
		}
		VShape[] s = {formulate};
		
		shapeLayer.renderToLayer(s, t);
		
		PixelProcess[] p = {gain};
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
		imprinting = map.getButton('z').state;
		gain.gain += (map.getSlider('o').val / 20.f);
		//have animation speed as another global rather than inside formulate eg?
		
		//shapes
		formulate.changeLength(map.getSlider('q').val * 2);
		formulate.changeRects(map.getSlider('w').val);
		formulate.changeSpeed(map.getSlider('e').val * .02f);
		
		//single push controls
		map.getSlider('o').release();
		map.getSlider('w').release();
	}
	
	public void keyPressed() {
		map.pressed(key);
		System.out.println(key + " " + (int)key);
	}
	
	public void keyReleased() {
		map.released(key);
	}
	
}
