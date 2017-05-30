package processing_eclipse;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import reflection.ReflectConsole;
import time.Timer;
import vcreations.*;
import vcreations.layers.Layer;
import vcreations.pixel.PixelColor;
import vcreations.pixel.PixelGain;
import vcreations.shapes.EllipseShape;
import vcreations.utils.IterativeColour;
import vlib.*;
import cellularcore.CA1D_;
import chaoscore.*;
import control.KeysVisualMap;

public class TestProcessing extends PApplet {
	CA1D_ ca;
	final EllipseShape[] ell = new EllipseShape[2];
	KeysVisualMap map = new KeysVisualMap();
	boolean imprinting = false;
	float sca = 1.4f;
	//PImage img;
	Layer layer;
	PixelGain pgain;
	IterativeColour col;
	
	public static void main(String[] args) {
		PApplet.main("processing_eclipse.TestProcessing");
	}
	
	public void settings() {
		size(1280, 800, P2D);
		//fullScreen(P2D);
	}
	
	public void setup() {
		ca = new CA1D_(4, 32);
		final int[] r = {0, 2, 0, 0, 3, 0, 2, 0, 3, 0, 0, 0, 3, 0, 0, 2, 1, 3, 2, 0, 1, 2, 0, 0, 0, 1, 0, 0, 0, 2, 2, 0, 2, 0, 1, 0, 3, 3, 1, 2, 0, 0, 1, 1, 2, 2, 0, 3, 0, 0, 0, 3, 0, 0, 1, 0, 3, 0, 1, 2, 0, 0, 3, 3};
		//int[] r = {0, 1, 1, 1, 0, 1, 1, 0};
		ca.setRuleset(r);
		ca.setRandomStates(0.1f);
		//ca.setState(31, 1);
		ell[0] = new EllipseShape(this, new PVector(-50, -50));
		ell[1] = new EllipseShape(this, new PVector(50, 50));
		ell[1].scale(sca);
		
		layer = new Layer(this);
		pgain = new PixelGain(.5f);
		layer.beginDraw();
		layer.background(125);
		layer.endDraw();
		
		col = new IterativeColour(0, 255);
		
		background(0);
	}
	
	public void draw() {
		route();
		//System.out.println(map.getButton('z').state);
		
		final float t = (float)frameCount / 100.f;
		
		background(0);
		
//		PixelProcess[] pix = {new PixelColor(-100, 500, -100)};
//		layer.beginDraw();
//		layer.colorMode(HSB, 255);
//		layer.noStroke();
//		col.setBase((frameCount / 20) % 20);
//		for(int i = 0; i < 100; i++) {
//			if(frameCount % 1 == 0) {
//				layer.fill(col.get(i), 255, 255);
//				layer.rect(0, (i * height / 100), width, height / 100);
//			}
//		}
//		layer.endDraw();
//		layer.colorMode(RGB, 255);
//		layer.process(pix);
//		layer.render();
		
		surface.setTitle((int)frameRate + "");
		
		/*final int s = ca.getSize();
		int c;
		for(int i = 0; i < s; i++) {
			c = ca.getState(i);
			fill(255 - (((float)c / ca.getNumStates()) * 255));
			noStroke();
			rect(i * width / s, ca.getGenCount() * height / s, width / s, width / s);
		}
		ca.update();*/
	}
	
	public void keyPressed() {
		map.pressed(key);
		System.out.println(key + " " + (int)key);
	}
	
	public void keyReleased() {
		map.released(key);
	}
	
	public void route() {
		imprinting = map.getButton('z').state;
		ell[0].scale(sca += (float)map.getSlider('q').val * 0.05f);
	}
}
