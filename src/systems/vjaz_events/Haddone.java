package systems.vjaz_events;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import vcreations.layers.Layer;
import vcreations.pixel.PixelGain;
import vcreations.shapes.*;
import vlib.PixelProcess;
import vlib.VShape;

public class Haddone extends PApplet {
	ASCIICastles ascii;
	HaddonCourt dance;
	HaddonTiles tiles;
	Layer layer;
	PImage welcome;
	int current = 0;

	public static void main(String[] args) {
		PApplet.main("systems.vjaz_events.Haddone");
	}
	
	public void settings() {
		size(1280, 800, P2D);
	}
	
	public void setup() {
		background(0);
		layer = new Layer(this);
		ascii = new ASCIICastles(this);
		dance = new HaddonCourt(this);
		tiles = new HaddonTiles(this);
		welcome = loadImage("welcome.JPG");
	}
	
	public void draw() {
		
		if(frameCount % 2000 == 0) current = (current+1) % 2;
		
		if(frameCount % 1000 == 0) 	welcome = invertImage(welcome);
		
		VShape[] s = {new BlankShape(this)};
		switch(current) {
		case 0:
			s[0] = ascii;
			break;
		case 1:
			s[0] = dance;
			break;
		}

		layer.beginDraw();
		layer.imageMode(PConstants.CENTER);
		layer.image(welcome, -width / 2 + ((frameCount * 2) % width * 2), height / 10, width / 2, height / 6);
		layer.endDraw();
		layer.renderToLayer(s, System.currentTimeMillis());
		layer.process(new PixelProcess[]{new PixelGain(0.85f)});
		layer.render();

		
		surface.setTitle((int)frameRate + "");
	}
	
	public void keyPressed() {
		ascii.setLambdaRuleset(0.45f);
		ascii.printRuleset();
	}
	
	public void mousePressed() {
		ascii.setRandomStates(0);
		ascii.seed();
		for(int i = 0; i < 17; i++) ascii.update();
	}
	
	 public PImage invertImage(PImage img){
		img.loadPixels();
		for(int i = 0;i < img.pixels.length;i++){
			int a = (img.pixels[i] >> 24) & 0xFF;
			int r = (img.pixels[i] >> 16) & 0xFF;
			int g = (img.pixels[i] >> 8) & 0xFF;
			int b = img.pixels[i] & 0xFF;
			img.pixels[i] = color(255 - r, 255 - g, 255 - b, a);
		}
		img.updatePixels();
		return img;
	}
	
}
